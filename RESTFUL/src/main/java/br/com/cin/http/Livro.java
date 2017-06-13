package br.com.cin.http;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Livro {

	private int id;
	
	private String nome;
	
	private String autor;
	
	private int ano;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
}
