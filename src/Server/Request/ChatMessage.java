package Server.Request;

public class ChatMessage {
	private String message;
	ChatMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
}
