package ChessEngine.piece;

import java.util.*;
import ChessEngine.ChessColor;
import ChessEngine.board.*;

public abstract class Piece {
        public int row, col; //Coordinates
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

        public abstract ArrayList<Move> calculateLegalMoves(Board board);
}
