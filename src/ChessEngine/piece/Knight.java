package ChessEngine.piece;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.board.move.*;

public class Knight extends Piece {
        public Knight(int row, int col, ChessColor color) {
                super(row, col, color);

                if (color == ChessColor.white) {
                        imagePath = "src/Main/Resources/piece-image2/wn.png";
                } else {
                        imagePath = "src/Main/Resources/piece-image2/bn.png";
                }
        }

        public Knight(Knight piece) {
                super(piece);
                this.imagePath = piece.imagePath;
        }

        @Override public Knight clone() {
                return new Knight(this);
        }
        
        @Override public ArrayList<Move> calculateLegalMoves(Gameplay game) {
                Board board = game.board;
                
                ArrayList<Move> legalMoves = new ArrayList<>();
                Tile tileFrom = board.tiles[this.row][this.col];
                final int[][] possibleDirections = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                                                        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
                final int pieceRow = tileFrom.row, pieceCol = tileFrom.col;
                //Calculate possible moves
                for (int[] direction: possibleDirections) {
                	System.out.println("Hướng con mã: " + direction[0] + ", " + direction[1] );
                                int rowTo = pieceRow + direction[0], colTo = pieceCol + direction[1];
                                System.out.println("hàng và cột đang xét: " +rowTo + ", " + colTo);
                                if (rowTo < 0 || rowTo >= 8 || colTo < 0 || colTo >= 8) {
                                        continue;
                                }

                                Tile tileTo = board.tiles[rowTo][colTo];
                                if (tileTo.isOccupied()) {
                                        if (tileTo.getPiece().color != this.color) {
                                                Move newMove = new Move(tileFrom, tileTo);
                                                if (newMove.isInCheckedAfterMove(board) == false) {
                                                        legalMoves.add(newMove);
                                                }
                                        }
                                        continue;
                                }

                                Move newMove = new Move(tileFrom, tileTo);
                                if (newMove.isInCheckedAfterMove(board) == false) {
                                        legalMoves.add(newMove);
                                }
                }
               return legalMoves;
        }
}