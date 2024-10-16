package gamechess;

import java.awt.Color;
import static java.awt.Color.decode;
import java.awt.Dimension;
import javax.swing.JPanel;
import Piece.*;
import com.sun.source.tree.ContinueTree;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller extends JPanel implements Runnable, MouseListener {
    
    private MouseState mouseState = MouseState.NONE; // Trạng thái chuột
    private int width = 800, height = 800;
    private static int getRow, getCol;
    private static int getRowBeforeMove, getColBeforeMove;
//    private static int getRowAfterMove, getColAfterMove;
    private static Piece getPiece;
    private static int countClick = 0;
    
    

    private Squares squares;
    Thread gameThread;

    King whiteKing;
    King blackKing;
    Queen whiteQueen;
    Queen blackQueen;
    Bishop whiteBishop;
    Bishop blackBishop;
    Knight whiteKnight;
    Knight blackKnight;
    Rook whiteRook;
    Rook blackRook;
    Pawn whitePawn1;
    Pawn whitePawn2;
    Pawn whitePawn3;
    Pawn whitePawn4;
    Pawn whitePawn5;
    Pawn whitePawn6;
    Pawn whitePawn7;
    Pawn whitePawn8;

    Pawn blackPawn1;
    Pawn blackPawn2;
    Pawn blackPawn3;
    Pawn blackPawn4;
    Pawn blackPawn5;
    Pawn blackPawn6;
    Pawn blackPawn7;
    Pawn blackPawn8;

    public Controller(String playerName) {
        this.setPreferredSize(new Dimension(width, height)); // khởi tạo kích thước ô cờ
        this.setLayout(new BorderLayout());
        squares = new Squares(8, 8);
        squares.addMouseListener(this); // Thêm MouseListener vào squares
        this.add(squares, BorderLayout.CENTER);
        newBoard(playerName);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBoard(String playerName) {
        drawBoard();
        initPiece(playerName);
    }

    public void drawBoard() {
        String color1Hex = "#FEE3C8";
        String color2Hex = "#DB9566";
        Color color1 = decode(color1Hex);
        Color color2 = decode(color2Hex);
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((i + j) % 2 == 0) {
                    squares.setColor(i, j, color1);
                } else {
                    squares.setColor(i, j, color2);
                }
            }
        }
    }

    public void initPiece(String playerName) {
        if (playerName.equals("player1")) {
            // Adding white pieces
            whiteKing = new King("king", "WHITE", 7, 4, true);
            squares.addPiece(whiteKing);
            whiteQueen = new Queen("queen", "WHITE", 7, 3, true);
            squares.addPiece(whiteQueen);

            whiteBishop = new Bishop("bishop", "WHITE", 7, 2, true);
            squares.addPiece(whiteBishop);
            whiteBishop = new Bishop("bishop", "WHITE", 7, 5, true);
            squares.addPiece(whiteBishop);

            whiteKnight = new Knight("knight", "WHITE", 7, 1, true);
            squares.addPiece(whiteKnight);
            whiteKnight = new Knight("knight", "WHITE", 7, 6, true);
            squares.addPiece(whiteKnight);

            whiteRook = new Rook("rook", "WHITE", 7, 0, true);
            squares.addPiece(whiteRook);
            whiteRook = new Rook("rook", "WHITE", 7, 7, true);
            squares.addPiece(whiteRook);

            // Adding white pawns
            whitePawn1 = new Pawn("pawn", "WHITE", 6, 0, true);
            squares.addPiece(whitePawn1);
            whitePawn2 = new Pawn("pawn", "WHITE", 6, 1, true);
            squares.addPiece(whitePawn2);
            whitePawn3 = new Pawn("pawn", "WHITE", 6, 2, true);
            squares.addPiece(whitePawn3);
            whitePawn4 = new Pawn("pawn", "WHITE", 6, 3, true);
            squares.addPiece(whitePawn4);
            whitePawn5 = new Pawn("pawn", "WHITE", 6, 4, true);
            squares.addPiece(whitePawn5);
            whitePawn6 = new Pawn("pawn", "WHITE", 6, 5, true);
            squares.addPiece(whitePawn6);
            whitePawn7 = new Pawn("pawn", "WHITE", 6, 6, true);
            squares.addPiece(whitePawn7);
            whitePawn8 = new Pawn("pawn", "WHITE", 6, 7, true);
            squares.addPiece(whitePawn8);

            // Adding black pieces
            blackKing = new King("king", "BLACK", 0, 4, true);
            squares.addPiece(blackKing);
            blackQueen = new Queen("queen", "BLACK", 0, 3, true);
            squares.addPiece(blackQueen);

            blackBishop = new Bishop("bishop", "BLACK", 0, 2, true);
            squares.addPiece(blackBishop);
            blackBishop = new Bishop("bishop", "BLACK", 0, 5, true);
            squares.addPiece(blackBishop);

            blackKnight = new Knight("knight", "BLACK", 0, 1, true);
            squares.addPiece(blackKnight);
            blackKnight = new Knight("knight", "BLACK", 0, 6, true);
            squares.addPiece(blackKnight);

            blackRook = new Rook("rook", "BLACK", 0, 0, true);
            squares.addPiece(blackRook);
            blackRook = new Rook("rook", "BLACK", 0, 7, true);
            squares.addPiece(blackRook);

            // Adding black pawns
            blackPawn1 = new Pawn("pawn", "BLACK", 1, 0, true);
            squares.addPiece(blackPawn1);
            blackPawn2 = new Pawn("pawn", "BLACK", 1, 1, true);
            squares.addPiece(blackPawn2);
            blackPawn3 = new Pawn("pawn", "BLACK", 1, 2, true);
            squares.addPiece(blackPawn3);
            blackPawn4 = new Pawn("pawn", "BLACK", 1, 3, true);
            squares.addPiece(blackPawn4);
            blackPawn5 = new Pawn("pawn", "BLACK", 1, 4, true);
            squares.addPiece(blackPawn5);
            blackPawn6 = new Pawn("pawn", "BLACK", 1, 5, true);
            squares.addPiece(blackPawn6);
            blackPawn7 = new Pawn("pawn", "BLACK", 1, 6, true);
            squares.addPiece(blackPawn7);
            blackPawn8 = new Pawn("pawn", "BLACK", 1, 7, true);
            squares.addPiece(blackPawn8);
        } else if (playerName.equals("player2")) {
            // Reverse setup for player2
            // Adding black pieces (bottom of the board)
            blackKing = new King("king", "BLACK", 7, 4, true);
            squares.addPiece(blackKing);
            blackQueen = new Queen("queen", "BLACK", 7, 3, true);
            squares.addPiece(blackQueen);

            blackBishop = new Bishop("bishop", "BLACK", 7, 2, true);
            squares.addPiece(blackBishop);
            blackBishop = new Bishop("bishop", "BLACK", 7, 5, true);
            squares.addPiece(blackBishop);

            blackKnight = new Knight("knight", "BLACK", 7, 1, true);
            squares.addPiece(blackKnight);
            blackKnight = new Knight("knight", "BLACK", 7, 6, true);
            squares.addPiece(blackKnight);

            blackRook = new Rook("rook", "BLACK", 7, 0, true);
            squares.addPiece(blackRook);
            blackRook = new Rook("rook", "BLACK", 7, 7, true);
            squares.addPiece(blackRook);

            // Adding black pawns (6th row)
            blackPawn1 = new Pawn("pawn", "BLACK", 6, 0, true);
            squares.addPiece(blackPawn1);
            blackPawn2 = new Pawn("pawn", "BLACK", 6, 1, true);
            squares.addPiece(blackPawn2);
            blackPawn3 = new Pawn("pawn", "BLACK", 6, 2, true);
            squares.addPiece(blackPawn3);
            blackPawn4 = new Pawn("pawn", "BLACK", 6, 3, true);
            squares.addPiece(blackPawn4);
            blackPawn5 = new Pawn("pawn", "BLACK", 6, 4, true);
            squares.addPiece(blackPawn5);
            blackPawn6 = new Pawn("pawn", "BLACK", 6, 5, true);
            squares.addPiece(blackPawn6);
            blackPawn7 = new Pawn("pawn", "BLACK", 6, 6, true);
            squares.addPiece(blackPawn7);
            blackPawn8 = new Pawn("pawn", "BLACK", 6, 7, true);
            squares.addPiece(blackPawn8);

            // Adding white pieces (top of the board)
            whiteKing = new King("king", "WHITE", 0, 4, true);
            squares.addPiece(whiteKing);
            whiteQueen = new Queen("queen", "WHITE", 0, 3, true);
            squares.addPiece(whiteQueen);

            whiteBishop = new Bishop("bishop", "WHITE", 0, 2, true);
            squares.addPiece(whiteBishop);
            whiteBishop = new Bishop("bishop", "WHITE", 0, 5, true);
            squares.addPiece(whiteBishop);

            whiteKnight = new Knight("knight", "WHITE", 0, 1, true);
            squares.addPiece(whiteKnight);
            whiteKnight = new Knight("knight", "WHITE", 0, 6, true);
            squares.addPiece(whiteKnight);

            whiteRook = new Rook("rook", "WHITE", 0, 0, true);
            squares.addPiece(whiteRook);
            whiteRook = new Rook("rook", "WHITE", 0, 7, true);
            squares.addPiece(whiteRook);

            // Adding white pawns (1st row)
            whitePawn1 = new Pawn("pawn", "WHITE", 1, 0, true);
            squares.addPiece(whitePawn1);
            whitePawn2 = new Pawn("pawn", "WHITE", 1, 1, true);
            squares.addPiece(whitePawn2);
            whitePawn3 = new Pawn("pawn", "WHITE", 1, 2, true);
            squares.addPiece(whitePawn3);
            whitePawn4 = new Pawn("pawn", "WHITE", 1, 3, true);
            squares.addPiece(whitePawn4);
            whitePawn5 = new Pawn("pawn", "WHITE", 1, 4, true);
            squares.addPiece(whitePawn5);
            whitePawn6 = new Pawn("pawn", "WHITE", 1, 5, true);
            squares.addPiece(whitePawn6);
            whitePawn7 = new Pawn("pawn", "WHITE", 1, 6, true);
            squares.addPiece(whitePawn7);
            whitePawn8 = new Pawn("pawn", "WHITE", 1, 7, true);
            squares.addPiece(whitePawn8);
        }
    }

    @Override
    public void run() {
        System.out.println("Your program is running...");

    }

