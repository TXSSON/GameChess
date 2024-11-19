package ChessEngine.board.move;

import ChessEngine.board.*;
import ChessEngine.piece.*;

public class CastlingMove extends Move {
        public CastlingMove(Tile tileFrom, Tile tileTo) {
                super(tileFrom, tileTo);
        }

        @Override
        protected void make(Board board) {
                Piece piece = tileFrom.getPiece();
                int row = piece.row;

                //Castling king-side
                if (tileTo.col == 6) {
                        Piece castlingRook = board.tiles[row][7].getPiece();
                        if (castlingRook == null) {
                                System.out.println("castlingRook null");
                        }

                        board.tiles[row][5].setPiece(castlingRook);
                        if (board.tiles[row][5].isOccupied() == false) {
                                System.out.println("Rook move fail");
                        }
                        board.tiles[row][7].clearTile();
                        ((Rook)castlingRook).hasMoved = true;
                }

                //Castling queen-side
                if (tileTo.col == 2) {
                        Piece castlingRook = board.tiles[row][0].getPiece();

                        board.tiles[row][3].setPiece(castlingRook);
                        board.tiles[row][0].clearTile();
                        ((Rook)castlingRook).hasMoved = true;
                }

                //Finally move the piece
                tileTo.setPiece(piece);
                tileFrom.clearTile();

                //Change the king's state
                ((King)piece).hasMoved = true;
        }
}
