package Pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.Vector;

import Client.Game.Tile;

public class King extends Piece {
	
	public King(Color c, Image im) {
		super(c, im);
	}
	
	public Vector<Tile> validMoves() {
		Vector<Tile> moves = new Vector<Tile>();
		
		//north 
		if(currentTile.getNorth() != null) {
			if(currentTile.getNorth().validSpace(color)) {
				moves.add(currentTile.getNorth());
			}
		}
		
		//south 
		if(currentTile.getSouth() != null) {
			if(currentTile.getSouth().validSpace(color)) {
				moves.add(currentTile.getSouth());
			}
		}
		
		//east
		if(currentTile.getEast() != null) {
			if(currentTile.getEast().validSpace(color)) {
				moves.add(currentTile.getEast());
			}
		}
		
		//west
		if(currentTile.getWest() != null) {
			if(currentTile.getWest().validSpace(color)) {
				moves.add(currentTile.getWest());
			}
		}
		
		//north east 
		if(currentTile.getNorth() != null) {
			if(currentTile.getNorth().getEast() != null && currentTile.getNorth().getEast().validSpace(color)) {
				moves.add(currentTile.getNorth().getEast());
			}
		}
		
		//north west 
		if(currentTile.getNorth() != null) {
			if(currentTile.getNorth().getWest() != null && currentTile.getNorth().getWest().validSpace(color)) {
				moves.add(currentTile.getNorth().getWest());
			}
		}
		
		//south east 
		if(currentTile.getSouth() != null) {
			if(currentTile.getSouth().getEast() != null && currentTile.getSouth().getEast().validSpace(color)) {
				moves.add(currentTile.getSouth().getEast());
			}
		}
		
		//south west 
		if(currentTile.getSouth() != null) {
			if(currentTile.getSouth().getWest() != null && currentTile.getSouth().getWest().validSpace(color)) {
				moves.add(currentTile.getSouth().getWest());
			}
		}
		return moves;
	}
}
