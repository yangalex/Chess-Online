package Pieces;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.Vector;

import Client.Game.Tile;

public abstract class Piece implements Serializable {
	public static final long serialVersionUID = 62134561L;
	
	protected Tile currentTile;
	protected boolean firstTime;
	protected final Color color;
	protected final Image image;
	
	public Piece(Color c, Image im) {
		color = c;
		image = im;
		
		firstTime = true;
	}
	
	public void setTile(Tile current) {
		currentTile = current;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void notFirstPawnMove() {
		firstTime = false;
	}
	
	public abstract Vector<Tile> validMoves();
}
