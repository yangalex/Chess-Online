package Client.Windows;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Alexandre
 * 
 * Panel that will contain the chat GUI
 * Instantiated in GameBoardWindow
 */
public class ChatBox extends JPanel {
	public static final long serialVersionUID = 34133515151L;
	
	private JTextArea chatArea;
	private JTextField chatTextField;
	private JButton sendButton;
	
	public ChatBox(ActionListener chatListener) {
		initializeComponents();
		sendButton.addActionListener(chatListener);
		chatTextField.addActionListener(chatListener);
		createGUI();
	}
	
	public void initializeComponents() {
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		
		chatTextField = new JTextField();
		
		sendButton = new JButton("Send");
	}
	
	public void createGUI() {
		setLayout(new BorderLayout());
		
		add(chatArea, BorderLayout.CENTER);
		
		// JPanel to hold textfield and sendButton
		JPanel textFieldAndButtonPanel = new JPanel(new BorderLayout());
		textFieldAndButtonPanel.add(chatTextField, BorderLayout.CENTER);
		textFieldAndButtonPanel.add(sendButton, BorderLayout.EAST);
		add(textFieldAndButtonPanel, BorderLayout.SOUTH);
	}
	
	public String getMessage() {
		return chatTextField.getText();
	}
	
	public JTextField getTextField() {
		return chatTextField;
	}
	
	public void appendToChatArea(String message) {
		chatArea.append(message + "\n");
	}
}







