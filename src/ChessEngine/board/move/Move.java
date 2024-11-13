package ChessEngine.board.move;

import java.util.*;
import ChessEngine.ChessColor;
import ChessEngine.board.Board;
import ChessEngine.board.Tile;
import ChessEngine.piece.*;

public class Move {
        public final Tile tileFrom, tileTo;
        
        
        
        public Move(Tile tileFrom, Tile tileTo) {
                this.tileFrom = tileFrom;
                this.tileTo = tileTo;
        }

        public void make(Board board) {
                Piece piece = tileFrom.getPiece();
                //Remove the piece at tileTo
                if (tileTo.isOccupied()) {
                        if (tileTo.getPiece().color == ChessColor.white) {
                                board.whitePieces.remove(tileTo.getPiece());
                        } else {
                                board.blackPieces.remove(tileTo.getPiece());
                        }
                }

                //Finally move the piece
                tileTo.setPiece(piece);
                tileFrom.clearTile();

                //Change the state of the king and rook if needed
                if (piece instanceof King) {
                        ((King)piece).hasMoved = true;
                } else if (piece instanceof Rook) {
                        ((Rook)piece).hasMoved = true;
                } 
        }

        public boolean isInCheckedAfterMove(Board board) {
                //Check if the move lets king in checked
                Piece thisPiece = tileFrom.getPiece();
                try {
                        //Make a clone board and simulate the move
                        Board simulationBoard = (Board) board.clone();
                        this.make(simulationBoard);

                        //Find the king in the simulation board
                        ArrayList<Piece> friendlyPieces;
                        if (thisPiece.color == ChessColor.white) {
                                friendlyPieces = board.whitePieces;
                        } else {
                                friendlyPieces = board.blackPieces;
                        }
                        for (Piece piece : friendlyPieces) {
                                if (piece instanceof King) {
                                        if (((King)piece).isChecked(simulationBoard)) {
                                                return true;
                                        }
                                }
                        }
                } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                }
                return false;
        }

}