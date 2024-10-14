package ChessEngine.board;

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
                //Capture
                if (tileTo.isOccupied()) {
                        if (tileTo.getPiece().color == ChessColor.white) {
                                board.whitePieces.remove(tileTo.getPiece());
                        } else {
                                board.blackPieces.remove(tileTo.getPiece());
                        }
                }
                //En passant
                //Castle
                //Finally move the piece
                tileTo.setPiece(piece);
                tileFrom.clearTile();
        }
}
