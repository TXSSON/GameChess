package ChessEngine.piece;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.board.move.*;

public class Queen extends Piece {
        public Queen(int row, int col, ChessColor color) {
                super(row, col, color);

                if (color == ChessColor.white) {
                        imagePath = "src/Main/Resources/piece-image2/wq.png";
                } else {
                        imagePath = "src/Main/Resources/piece-image2/bq.png";
                }
        }

        public Queen(Queen piece) {
                super(piece);
                this.imagePath = piece.imagePath;
        }

        @Override public Queen clone() {
                return new Queen(this);
        }
        
        @Override public ArrayList<Move> calculateLegalMoves(Gameplay game) {
                Board board = game.board;
                ArrayList<Move> legalMoves = new ArrayList<>();
                Tile tileFrom = board.tiles[this.row][this.col];
                final int[][] possibleDirections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},
                                                        {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

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
                                                RegularMove newMove = new RegularMove(tileFrom, tileTo);
                                                if (newMove.isInCheckedAfterMove(board) == false) {
                                                        legalMoves.add(newMove);
                                                }
                                        }
                                        break;
                                }

                                RegularMove newMove = new RegularMove(tileFrom, tileTo);
                                if (newMove.isInCheckedAfterMove(board) == false) {
                                        legalMoves.add(newMove);
                                }
                        }
                }
                return legalMoves;
        }
}
