package br.com.cin.http;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pessoa {
	
	private int codigo;
	private String nome;
	private int idade;
	
	public Pessoa() {

	}

	public Pessoa(int codigo, String nome, int idade) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.idade = idade;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
}
