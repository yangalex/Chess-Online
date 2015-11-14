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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
	
	// Register Window Components
	private JButton registerButton;
	private JTextField usernameField, passwordFieldOne, passwordFieldTwo, firstNameField, lastNameField;
	private JLabel errorLabel;
	
	// Connections
	private ClientPanelWindow cpw;
	
	public RegisterWindow(ActionListener registerAction, ClientPanelWindow cpw) {
		this.cpw = cpw;
		initializeVariables();
		createGUI();
		registerButton.addActionListener(registerAction);
		
		}
	
	private void initializeVariables() {
		registerButton = new JButton("Register");
		firstNameField = new JTextField(20);
		lastNameField = new JTextField(20);
		usernameField = new JTextField(20);
		passwordFieldOne = new JPasswordField(20);
		passwordFieldTwo = new JPasswordField(20);
		errorLabel = new JLabel("");
	}

	private void createGUI() {
		setLayout(new GridBagLayout());
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Last Name: ");
		JLabel usernameLabel = new JLabel("Username: ");
		JLabel passwordLabelOne = new JLabel("Choose Password: ");
		JLabel passwordLabelTwo = new JLabel("Confirm Password: ");
		
		JPanel firstNamePanel = new JPanel();
		firstNamePanel.setOpaque(false);
		firstNamePanel.setLayout(new FlowLayout());
		firstNamePanel.add(firstNameLabel);
		firstNamePanel.add(firstNameField);
		
		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setOpaque(false);
		lastNamePanel.setLayout(new FlowLayout());
		lastNamePanel.add(lastNameLabel);
		lastNamePanel.add(lastNameField);
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setOpaque(false);
		usernamePanel.setLayout(new FlowLayout());
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		
		JPanel passwordPanelOne = new JPanel();
		passwordPanelOne.setOpaque(false);
		passwordPanelOne.setLayout(new FlowLayout());
		passwordPanelOne.add(passwordLabelOne);
		passwordPanelOne.add(passwordFieldOne);
		
		JPanel passwordPanelTwo = new JPanel();
		passwordPanelTwo.setOpaque(false);
		passwordPanelTwo.setLayout(new FlowLayout());
		passwordPanelTwo.add(passwordLabelTwo);
		passwordPanelTwo.add(passwordFieldTwo);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,30,5,30);
		
		gbc.gridy = 1;
		add(firstNamePanel, gbc);
		
		gbc.gridy = 2;
		add(lastNamePanel, gbc);
				
		gbc.gridy = 3;
		add(usernamePanel, gbc);
		
		gbc.gridy = 4;
		add(passwordPanelOne, gbc);
		
		gbc.gridy = 5;
		add(passwordPanelTwo, gbc);
		
		gbc.gridy = 6;
		add(errorLabel,gbc);
		
		gbc.gridy = 7;
		add(registerButton, gbc);
	}
	
	public void errorMessage(String errorMessage){
		errorLabel.setText(errorMessage);
	}

	//////// GETTERS /////////////
	public String getUsername() {
		return usernameField.getText();
	}
	
	public String getPasswordOne(){
		return passwordFieldOne.getText();
	}
	
	public String getPasswordTwo(){
		return passwordFieldTwo.getText();
	}
	
	public String getFirstName(){
		return firstNameField.getText();
	}
	
	public String getLastName(){
		return lastNameField.getText();
	}
	

}
