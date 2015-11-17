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

import Library.FontLibrary;
import Library.ImageLibrary;
import Library.PaintedButton;

public class MainMenuWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
	
	private JLabel logoImage;
	private PaintedButton loginButton;
	private PaintedButton registerButton;
	
	private JLabel errorLabel;
		
	public MainMenuWindow(ActionListener loginAction, ActionListener registerAction) {
		initializeVariables();
		createGUI();
		
		loginButton.addActionListener(loginAction);
		registerButton.addActionListener(registerAction);
	}
	
	private void initializeVariables() {
		logoImage = new JLabel("ChessOnline");
		logoImage.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 60));
		logoImage.setForeground(Color.WHITE);
		
		loginButton = new PaintedButton("Login", ImageLibrary.getImage("images/button_image_white.png"), 20);
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		loginButton.setPreferredSize(new Dimension(100,30));
		loginButton.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 20));
		
		registerButton = new PaintedButton("Register", ImageLibrary.getImage("images/button_image_white.png"), 20);
		registerButton.setBorder(BorderFactory.createEmptyBorder());
		registerButton.setPreferredSize(new Dimension(100,30));
		registerButton.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 20));
		errorLabel = new JLabel("  ");
		errorLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 12));
		errorLabel.setForeground(Color.WHITE);
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
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImageLibrary.getImage("images/background_texture.jpg"), 0, 0, getWidth(), getHeight(), null);
	}
}


