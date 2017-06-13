package br.com.unipe.util;

public class Constantes {
//	SESSION
	public static final String USER_SESSION = "usuarioLogado";
//	MENSAGENS DE ERRO
	public static final String ERRO_CONEXAO_BD = " Não conseguiu conectar ao banco de dados ";
	public static final String ERRO_REALIZAR_CONSULTA = " Não foi possível realizar a consulta "; 
	
//	SCRIPT SQL PARA CONSULTAS
	public static final String SQL_VENDAS_DOS_ESTADOS = "select UF_CLIENTE, VL_TOT_NOTA from ( SELECT UF_CLIENTE, sum(VL_TOT_NOTA) as VL_TOT_NOTA FROM tb_dados group by UF_CLIENTE )  as td_dados  order by VL_TOT_NOTA desc limit 6";
	public static final String SQL_VENDAS_DAS_CIDADES_POR_ESTADOS = " select NM_CIDADE, VL_TOT_NOTA from (  SELECT NM_CIDADE, sum(VL_TOT_NOTA) as VL_TOT_NOTA  FROM tb_dados WHERE UF_CLIENTE= ? group by NM_CIDADE )   as td_dados  order by VL_TOT_NOTA desc limit 6";
	public static final String SQL_PRODUTOS_MAIS_VENDIDOS = "select NM_PRODUTO, cd_produto from ( select NM_PRODUTO, count(cd_produto) as cd_produto from tb_dados group by cd_produto ) as td_dados order by td_dados.cd_produto desc limit 6";
	public static final String SQL_DIAS_COM_MAIORES_VENDAS = "select dt_emissao, sum(VL_TOT_NOTA) as 'total' from tb_dados group by dt_emissao order by sum(VL_TOT_NOTA) desc limit 6";
	public static final String SQL_CLIENTES_MAIS_COMPRARAM_QTDE = "select nm_cliente, count(nm_cliente) as 'quantidade' from tb_dados group by nm_cliente order by count(nm_cliente) desc limit 6";
	public static final String SQL_CLIENTES_MAIS_COMPRARAM_VALOR = "select nm_cliente, sum(VL_TOT_NOTA) as 'total' from tb_dados group by nm_cliente order by sum(VL_TOT_NOTA) desc limit 6";
	public static final String SQL_SELECT_TODAS_UFS = "select UF_CLIENTE from tb_dados group by UF_CLIENTE";

//	REST
	public static final String URL_BASE = "http://localhost:8080/RESTFUL/rest/";
//	LOGIN
	public static final String REST_LOGIN = URL_BASE + "autenticacao/login";
	public static final String REST_LOGOUT = URL_BASE + "autenticacao/logout";
//	ERRO LOGIN
	public static final String NO_USER = "Nenhum usuario encontrado";
	public static final String ERROR_LOGIN = "Erro ao realizar login";

//	LIVRO
	public static final String REST_GET_LIVRO = URL_BASE + "livro/listar";
	public static final String REST_GET_LIVRO_ID = URL_BASE + "livro/livro/%s";
	public static final String REST_POST_CADASTRAR = URL_BASE + "livro/cadastrar";
	public static final String REST_PUT_ATUALIZAR = URL_BASE + "livro/alterar";
	public static final String REST_DELTE_EXCLUIR = URL_BASE + "livro/excluir/%s";
	

//	LIVRO
	public static final String REST_GET_LIVROS = URL_BASE + "livro/listar";
}
