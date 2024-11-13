package ChessEngine.board;

import java.util.*;
import ChessEngine.ChessColor;
import ChessEngine.piece.*;

public class Move {
        public final Tile tileFrom, tileTo;
        
        
        
        public Move(Tile tileFrom, Tile tileTo) {
                this.tileFrom = tileFrom;
                this.tileTo = tileTo;
        }

        public void make(Board board) {
                Piece piece = tileFrom.getPiece();
                //Capture
                if (tileTo.isOccupied()) {
                        if (tileTo.getPiece().color == ChessColor.white) {
                                board.whitePieces.remove(tileTo.getPiece());
                        } else {
                                board.blackPieces.remove(tileTo.getPiece());
                        }
                }
                //En passant
                //Castle
                //Finally move the piece
                tileTo.setPiece(piece);
                tileFrom.clearTile();
        }

        public boolean isInCheckedAfterMove(Board board) {
                //Check if the moves let king in checked
                Piece thisPiece = tileFrom.getPiece();
                try {
                        Board simulationBoard = (Board) board.clone();
                        this.make(simulationBoard);
                        King kingPiece = new King(0, 0, thisPiece.color);
                        ArrayList<Piece> friendlyPieces;
                        if (thisPiece.color == ChessColor.white) {
                                friendlyPieces = board.whitePieces;
                        } else {
                                friendlyPieces = board.blackPieces;
                        }
                        for (Piece piece : friendlyPieces) {
                                if (piece instanceof King) {
                                        kingPiece = (King)piece;
                                }
                        }
                        if (kingPiece.isChecked(simulationBoard)) {
                                return true;
                        }
                } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                }
                return false;
        }

}
