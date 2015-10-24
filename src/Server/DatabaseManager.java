package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

	DatabaseManager(Server server, String url, String username, String password){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			server.message(e.getMessage());
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(url,username,password);
			server.message("Established connection to database!");

		} catch (SQLException e) {
			server.message(e.getMessage());
			e.printStackTrace();
		}
	}
}
