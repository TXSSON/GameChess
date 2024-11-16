package ChessEngine.board.move;

import ChessEngine.board.*;

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
}
