package Server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
		private Connection conn;

		DatabaseManager(ChessServer cs) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try { 
				conn = DriverManager.getConnection(Settings.url, Settings.username, Settings.password); 
				message("Connected to Database!");
				cs.start();
			}
			catch (SQLException e) {
				message("Could not connect to Database.");
				throw e;
			}	
		}

		private void message(String message){
			System.out.println(message);
		}
		
		public Boolean authenticate(Authenticate a){
			System.out.println("Got an Authenticate Object");
			try {
				Statement st = conn.createStatement();
				ResultSet users = st.executeQuery("USE Users; SELECT * FROM Users;");
				while (users.next()){
					System.out.println(users.getString("username"));
				}
			} catch (SQLException e) {
				if (Settings.Debug) e.printStackTrace();
				return false;
			}
			
			return false;
		}
}
