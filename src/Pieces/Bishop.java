package Pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.Vector;

import Client.Game.Tile;

public class Bishop extends Piece {
	
	public Bishop(Color c, Image im) {
		super(c, im);
	}
	
	public Vector<Tile> validMoves() {
		Vector<Tile> moves = new Vector<Tile>();
		
		//north east 
		Tile help = currentTile;
		boolean free = true;
		while(help.getNorth() != null && free) {
			help = help.getNorth();
			if(help.getEast() != null && help.getEast().validSpace(color)) {
				help = help.getEast();
				moves.add(help);
				if(! help.isEmpty()) {
					free = false;
				}
			}
			else {
				free = false;
			}
			
		}
		
		//north west 
		help = currentTile;
		free = true;
		while(help.getNorth() != null && free) {
			help = help.getNorth();
			if(help.getWest() != null && help.getWest().validSpace(color)) {
				help = help.getWest();
				moves.add(help);
				if(! help.isEmpty()) {
					free = false;
				}
			}
			else {
				free = false;
			}
		}
		
		//south east 
		help = currentTile;
		free = true;
		while(help.getSouth() != null && free) {
			help = help.getSouth();
			if(help.getEast() != null && help.getEast().validSpace(color)) {
				help = help.getEast();
				moves.add(help);
				if(! help.isEmpty()) {
					free = false;
				}
			}
			else {
				free = false;
			}
		}
//		
		//south west 
		help = currentTile;
		free = true;
		while(help.getSouth() != null && free) {
			help = help.getSouth();
			if(help.getWest() != null && help.getWest().validSpace(color)) {
				help = help.getWest();
				moves.add(help);
				if(! help.isEmpty()) {
					free = false;
				}
			}
			else {
				free = false;
			}
		
		}
		return moves;	
	}

}
