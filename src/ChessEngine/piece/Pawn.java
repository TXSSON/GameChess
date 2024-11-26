package ChessEngine.piece;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.board.*;
import ChessEngine.board.move.*;

public class Pawn extends Piece {
        public Pawn(int row, int col, ChessColor color) {
                super(row, col, color);

                if (color == ChessColor.white) {
                        imagePath = "src/Main/Resources/piece-image2/wp.png";
                } else {
                        imagePath = "src/Main/Resources/piece-image2/bp.png";
                }
        }

        public Pawn(Pawn piece) {
                super(piece);
                this.imagePath = piece.imagePath;
        }

        @Override public Pawn clone() {
                return new Pawn(this);
        }

        private boolean canBeEnPassant(Move prevMove) {
                Piece pieceMoved = prevMove.tileTo.getPiece();

                if (pieceMoved.equals(this)) {
                        if (color == ChessColor.white && prevMove.tileFrom.row == 1 && prevMove.tileTo.row == 3) {
                                return true;
                        } else if (color == ChessColor.black && prevMove.tileFrom.row == 6 && prevMove.tileTo.row == 4) {
                                return true;
                        }
                }

                return false;
        }

        @Override public ArrayList<Move> calculateLegalMoves(Gameplay game) {
                Board board = game.board;
                Move prevMove = game.getPrevMove();
                ArrayList<Move> legalMoves = new ArrayList<>();
                final Tile tileFrom = board.tiles[this.row][this.col];
                
                if (color == ChessColor.white) {
                        //Move forward and capture and promotion
                        if (board.tiles[row+1][col].isOccupied() == false) {
                                if (row == 6) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row+1][col]));
                                } else {
                                        legalMoves.add(new RegularMove(tileFrom, board.tiles[row+1][col]));
                                }
                        }

                        if (col < 7 && board.tiles[row+1][col+1].isOccupied() && board.tiles[row+1][col+1].getPiece().color == ChessColor.black) {
                                if (row == 6) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row+1][col+1]));
                                } else {
                                        legalMoves.add(new RegularMove(tileFrom, board.tiles[row+1][col+1]));
                                }
                        }

                        if (col > 0 && board.tiles[row+1][col-1].isOccupied() && board.tiles[row+1][col-1].getPiece().color == ChessColor.black) {
                                if (row == 6) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row+1][col-1]));
                                } else {
                                        legalMoves.add(new RegularMove(tileFrom, board.tiles[row+1][col-1]));
                                }
                        }

                        //First move can go 2 squares
                        if (row == 1 && board.tiles[row+1][col].isOccupied() == false && board.tiles[row+2][col].isOccupied() == false) {
                                legalMoves.add(new RegularMove(tileFrom, board.tiles[row+2][col]));
                        }

                        //En passant
                        if (row == 4) {
                                if (col < 7 && board.tiles[4][col+1].isOccupied() && board.tiles[4][col+1].getPiece() instanceof Pawn) {
                                        if (((Pawn)board.tiles[4][col+1].getPiece()).canBeEnPassant(prevMove)) {
                                                legalMoves.add(new EnPassantMove(tileFrom, board.tiles[5][col+1]));
                                        }
                                }

                                if (col > 0 && board.tiles[4][col-1].isOccupied() && board.tiles[4][col-1].getPiece() instanceof Pawn) {
                                        if (((Pawn)board.tiles[4][col-1].getPiece()).canBeEnPassant(prevMove)) {
                                                legalMoves.add(new EnPassantMove(tileFrom, board.tiles[5][col-1]));
                                        }
                                }
                        }
                } else { //ChessColor == black
                        //Move forward and capture and promotion
                        if (board.tiles[row-1][col].isOccupied() == false) {
                                if (row == 1) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row-1][col]));
                                } else {
                                        legalMoves.add(new RegularMove(tileFrom, board.tiles[row-1][col]));
                                }
                        }

                        if (col < 7 && board.tiles[row-1][col+1].isOccupied() && board.tiles[row-1][col+1].getPiece().color == ChessColor.white) {
                                if (row == 1) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row-1][col+1]));
                                } else {
                                        legalMoves.add(new RegularMove(tileFrom, board.tiles[row-1][col+1]));
                                }
                        }

                        if (col > 0 && board.tiles[row-1][col-1].isOccupied() && board.tiles[row-1][col-1].getPiece().color == ChessColor.white) {
                                if (row == 1) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row-1][col-1]));
                                } else {
                                        legalMoves.add(new RegularMove(tileFrom, board.tiles[row-1][col-1]));
                                }
                        }

                        //First move can go 2 squares
                        if (row == 6 && board.tiles[row-1][col].isOccupied() == false && board.tiles[row-2][col].isOccupied() == false) {
                                legalMoves.add(new RegularMove(tileFrom, board.tiles[row-2][col]));
                        }

                        //En passant
                        if (row == 3) {
                                if (col < 7 && board.tiles[3][col+1].isOccupied() && board.tiles[3][col+1].getPiece() instanceof Pawn) {
                                        if (((Pawn)board.tiles[3][col+1].getPiece()).canBeEnPassant(prevMove)) {
                                                legalMoves.add(new EnPassantMove(tileFrom, board.tiles[2][col+1]));
                                        }
                                }

                                if (col > 0 && board.tiles[3][col-1].isOccupied() && board.tiles[3][col-1].getPiece() instanceof Pawn) {
                                        if (((Pawn)board.tiles[3][col-1].getPiece()).canBeEnPassant(prevMove)) {
                                                legalMoves.add(new EnPassantMove(tileFrom, board.tiles[2][col-1]));
                                        }
                                }
                        }
                }

                legalMoves.removeIf(move -> move.isInCheckedAfterMove(board));

               return legalMoves;
        }
}
