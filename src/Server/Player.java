package Server;

import java.io.Serializable;

public class Player implements Serializable{
	private static final long serialVersionUID = 7695261978618738318L;
	
	//// PLAYER PROPERTIES
	private String username;
	private String password;
	private String fname, lname;
	
	public Player(String username, String password, String firstName, String lastName){
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	}

	//// SETTERS AND GETTERS ////////
	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	protected String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lname;
	}

	private void setLastName(String lname) {
		this.lname = lname;
	}

	public String getFirstName() {
		return fname;
	}

	private void setFirstName(String fname) {
		this.fname = fname;
	}
}
