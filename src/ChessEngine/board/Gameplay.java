package ChessEngine.board;

import java.util.*;

import ChessEngine.ChessColor;

public class Gameplay {
        Board board;
        Stack<Board> gameStates = new Stack<>();

        //TODO: Implement gameplay
        public Gameplay(int SQUARE_SIZE, ChessColor side) {
                board = new Board(SQUARE_SIZE, side);
        }
}
