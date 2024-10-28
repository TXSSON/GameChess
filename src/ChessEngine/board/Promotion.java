package ChessEngine.board;

import ChessEngine.ChessColor;
import ChessEngine.piece.Piece;
import ChessEngine.piece.Queen;

public class Promotion extends Move {
        String piecePromoteTo; //"Queen", "Rook", "Knight", "Bishop"

        Promotion(Tile tileFrom, Tile tileTo, String piecePromoteTo) {
                super(tileFrom, tileTo);
                this.piecePromoteTo = piecePromoteTo;
        }

        @Override
        public void make(Board board) {
                //Capture if tileTo is occupied
                if (tileTo.isOccupied()) {
                        if (tileTo.getPiece().color == ChessColor.white) {
                                board.whitePieces.remove(tileTo.getPiece());
                        } else {
                                board.blackPieces.remove(tileTo.getPiece());
                        }
                }

                //Remove the piece on tileFrom
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
