package ChessEngine.board.move;

import ChessEngine.board.*;
import ChessEngine.piece.*;

public class CastlingMove extends Move {
        public CastlingMove(Tile tileFrom, Tile tileTo) {
                super(tileFrom, tileTo);
        }

        @Override public void make(Gameplay game) {
                Board newBoard = new Board(game.board);
                this.make(newBoard);
                game.gameStates.push(newBoard);
        }

        @Override
        protected void make(Board board) {
                Piece piece = tileFrom.getPiece();
                int row = piece.row;

                //Castling king-side
                if (tileTo.col == 6) {
                        Tile tiles[][] = board.tiles;
                        Piece castlingRook = tiles[row][7].getPiece();

                        tiles[row][5].setPiece(castlingRook);
                        tiles[row][7].clearTile();
                        ((Rook)castlingRook).hasMoved = true;
                }

                //Castling queen-side
                if (tileTo.col == 2) {
                        Tile tiles[][] = board.tiles;
                        Piece castlingRook = tiles[row][0].getPiece();

                        tiles[row][3].setPiece(castlingRook);
                        tiles[row][0].clearTile();
                        ((Rook)castlingRook).hasMoved = true;
                }

                //Finally move the piece
                tileTo.setPiece(piece);
                tileFrom.clearTile();

                //Change the king's state
                ((King)piece).hasMoved = true;
        }
}
