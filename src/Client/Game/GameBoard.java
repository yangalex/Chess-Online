package Client.Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Client.ChessClient;
import Client.Windows.ClientPanelWindow;
import Library.ImageLibrary;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Server.Player;

public class GameBoard extends JPanel {
	private static final long serialVersionUID = 6415716059554739910L;
	
	//client
	private final ChessClient client;
	private final ClientPanelWindow cpw;
	
	//player color
	private final Color color;
	//other player's color 
	private final Color oponentColor;
	
	private Tile[][] panels;
	
	//help variables 
	private boolean currentPlayer;
	private Tile selectedTile;
	
	private boolean waitingPiece;
	private boolean waitingMove;
	
	private Player opponent;
	
	private Vector<Tile> highlightedTiles;
	
	public GameBoard(Color c, ChessClient cl, Player opponent, ClientPanelWindow cpw) {
		color = c;
		client = cl;
		this.opponent = opponent;
		this.cpw = cpw;
		
		if(color == Color.WHITE) {
			oponentColor = Color.BLACK;
			currentPlayer = true;
		}
		else {
			oponentColor = Color.WHITE;
			currentPlayer = false;
		}
		
		panels = new Tile[8][8];
		createGUI();
		initializeElements();
	}
	
	private void initializeElements() {
		highlightedTiles = new Vector<Tile>();
		setNeighbors();
		addActionListeners();
		setInitialPieces();
		
		selectedTile = null;
		
		waitingPiece = true;
		waitingMove = false;
	}
	