@Override
    public void mouseClicked(MouseEvent e) {

        int cellWidth = width / 8;  // Kích thước mỗi ô theo chiều rộng
        int cellHeight = height / 8; // Kích thước mỗi ô theo chiều cao

        // Lấy tọa độ pixel từ sự kiện click chuột
        int x = e.getX();  // Lấy tọa độ x của chuột
        int y = e.getY();  // Lấy tọa độ y của chuột

        // Tính toán hàng và cột tương ứng với tọa độ chuột
        getRow = y / cellHeight;
        getCol = x / cellWidth;

        System.out.println(countClick);

        if (squares.status[getRow][getCol] && countClick != 1) {
            getPiece = squares.arrpiece[getRow][getCol];
            getRowBeforeMove = getRow;
            getColBeforeMove = getCol;
            countClick += 1;
            System.out.println(getRowBeforeMove + " " + getColBeforeMove);
            System.out.println(countClick);
        } else if (countClick == 1) {
            getPiece.setPosition(getRow, getCol);
            squares.addPiece(getPiece);
            squares.arrpiece[getRowBeforeMove][getColBeforeMove] = null;
            squares.arr[getRowBeforeMove][getColBeforeMove].removeAll(); 
            squares.status[getRowBeforeMove][getColBeforeMove] = false;
            squares.arr[getRowBeforeMove][getColBeforeMove].removeAll(); 
            countClick = 0;
            
            System.out.println(getRowBeforeMove + " " + getColBeforeMove);

            System.out.println(countClick);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
