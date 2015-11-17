package Server.Request;

public class ChatMessage extends Request{
	private static final long serialVersionUID = 3285307679920556275L;
	private String message;
	public ChatMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
}
