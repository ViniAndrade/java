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

import br.com.cin.entity.PessoaEntity;
import br.com.cin.http.Pessoa;
import br.com.cin.repository.PessoaRepository;

@Path("/service")
public class ServiceController {
	
	private final  PessoaRepository repository = new PessoaRepository();
	 
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova pessoa
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String cadastrar(Pessoa pessoa){
 
		PessoaEntity entity = new PessoaEntity();
 
		try {
 
			entity.setNome(pessoa.getNome());
			entity.setIdade(pessoa.getIdade());
 
			repository.salvar(entity);
 
			return "Registro cadastrado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
 
	}
 
	/**
	 * Essse método altera uma pessoa já cadastrada
	 * **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String alterar(Pessoa pessoa){
 
		PessoaEntity entity = new PessoaEntity();
 
		try {
 
			entity.setCodigo(pessoa.getCodigo());
			entity.setNome(pessoa.getNome());
			entity.setIdade(pessoa.getIdade());
 
			repository.alterar(entity);
 
			return "Registro alterado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao alterar o registro " + e.getMessage();
 
		}
 
	}
	/**
	 * Esse método lista todas pessoas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todasPessoas")
	public List<Pessoa> todasPessoas(){
 
		List<Pessoa> pessoas =  new ArrayList<Pessoa>();
 
		List<PessoaEntity> listaEntityPessoas = repository.todasPessoas();
 
		for (PessoaEntity entity : listaEntityPessoas) {
 
			pessoas.add(new Pessoa(entity.getCodigo(), entity.getNome(),entity.getIdade()));
		}
 
		return pessoas;
	}
 
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getPessoa/{codigo}")
	public Pessoa getPessoa(@PathParam("codigo") Integer codigo){
 
		PessoaEntity entity = repository.getPessoa(codigo);
 
		if(entity != null)
			return new Pessoa(entity.getCodigo(), entity.getNome(),entity.getIdade());
 
		return null;
	}
 
	/**
	 * Excluindo uma pessoa pelo código
	 * */
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

}
