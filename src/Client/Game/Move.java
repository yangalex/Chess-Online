package Client.Game;

import java.io.Serializable;

import Server.Player;

public class Move implements Serializable {
	public static final long serialVersionUID = 4651613451L;
	
	final int startRow;
	final int startCol;
	final int endRow;
	final int endCol;
	private boolean winMove;
	
	private final String playerUsername;
	private final String opponentUsername;
	
	public Move(Tile startTile, Tile endTile, Player player, Player opponent) {
		startRow = startTile.getRow();
		startCol = startTile.getCol();
		endRow = endTile.getRow();
		endCol = endTile.getCol();
		playerUsername = player.getUsername();
		opponentUsername = opponent.getUsername();
		winMove = false;
	}
	
	public int getStartRow() {
		return startRow;
	}
	
	public int getStartCol() {
		return startCol;
	}
	
	public int getEndRow() {
		return endRow;
	}
	
	public int getEndCol() {
		return endCol;
	}
	
	public String getPlayerUsername() {
		return playerUsername;
	}
	
	public String getOpponentUsername() {
		return opponentUsername;
	}
	
	public void setWin(boolean win) {
		winMove = win;
	}
	
	public boolean isWin() {
		return winMove;
	}
	
}