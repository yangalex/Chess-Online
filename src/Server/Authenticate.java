package Server;


import java.io.Serializable;

public class Authenticate implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String publicKey;
	public Authenticate(String u, String p, String pk){
		username = u;
		password = p;
		publicKey = pk;
	}
	
	public String getUsername(String publicKey){
		if (publicKey == this.publicKey){
			return username;
		}
		else return null;
	}
	
	public String getPassword(String publicKey){
		if (publicKey == this.publicKey){
			return password;
		}
		else return null;
	}
}
