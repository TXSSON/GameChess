package ChessEngine.board.move;

import java.util.ArrayList;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.piece.*;

public class PromotionMove extends Move {
        private  String piecePromoteTo; //"Queen", "Rook", "Knight", "Bishop"

        public PromotionMove(Tile tileFrom, Tile tileTo) {
                super(tileFrom, tileTo);
                this.piecePromoteTo = "Queen"; //Default auto queen
        }

        public PromotionMove(PromotionMove move) {
                super(move);
                this.piecePromoteTo = move.piecePromoteTo;
        }

        @Override
        public PromotionMove clone() {
                return new PromotionMove(this);
        }

        public void setPieceToPromote(String piece) {
                piecePromoteTo = piece;
        }

        @Override
        public void make(Board board) {
                //Remove the piece on tileTo
                if (tileTo.isOccupied()) {
                        if (tileTo.getPiece().color == ChessColor.white) {
                                board.whitePieces.remove(tileTo.getPiece());
                        } else {
                                board.blackPieces.remove(tileTo.getPiece());
                        }
                }

                //Put the new piece on tileTo
                Piece newPiece;
                switch (piecePromoteTo) {
                        case "Rook":
                                newPiece = new Rook(tileTo.row, tileTo.col, tileFrom.getPiece().color);
                                break;
                        case "Knight":
                                newPiece = new Knight(tileTo.row, tileTo.col, tileFrom.getPiece().color);
                                break;
                        case "Bishop":
                                newPiece = new Bishop(tileTo.row, tileTo.col, tileFrom.getPiece().color);
                                break;
                        default: //Auto-queen
                                newPiece = new Queen(tileTo.row, tileTo.col, tileFrom.getPiece().color);
                                break;
                }

                tileTo.setPiece(newPiece);
                if (newPiece.color == ChessColor.white) {
                        board.whitePieces.add(newPiece);
                } else {
                        board.blackPieces.add(newPiece);
                }

                //Remove the pawn on tileFrom
                if (tileFrom.getPiece().color == ChessColor.white) {
                        board.whitePieces.remove(tileFrom.getPiece());
                } else {
                        board.blackPieces.remove(tileFrom.getPiece());
                }
                tileFrom.clearTile();
        }

        @Override
        public boolean isInCheckedAfterMove(Board board) {
                //Make a clone board and simulate the move
                Board simulationBoard = new Board(board);
                Tile simulationTileFrom = simulationBoard.tiles[tileFrom.row][tileFrom.col];
                ChessColor thisPieceColor = simulationTileFrom.getPiece().color;

                PromotionMove simulationMove = new PromotionMove(simulationTileFrom, simulationBoard.tiles[tileTo.row][tileTo.col]);
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
