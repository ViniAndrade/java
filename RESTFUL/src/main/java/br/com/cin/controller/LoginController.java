package br.com.cin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.cin.entity.LoginEntity;
import br.com.cin.http.Login;
import br.com.cin.repository.LoginRepository;
import br.com.cin.util.GerenciadorUsuario;

@Path("autenticacao")
public class LoginController {
	private static LoginRepository repository = LoginRepository.getInstance();
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/login")
	public Object login(Login login){
		String msgRetorno = null;
		try {
			LoginEntity usuario = repository.verificaUsuario(login.getLogin(), login.getSenha());
			
			if(usuario != null){
				msgRetorno = UUID.randomUUID().toString();
				GerenciadorUsuario.adicionarUsuario(msgRetorno, usuario);
			}else{
				msgRetorno = "Nenhum usuario encontrado";
			}
		} catch (Exception e) {
 
			msgRetorno = "Erro ao realizar login";
		}
		return msgRetorno;
	}
	

	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/logout")
	public Object logout(String token){
		GerenciadorUsuario.removerUsuario(token);
		return "Logout realizado com sucesso";
	}
}
