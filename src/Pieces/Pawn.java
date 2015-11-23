package Pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.Vector;

import Client.Game.Tile;

public class Pawn extends Piece {
	
	public Pawn(Color c, Image im) {
		super(c, im);
		firstTime = true;
	}
	
	public Vector<Tile> validMoves() {
		Vector<Tile> moves = new Vector<Tile>();
		
		if(currentTile.getNorth() !=null && currentTile.getNorth().validPawnSpace(color)) {
			moves.add(currentTile.getNorth());
			if(firstTime) {
				moves.add(currentTile.getNorth().getNorth());
			}
		} 
		
		if(currentTile.getNorth() != null) {
			if(currentTile.getNorth().getEast() != null && currentTile.getNorth().getEast().validPawnAttack(color)) {
				moves.add(currentTile.getNorth().getEast());
			}
		}
		
		if(currentTile.getNorth() != null) {
			if(currentTile.getNorth().getWest() != null && currentTile.getNorth().getWest().validPawnAttack(color)) {
				moves.add(currentTile.getNorth().getWest());
			}
		}
		
		return moves;
	}
}
