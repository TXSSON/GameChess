package ChessEngine.board.move;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.piece.*;

public class PromotionMove extends Move {
        private String piecePromoteTo; //"Queen", "Rook", "Knight", "Bishop"

        public PromotionMove(Tile tileFrom, Tile tileTo) {
                super(tileFrom, tileTo);
                this.piecePromoteTo = "Queen"; //Default auto queen
        }

        public void setPieceToPromote(String piece) {
                piecePromoteTo = piece;
        }

        @Override
        protected void make(Board board) {
                //Remove the piece on tileTo
                if (tileTo.isOccupied()) {
                        if (tileTo.getPiece().color == ChessColor.white) {
                                board.whitePieces.remove(tileTo.getPiece());
                        } else {
                                board.blackPieces.remove(tileTo.getPiece());
                        }
                }

                //Remove the pawn on tileFrom
                tileFrom.clearTile();
                if (tileFrom.getPiece().color == ChessColor.white) {
                        board.whitePieces.remove(tileFrom.getPiece());
                } else {
                        board.blackPieces.remove(tileFrom.getPiece());
                }

                //Put the new piece on tileTo
                Piece newPiece;
                switch (piecePromoteTo) {
                        case "Rook":
                                newPiece = new Queen(tileTo.row, tileTo.col, tileFrom.getPiece().color);
                                break;
                        case "Knight":
                                newPiece = new Queen(tileTo.row, tileTo.col, tileFrom.getPiece().color);
                                break;
                        case "Bishop":
                                newPiece = new Queen(tileTo.row, tileTo.col, tileFrom.getPiece().color);
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
        }
}
