package Server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
		private Connection conn;

		DatabaseManager() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect();
		}
		
		private void connect() throws SQLException {
			try { 
				conn = DriverManager.getConnection(Settings.url, Settings.username, Settings.password); 
				message("Connected to Database!");
			}
			catch (SQLException e) {
				throw e;
			}			
		}

		private void message(String message){
			System.out.println(message);
		}
}
