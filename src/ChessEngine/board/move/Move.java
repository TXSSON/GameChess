package ChessEngine.board.move;

import ChessEngine.board.*;

public abstract class Move {
        public final Tile tileFrom, tileTo;
        
        public Move(Tile tileFrom, Tile tileTo) {
                this.tileFrom = tileFrom;
                this.tileTo = tileTo;
        }

        public Move(Move move) {
                this.tileFrom = move.tileFrom;
                this.tileTo = move.tileTo;

        }

        public void make(Gameplay game) {
                Board newBoard = new Board(game.board);
                make(newBoard);
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
                }
                if (piece instanceof Rook) {
                        ((Rook)piece).hasMoved = true;
                } 
        }

        public boolean isInCheckedAfterMove(Board board) {
                //Make a clone board and simulate the move
                Board simulationBoard = new Board(board);
                Tile simulationTileFrom = simulationBoard.tiles[tileFrom.row][tileFrom.col];
                ChessColor thisPieceColor = simulationTileFrom.getPiece().color;

                Move simulationMove = new Move(this);
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

        public abstract Move clone();
        public abstract void make(Board board);
        public abstract boolean isInCheckedAfterMove(Board board);
}