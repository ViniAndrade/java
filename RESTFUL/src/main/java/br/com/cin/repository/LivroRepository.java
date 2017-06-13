package br.com.cin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.cin.entity.LivroEntity;

public class LivroRepository {
	
	private final EntityManager entityManager;
	private static LivroRepository instance;

	private LivroRepository(){
    	this.entityManager = Persistence
    			.createEntityManagerFactory("persistence_unit_unipe")
    			.createEntityManager();
    }

    public static LivroRepository getInstance(){
        if(instance == null){
        	instance = new LivroRepository();
        }
        return instance;
    }
    
    public List<LivroEntity> listar(){
    	return this.entityManager
			.createQuery("SELECT e FROM LivroEntity e", LivroEntity.class)
			.getResultList();
    }
    
    public void salvar(LivroEntity livroEntity){

		if(!this.entityManager.getTransaction().isActive())
			this.entityManager.getTransaction().begin();
		this.entityManager.persist(livroEntity);
		this.entityManager.getTransaction().commit();
	}
 
	public void alterar(LivroEntity livroEntity){

		if(!this.entityManager.getTransaction().isActive())
			this.entityManager.getTransaction().begin();
		this.entityManager.merge(livroEntity);
		this.entityManager.getTransaction().commit();
	}
 
	public LivroEntity getLivro(Integer codigo){
 
		return this.entityManager.find(LivroEntity.class, codigo);
	}

	public void excluir(Integer codigo){
 
		LivroEntity pessoa = this.getLivro(codigo);
 
		if(!this.entityManager.getTransaction().isActive())
			this.entityManager.getTransaction().begin();
		this.entityManager.remove(pessoa);
		this.entityManager.getTransaction().commit();
 
	}

}
