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
        
        @Override public ArrayList<Move> calculateLegalMoves(final Board board) {
                ArrayList<Move> legalMoves = new ArrayList<>();
                
                //TODO: Move forward

                //TODO: Capture

                //TODO: En passant

                //TODO: Promotion
                
                return legalMoves;
        }
}
