package ChessEngine.piece;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.board.move.Move;

public class Rook extends Piece {
        public boolean hasMoved;
        
        public Rook(int row, int col, ChessColor color) {
                super(row, col, color);
                this.hasMoved = false;

                if (color == ChessColor.white) {
                        imagePath = "piece-image/rook-w.png";
                } else {
                        imagePath = "piece-image/rook-b.png";
                }
        }
        
        @Override public ArrayList<Move> calculateLegalMoves(final Board board) {
                ArrayList<Move> legalMoves = new ArrayList<>();
                Tile tileFrom = board.tiles[this.row][this.col];
                final int[][] possibleDirections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

                for (int[] direction: possibleDirections) {
                        int rowTo = this.row, colTo = this.col;
                        while (true) {
                                rowTo += direction[0];
                                colTo += direction[1];
                                if (rowTo < 0 || rowTo >= 8 || colTo < 0 || colTo >= 8) {
                                        break;
                                }

                                Tile tileTo = board.tiles[rowTo][colTo];
                                if (tileTo.isOccupied()) {
                                        if (tileTo.getPiece().color != this.color) {
                                                Move newMove = new Move(tileFrom, tileTo);
                                                if (newMove.isInCheckedAfterMove(board) == false) {
                                                        legalMoves.add(newMove);
                                                }
                                        }
                                        break;
                                }

                                Move newMove = new Move(tileFrom, tileTo);
                                if (newMove.isInCheckedAfterMove(board) == false) {
                                        legalMoves.add(newMove);
                                }
                        }
                }
                return legalMoves;
        }
}