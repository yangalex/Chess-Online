package Server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
		private Connection connect;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;

		DatabaseManager(ChessServer cs) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try { 
				connect = DriverManager.getConnection(Settings.url, Settings.username, Settings.password); 
				message("Connected to Database!");
			}
			catch (SQLException e) {
				//message("Could not connect to Database.");
				throw e;
			}	
			finally{
				cs.start();
			}
		}
		
		
		
		private void message(String message){
			System.out.println(message);
		}
		
		public Boolean authenticate(Authenticate a){
			System.out.println("Got an Authenticate Object");
			try {
				Statement st = connect.createStatement();
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

		public void close() {
			try {
				if (connect != null)
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		/////////// COMMANDS /////////////////
		public Boolean createUser(Register r){
			try {
				String command = "INSERT INTO CHESS.Users (username,password,fname,lname) VALUES ("+ 
						r.getUsername() + "," +
						r.getPassword() + "," +
						r.getFirstName()+ "," +
						r.getLastName() + ");";
				statement = connect.createStatement();
			    if (statement.execute(command)){
			    	return true;
			    }
			    return false;
			} catch (SQLException e) {
				if(Settings.Debug) e.printStackTrace();
				return false;
			}
		}
}
