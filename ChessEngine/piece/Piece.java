package ChessEngine.piece;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import ChessEngine.ChessColor;
import ChessEngine.board.*;

public abstract class Piece {
        public int row, col; //Coordinates
        public final ChessColor color;
        public BufferedImage image;

        public ArrayList<Move> legalMoves;

        Piece(int row, int col, ChessColor color) {
                this.row = row;
                this.col = col;
                this.color = color;
        }

        public BufferedImage getImage(String imagePath) {
                BufferedImage image = null;

                try {
                        image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return image;
        }

        public abstract ArrayList<Move> calculateLegalMoves(Board board);
}
