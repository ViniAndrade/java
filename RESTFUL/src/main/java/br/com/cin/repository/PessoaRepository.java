package br.com.cin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cin.entity.PessoaEntity;

public class PessoaRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	 
	private final EntityManager entityManager;
 
	public PessoaRepository(){
 
		/*CRIANDO O NOSSO EntityManagerFactory COM AS PORPRIEDADOS DO ARQUIVO persistence.xml */
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_unipe");
 
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
 
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void salvar(PessoaEntity pessoaEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
 
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(PessoaEntity pessoaEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
 
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<PessoaEntity> todasPessoas(){
 
		return this.entityManager.createQuery("SELECT p FROM PessoaEntity p ORDER BY p.nome").getResultList();
	}
 
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public PessoaEntity getPessoa(Integer codigo){
 
		return this.entityManager.find(PessoaEntity.class, codigo);
	}
 
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void excluir(Integer codigo){
 
		PessoaEntity pessoa = this.getPessoa(codigo);
 
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(pessoa);
		this.entityManager.getTransaction().commit();
 
	}
}
