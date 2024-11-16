package ChessEngine.board;

import java.util.*;

import ChessEngine.ChessColor;

public class Gameplay {
        public Board board;
        public Stack<Board> gameStates = new Stack<>();

        //TODO: Implement gameplay
        public Gameplay(int SQUARE_SIZE, ChessColor side) {
                this.board = new Board(SQUARE_SIZE, side);
        }
}
