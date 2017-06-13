package br.com.cin.http;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LivroAutor {
	
	private Livro livro;
	
	private List<Livro> livros;
	
	private Autor autor;

	public LivroAutor(){}

	public LivroAutor(Livro livro, List<Livro> livros, Autor autor) {
		super();
		this.livro = livro;
		this.livros = livros;
		this.autor = autor;
	}
	public LivroAutor(Livro livro, Autor autor) {
		super();
		this.livro = livro;
		this.livros = null;
		this.autor = autor;
	}
	public LivroAutor(List<Livro> livros, Autor autor) {
		super();
		this.livro = null;
		this.livros = livros;
		this.autor = autor;
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

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
