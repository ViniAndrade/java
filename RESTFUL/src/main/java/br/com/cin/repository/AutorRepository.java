package br.com.cin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.cin.entity.AutorEntity;

public class AutorRepository {
	private final EntityManager entityManager;
	private static AutorRepository instance;
	private static boolean isConnected = false;

	private AutorRepository(){
    	this.entityManager = Persistence
    			.createEntityManagerFactory("persistence_unit_unipe")
    			.createEntityManager();
    }
	
	public static boolean isConnected(){
		return isConnected;
	}

    public static AutorRepository getInstance(){
        if(instance == null){
        	instance = new AutorRepository();
        }
        return instance;
    }
    
    public List<AutorEntity> listar(){
    	return this.entityManager
			.createQuery("SELECT e FROM AutorEntity e", AutorEntity.class)
			.getResultList();
    }
    
    public void salvar(AutorEntity autorEntity){
    	 
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(autorEntity);
		this.entityManager.getTransaction().commit();
	}
 
	public void alterar(AutorEntity autorEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(autorEntity);
		this.entityManager.getTransaction().commit();
	}
 
	public AutorEntity getAutor(Integer codigo){
 
		return this.entityManager.find(AutorEntity.class, codigo);
	}

	public void excluir(Integer codigo){
 
		AutorEntity pessoa = this.getAutor(codigo);
 
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(pessoa);
		this.entityManager.getTransaction().commit();
 
	}
}
