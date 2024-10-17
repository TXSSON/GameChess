package ChessEngine.piece;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.board.*;

public class King extends Piece {
        public boolean isChecked;
        public boolean hasMoved;

        public King(int row, int col, ChessColor color) {
                super(row, col, color);
                this.hasMoved = false;

                if (color == ChessColor.white) {
                        image = getImage("/piece-image/king-w");
                } else {
                        image = getImage("/piece-image/king-b");
                }
        }
        
        @Override public ArrayList<Move> calculateLegalMoves(Board board) {
                ArrayList<Move> legalMoves = new ArrayList<>();
                final Tile tileFrom = board.tiles[row][col];

                //Normal move
                final int[][] possibleDirections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
                                                        {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

                for (int[] direction: possibleDirections) {
                        while (true) {
                                int rowTo = row + direction[0], colTo = col + direction[1];
                                if (rowTo < 0 || rowTo >= 8 || colTo < 0 || colTo >= 8) {
                                        break;
                                }

                                Tile tileTo = board.tiles[rowTo][colTo];
                                if (tileTo.isOccupied()) {
                                        if (tileTo.getPiece().color != this.color) {
                                                legalMoves.add(new Move(tileFrom, tileTo));
                                        }
                                        break;
                                }

                                legalMoves.add(new Move(tileFrom, tileTo));
                        }
                }

                //TODO: Castling move
                return legalMoves;
        }

        public boolean isChecked(Board board) {
                ArrayList<Piece> opponentPieces;
                if (this.color == ChessColor.white) {
                        opponentPieces = board.blackPieces;
                } else {
                        opponentPieces = board.whitePieces;
                }

                //Is checked by knight
                for (Piece piece : opponentPieces) {

                }
                return false;
        }
}

