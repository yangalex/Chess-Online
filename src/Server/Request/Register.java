package Server.Request;

import java.io.Serializable;

//This class will be used to send a request to register a new user.
public class Register implements Serializable{
	private static final long serialVersionUID = -2897494051570722818L;
	private String username, password, firstName, lastName;
	public String message;
	public Register(String username, String password, String firstName, String lastName){
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		
	}
	
	//////// GETTER ANS SETTERS ////////////
	public String getUsername() {
		return username;
	}
	
	private void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}

	
	public String getFirstName() {
		return firstName;
	}

	
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
