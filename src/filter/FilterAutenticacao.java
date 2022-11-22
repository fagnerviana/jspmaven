package filter;

import java.io.IOException;
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
@WebFilter(urlPatterns= {"/principal/*"})  /*Intercepta todas as requisições que vierem do projeto ou mapeamento*/
public class FilterAutenticacao implements Filter {

    public FilterAutenticacao() {
        
    }

    /*Encerra os processos quando o servidor é parado */
    /*Mataria os processos de conexão com banco*/
	public void destroy() {
	
	}
	
	/*Intercepta as requisicoes e as respostas no sistemna */
	/*Tudo que fizer no sistema vai fazer aqui*/
	/*Validação de autenticação*/
	/*Dar commit e rolback de transações do banco*/
	/*Validar e fazer redirecionamento de paginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Sessão
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//Verifica se esta logado
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		//Verifica a URL que esta tentando ser acessada
		String urlParaAutenticar = req.getServletPath();/*Url que esta sendo acessada*/
		
		/*Validar se esta logado senão redirecionar para a tela de login*/
		if(usuarioLogado==null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url= " + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login!");
			redireciona.forward(request, response);
			return;/*Para execução e redireciona para o login */
		}else {
			chain.doFilter(request, response);
		}
		
		
	}
	
	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	/*iniciar a conexão com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
