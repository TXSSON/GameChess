package ChessEngine.board;

import ChessEngine.ChessColor;
import ChessEngine.board.move.*;

public class Gameplay {
        public Board board;
        private Move prevMove;

        public Gameplay(int SQUARE_SIZE, ChessColor side) {
                this.board = new Board(SQUARE_SIZE, side);
        }

        public Move getPrevMove() {
                return prevMove;
        }

        public void movePiece(Move move) {
                move.make(board);
                prevMove = move.clone();
        }
}
