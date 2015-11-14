package Server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Server.Request.Authenticate;
import Server.Request.Register;

public class DatabaseManager {
		//// DATABASE
		private Connection connect;
		private Statement statement = null;
		private ResultSet rs = null;
		private PreparedStatement PS = null;
		
		//// PREPARE STATEMENTS
		private String createUser = "INSERT INTO Users (username,password,fname,lname) VALUES (?,?,?,?);";
		private String deleteUser = "DELETE FROM Users WHERE username = ?";
		private String userLogin = "SELECT username,password,fname,lname FROM Users WHERE username = ?;";
		private String playerOnline = "UPDATE Users SET `active`= 1 WHERE username=?;";
		private String playerOffline = "UPDATE Users SET `active`= 0 WHERE username=?;";
		
		//// SERVER
		private ChessServer cs;

		
		///////////// METHODS //////////////////
		DatabaseManager(ChessServer chessServer) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
			cs = chessServer;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try { 
				connect = DriverManager.getConnection(Settings.url, Settings.username, Settings.password); 
				message("Connected to Database!");
				statement = connect.createStatement();
				statement.executeQuery("SET SQL_SAFE_UPDATES = 0;");
				statement.executeQuery("USE Chess;");
			}
			catch (SQLException e) {
				throw e;
			}	
		}
		
		private void message(String message){
			cs.message("Database: " + message);
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
		public Player playerLogin(Authenticate a){
			try {
				PS = connect.prepareStatement(userLogin);
				PS.setString(1, a.getUsername());
				rs = PS.executeQuery();
				while(rs.next()){
					if (rs.getString("password").equals(a.getPassword()) || rs.getString("password") == a.getPassword()){
						Player p = new Player(rs.getString("username"),rs.getString("password"), rs.getString("fname"),rs.getString("lname"));
						message("User authenticated: " + rs.getString("fname") + " " + rs.getString("lname"));
						playerOnline(p);
						return p;
					}
				}
			} catch (SQLException e) {
				if (Settings.Debug) e.printStackTrace();
				message("User could not be authenticated: " + a.getUsername());
				return null;
			}
			return null;
		}
		
		public Player createPlayer(Register r) throws SQLException{
			try {
				PS = connect.prepareStatement(createUser);
				PS.setString(1, r.getUsername());
				PS.setString(2, r.getPassword());
				PS.setString(3, r.getFirstName());
				PS.setString(4, r.getLastName());
			    if (PS.executeUpdate() == 1){
			    	Player p = new Player(r.getUsername(),r.getPassword(),r.getFirstName(),r.getLastName());
			    	message("Created new user: " + r.getFirstName() + " " + r.getLastName());
			    	playerOnline(p);
			    	return p;
			    }
		    	message("Could not create user: "  + r.getFirstName() + " " + r.getLastName());
			    return null;

			} catch (SQLException e) {
				if(Settings.Debug) e.printStackTrace();
				throw e; 
			}
		}

		public Boolean deletePlayer(Player p){
			try {
				PS = connect.prepareStatement(deleteUser);
				PS.setString(1, p.getUsername());
				if(PS.executeUpdate() == 1){
					message("Delete user: " + p.getFirstName() + " " + p.getLastName());
					return true;
				}
				message("Could not delete user: " + p.getFirstName() + " " + p.getLastName());
				return false;
				
			} catch (SQLException e) {
				if (Settings.Debug) e.printStackTrace();
				return false;
			}
		}

		public Vector<Player> getPlayers(){
			Vector<Player> players = new Vector<Player>();
			try {
				statement = connect.createStatement();
				rs = statement.executeQuery("SELECT * FROM Chess.Users");
				while(rs.next()){
					players.add(new Player(rs.getString("username"),rs.getString("password"), rs.getString("fname"),rs.getString("lname")));
				}
				return players;
				
			} catch (SQLException e) {
				if (Settings.Debug) e.printStackTrace();
				return null;
			}
		}

		public Vector<Player> getOnlinePlayers(){
			Vector<Player> players = new Vector<Player>();
			try {
				statement = connect.createStatement();
				rs = statement.executeQuery("SELECT * FROM Chess.Users WHERE active = 1;");
				while(rs.next()){
					players.add(new Player(rs.getString("username"),rs.getString("password"), rs.getString("fname"),rs.getString("lname")));
				}
				return players;
				
			} catch (SQLException e) {
				if (Settings.Debug) e.printStackTrace();
				return null;
			}
		}

		public Boolean playerOnline(Player p){
			try {
				PS = connect.prepareStatement(playerOnline);
				PS.setString(1, p.getUsername());
				if(PS.executeUpdate() == 1){
					return true;
				}
			} catch (SQLException e) {
				if (Settings.Debug) e.printStackTrace();
				return false;
			}
			return false;
		}
		
		public Boolean playerOffline(Player p){
			try {
				PS = connect.prepareStatement(playerOffline);
				PS.setString(1, p.getUsername());
				if(PS.executeUpdate() == 1){
					return true;
				}
			} catch (SQLException e) {
				if (Settings.Debug) e.printStackTrace();
				return false;
			}
			return false;
		}
}






