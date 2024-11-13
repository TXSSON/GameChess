package ChessEngine.piece;

import java.util.*;
import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.board.move.Move;

public abstract class Piece {
        public int row, col;
        public final ChessColor color;
        protected String imagePath;

        public ArrayList<Move> legalMoves;

        Piece(int row, int col, ChessColor color) {
                this.row = row;
                this.col = col;
                this.color = color;
        }

        public String getImagePath() {
                return imagePath;
        }

        public abstract ArrayList<Move> calculateLegalMoves(final Board board);
}
