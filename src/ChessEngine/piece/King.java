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
                        imagePath = "piece-image/king-w.png";
                } else {
                        imagePath = "piece-image/king-b.png";
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

                //Check if the move let king in checked
                for (Move move : legalMoves) {
                        if (move.isInCheckedAfterMove(board)) {
                                legalMoves.remove(move);
                        }
                }

                return legalMoves;
        }

        public boolean isChecked(Board board) {
                ArrayList<Piece> opponentPieces;
                if (this.color == ChessColor.white) {
                        opponentPieces = board.blackPieces;
                } else {
                        opponentPieces = board.whitePieces;
                }

                for (Piece piece : opponentPieces) {
                        int deltaRow = piece.row - this.row, deltaCol = piece.col - this.col;

                        //Is checked by pawn
                        if (piece instanceof Pawn) {
                                if (deltaCol == 1 || deltaCol == -1) {
                                        if ((this.color == ChessColor.white && deltaRow == 1)
                                                || (this.color == ChessColor.black && deltaRow == -1)) {
                                                        return true;
                                                }
                                }
                        }
                        //Is checked by bishop
                        if (piece instanceof Bishop) {
                                if (deltaRow / deltaCol == 1 || deltaRow / deltaCol == -1) {
                                        //Check if there is a piece between them
                                        int absDelta = (deltaRow > 0) ? deltaRow : (0-deltaRow);
                                        int[] direction = {deltaRow/absDelta, deltaCol/absDelta};

                                        int rowPieceBetween = this.row, colPieceBetween = this.col;
                                        while (true) {
                                                rowPieceBetween += direction[0];
                                                colPieceBetween += direction[1];
                                                if (rowPieceBetween == piece.row) {
                                                        return true;
                                                }

                                                if (board.tiles[rowPieceBetween][colPieceBetween].isOccupied()) {
                                                        break;
                                                }
                                        }
                                }
                        }

                        //Is checked by knight
                        if (piece instanceof Knight) {
                                if ((deltaRow == 2 && deltaCol == 1)
                                        || (deltaRow == 2 && deltaCol == -1)
                                        || (deltaRow == 1 && deltaCol == 2)
                                        || (deltaRow == 1 && deltaCol == -2)
                                        || (deltaRow == -2 && deltaCol == 1)
                                        || (deltaRow == -2 && deltaCol == -1)
                                        || (deltaRow == -1 && deltaCol == 2)
                                        || (deltaRow == -1 && deltaCol == -2)) {
                                                return true;
                                        }
                        }

                        //Is checked by rook
                        if (piece instanceof Rook) {
                                if (deltaRow == 0 || deltaCol == 0) {
                                        int absDelta = deltaRow + deltaCol;
                                        if (absDelta < 0) {
                                                absDelta = 0 - absDelta;
                                        }

                                        int[] direction = {deltaRow/absDelta, deltaCol/absDelta};
                                        int rowPieceBetween = this.row, colPieceBetween = this.col;
                                        while (true) {
                                                rowPieceBetween += direction[0];
                                                colPieceBetween += direction[1];

                                                if (rowPieceBetween == piece.row) {
                                                        return true;
                                                }

                                                if (board.tiles[rowPieceBetween][colPieceBetween].isOccupied()) {
                                                        break;
                                                }
                                        }
                                }
                        }

                        //Is checked by queen
                        if (piece instanceof Queen) {
                                //By straight line
                                        if (deltaRow == 0 || deltaCol == 0) {
                                        int absDelta = deltaRow + deltaCol;
                                        if (absDelta < 0) {
                                                absDelta = 0 - absDelta;
                                        }

                                        int[] direction = {deltaRow/absDelta, deltaCol/absDelta};
                                        int rowPieceBetween = this.row, colPieceBetween = this.col;
                                        while (true) {
                                                rowPieceBetween += direction[0];
                                                colPieceBetween += direction[1];

                                                if (rowPieceBetween == piece.row) {
                                                        return true;
                                                }

                                                if (board.tiles[rowPieceBetween][colPieceBetween].isOccupied()) {
                                                        break;
                                                }
                                        }
                                }

                                //By diagonal
                                if (deltaRow == 0 || deltaCol == 0) {
                                        int absDelta = deltaRow + deltaCol;
                                        if (absDelta < 0) {
                                                absDelta = 0 - absDelta;
                                        }

                                        int[] direction = {deltaRow/absDelta, deltaCol/absDelta};
                                        int rowPieceBetween = this.row, colPieceBetween = this.col;
                                        while (true) {
                                                rowPieceBetween += direction[0];
                                                colPieceBetween += direction[1];

                                                if (rowPieceBetween == piece.row) {
                                                        return true;
                                                }

                                                if (board.tiles[rowPieceBetween][colPieceBetween].isOccupied()) {
                                                        break;
                                                }
                                        }
                                }
                        }
                }
                return false;
        }
}