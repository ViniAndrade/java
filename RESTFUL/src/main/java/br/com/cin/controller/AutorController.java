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

import br.com.cin.entity.AutorEntity;
import br.com.cin.http.Autor;
import br.com.cin.repository.AutorRepository;

@Path("autor")
public class AutorController {
	
private AutorRepository repository = AutorRepository.getInstance();
	

	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String cadastrar(Autor autor){
		try {
			AutorEntity entity = convert(autor);
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
	public String alterar(Autor autor){
		try {
			AutorEntity entity = convert(autor);
			repository.alterar(entity);
			return "Registro alterado com sucesso!";
		} catch (Exception e) {
			return "Erro ao alterar o registro " + e.getMessage();
		}
 
	}

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/listar")
	public List<Autor> listar(){
		List<Autor> estabelecimentos =  new ArrayList<Autor>();
		List<AutorEntity> listaEstabelecimentos = repository.listar();
		for (AutorEntity entity : listaEstabelecimentos) {
			estabelecimentos.add( convert(entity) );
		}
		return estabelecimentos;
	}
 
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/autor/{codigo}")
	public Autor getPessoa(@PathParam("codigo") Integer codigo){
		Autor entity = convert( repository.getAutor(codigo) );
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

	private AutorEntity convert(Autor autor) {
		AutorEntity entity = new AutorEntity();
		entity.setId(autor.getId());
		entity.setNome(autor.getNome());
		entity.setLivros(null);
		return entity;
	}

	private Autor convert(AutorEntity entity) {
		Autor autor = new Autor();
		autor.setId(entity.getId());
		autor.setNome(entity.getNome());
		autor.setLivros(null);
		return autor;
	}

}
