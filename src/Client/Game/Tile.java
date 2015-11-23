package Client.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import Pieces.King;
import Pieces.Pawn;
import Pieces.Piece;

public class Tile extends JPanel {
	private static final long serialVersionUID = 6415716059554739910L;
	
	//neighbors 
	private Tile north;
	private Tile south;
	private Tile east;
	private Tile west;
	private int row;
	private int col;
	
	//current piece
	private Piece currentPiece;
	private boolean isEmpty;
	
	//tile color
	private final Color color;
	
	public Tile(Color c, int i, int j) {
		color = c;
		row = i;
		col = j;
		
		setBackground(c);
		setOpaque(true);
		
		//initalizeVariables
		currentPiece = null;
		isEmpty = true;
	}
	
	public void setNorth(Tile n) {
		north = n;
	}
	
	public void setSouth(Tile s) {
		south = s;
	}
	
	public void setEast(Tile e) {
		east = e;
	}
	
	public void setWest(Tile w) {
		west = w;
	}
	
	public Tile getNorth() {
		return north;
	}
	
	public Tile getSouth() {
		return south;
	}
	
	public Tile getEast() {
		return east;
	}
	
	public Tile getWest() {
		return west;
	}
	
	public Tile getNorthEast() {
		Tile ne = this;
		if(this.getNorth() != null) {
			ne = this.getNorth();
			if(ne.getEast() != null) {
				ne = ne.getEast();
			}
			else {
				ne = null;
			}
		}
		else {
			ne = null;
		}
		return null;
	}
	
	public Tile getNorthWest() {
		Tile ne = this;
		if(this.getNorth() != null) {
			ne = this.getNorth();
			if(ne.getWest() != null) {
				ne = ne.getWest();
			}
			else {
				ne = null;
			}
		}
		else {
			ne = null;
		}
		return null;
	}
	
	public Tile getSouthEast() {
		Tile ne = this;
		if(this.getSouth() != null) {
			ne = this.getSouth();
			if(ne.getWest() != null) {
				ne = ne.getEast();
			}
			else {
				ne = null;
			}
		}
		else {
			ne = null;
		}
		return null;
	}
	
	public Tile getSouthWest() {
		Tile ne = this;
		if(this.getSouth() != null) {
			ne = this.getSouth();
			if(ne.getWest() != null) {
				ne = ne.getWest();
			}
			else {
				ne = null;
			}
		}
		else {
			ne = null;
		}
		return null;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public boolean validSpace(Color c) {
		if(isEmpty) {
			return true;
		}
		else if(currentPiece.getColor() != c) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validPawnSpace(Color c) {
		if(isEmpty) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validPawnAttack(Color c) {
		if(isEmpty) {
			return false;
		}
		else if(currentPiece.getColor() != c) {  
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean hasPlayerPiece(Color c) {
		if(isEmpty) {
			return false;
		}
		else if(currentPiece.getColor() == c) {	
			return true;
		}
		else {	
			return false;
		}
	}
	
	public Vector<Tile> getPieceMoves() {
		Vector<Tile> moves = currentPiece.validMoves();
		return moves;
	}
	
	public void setPiece(Piece p) {
		isEmpty = false;
		currentPiece = p;
		currentPiece.setTile(this);
		revalidate();
		repaint();
	}
	
	public void removePiece() {
		if(currentPiece instanceof Pawn) {
			currentPiece.notFirstPawnMove();
		}
		isEmpty = true;
		currentPiece = null;
		revalidate();
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!isEmpty) {
			g.drawImage(currentPiece.getImage(), 0, 0, this.getWidth() - 10, this.getHeight() - 10, null);
		}
	}
	
	public void printColor() {
		
		if(!isEmpty) {
			if(currentPiece.getColor() == Color.BLACK) {
				System.out.println("The piece color is black");
			}
			else {
				System.out.println("The piece Color is white");
			}
		}
		
	}
	
	public Piece getPiece() {
		return currentPiece;
	}
	
	public void highlight() {
		setBackground(Color.YELLOW);
	}
	
	public void clearHighlight() {
		setBackground(color);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean isKing() {
		if(currentPiece instanceof King) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
