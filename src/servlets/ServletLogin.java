package servlets;

import java.io.IOException;

import dao.LoginDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoginModel;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(urlPatterns = {"/principal/ServletLogin","/ServletLogin"}) /*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private LoginDao loginDao = new LoginDao();

	public ServletLogin() {
        	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String acao = request.getParameter("acao");
		
		if(acao !=null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			
			request.getSession().invalidate(); //invalida a sess?o 
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
			
		}else {
			
			doPost(request, response);
			
		}
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		try {
			// Verificar se a senha e login ? nulo
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {

				LoginModel loginModel = new LoginModel();
				loginModel.setLogin(login);
				loginModel.setSenha(senha);

				// Simula o login
				/*
				 * if(loginModel.getLogin().equalsIgnoreCase("admin") &&
				 * loginModel.getSenha().equalsIgnoreCase("admin"))
				 */

				if (loginDao.validarAutenticacao(loginModel)) {

					request.getSession().setAttribute("usuario", loginModel.getLogin());

					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}

					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					// Aqui ele vai redirecionar para o index
					redirecionar.forward(request, response);

				} else {
					// Aqui ele vai redirecionar para a pagina desejada
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					// Vai apresentar a MSG
					request.setAttribute("msg", "Favor informar o login e senha do admin corretamente!");
					// Aqui ele vai redirecionar para o index
					redirecionar.forward(request, response);
				}

			} else {
				// Aqui ele vai redirecionar para a pagina desejada
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				// Vai apresentar a MSG
				request.setAttribute("msg", "Senha e login n?o podem est? em branco");
				// Aqui ele vai redirecionar para o index
				redirecionar.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		} 
	}
}
