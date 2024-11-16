package ChessEngine.board;

import ChessEngine.ChessColor;
import ChessEngine.piece.*;

public class Tile {
        public final int row, col;
        public final ChessColor color;
        private Piece piece;

        public Tile(int row, int col, Piece piece, ChessColor color) {
                this.row = row;
                this.col = col;
                this.piece = piece;
                this.color = color;
        }

        public Tile(Tile tile) {
                this.row = tile.row;
                this.col = tile.col;
                this.piece = tile.piece;
                this.color = tile.color;

                if (tile.piece != null) {
                        this.piece = tile.piece.clone();
                }
        }

        public boolean isOccupied() {
                if (piece == null) return false;
                return true;
        }

        public Piece getPiece() {
                return piece;
        }

        //Set the piece in tile to another not-null piece
        public void setPiece(Piece piece) {
                if (piece != null) {
                        piece.row = row;
                        piece.col = col;
                        this.piece = piece;
                }
        }

        public void clearTile() {
                this.piece = null;
        }
}
