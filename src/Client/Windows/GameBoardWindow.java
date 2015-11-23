package Client.Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Client.ChessClient;
import Client.Game.GameBoard;
import Server.Player;
import Server.Request.ChatMessage;

/**
 * 
 * @author Alexandre
 * Main game window that will hold the gameboard, chat, timer and lost pieces
*
 */
public class GameBoardWindow extends JPanel {
	private static final long serialVersionUID = 6415716059554739910L;
	
	GameBoard gameBoard;
	ChatBox chatBox;
	TimerPanel timerPanel;
	
	// Connections
	ClientPanelWindow cpw;
	ChessClient chessClient;
	
	// Game logic
	Player player;
	Player opponent;
	Color playerColor;
	
	public GameBoardWindow(ClientPanelWindow cpw, Player opponent, Color playerColor) {
		this.cpw = cpw;
		this.chessClient = cpw.getChessClient();
		this.opponent = opponent;
		this.player = cpw.getChessClient().getPlayer();
		this.playerColor = playerColor;

		initializeComponents();
		createGUI();
	}
	
	public void initializeComponents() {
		// TODO pass in local chessClient and opponent player
		gameBoard = new GameBoard(playerColor, chessClient, opponent, cpw);
		
		chatBox = new ChatBox(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// create new chat message and send it to server
				ChatMessage cm = new ChatMessage(chessClient.getPlayer(), chatBox.getMessage());
				chessClient.sendToServer(cm);
				
				// clear sender's textfield
				chatBox.getTextField().setText("");
			}
		});
		
		timerPanel = new TimerPanel(player.getUsername(), opponent.getUsername());
		// start timer 
		new Thread(timerPanel).start();
	}
	
	public void createGUI() {
		setLayout(new BorderLayout());
		
		// set GameBoard as CENTER
		add(gameBoard, BorderLayout.CENTER);
		
		// Create panel on EAST that will contain the timer and the chatbox
		JPanel timerAndChatPanel = new JPanel(new BorderLayout());
		timerAndChatPanel.setPreferredSize(new Dimension(200, 0));
		timerAndChatPanel.add(timerPanel, BorderLayout.NORTH);
		timerAndChatPanel.add(chatBox, BorderLayout.CENTER);
		add(timerAndChatPanel, BorderLayout.EAST);
	}
	
	/////////// GETTERS AND SETTERS /////////////
	public ChatBox getChatBox() {
		return chatBox;
	}
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(740, 480);
//		frame.setLocationRelativeTo(null);
//		frame.add(new GameBoardWindow(null, null));
//		frame.setVisible(true);
//	}

}








