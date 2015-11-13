package Server;


//This class will be used to send a request to register a new user.
public class Register {
	private String username, password, firstName, lastName;
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
