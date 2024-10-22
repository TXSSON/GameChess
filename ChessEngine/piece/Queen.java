package ChessEngine.piece;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.board.*;

public class Queen extends Piece {
        public Queen(int row, int col, ChessColor color) {
                super(row, col, color);

                if (color == ChessColor.white) {
                        imagePath = "piece-image/queen-w.png";
                } else {
                        imagePath = "piece-image/queen-b.png";
                }
        }
        
        @Override public ArrayList<Move> calculateLegalMoves(Board board) {
                ArrayList<Move> legalMoves = new ArrayList<>();
                final Tile tileFrom = board.tiles[row][col];
                final int[][] possibleDirections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
                                                        {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

                for (int[] direction: possibleDirections) {
                        int rowTo = row, colTo = col;
                        while (true) {
                                rowTo += direction[0];
                                colTo += direction[1];
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

                //Check if the move let king in checked
                for (Move move : legalMoves) {
                        if (move.isInCheckedAfterMove(board)) {
                                legalMoves.remove(move);
                        }
                }
                return legalMoves;
        }
}
