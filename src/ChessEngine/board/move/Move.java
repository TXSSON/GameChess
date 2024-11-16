package ChessEngine.board.move;

import java.util.*;
import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.piece.*;

public class Move {
        public final Tile tileFrom, tileTo;

        public Move(Tile tileFrom, Tile tileTo) {
                this.tileFrom = tileFrom;
                this.tileTo = tileTo;
        }

        public void make(Gameplay game) {
                Board newBoard = new Board(game.board);
                this.make(newBoard);
                game.gameStates.push(newBoard);
        }

        protected void make(Board board) {
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
                } else if (piece instanceof Rook) {
                        ((Rook)piece).hasMoved = true;
                } 
        }

        public boolean isInCheckedAfterMove(Board board) {
                //Check if the move lets king in checked
                Piece thisPiece = tileFrom.getPiece();
                //Make a clone board and simulate the move
                Board simulationBoard = new Board(board);
                this.make(simulationBoard);
                //Find the king in the simulation board
                ArrayList<Piece> friendlyPieces;
                if (thisPiece.color == ChessColor.white) {
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