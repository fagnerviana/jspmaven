package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url="jdbc:postgresql://localhost:5434/bancojsp";
	private static String password= "admin";
	private static String user="postgres";
	private static Connection connection=null;
	
	//Metodo static sem precisão de criar uma instancia 
		static {
			conectar();
		}
		
		
		public SingleConnection() { //quando estiver uma instancia vai conectar
			conectar();
		}
		
		private static void conectar() {
			try {
				if(connection==null) {
					Class.forName("org.postgresql.Driver"); //carrega o driver de conexão do banco
					connection=DriverManager.getConnection(url, user, password);
					connection.setAutoCommit(false);//false para não efetuar alteração do banco sem o comando
					//System.out.println("Conectado com Sucesso");
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection() {
			return connection;
		}

}
