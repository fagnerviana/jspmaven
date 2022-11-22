package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.LoginModel;

public class LoginDao {
	
	private Connection connection;
	
	public LoginDao() {
		connection = SingleConnection.getConnection();
	}
	
    
    public boolean validarAutenticacao(LoginModel loginModel) throws SQLException {
    	
    	String sql ="select * from usuario where login=? and senha=?";
    	
    	PreparedStatement statement = connection.prepareStatement(sql);
    	
    	statement.setString(1, loginModel.getLogin());
    	statement.setString(2, loginModel.getSenha());
    	
    	ResultSet resultSet = statement.executeQuery();
    	
    	if(resultSet.next()) {
    		return true;  //autenticado
    	}
    	
    	return false;//não autenticado
    	
    	
    } 

}
