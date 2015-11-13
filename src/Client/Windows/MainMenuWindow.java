package Client.Windows;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainMenuWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
	
	private final JButton login;
	private final JButton register;
	
	private final JLabel logo;
	
	private final String spaces;
	
	public MainMenuWindow(ActionListener loginAction, ActionListener registerAction) {
		
		login = new JButton("Login");
		register = new JButton("Register");
		logo = new JLabel("LOGO");
		
		spaces = "                         ";
		
		createGUI();
		
		login.addActionListener(loginAction);
		register.addActionListener(registerAction);
	}
	
	private void createGUI() {
		setLayout(new GridBagLayout());
		
		JLabel username = new JLabel("username: ");
		JLabel password = new JLabel("password: ");
		JLabel newUser = new JLabel("new user: ");
		
		JTextArea usernameInput = new JTextArea(spaces);
		JTextArea passwordInput = new JTextArea(spaces);
		
		JPanel uPanel = new JPanel();
		uPanel.setLayout(new FlowLayout());
		uPanel.setOpaque(false);
		uPanel.add(username);
		uPanel.add(usernameInput);
		
		JPanel pPanel = new JPanel();
		pPanel.setLayout(new FlowLayout());
		pPanel.setOpaque(false);
		pPanel.add(password);
		pPanel.add(passwordInput);
		
		JPanel rPanel = new JPanel();
		rPanel.setLayout(new FlowLayout());
		rPanel.setOpaque(false);
		rPanel.add(newUser);
		rPanel.add(register);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(40,40,40,40);
		
		gbc.gridy = 1;
		add(logo, gbc);
		
		gbc.gridy = 2;
		add(uPanel, gbc);
				
		gbc.gridy = 3;
		add(pPanel, gbc);
		
		gbc.gridy = 4;
		add(login, gbc);
		
		gbc.gridy = 5;
		add(rPanel, gbc);
	}
}


