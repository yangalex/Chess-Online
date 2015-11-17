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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Library.FontLibrary;
import Library.ImageLibrary;
import Library.PaintedButton;
import Server.Request.Authenticate;

public class LoginWindow extends JPanel{
	private static final long serialVersionUID = 1L;
	
	/// MEMBER VARIABLES
	private JTextField usernameField,passwordField;
	private PaintedButton loginButton;
	private JLabel errorLabel;
	
	public LoginWindow(ActionListener loginAction){
		initializeVariables();
		createGUI();
		loginButton.addActionListener(loginAction);
		usernameField.addActionListener(loginAction);
		passwordField.addActionListener(loginAction);
	}

	private void createGUI() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setOpaque(false);
		usernamePanel.setLayout(new FlowLayout());
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 20));
		usernameLabel.setForeground(Color.WHITE);
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		
		gbc.gridy = 1;
		add(usernamePanel, gbc);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout());
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 20));
		passwordLabel.setForeground(Color.WHITE);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		gbc.gridy = 2;
		add(passwordPanel, gbc);
		
		gbc.gridy = 3;
		add(errorLabel, gbc);

		
		gbc.gridy = 4;
		add(loginButton, gbc);
	}

	private void initializeVariables() {
		usernameField = new JTextField(20);
		usernameField.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 18));
		passwordField = new JPasswordField(20);
		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 20));
		loginButton = new PaintedButton("Login", ImageLibrary.getImage("images/button_image_white.png"), 20);
		loginButton.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 20));
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		loginButton.setPreferredSize(new Dimension(100,30));
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
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImageLibrary.getImage("images/background_texture.jpg"), 0, 0, getWidth(), getHeight(), null);
	}

}
