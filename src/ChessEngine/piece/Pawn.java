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

        private boolean canBeEnPassant(Stack<Board> gameStates) {
                final Board presentBoard = new Board(gameStates.pop());
                final Board previousBoard = new Board(gameStates.pop());

                if (color == ChessColor.white && row == 3) {
                        if (presentBoard.tiles[1][this.col].isOccupied() == false) {
                                if (previousBoard.tiles[1][this.col].isOccupied() && previousBoard.tiles[1][this.col].getPiece() instanceof Pawn) {
                                        return true;
                                }
                        }
                }

                if (color == ChessColor.black && row == 4) {
                        if (presentBoard.tiles[6][this.col].isOccupied() == false) {
                                if (previousBoard.tiles[6][this.col].isOccupied() && previousBoard.tiles[6][this.col].getPiece() instanceof Pawn) {
                                        return true;
                                }
                        }
                }

                gameStates.push(previousBoard);
                gameStates.push(presentBoard);
                return false;
        }

        @Override public ArrayList<Move> calculateLegalMoves(Gameplay game) {
                Board board = game.board;
                Stack<Board> gameStates = game.gameStates;
                ArrayList<Move> legalMoves = new ArrayList<>();
                final Tile tileFrom = board.tiles[this.row][this.col];
                
                if (color == ChessColor.white) {
                        //Move forward and capture normally
                        if (row < 6) {
                                if (board.tiles[row+1][col].isOccupied() == false) {
                                        legalMoves.add(new Move(tileFrom, board.tiles[row+1][col]));
                                }
                                if (col < 7 && board.tiles[row+1][col+1].isOccupied()) {
                                        legalMoves.add(new Move(tileFrom, board.tiles[row+1][col+1]));
                                }
                                if (col > 0 && board.tiles[row+1][col-1].isOccupied()) {
                                        legalMoves.add(new Move(tileFrom, board.tiles[row+1][col-1]));
                                }
                        }

                        //First move can go 2 squares
                        if (row == 1 && board.tiles[row+2][col].isOccupied() == false) {
                                legalMoves.add(new Move(tileFrom, board.tiles[row+2][col]));
                        }

                        //Promotion
                        if (row == 6) {
                                if (board.tiles[row+1][col].isOccupied() == false) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row+1][col]));
                                }
                                if (col < 7 && board.tiles[row+1][col+1].isOccupied()) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row+1][col+1]));
                                }
                                if (col > 0 && board.tiles[row+1][col-1].isOccupied()) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row+1][col-1]));
                                }
                        }

                        //En passant
                        if (row == 4) {
                                if (col < 7 && board.tiles[4][col+1].isOccupied() && board.tiles[4][col+1].getPiece() instanceof Pawn) {
                                        if (((Pawn)board.tiles[4][col+1].getPiece()).canBeEnPassant(gameStates)) {
                                                legalMoves.add(new EnPassantMove(tileFrom, board.tiles[5][col+1]));
                                        }
                                }

                                if (col > 0 && board.tiles[4][col-1].isOccupied() && board.tiles[4][col-1].getPiece() instanceof Pawn) {
                                        if (((Pawn)board.tiles[4][col-1].getPiece()).canBeEnPassant(gameStates)) {
                                                legalMoves.add(new EnPassantMove(tileFrom, board.tiles[5][col-1]));
                                        }
                                }
                        }
                } else { //ChessColor == black
                        //Move forward and capture normally
                        if (row > 1) {
                                if (board.tiles[row-1][col].isOccupied() == false) {
                                        legalMoves.add(new Move(tileFrom, board.tiles[row-1][col]));
                                }
                                if (col < 7 && board.tiles[row-1][col+1].isOccupied()) {
                                        legalMoves.add(new Move(tileFrom, board.tiles[row-1][col+1]));
                                }
                                if (col > 0 && board.tiles[row-1][col-1].isOccupied()) {
                                        legalMoves.add(new Move(tileFrom, board.tiles[row-1][col-1]));
                                }
                        }

                        //First move can go 2 squares
                        if (row == 6 && board.tiles[row-2][col].isOccupied() == false) {
                                legalMoves.add(new Move(tileFrom, board.tiles[row-2][col]));
                        }

                        //Promotion
                        if (row == 1) {
                                if (board.tiles[row-1][col].isOccupied() == false) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row-1][col]));
                                }
                                if (col < 7 && board.tiles[row-1][col+1].isOccupied()) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row-1][col+1]));
                                }
                                if (col > 0 && board.tiles[row-1][col-1].isOccupied()) {
                                        legalMoves.add(new PromotionMove(tileFrom, board.tiles[row-1][col-1]));
                                }
                        }

                        //En passant
                        if (row == 3) {
                                if (col < 7 && board.tiles[3][col+1].isOccupied() && board.tiles[3][col+1].getPiece() instanceof Pawn) {
                                        if (((Pawn)board.tiles[3][col+1].getPiece()).canBeEnPassant(gameStates)) {
                                                legalMoves.add(new EnPassantMove(tileFrom, board.tiles[2][col+1]));
                                        }
                                }

                                if (col > 0 && board.tiles[3][col-1].isOccupied() && board.tiles[3][col-1].getPiece() instanceof Pawn) {
                                        if (((Pawn)board.tiles[3][col-1].getPiece()).canBeEnPassant(gameStates)) {
                                                legalMoves.add(new EnPassantMove(tileFrom, board.tiles[2][col-1]));
                                        }
                                }
                        }
                }

                for (Move move : legalMoves) {
                        if (move.isInCheckedAfterMove(board)) {
                                legalMoves.remove(move);
                        }
                }

               return legalMoves;
        }
}
