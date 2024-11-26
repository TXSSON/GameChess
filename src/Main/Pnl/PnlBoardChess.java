package Main.Pnl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import ChessEngine.ChessColor;
import ChessEngine.board.Tile;
import ChessEngine.board.move.Move;
import ChessEngine.piece.King;
import ChessEngine.piece.Piece;
import ChessEngine.piece.Rook;
import Main.Frame.GameFrame;
import Main.Utils.ColorOption;

public class PnlBoardChess extends JPanel {
	private JPanel[][] squares = new JPanel[8][8]; // Lưu trữ các ô của bàn cờ
	private Color colorselectedTile = Color.decode("#F1F280");
	private List<Move> availableMoves;
	String pathRookImage = "";
	public PnlBoardChess() {
	}

	public void setPnlBoardChess(ColorOption selectedColor) {
		this.setLayout(new GridLayout(8, 8)); // Bàn cờ 8x8
		this.setPreferredSize(new Dimension(GameFrame.width * 3 / 4, GameFrame.height)); // Chia diện tích hợp lý

		boolean isWhite = false; // Bắt đầu từ ô trắng

		Color color1 = Color.decode(selectedColor.getHexCode().split(",")[0].trim()); // Màu 1
		Color color2 = Color.decode(selectedColor.getHexCode().split(",")[1].trim()); // Màu 2

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				PnlTile square = new PnlTile(); // Sử dụng lớp ChessTile

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

	public void updateUIAfterMove(Tile selectedTile, Tile targetTile,Piece selectedPiece) {
		deleteHighlightTiles();
		deletePieceToPanel(selectedTile.row, selectedTile.col);
		addPieceToPanel(selectedPiece.getImagePath(), targetTile.row, targetTile.col);
		if (selectedPiece instanceof King && Math.abs(targetTile.col - selectedTile.col) >= 2) {
			if (selectedPiece.color.equals(ChessColor.white)) {
				pathRookImage = "src/Main/Resources/piece-image2/wr.png";
			} else {
				pathRookImage = "src/Main/Resources/piece-image2/br.png";
			}
			if (targetTile.col == 6) {
				deletePieceToPanel(targetTile.row,7);
				addPieceToPanel(pathRookImage, targetTile.row, 5);
			} else {
				deletePieceToPanel(targetTile.row, 0);
				addPieceToPanel(pathRookImage, targetTile.row, 3);
			}
		}
	}
	
	public void addPieceToPanel(String imagePath, int row, int col) {
		try {
			BufferedImage originalImage = ImageIO.read(new File(imagePath));
			int sizeIcon = GameFrame.height / 8;
			Image scaledImage = originalImage.getScaledInstance(sizeIcon, sizeIcon, Image.SCALE_SMOOTH);

			ImageIcon icon = new ImageIcon(scaledImage);

			JLabel pieceLabel = new JLabel(icon);
			pieceLabel.setBackground(Color.BLACK);

			//TODO
			col = 7 - col;
			squares[row][col].removeAll();

			// Thêm JLabel vào ô
			squares[row][col].add(pieceLabel);
			squares[row][col].revalidate();
			squares[row][col].repaint();
		} catch (IOException e) {
			System.out.println("Lỗi khi đọc ảnh: " + e.getMessage());
		}
	}

	public void deletePieceToPanel(int row, int col) {
		squares[row][7 - col].removeAll();
	}

	public void addHighlightTiles(List<Move> availableMoves) {
		for (Move move : availableMoves) {
			int row = move.tileTo.row;
			int col = move.tileTo.col;
			((PnlTile) squares[row][7 - col]).setHighlight(true); // Thêm highlight
		}
	}
	
	public void deleteHighlightTiles() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				((PnlTile) squares[row][col]).setHighlight(false); // Loại bỏ highlight
			}
		}
	}

}
