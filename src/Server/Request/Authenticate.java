package Server.Request;


import java.io.Serializable;

public class Authenticate implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	public Authenticate(String u, String p){
		username = u;
		password = p;
	}
	
	public String getUsername(){
			return username;
	}
	
	public String getPassword(){
		return password;
	}
}
