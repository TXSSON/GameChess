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
                        imagePath = "src/Main/Resources/piece-image2/wp.png";
                } else {
                        imagePath = "src/Main/Resources/piece-image2/bp.png";
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
