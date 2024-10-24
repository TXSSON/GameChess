package ChessEngine.piece;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.board.*;

public class Pawn extends Piece {
        public boolean isJustMoved;
        public Pawn(int row, int col, ChessColor color) {
                super(row, col, color);
                this.isJustMoved = false;

                if (color == ChessColor.white) {
                        imagePath = "piece-image/pawn-w.png";
                } else {
                        imagePath = "piece-image/pawn-b.png";
                }
        }
        
        @Override public ArrayList<Move> calculateLegalMoves(Board board) {
                ArrayList<Move> legalMoves = new ArrayList<>();
                final Tile tileFrom = board.tiles[row][col];
                
                //TODO: Move forward

                //TODO: Capture

                //TODO: En passant

                //TODO: Promotion
                
                //Check if the move let king in checked
                for (Move move : legalMoves) {
                        if (move.isInCheckedAfterMove(board)) {
                                legalMoves.remove(move);
                        }
                }
                return legalMoves;
        }
}
