package br.com.cin.util;

import java.util.HashMap;
import java.util.Map;

import br.com.cin.entity.LoginEntity;

public class GerenciadorUsuario {

	private static Map<String, LoginEntity> usuarioLogados = new HashMap<String , LoginEntity>();
	
	public static void adicionarUsuario(String token, LoginEntity usuario){
		usuarioLogados.put(token, usuario);
	}
	
	public static void removerUsuario(String token){
		usuarioLogados.remove(token);
	}
}
