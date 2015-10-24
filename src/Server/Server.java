package Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame{
	private static final long serialVersionUID = -2484948066340307505L;
	
	//Server Settings
	private static final int port = 61111;
	private int connectedUsers = 0;
	private ChestServer cs;
	
	// GUI Components
	private JTextArea messageBox;
	private JLabel topLabel;
	
	//Database Settings
	private String url = "jdbc:mysql://localhost:3306";;
	private String username = "root";
	private String password = "American12!!";
	
	{		
		setSize(new Dimension(400,500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Creating ServerSocket
	public Server(){
		super("Chess Server");
		initializeVariables();
		creatGUI();
		connectToDatabase();
		startServer();
		setVisible(true);
	}
	
	private void connectToDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//Connection conn = 
			DriverManager.getConnection(url,username,password);	
			message("Established connection to database!");
		} 
		catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			message("Could not connect to database."+ e.getMessage());
			e.printStackTrace();
		}
	}

	private void startServer() {
		cs = new ChestServer(port,this);
		cs.start();
	}

	private void initializeVariables() {
		messageBox = new JTextArea();
			messageBox.setEditable(false);
			messageBox.setText("Welcome to the Chess Server");
		topLabel = new JLabel("Connected Users: " + connectedUsers);
			topLabel.setBackground(Color.WHITE);
	}
	
	private void creatGUI() {
		createMenu();
		add(topLabel,BorderLayout.PAGE_START);
			JScrollPane jsp = new JScrollPane(messageBox);
			//jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp, BorderLayout.CENTER);
	}
	
	private void createMenu() {
		JMenuBar jmb = new JMenuBar();
			JMenu jm = new JMenu("File");
				JMenuItem port = new JMenuItem("Port");
				JMenuItem close = new JMenuItem("Close Server");
				JMenuItem database = new JMenuItem("Database");
				jm.add(port);
				jm.add(database);
				jm.add(close);
			jmb.add(jm);
		setJMenuBar(jmb);		
	}
	
	public void message(String message){
		String original = messageBox.getText();
		messageBox.setText(original + "\n" + message);
	}
	
	public void userConnected(){
		connectedUsers += 1;
		topLabel.setText("Connected Users: " + connectedUsers);
		revalidate();
		repaint();
	}
	
	public static void main(String [] args){
		new Server();
	}
}
