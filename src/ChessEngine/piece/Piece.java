package ChessEngine.piece;

import java.util.*;
import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.board.move.*;

public abstract class Piece {
        public int row, col;
        public final ChessColor color;
        protected String imagePath;

        public Piece(int row, int col, ChessColor color) {
                this.row = row;
                this.col = col;
                this.color = color;
        }

        public Piece(Piece piece) {
                this.row = piece.row;
                this.col = piece.col;
                this.color = piece.color;
        }

        public String getImagePath() {
                return imagePath;
        }

        public abstract Piece clone();
        public abstract ArrayList<Move> calculateLegalMoves(Gameplay game);
}
