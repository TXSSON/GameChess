/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Piece;

/**
 *
 * @author SON
 */
public class Pawn extends Piece{
     public Pawn(String name, String color, int positionX, int positionY, boolean isAlive) {
        super(name, color, positionX, positionY, isAlive);
        if (this.color.equals("WHITE")) {
            this.path = "/resources/WhitePiece/pawn.png";
        } else {
            this.path = "/resources/BlackPiece/pawn.png";
        }
    }
}
