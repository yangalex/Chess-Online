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

public class RegisterWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
	
	private final JButton register;
	
	private final String spaces;
	
	public RegisterWindow(ActionListener registerAction) {
		
		register = new JButton("Register");
		
		spaces = "                         ";
		
		createGUI();
		
		register.addActionListener(registerAction);
	}
	
	private void createGUI() {
		setLayout(new GridBagLayout());
		
		JLabel fName = new JLabel("First Name: ");
		JLabel lName = new JLabel("Last Name: ");
		JLabel username = new JLabel("Username: ");
		JLabel password1 = new JLabel("Choose Password: ");
		JLabel password2 = new JLabel("Confirm Password: ");
		
		JTextArea fNameInput = new JTextArea(spaces);
		JTextArea lNameInput = new JTextArea(spaces);
		JTextArea usernameInput = new JTextArea(spaces);
		JTextArea password1Input = new JTextArea(spaces);
		JTextArea password2Input = new JTextArea(spaces);
		
		JPanel fNamePanel = new JPanel();
		fNamePanel.setOpaque(false);
		fNamePanel.setLayout(new FlowLayout());
		fNamePanel.add(fName);
		fNamePanel.add(fNameInput);
		
		JPanel lNamePanel = new JPanel();
		lNamePanel.setOpaque(false);
		lNamePanel.setLayout(new FlowLayout());
		lNamePanel.add(lName);
		lNamePanel.add(lNameInput);
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setOpaque(false);
		usernamePanel.setLayout(new FlowLayout());
		usernamePanel.add(username);
		usernamePanel.add(usernameInput);
		
		JPanel password1Panel = new JPanel();
		password1Panel.setOpaque(false);
		password1Panel.setLayout(new FlowLayout());
		password1Panel.add(password1);
		password1Panel.add(password1Input);
		
		JPanel password2Panel = new JPanel();
		password2Panel.setOpaque(false);
		password2Panel.setLayout(new FlowLayout());
		password2Panel.add(password2);
		password2Panel.add(password2Input);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(30,30,30,30);
		
		gbc.gridy = 1;
		add(fNamePanel, gbc);
		
		gbc.gridy = 2;
		add(lNamePanel, gbc);
				
		gbc.gridy = 3;
		add(usernamePanel, gbc);
		
		gbc.gridy = 4;
		add(password1Panel, gbc);
		
		gbc.gridy = 5;
		add(password2Panel, gbc);
		
		gbc.gridy = 6;
		add(register, gbc);
	}

}
