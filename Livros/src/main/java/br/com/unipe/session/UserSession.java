package br.com.unipe.session;

import br.com.unipe.http.Login;

public class UserSession {

	private String token;
	private Login login;

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	
}
