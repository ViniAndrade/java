package br.com.cin.client;

import java.util.ArrayList;
import java.util.List;

import br.com.cin.entity.PessoaEntity;
import br.com.cin.http.Pessoa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


public class ClientRest {
	
	static Client c  = Client.create();
	
	public static void main(String[] args) {
		
		WebResource wr = c.resource("http://localhost:8080/RESTFUL/rest/service/todasPessoas");
		
		String json = wr.get(String.class);
		
		Gson gson = new Gson();
		
		List<PessoaEntity> lista = gson.fromJson(json, 
				new TypeToken<List<PessoaEntity>>(){}.getType());
		
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		
		for(PessoaEntity p : lista){
			
			listaPessoa.add(new Pessoa(p.getCodigo(), p.getNome(), p.getIdade()));
			
			System.out.println("codigo: " + p.getCodigo() + 
					"nome: " + p.getNome() + 
					"idade: " + p.getIdade());
		}
	}
	
	

}