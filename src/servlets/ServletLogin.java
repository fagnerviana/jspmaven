package servlets;

import java.io.IOException;

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
@WebServlet("/ServletLogin") /*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletLogin() {
    
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String login = request.getParameter("login");
		 String senha = request.getParameter("senha");
		 
		 //Verificar se a senha e login é nulo 
		 if(login!=null && !login.isEmpty() && senha!= null && !senha.isEmpty()) {
			 

			 LoginModel loginModel = new LoginModel();
			 loginModel.setLogin(login);
			 loginModel.setSenha(senha);
			 
			 //Simula o login
			 if(loginModel.getLogin().equalsIgnoreCase("admin") 
					 && loginModel.getSenha().equalsIgnoreCase("admin")) {
				 
				 request.getSession().setAttribute("usuario", loginModel.getLogin());
				 RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");
				 //Aqui ele vai redirecionar para o index
				 redirecionar.forward(request, response);
				 
			 }else {
				//Aqui ele vai redirecionar para a pagina desejada 
				 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				 //Vai apresentar a MSG 
				 request.setAttribute("msg", "Favor informar o login e senha do admin corretamente!");
				 //Aqui ele vai redirecionar para o index
				 redirecionar.forward(request, response);
			 }
			 
			 
		 }else {
			 //Aqui ele vai redirecionar para a pagina desejada 
			 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			 //Vai apresentar a MSG 
			 request.setAttribute("msg", "Senha e login não podem está em branco");
			 //Aqui ele vai redirecionar para o index
			 redirecionar.forward(request, response);
		 }
		 
	 }

}
