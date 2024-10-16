/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Piece;

/**
 *
 * @author SON
 */
public class Queen extends Piece{
    
     public Queen(String name, String color, int positionX, int positionY, boolean isAlive) {
        super(name, color, positionX, positionY, isAlive);
        if (this.color.equals("WHITE")) {
            this.path = "/resources/WhitePiece/queen.png";
        } else {
            this.path = "/resources/BlackPiece/queen.png";
        }
    }
}
