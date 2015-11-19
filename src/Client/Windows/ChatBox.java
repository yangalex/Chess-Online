package Client.Windows;

import java.awt.BorderLayout;

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
	
	public ChatBox() {
		initializeComponents();
		createGUI();
	}
	
	public void initializeComponents() {
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		
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
}







