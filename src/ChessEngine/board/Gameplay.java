package ChessEngine.board;

import java.util.*;

import ChessEngine.ChessColor;

public class Gameplay {
        public static Board board;
        public static ArrayList<Board> gameStates = new ArrayList<>();

        //TODO: Implement gameplay
        public Gameplay(int SQUARE_SIZE, ChessColor side) {
                board = new Board(SQUARE_SIZE, side);
        }
}
