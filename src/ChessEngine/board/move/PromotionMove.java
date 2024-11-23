package ChessEngine.board.move;

import java.util.ArrayList;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.piece.*;

public class PromotionMove extends Move {
        private String piecePromoteTo; //"Queen", "Rook", "Knight", "Bishop"

        public PromotionMove(Tile tileFrom, Tile tileTo) {
                super(tileFrom, tileTo);
                this.piecePromoteTo = "Queen"; //Default auto queen
        }

        public PromotionMove(PromotionMove move) {
                super(move);
                this.piecePromoteTo = move.piecePromoteTo;
        }

        public void setPieceToPromote(String piece) {
                piecePromoteTo = piece;
        }

        @Override
        public void make(Gameplay game) {
                Board newBoard = new Board(game.board);
                make(newBoard);
                game.gameStates.push(newBoard);
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
        }

        @Override
        public boolean isInCheckedAfterMove(Board board) {
                //Make a clone board and simulate the move
                Board simulationBoard = new Board(board);
                Tile simulationTileFrom = simulationBoard.tiles[tileFrom.row][tileFrom.col];
                ChessColor thisPieceColor = simulationTileFrom.getPiece().color;

                PromotionMove simulationMove = new PromotionMove(this);
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
