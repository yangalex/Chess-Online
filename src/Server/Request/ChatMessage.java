package Server.Request;

import Server.Player;

public class ChatMessage extends Request{
	private static final long serialVersionUID = 3285307679920556275L;
	private String message;
	private Player sender;

	public ChatMessage(Player sender, String message){
		this.sender = sender;
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
	
	public Player getSender() {
		return sender;
	}
}
