package ChessEngine.board;

import java.util.*;
import ChessEngine.ChessColor;
import ChessEngine.piece.*;

public class Move {
        public final Tile tileFrom, tileTo;

        public Move(Tile tileFrom, Tile tileTo) {
                this.tileFrom = tileFrom;
                this.tileTo = tileTo;
        }

        public void make(Board board) {
                Piece piece = tileFrom.getPiece();
                //Capture if tileTo is occupied
                if (tileTo.isOccupied()) {
                        if (tileTo.getPiece().color == ChessColor.white) {
                                board.whitePieces.remove(tileTo.getPiece());
                        } else {
                                board.blackPieces.remove(tileTo.getPiece());
                        }
                }

                //DONE: Castling
                if (piece instanceof King && tileTo.row == piece.row) {
                        //Castling king-side
                        if (tileTo.col == 6) {
                                Tile tiles[][] = board.tiles;
                                Piece castlingRook = tiles[piece.row][7].getPiece();

                                castlingRook.col = 5;
                                tiles[castlingRook.row][castlingRook.col].setPiece(castlingRook);
                                tiles[piece.row][7].clearTile();
                        }

                        //Castling queen-side
                        if (tileTo.col == 2) {
                                Tile tiles[][] = board.tiles;
                                Piece castlingRook = tiles[piece.row][0].getPiece();

                                castlingRook.col = 3;
                                tiles[castlingRook.row][castlingRook.col].setPiece(castlingRook);
                                tiles[piece.row][0].clearTile();
                        }
                }

                //TODO: En passant

                //Finally move the piece
                piece.row = tileTo.row;
                piece.col = tileFrom.col;
                tileTo.setPiece(piece);
                tileFrom.clearTile();
                if (tileFrom.getPiece().color == ChessColor.white) {
                        board.whitePieces.remove(tileFrom.getPiece());
                } else {
                        board.blackPieces.remove(tileFrom.getPiece());
                }

                //Change the state of king, rook if needed
                if (piece instanceof King) {
                        ((King)piece).hasMoved = true;
                } else if (piece instanceof Rook) {
                        ((Rook)piece).hasMoved = true;
                } 
        }

        public boolean isInCheckedAfterMove(Board board) {
                //Check if the move lets king in checked
                Piece thisPiece = tileFrom.getPiece();
                try {
                        //Make a clone board and simulate the move
                        Board simulationBoard = (Board) board.clone();
                        this.make(simulationBoard);

                        //Find the king in the simulation board
                        King kingPiece = new King(0, 0, thisPiece.color); //Dummy king piece
                        ArrayList<Piece> friendlyPieces;
                        if (thisPiece.color == ChessColor.white) {
                                friendlyPieces = board.whitePieces;
                        } else {
                                friendlyPieces = board.blackPieces;
                        }
                        for (Piece piece : friendlyPieces) {
                                if (piece instanceof King) {
                                        kingPiece = (King)piece;
                                }
                        }

                        //Check if the king is in checked
                        if (kingPiece.isChecked(simulationBoard)) {
                                return true;
                        }
                } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                }
                return false;
        }

}