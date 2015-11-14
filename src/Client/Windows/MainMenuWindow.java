package Client.Windows;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Library.ImageLibrary;

public class MainMenuWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
	
	private JLabel logoImage;
	private JButton loginButton;
	private JButton registerButton;
	
	private JLabel errorLabel;
		
	public MainMenuWindow(ActionListener loginAction, ActionListener registerAction) {
		initializeVariables();
		createGUI();
		
		loginButton.addActionListener(loginAction);
		registerButton.addActionListener(registerAction);
	}
	
	private void initializeVariables() {
		logoImage = new JLabel(ImageLibrary.getImageIcon("images/logo.png"));
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		errorLabel = new JLabel("  ");
	}

	private void createGUI() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(40,5,40,5);
		
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(logoImage, gbc);
		
		gbc.gridy = 2;
		add(errorLabel,gbc);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(loginButton);
		buttonsPanel.add(registerButton);
		
		gbc.gridy = 3;
		add(buttonsPanel, gbc);
	}

	public void errorMessage(String string) {
		errorLabel.setText(string);		
	}
}


