package Main.Pnl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ChessEngine.board.Move;
import ChessEngine.board.Tile;
import ChessEngine.piece.Piece;
import Main.Frame.GameFrame;
import Main.Utils.ColorOption;

public class PnlBoardChess extends JPanel {
	private JPanel[][] squares = new JPanel[8][8]; // Lưu trữ các ô của bàn cờ
	private Color colorselectedTile = Color.decode("#F1F280");

	public PnlBoardChess() {
	}

	public void setPnlBoardChess(ColorOption selectedColor) {
		this.setLayout(new GridLayout(8, 8)); // Bàn cờ 8x8
		this.setPreferredSize(new Dimension(GameFrame.width * 3 / 4, GameFrame.height)); // Chia diện tích hợp lý

		boolean isWhite = true; // Bắt đầu từ ô trắng

		Color color1 = Color.decode(selectedColor.getHexCode().split(",")[0].trim()); // Màu 1
		Color color2 = Color.decode(selectedColor.getHexCode().split(",")[1].trim()); // Màu 2

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				JPanel square = new JPanel();
				if (isWhite) {
					square.setBackground(color1);
				} else {
					square.setBackground(color2);
				}
				this.add(square);
				squares[row][col] = square; // Lưu lại ô hiện tại
				isWhite = !isWhite; // Chuyển đổi màu cho ô tiếp theo
			}
			isWhite = !isWhite; // Chuyển đổi màu khi sang hàng tiếp theo
		}
	}

	public void addPieceToPanel(String imagePath, int row, int col) {
		try {
			BufferedImage originalImage = ImageIO.read(new File(imagePath));
			int sizeIcon = 150;
			Image scaledImage = originalImage.getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);

			ImageIcon icon = new ImageIcon(scaledImage);

			JLabel pieceLabel = new JLabel(icon);
			pieceLabel.setBackground(Color.BLACK);

			squares[row][col].removeAll();

			// Thêm JLabel vào ô
			squares[row][col].add(pieceLabel);
			squares[row][col].revalidate();
			squares[row][col].repaint();
		} catch (IOException e) {
			System.out.println("Lỗi khi đọc ảnh: " + e.getMessage());
		}
	}

	public void deletePieceToPanel(Tile tile) {
		
		System.out.println("Xóa ô cờ tại hàng: " + tile.row + " cột " + tile.col);
	    squares[tile.row][tile.col].removeAll();
	    squares[tile.row][tile.col].revalidate();
	    squares[tile.row][tile.col].repaint();
	}

	public void highlightTiles(List<Move> availableMoves) {
		Boolean selectedTile = true;
		System.out.println("Số nước đi: " + availableMoves.size());
		for (Move move : availableMoves) {
			if (selectedTile) {
				squares[move.tileFrom.row][move.tileFrom.col].setBackground(colorselectedTile);
			}
			squares[move.tileTo.row][move.tileTo.col].setBackground(colorselectedTile);
		}
	}

}