	private void createGUI() {
		setLayout(new GridLayout(8,8));
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if(i%2 == 0) {
					if(j%2 == 0) {
						panels[i][j] = new Tile(Color.WHITE, i, j);
						add(panels[i][j]);
					}
					else {
						panels[i][j] = new Tile(Color.BLACK, i, j);
						add(panels[i][j]);
					}
				}
				else {
					if(j%2 == 0) {
						panels[i][j] = new Tile(Color.BLACK, i, j);
						add(panels[i][j]);
					}
					else {
						panels[i][j] = new Tile(Color.WHITE, i, j);
						add(panels[i][j]);
					}
				}
			}
		}
	}
	
	private void setInitialPieces() {
		if(color == Color.BLACK) {
			//player's black pawns 
			for(int j=0; j<8; j++) {
				Pawn pawn = new Pawn(color, ImageLibrary.getImage("images/pieces/pawn_black.png"));
				panels[6][j].setPiece(pawn);
			}
			
			//oponent's white pawns 
			for(int j=0; j<8; j++) {
				Pawn pawn = new Pawn(oponentColor, ImageLibrary.getImage("images/pieces/pawn_white.png"));
				panels[1][j].setPiece(pawn);
			}
			
			if(oponentColor == Color.BLACK) {
			}
			else {
			}
			
			//playe's black rooks
			Rook PLeftRook = new Rook(color, ImageLibrary.getImage("images/pieces/rook_black.png"));
			panels[7][0].setPiece(PLeftRook);
			Rook PRightRook = new Rook(color, ImageLibrary.getImage("images/pieces/rook_black.png"));
			panels[7][7].setPiece(PRightRook);
			
			//oponent's white rooks
			Rook OLeftRook = new Rook(oponentColor, ImageLibrary.getImage("images/pieces/rook_white.png"));
			panels[0][0].setPiece(OLeftRook);
			Rook ORightRook = new Rook(oponentColor, ImageLibrary.getImage("images/pieces/rook_white.png"));
			panels[0][7].setPiece(ORightRook);
			
			//player's white knights
			Knight PLeftKnight = new Knight(color, ImageLibrary.getImage("images/pieces/knight_black.png"));
			panels[7][1].setPiece(PLeftKnight);
			Knight PRightKnight = new Knight(color, ImageLibrary.getImage("images/pieces/knight_black.png"));
			panels[7][6].setPiece(PRightKnight);
			
			//oponent's white knights 
			Knight OLeftKnight = new Knight(oponentColor, ImageLibrary.getImage("images/pieces/knight_white.png"));
			panels[0][1].setPiece(OLeftKnight);
			Knight ORightKnight = new Knight(oponentColor, ImageLibrary.getImage("images/pieces/knight_white.png"));
			panels[0][6].setPiece(ORightKnight);
			
			//player's white bishops 
			Bishop PLeftBishop = new Bishop(color, ImageLibrary.getImage("images/pieces/bishop_black.png"));
			panels[7][2].setPiece(PLeftBishop);
			Bishop PRightBishop = new Bishop(color, ImageLibrary.getImage("images/pieces/bishop_black.png"));
			panels[7][5].setPiece(PRightBishop);
			
			//oponent's black bishops 
			Bishop OLeftBishop = new Bishop(oponentColor, ImageLibrary.getImage("images/pieces/bishop_white.png"));
			panels[0][2].setPiece(OLeftBishop);
			Bishop ORightBishop = new Bishop(oponentColor, ImageLibrary.getImage("images/pieces/bishop_white.png"));
			panels[0][5].setPiece(ORightBishop);
			
			//queens and kings 
			Queen OQueen = new Queen(oponentColor, ImageLibrary.getImage("images/pieces/queen_white.png"));
			Queen PQueen = new Queen(color, ImageLibrary.getImage("images/pieces/queen_black.png"));
			King OKing = new King(oponentColor, ImageLibrary.getImage("images/pieces/king_white.png"));
			King PKing = new King(color, ImageLibrary.getImage("images/pieces/king_black.png"));
			
			panels[0][3].setPiece(OKing);
			panels[0][4].setPiece(OQueen);
			panels[7][3].setPiece(PKing);
			panels[7][4].setPiece(PQueen);
			
		}
		else {
			//player's white pawns 
			for(int j=0; j<8; j++) {
				Pawn pawn = new Pawn(color, ImageLibrary.getImage("images/pieces/pawn_white.png"));
				panels[6][j].setPiece(pawn);
			}
			
			//oponent's black pawns 
			for(int j=0; j<8; j++) {
				Pawn pawn = new Pawn(oponentColor, ImageLibrary.getImage("images/pieces/pawn_black.png"));
				panels[1][j].setPiece(pawn);
			}
			
			//player's white rooks
			Rook PLeftRook = new Rook(color, ImageLibrary.getImage("images/pieces/rook_white.png"));
			panels[7][0].setPiece(PLeftRook);
			Rook PRightRook = new Rook(color, ImageLibrary.getImage("images/pieces/rook_white.png"));
			panels[7][7].setPiece(PRightRook);
			
			//oponent's black rooks
			Rook OLeftRook = new Rook(oponentColor, ImageLibrary.getImage("images/pieces/rook_black.png"));
			panels[0][0].setPiece(OLeftRook);
			Rook ORightRook = new Rook(oponentColor, ImageLibrary.getImage("images/pieces/rook_black.png"));
			panels[0][7].setPiece(ORightRook);
			
			//player's white knights
			Knight PLeftKnight = new Knight(color, ImageLibrary.getImage("images/pieces/knight_white.png"));
			panels[7][1].setPiece(PLeftKnight);
			Knight PRightKnight = new Knight(color, ImageLibrary.getImage("images/pieces/knight_white.png"));
			panels[7][6].setPiece(PRightKnight);
			
			//oponent's black knights 
			Knight OLeftKnight = new Knight(oponentColor, ImageLibrary.getImage("images/pieces/knight_black.png"));
			panels[0][1].setPiece(OLeftKnight);
			Knight ORightKnight = new Knight(oponentColor, ImageLibrary.getImage("images/pieces/knight_black.png"));
			panels[0][6].setPiece(ORightKnight);
			
			//player's white bishops 
			Bishop PLeftBishop = new Bishop(color, ImageLibrary.getImage("images/pieces/bishop_white.png"));
			panels[7][2].setPiece(PLeftBishop);
			Bishop PRightBishop = new Bishop(color, ImageLibrary.getImage("images/pieces/bishop_white.png"));
			panels[7][5].setPiece(PRightBishop);
			
			//oponent's black bishops 
			Bishop OLeftBishop = new Bishop(oponentColor, ImageLibrary.getImage("images/pieces/bishop_black.png"));
			panels[0][2].setPiece(OLeftBishop);
			Bishop ORightBishop = new Bishop(oponentColor, ImageLibrary.getImage("images/pieces/bishop_black.png"));
			panels[0][5].setPiece(ORightBishop);
			
			
			//queens and kings
			Queen OQueen = new Queen(oponentColor, ImageLibrary.getImage("images/pieces/queen_black.png"));
			Queen PQueen = new Queen(color, ImageLibrary.getImage("images/pieces/queen_white.png"));
			King OKing = new King(oponentColor, ImageLibrary.getImage("images/pieces/king_black.png"));
			King PKing = new King(color, ImageLibrary.getImage("images/pieces/king_white.png"));
			
			panels[0][3].setPiece(OQueen);
			panels[0][4].setPiece(OKing);
			panels[7][3].setPiece(PQueen);
			panels[7][4].setPiece(PKing);
		}
	}
	
	private void setNeighbors() {
		for(int i=0; i<7; i++) {
			for(int j=0; j<8; j++) {
				panels[i][j].setSouth(panels[i+1][j]);
			}
		}
		for(int i=1; i<8; i++) {
			for(int j=0; j<8; j++) {
				panels[i][j].setNorth(panels[i-1][j]);
			}
		}
		for(int i=0; i<8; i++) {
			for(int j=0; j<7; j++) {
				panels[i][j].setEast(panels[i][j+1]);
			}
		}
		for(int i=0; i<8; i++) {
			for(int j=1; j<8; j++) {
				panels[i][j].setWest(panels[i][j-1]);
			}
		}
	}
	
	private void addActionListeners() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				panels[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						if(currentPlayer) {
							spaceClicked(me.getSource());
						}
					}
				});
			}
		}
	}
	
	private void spaceClicked(Object o) {
		Tile clickedTile = (Tile)o;
		
		if(waitingPiece) {
			if(clickedTile.hasPlayerPiece(color)) {
				Vector<Tile> moves = clickedTile.getPieceMoves();
				if(! moves.isEmpty()) {
					selectedTile = clickedTile;
					highlightMoves(moves);
					
					waitingPiece = false;
					waitingMove = true;
				}
			}
		}
		else if(waitingMove) {
			if(selectedTile.getPieceMoves().contains(clickedTile)) {				
				if(clickedTile.isEmpty()) {
					clickedTile.setPiece(selectedTile.getPiece());
					selectedTile.removePiece();
					
					//send the move to the server
					Move move = new Move(selectedTile, clickedTile, client.getPlayer(), opponent);
					client.sendToServer(move);
					currentPlayer = false;
				}
				else {
					if(clickedTile.isKing()) {
						Move move = new Move(selectedTile, clickedTile, client.getPlayer(), opponent);
						move.setWin(true);
						client.sendToServer(move);
						playerWins();
					}
					else {
						clickedTile.removePiece();
						clickedTile.setPiece(selectedTile.getPiece());
						selectedTile.removePiece();
						
						//send the move to the server
						Move move = new Move(selectedTile, clickedTile, client.getPlayer(), opponent);
						client.sendToServer(move);
						currentPlayer = false;
					}
				}
				selectedTile = null;
				waitingPiece = true;
				waitingMove = false;
				clearHighlight();
			}
			else {
				selectedTile = null;
				waitingPiece = true;
				waitingMove = false;
				clearHighlight();
			}
		}
	}
	
	private void clearHighlight() {
		for(Tile t: highlightedTiles) {
			t.clearHighlight();
		}
	}
	
	private void highlightMoves(Vector<Tile> moves) {
		highlightedTiles = moves;
		for(Tile t: moves) {
			t.highlight();
		}
	}
	
	public void recievedMove(Move move) {
		// need to subtract from 7 to translate coordinates because of flipped boards
		Piece movedPiece = panels[7-move.getStartRow()][7-move.getStartCol()].getPiece();
		panels[7-move.getEndRow()][7-move.getEndCol()].setPiece(movedPiece);
		panels[7-move.getStartRow()][7-move.getStartCol()].removePiece();
		
		// if opponent won the game
		if (move.isWin()) {
			opponentWins();
			return;
		}

		currentPlayer = true;
	}
	
	private void playerWins() {
		JOptionPane.showMessageDialog(cpw, "You won!");
		
		cpw.removeAll();
		cpw.add(cpw.getDashBoardWindow());
		cpw.revalidate();
		cpw.repaint();	

	}
	
	private void opponentWins() {
		JOptionPane.showMessageDialog(cpw, "You lost...");
		
		cpw.removeAll();
		cpw.add(cpw.getDashBoardWindow());
		cpw.revalidate();
		cpw.repaint();	

	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(600, 600);
//		GameBoard board = new GameBoard(Color.WHITE);
//		frame.add(board);
//		frame.setVisible(true);
//	}
	
	
}
