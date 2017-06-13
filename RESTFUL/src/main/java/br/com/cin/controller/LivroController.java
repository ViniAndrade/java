package br.com.cin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import br.com.cin.entity.LivroEntity;

import br.com.cin.http.Livro;
import br.com.cin.repository.LivroRepository;

@Path("livro")
public class LivroController {
private LivroRepository repository = LivroRepository.getInstance();
	

	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String cadastrar(Livro livro){
		try {
			LivroEntity entity = convert(livro);
			repository.salvar(entity);
			return "Registro cadastrado com sucesso!";
		} catch (Exception e) {
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
 
	}

	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String alterar(Livro livro){
		try {
			LivroEntity entity = convert(livro);
			repository.alterar(entity);
			return "Registro alterado com sucesso!";
		} catch (Exception e) {
			return "Erro ao alterar o registro " + e.getMessage();
		}
 
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/listar")
	public List<Livro> listar(){
		List<Livro> livros =  new ArrayList<Livro>();
		List<LivroEntity> listaLivros = repository.listar();
		for (LivroEntity entity : listaLivros) {
			livros.add( convert(entity) );
		}
		return livros;
	}
 
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/livro/{codigo}")
	public Livro getPessoa(@PathParam("codigo") Integer codigo){
		Livro entity = convert( repository.getLivro(codigo) );
		if(entity != null)
			return entity;
		return null;
	}
 
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String excluir(@PathParam("codigo") Integer codigo){
		try {
			repository.excluir(codigo);
			return "Registro excluido com sucesso!";
		} catch (Exception e) {
			return "Erro ao excluir o registro! " + e.getMessage();
		}
	}

	private LivroEntity convert(Livro livro) {
		LivroEntity entity = new LivroEntity();
		entity.setId(livro.getId());
		entity.setNome(livro.getNome());
		entity.setAutor(livro.getAutor());
		entity.setAno(livro.getAno());
		
		return entity;
	}

	private Livro convert(LivroEntity entity) {
		Livro livro = new Livro();
		livro.setId(entity.getId());
		livro.setNome(entity.getNome());
		livro.setAutor(entity.getAutor());
		livro.setAno(entity.getAno());
		
		return livro;
	}

}
