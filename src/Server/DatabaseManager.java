package Server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseManager {
		private Connection connect;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		
		// SERVER
		private ChessServer cs;

		DatabaseManager(ChessServer chessServer) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
			cs = chessServer;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Scanner in = new Scanner(System.in);
			message("Enter database URL: ");
			String url1 = in.nextLine();
			String url = "jdbc:mysql://"+ url1 +":3306";
			message("Enter username: ");
			String username = in.nextLine();
			message("Enter password: ");
			String password = in.nextLine();
			try { 
				connect = DriverManager.getConnection(url, username, password); 
				message("Connected to Database!");
			}
			catch (SQLException e) {
				throw e;
			}	
		}
		
		
		
		private void message(String message){
			cs.message(message);
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
				if(Settings.Debug) e.printStackTrace();
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
