package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnection;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class FilterAutenticacao
 */
@WebFilter(urlPatterns= {"/principal/*"})  /*Intercepta todas as requisi��es que vierem do projeto ou mapeamento*/
public class FilterAutenticacao implements Filter {
	
	private static Connection connection;

    public FilterAutenticacao() {
        
    }

    /*Encerra os processos quando o servidor � parado */
    /*Mataria os processos de conex�o com banco*/
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	/*Intercepta as requisicoes e as respostas no sistemna */
	/*Tudo que fizer no sistema vai fazer aqui*/
	/*Valida��o de autentica��o*/
	/*Dar commit e rolback de transa��es do banco*/
	/*Validar e fazer redirecionamento de paginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		try {
				
		//Sess�o
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//Verifica se esta logado
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		//Verifica a URL que esta tentando ser acessada
		String urlParaAutenticar = req.getServletPath();/*Url que esta sendo acessada*/
		
		/*Validar se esta logado sen�o redirecionar para a tela de login*/
		if(usuarioLogado==null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url= " + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login!");
			redireciona.forward(request, response);
			return;/*Para execu��o e redireciona para o login */
		}else {
			chain.doFilter(request, response);
		}
		connection.commit(); //dando tudo certo comita as altera��es no banco de dados
		
	
	} catch (Exception e) {
		e.printStackTrace();
		
		// Aqui ele vai redirecionar para a pagina desejada
		RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
		// Vai apresentar a MSG
		request.setAttribute("msg", e.getStackTrace());
		// Aqui ele vai redirecionar para o index
		redirecionar.forward(request, response);
		
		try {
			connection.rollback();
					
		} catch (Exception e2) {
			e.printStackTrace();
		}
		
	   }
		
	}
	
	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	/*iniciar a conex�o com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConnection();
	}
	
	

}
