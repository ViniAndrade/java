package br.com.cin.http;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Login {
	
	public Login(){}
	
	private int id;

	private String login;

	private String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
