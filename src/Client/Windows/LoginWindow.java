package Client.Windows;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Server.Request.Authenticate;

public class LoginWindow extends JPanel{
	private static final long serialVersionUID = 1L;
	
	/// MEMBER VARIABLES
	private JTextField usernameField,passwordField;
	private JButton loginButton;
	private JLabel errorLabel;
	
	public LoginWindow(ActionListener loginAction){
		initializeVariables();
		createGUI();
		loginButton.addActionListener(loginAction);
	}

	private void createGUI() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setOpaque(false);
		usernamePanel.setLayout(new FlowLayout());
		usernamePanel.add(new JLabel("Username: "));
		usernamePanel.add(usernameField);
		
		gbc.gridy = 1;
		add(usernamePanel, gbc);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout());
		passwordPanel.add(new JLabel("Password: "));
		passwordPanel.add(passwordField);
		
		gbc.gridy = 2;
		add(passwordPanel, gbc);
		
		gbc.gridy = 3;
		add(loginButton, gbc);
	}

	private void initializeVariables() {
		usernameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		errorLabel = new JLabel("");
		loginButton = new JButton("Login");
	}
	
	public void errorMessage(String message){
		errorLabel.setText(message);
	}

	public Authenticate getAuthenticate() {
		return new Authenticate(usernameField.getText(),passwordField.getText());
	}
	
	public String getUsername(){
		return usernameField.getText();
	}
	
	public String getPassword(){
		return passwordField.getText();
	}

}
