package Main.Controller;

import java.util.List;

import ChessEngine.board.Tile;
import ChessEngine.board.move.Move;
import ChessEngine.piece.Piece;

public class MoveValidator {
	public boolean isValidMove(Piece selectedPiece, Tile targetTile, List<Move> availableMoves) {
		 if (selectedPiece == null || availableMoves == null) {
	            return false;
	        }
	        for (Move move : availableMoves) {
	            if (move.tileTo.equals(targetTile)) {
	                return true;
	            }
	        }	
		return false;
	}
	 public List<Move> calculateAvailableMoves(Piece piece, MainController mainController) {
	        try {
	            return piece.calculateLegalMoves(mainController.gameplay);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
