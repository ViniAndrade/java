package br.com.cin.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.cin.entity.LoginEntity;

public class LoginRepository {

	private final EntityManager entityManager;
	private static LoginRepository instance;

	private LoginRepository(){
    	this.entityManager = Persistence
    			.createEntityManagerFactory("persistence_unit_unipe")
    			.createEntityManager();
    }

    public static LoginRepository getInstance(){
        if(instance == null){
        	instance = new LoginRepository();
        }
        return instance;
    }

    public LoginEntity verificaUsuario(String login, String senha){
    	LoginEntity loginEntity = null;
    	try{
    		TypedQuery<LoginEntity> createQuery = this.entityManager.createQuery("SELECT l FROM LoginEntity l "
        			+ "where l.login = :login and l.senha = :senha" , LoginEntity.class);
        	createQuery.setParameter("login", login);
        	createQuery.setParameter("senha", senha);
        	
        	loginEntity = createQuery.getSingleResult();
    	}catch(Exception e){
    		System.out.println("Não localizou nenhum usuario.");
    		System.out.println("Erro:" + e.getMessage());
    	}
    	return loginEntity ;
    }

}
