package br.com.unipe;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.unipe.http.Login;
import br.com.unipe.session.UserSession;
import br.com.unipe.util.Constantes;
import br.com.unipe.util.TokenUtil;


@ManagedBean(name = "loginBeans")
@ViewScoped
public class LoginBean {

	private String login;
	private String senha;
	static Client c = Client.create();

	private static Gson gson = new Gson();
	
	public String autenticar(){
		String redirectTo = null;
		Login login = new Login();
		login.setId(0);
		login.setLogin( getLogin() );
		login.setSenha( getSenha() );
		
		WebResource webResource = c.resource(Constantes.REST_LOGIN);
		ClientResponse response = webResource.type("application/json")
				   .post(ClientResponse.class, gson.toJson(login));
		
		String json = response.getEntity(String.class);
		
		if(json.equals(Constantes.NO_USER) || json.equals(Constantes.ERROR_LOGIN)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					 json, ""));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					 "Você será redirecionado em alguns instantes", ""));
			redirectTo = "restrito/index.xhtml?faces-redirect=true";
			UserSession session = new UserSession();
			session.setToken(json);
			session.setLogin(login);
			
			TokenUtil.setToken(json);
			
			HttpSession  httpSession = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(true);
			httpSession.setAttribute(Constantes.USER_SESSION, session);
	
		}
		
		System.out.println( String.format(" Login: %s Senha: %s ", login,senha) );
		
		
		return redirectTo;
	}
	
	public void logout(){
		try {
			HttpSession  httpSession = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(true);
			UserSession session = (UserSession) httpSession.getAttribute(Constantes.USER_SESSION);
			
			WebResource webResource = c.resource(Constantes.REST_LOGOUT);
			ClientResponse response = webResource.type("application/json")
					   .post(ClientResponse.class, TokenUtil.getToken());
			
			httpSession.removeAttribute(Constantes.USER_SESSION);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(getContextPath() + "/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return "index.xhtml?faces-redirect=true";
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	protected String getContextPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
	
}
