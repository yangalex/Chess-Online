package Client.Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.ChessClient;

/**
 * 
 * @author Alexandre
 * Main game window that will hold the gameboard, chat, timer and lost pieces
*
 */
public class GameBoardWindow extends JPanel {
	private static final long serialVersionUID = 6415716059554739910L;
	
	// waiting for Lea's code
//	GameBoard gameBoard;
	ChatBox chatBox;
	TimerPanel timerPanel;
	
	// Connections
	ClientPanelWindow cpw;
	ChessClient chessClient;
	
	public GameBoardWindow(ClientPanelWindow cpw) {
		this.cpw = cpw;
		this.chessClient = cpw.getChessClient();
		
		initializeComponents();
		createGUI();
	}
	
	public void initializeComponents() {
//		gameBoard = new GameBoard();
		chatBox = new ChatBox();
		timerPanel = new TimerPanel("Alex", "Camilo");
		
		// start timer 
		new Thread(timerPanel).start();
	}
	
	public void createGUI() {
		setLayout(new BorderLayout());
		
		// set GameBoard as CENTER
//		add(gameBoard, BorderLayout.CENTER);
		
		// temporary JPanel to replace gameboard
		JPanel jp = new JPanel();
		jp.setBackground(Color.black);
		add(jp, BorderLayout.CENTER);
		
		// Create panel on EAST that will contain the timer and the chatbox
		JPanel timerAndChatPanel = new JPanel(new BorderLayout());
		timerAndChatPanel.setPreferredSize(new Dimension(200, 0));
		timerAndChatPanel.add(timerPanel, BorderLayout.NORTH);
		timerAndChatPanel.add(chatBox, BorderLayout.CENTER);
		add(timerAndChatPanel, BorderLayout.EAST);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.add(new GameBoardWindow(null));
		frame.setVisible(true);
	}

}








