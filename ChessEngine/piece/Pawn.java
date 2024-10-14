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
                        image = getImage("/piece-image/pawn-w");
                } else {
                        image = getImage("/piece-image/pawn-b");
                }
        }
        
        @Override public ArrayList<Move> calculateLegalMoves(Board board) {
                ArrayList<Move> legalMoves = new ArrayList<>();
                final Tile tileFrom = board.tiles[row][col];
                
                //TODO: Move forward

                //TODO: Capture

                //TODO: En passant

                //TODO: Promotion
                return legalMoves;
        }
}
