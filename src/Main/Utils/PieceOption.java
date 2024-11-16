package Main.Utils;

import ChessEngine.ChessColor;

public class PieceOption {
    private String name;
    private ChessColor color;

    public PieceOption(String name, ChessColor color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return name; // Chỉ trả về tên để hiển thị trong JComboBox
    }

    public ChessColor getColor() {
        return color;
    }

	public String getName() {
		return name;
	} 
    
}


