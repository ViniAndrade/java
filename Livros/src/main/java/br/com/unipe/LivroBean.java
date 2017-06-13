package br.com.unipe;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import br.com.unipe.http.Livro;
import br.com.unipe.util.Constantes;

@ManagedBean(name = "livroBeans")
@ViewScoped
public class LivroBean {

	public LivroBean(){}
	
	static Client c = Client.create();

	private static Gson gson = new Gson();
	
	private static Livro livro;
	
	private static List<Livro> livros;

	@PostConstruct
	public void init() {
		livrosREST();
	}
	
	public void voltarIndex(){
		try {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(getContextPath() + "/restrito/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void preparaCadastrar(){
		try {
			livro = new Livro();
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(getContextPath() + "/restrito/cadastrar.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrar(){
		try {
			WebResource webResource = c.resource(Constantes.REST_POST_CADASTRAR);
			ClientResponse response = webResource.type("application/json")
					   .post(ClientResponse.class, gson.toJson(livro));
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(getContextPath() + "/restrito/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(int id){
		try {
			WebResource webResource = c.resource(String.format(Constantes.REST_DELTE_EXCLUIR, id));
			ClientResponse response = webResource.type("application/json")
					   .delete(ClientResponse.class);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(getContextPath() + "/restrito/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void preparaVisualizacao(int id) {
		try {
			WebResource webResource = c.resource(String.format(Constantes.REST_GET_LIVRO_ID , id));
			String json = webResource.get(String.class);
			livro = gson.fromJson(json, Livro.class);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(getContextPath() + "/restrito/visualizar.xhtml");
		} catch (IOException e) {
			System.out.println("Não foi possível visualizar");
			e.printStackTrace();
		}
		
	}
	
	public void preparaEdicao(int id) {
		try {
			WebResource webResource = c.resource(String.format(Constantes.REST_GET_LIVRO_ID , id));
			String json = webResource.get(String.class);
			livro = gson.fromJson(json,Livro.class);
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(getContextPath() + "/restrito/edicao.xhtml");
		} catch (IOException e) {
			System.out.println("Não foi possível visualizar");
			e.printStackTrace();
		}
		
	}
	
	public void atualizar(){
		try {
			WebResource webResource = c.resource(Constantes.REST_PUT_ATUALIZAR);
			ClientResponse response = webResource.type("application/json")
					   .put(ClientResponse.class, gson.toJson(livro));
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect(getContextPath() + "/restrito/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void livrosREST(){
		WebResource webResource = c.resource(Constantes.REST_GET_LIVROS);
		String json = webResource.get(String.class);
		livros = gson.fromJson(json, 
				new TypeToken<List<Livro>>(){}.getType());
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	protected String getContextPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
}
