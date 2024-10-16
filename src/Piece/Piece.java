package Piece;

import java.util.ArrayList;

public abstract class Piece {

    public String name;   // Tên quân cờ
    protected String color;  // Màu sắc (trắng hoặc đen)
    public int positionX; // Tọa độ X
    public int positionY; // Tọa độ Y
    protected boolean isAlive; // Trạng thái sống/chết
    public String path;

    public Piece(String name, String color, int positionX, int positionY, boolean isAlive) {
        this.name = name;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.isAlive = isAlive;
    }

    // Phương thức cập nhật trạng thái sống/chết
    public void Alive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    public void setPosition(int row, int col){
        positionX = row;
        positionY = col;
    }

}
