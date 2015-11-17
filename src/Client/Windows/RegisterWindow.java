package Client.Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Library.FontLibrary;
import Library.ImageLibrary;
import Library.PaintedButton;

public class RegisterWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
	
	// Register Window Components
	private JButton registerButton;
	private JTextField usernameField, passwordFieldOne, passwordFieldTwo, firstNameField, lastNameField;
	private JLabel errorLabel;
	
	// Connections
	//private ClientPanelWindow cpw;
	
	public RegisterWindow(ActionListener registerAction, ClientPanelWindow cpw) {
		//this.cpw = cpw;
		initializeVariables();
		createGUI();
		registerButton.addActionListener(registerAction);
		
		}
	
	private void initializeVariables() {
		registerButton = new PaintedButton("Register", ImageLibrary.getImage("images/button_image_white.png"), 20);
		registerButton.setBorder(BorderFactory.createEmptyBorder());
		registerButton.setPreferredSize(new Dimension(100,30));
		registerButton.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 20));
		
		firstNameField = new JTextField(20);
		firstNameField.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));
		
		lastNameField = new JTextField(20);
		lastNameField.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));

		usernameField = new JTextField(20);
		usernameField.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));

		passwordFieldOne = new JPasswordField(20);
		passwordFieldOne.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));

		passwordFieldTwo = new JPasswordField(20);
		passwordFieldTwo.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));

		errorLabel = new JLabel("  ");
		errorLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));
		errorLabel.setForeground(Color.WHITE);
	}

	private void createGUI() {
		setLayout(new GridBagLayout());
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));
		firstNameLabel.setForeground(Color.WHITE);

		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));
		lastNameLabel.setForeground(Color.WHITE);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));
		usernameLabel.setForeground(Color.WHITE);
		
		JLabel passwordLabelOne = new JLabel("Choose Password: ");
		passwordLabelOne.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));
		passwordLabelOne.setForeground(Color.WHITE);
		
		JLabel passwordLabelTwo = new JLabel("Confirm Password: ");
		passwordLabelTwo.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));
		passwordLabelTwo.setForeground(Color.WHITE);
		
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
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImageLibrary.getImage("images/background_texture.jpg"), 0, 0, getWidth(), getHeight(), null);
	}

}
