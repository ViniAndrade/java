package br.com.cin.client;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.cin.http.Login;

public class LoginRest {
	static Client c = Client.create();

	private static Gson gson = new Gson();

	public static void main(String[] args) {

			Login l = new Login();
			l.setId(1);
			l.setLogin("emille");
			l.setSenha("emille");
			
			WebResource webResource = c.resource("http://localhost:8080/RESTFUL/rest/autenticacao/login");
			ClientResponse response = webResource.type("application/json")
					   .post(ClientResponse.class, gson.toJson(l));
			
			String json = response.getEntity(String.class);
		
//			Login lista = gson.fromJson(json, Login.class);
		
			System.out.println(json);
		}
}
