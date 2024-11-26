package ChessEngine.board.move;

import java.util.*;
import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.piece.*;

public class RegularMove extends Move { 
        public RegularMove(Tile tileFrom, Tile tileTo) {
                super(tileFrom, tileTo);
        }

        public RegularMove(RegularMove move) {
                super(move);
        }

        @Override
        public RegularMove clone() {
                return new RegularMove(this);
        }

        @Override
        public void make(Board board) {
                Piece piece = tileFrom.getPiece();
                //Remove the piece at tileTo
                if (tileTo.isOccupied()) {
                        if (tileTo.getPiece().color == ChessColor.white) {
                                board.whitePieces.remove(tileTo.getPiece());
                        } else {
                                board.blackPieces.remove(tileTo.getPiece());
                        }
                }

                //Finally move the piece
                tileTo.setPiece(piece);
                tileFrom.clearTile();

                //Change the state of the king and rook if needed
                if (piece instanceof King) {
                        ((King)piece).hasMoved = true;
                }
                if (piece instanceof Rook) {
                        ((Rook)piece).hasMoved = true;
                } 
        }

        @Override
        public boolean isInCheckedAfterMove(Board board) {
                //Make a clone board and simulate the move
                Board simulationBoard = new Board(board);
                Tile simulationTileFrom = simulationBoard.tiles[tileFrom.row][tileFrom.col];
                ChessColor thisPieceColor = simulationTileFrom.getPiece().color;

                RegularMove simulationMove = new RegularMove(simulationTileFrom, simulationBoard.tiles[tileTo.row][tileTo.col]);
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