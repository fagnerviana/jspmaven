package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;
import model.LoginModel;

public class LoginDao {
	
	private Connection connection;
	
	public LoginDao() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(LoginModel loginModel) throws SQLException {
		try {
			
			String sql = "insert into login(id,nome,senha) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, 01);
			insert.setString(2, "Fagner Viana");
			insert.setString(3, "admin");
			insert.execute();
			connection.commit();	
			
			System.out.println("Login salvo com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao salvar Login ");
		}
		
	}

}
