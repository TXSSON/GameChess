package ChessEngine.piece;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.board.*;

public class Knight extends Piece {
        public Knight(int row, int col, ChessColor color) {
                super(row, col, color);

                if (color == ChessColor.white) {
                        imagePath = "src/Main/Resources/piece-image2/wn.png";
                } else {
                        imagePath = "src/Main/Resources/piece-image2/bn.png";
                }
        }
        
        @Override public ArrayList<Move> calculateLegalMoves(Board board) {
                ArrayList<Move> legalMoves = new ArrayList<>();
                final Tile tileFrom = board.tiles[row][col];
                final int[][] possibleDirections = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                                                        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
                //Calculate possible moves
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

                //Check if the move let king in checked
                for (Move move : legalMoves) {
                        if (move.isInCheckedAfterMove(board)) {
                                legalMoves.remove(move);
                        }
                }

                return legalMoves;
        }
}