package ChessEngine.board;

import java.util.*;

import ChessEngine.ChessColor;
import ChessEngine.piece.*;

public class Board {
        public static final int MAX_ROW = 8;
        public static final int MAX_COL = 8;
        public final int SQUARE_SIZE;
        public final int HALF_SQUARE_SIZE;
        public final ChessColor side; //Point of view from white side or black side

        public Tile[][] tiles = new Tile[8][8];
        public ArrayList<Piece> whitePieces = new ArrayList<>();
        public ArrayList<Piece> blackPieces = new ArrayList<>();

        public Board(int SQUARE_SIZE, ChessColor side) {
                this.SQUARE_SIZE = SQUARE_SIZE;
                this.HALF_SQUARE_SIZE = SQUARE_SIZE / 2;
                this.side = side;

                ChessColor color = ChessColor.black;
                for (int row = 0; row < 8; row++) {
                        for (int col = 0; col < 8; col++) {
                                        tiles[row][col] = new Tile(row, col, null, color);
                                        color = ChessColor.opposite(color);
                        }
                        color = ChessColor.opposite(color);
                }

                this.resetPieces();
        }

        public void resetPieces() {
                //White starting position
                for (int col = 0; col < 8; col++) {
                        tiles[1][col].setPiece(new Pawn(1, col, ChessColor.white));
                }
                tiles[0][0].setPiece(new Rook(0, 0, ChessColor.white));
                tiles[0][1].setPiece(new Knight(0, 1, ChessColor.white));
                tiles[0][2].setPiece(new Bishop(0, 2, ChessColor.white));
                tiles[0][3].setPiece(new Queen(0, 3, ChessColor.white));
                tiles[0][4].setPiece(new King(0, 4, ChessColor.white));
                tiles[0][5].setPiece(new Bishop(0, 5, ChessColor.white));
                tiles[0][6].setPiece(new Knight(0, 6, ChessColor.white));
                tiles[0][7].setPiece(new Rook(0, 7, ChessColor.white));

                //Black starting position
                for (int col = 0; col < 8; col++) {
                        tiles[6][col].setPiece(new Pawn(6, col, ChessColor.black));
                }
                tiles[7][0].setPiece(new Rook(7, 0, ChessColor.black));
                tiles[7][1].setPiece(new Knight(7, 1, ChessColor.black));
                tiles[7][2].setPiece(new Bishop(7, 2, ChessColor.black));
                tiles[7][3].setPiece(new King(7, 3, ChessColor.black));
                tiles[7][4].setPiece(new Queen(7, 4, ChessColor.black));
                tiles[7][5].setPiece(new Bishop(7, 5, ChessColor.black));
                tiles[7][6].setPiece(new Knight(7, 6, ChessColor.black));
                tiles[7][7].setPiece(new Rook(7, 7, ChessColor.black));

                //Starting pieces
                for (int col = 0; col < 8; col++) {
                        whitePieces.add(tiles[0][col].getPiece());
                        whitePieces.add(tiles[1][col].getPiece());
                        blackPieces.add(tiles[6][col].getPiece());
                        blackPieces.add(tiles[7][col].getPiece());
                }
        }
}
