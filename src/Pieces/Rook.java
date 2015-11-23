package Pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.Vector;

import Client.Game.Tile;

public class Rook extends Piece{

	public Rook(Color c, Image im) {
		super(c, im);
	}
	
	public Vector<Tile> validMoves() {
		Vector<Tile> moves = new Vector<Tile>();
		
		//north 
		Tile help = currentTile;
		while(help.getNorth() != null && help.getNorth().validSpace(color)) {
			help = help.getNorth();
			moves.add(help);
		}
		
		//south
		help = currentTile;
		while(help.getSouth() != null && help.getSouth().validSpace(color)) {
			help = help.getSouth();
			moves.add(help);
		}
		
		//east 
		help = currentTile;
		while(help.getEast() != null && help.getEast().validSpace(color)) {
			help = help.getEast();
			moves.add(help);
		}
		
		//west
		help = currentTile;
		while(help.getWest() != null && help.getWest().validSpace(color)) {
			help = help.getWest();
			moves.add(help);
		}
		
		return moves;
	}
}
