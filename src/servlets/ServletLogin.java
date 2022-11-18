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
		 
		 if(login!=null && !login.isEmpty() && senha!= null && !senha.isEmpty()) {
			 

			 LoginModel loginModel = new LoginModel();
			 loginModel.setLogin(login);
			 loginModel.setSenha(senha);
			 
			 
		 }else {
			 //Aqui ele vai redirecionar para a pagina desejada 
			 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			 //Vai apresentar a MSG 
			 request.setAttribute("msg", "Informe o login e senha corretamente!");
			 //Aqui ele vai redirecionar para o index
			 redirecionar.forward(request, response);
		 }
		 
	 }

}
