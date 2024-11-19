package ChessEngine.board.move;

import java.util.ArrayList;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.piece.King;
import ChessEngine.piece.Piece;

public class EnPassantMove extends Move{
        public EnPassantMove(Tile tileFrom, Tile tileTo) {
                super(tileFrom, tileTo);
        }

        @Override
        protected void make(Board board) {
                //White pawn
                if (tileFrom.row == 4) {
                        board.blackPieces.remove(board.tiles[4][tileTo.col].getPiece());
                }

                //Black pawn
                if (tileFrom.row == 3) {
                        board.whitePieces.remove(board.tiles[3][tileTo.col].getPiece());
                }

                tileTo.setPiece(tileFrom.getPiece());
                tileFrom.clearTile();
        }

        @Override
        public boolean isInCheckedAfterMove(Board board) {
                //Make a clone board and simulate the move
                Board simulationBoard = new Board(board);
                Tile simulationTileFrom = simulationBoard.tiles[tileFrom.row][tileFrom.col];
                ChessColor thisPieceColor = simulationTileFrom.getPiece().color;

                EnPassantMove simulationMove = new EnPassantMove(simulationTileFrom, simulationBoard.tiles[tileTo.row][tileTo.col]);
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
