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
                ArrayList<Piece> pieces = (this.color == ChessColor.white) ? board.blackPieces : board.whitePieces;
                for (Piece piece : board.pieces) {
                        Class pieceClass = piece.getClass();
                        //Case: Checked by knights
                        if (pieceClass == Knight.class) {
                        }
                        //Case: Checked by pawns
                        if (pieceClass == Pawn.class) {
                                if (this.color == ChessColor.white
                                        && piece.row == this.row + 1 && (piece.col == this.col - 1 || piece.col == this.col + 1)) {
                                                return true;
                                } else if (this.color == ChessColor.black
                                        && piece.row == this.row - 1 && (piece.col == this.col - 1 || piece.col == this.col + 1)) {
                                                return true;
                                }
                        }
                }
                return false;
        }
}

