package Pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.Vector;

import Client.Game.Tile;

public class Knight extends Piece {
	
	public Knight(Color c, Image im) {
		super(c, im);
	}
	
	public Vector<Tile> validMoves() {
		Vector<Tile> moves = new Vector<Tile>();
		
		Tile help = currentTile;
		
		//north north 
		if(help.getNorth() != null) {
			help = help.getNorth();
			if(help.getNorth() != null) {
				help = help.getNorth();
				//west 
				if(help.getWest() != null && help.getWest().validSpace(color)) {
					help = help.getWest();
					moves.add(help);
					help = help.getEast();
				}
				//east
				if(help.getEast() != null && help.getEast().validSpace(color)) {
					help = help.getEast();
					moves.add(help);
				}
			}
		}
		
		//south south 
		help = currentTile;
		if(help.getSouth() != null) {
			help = help.getSouth();
			if(help.getSouth() != null) {
				help = help.getSouth();
				//west 
				if(help.getWest() != null && help.getWest().validSpace(color)) {
					help = help.getWest();
					moves.add(help);
					help = help.getEast();
				}
				//east
				if(help.getEast() != null && help.getEast().validSpace(color)) {
					help = help.getEast();
					moves.add(help);
				}
			}
		}
		
		//east east 
		help = currentTile;
		if(help.getEast() != null) {
			help = help.getEast();
			if(help.getEast() != null) {
				help = help.getEast();
				//north 
				if(help.getNorth() != null && help.getNorth().validSpace(color)) {
					help = help.getNorth();
					moves.add(help);
					help = help.getSouth();
				}
				//south 
				if(help.getSouth() != null && help.getSouth().validSpace(color)) {
					help = help.getSouth();
					moves.add(help);
				}
			}
		}
		
		//west west 
		help = currentTile;
		if(help.getWest() != null) {
			help = help.getWest();
			if(help.getWest() != null) {
				help = help.getWest();
				//north 
				if(help.getNorth() != null && help.getNorth().validSpace(color)) {
					help = help.getNorth();
					moves.add(help);
					help = help.getSouth();
				}
				//south 
				if(help.getSouth() != null && help.getSouth().validSpace(color)) {
					help = help.getSouth();
					moves.add(help);
				}
			}
		}
		
		return moves;
	}

}
