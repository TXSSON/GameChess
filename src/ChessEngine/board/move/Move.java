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

        public abstract Move clone();
        public abstract void make(Board board);
        public abstract boolean isInCheckedAfterMove(Board board);
}