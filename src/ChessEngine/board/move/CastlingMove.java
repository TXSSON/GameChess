package ChessEngine.board.move;

import java.util.ArrayList;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.piece.*;

public class CastlingMove extends Move {
        public CastlingMove(Tile tileFrom, Tile tileTo) {
                super(tileFrom, tileTo);
        }

        public CastlingMove(CastlingMove move) {
                super(move);
        }

        @Override
        public void make(Gameplay game) {
                Board newBoard = new Board(game.board);
                make(newBoard);
                game.gameStates.push(newBoard);
        }

        @Override
        public CastlingMove clone() {
                return new CastlingMove(this);
        }

        @Override
        public void make(Board board) {
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

        @Override
        public boolean isInCheckedAfterMove(Board board) {
                //Make a clone board and simulate the move
                Board simulationBoard = new Board(board);
                Tile simulationTileFrom = simulationBoard.tiles[tileFrom.row][tileFrom.col];
                ChessColor thisPieceColor = simulationTileFrom.getPiece().color;

                CastlingMove simulationMove = new CastlingMove(this);
                simulationMove.make(simulationBoard);

                //Find the king in the simulation board
                ArrayList<Piece> friendlyPieces;
                if (thisPieceColor== ChessColor.white) {
                        friendlyPieces = simulationBoard.whitePieces;
                } else {
                        friendlyPieces = simulationBoard.blackPieces;
                }
                for (Piece piece : friendlyPieces) {
                        if (piece instanceof King) {
                                if (((King)piece).isChecked(simulationBoard)) {
                                        return true;
                                }
                        }
                }

                return false;
        }
}
