package Server.Request;

import Server.Player;

public class GameRequest extends Request{
	private static final long serialVersionUID = -6097123844261824548L;
	/// PLAYERS
	private Player asking, request;
	private Boolean cancel;
	public GameRequest(Player asking, Player request){
		setAsking(asking);
		setRequest(request);
		setCancel(false);
	}
	
	
	//// SETTER AND GETTERS ///////////
	public Player getAsking() {
		return asking;
	}
	public void setAsking(Player asking) {
		this.asking = asking;
	}
	public Player getRequest() {
		return request;
	}
	public void setRequest(Player request) {
		this.request = request;
	}

	public Boolean getCancel() {
		return cancel;
	}

	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}
	

}
