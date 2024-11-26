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
import javax.swing.SwingUtilities;

import ChessEngine.ChessColor;
import ChessEngine.board.Tile;
import ChessEngine.board.move.Move;
import ChessEngine.board.move.PromotionMove;
import ChessEngine.piece.King;
import ChessEngine.piece.Pawn;
import ChessEngine.piece.Piece;
import ChessEngine.piece.Rook;
import Main.Controller.GameLogicHandler;
import Main.Controller.MainController;
import Main.Controller.PromoteController;
import Main.Frame.GameFrame;
import Main.Utils.ColorOption;

public class PnlBoardChess extends JPanel {
	private JPanel[][] squares = new JPanel[8][8]; // Lưu trữ các ô của bàn cờ
	private Color colorselectedTile = Color.decode("#F1F280");
	private List<Move> availableMoves;
	private String pathImage = "";
	Thread promotionThread;
	public static volatile boolean isPromoted = false;
	
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
	public void updateUIAfterRegularMove(Tile selectedTile, Tile targetTile,Piece selectedPiece) {
		deleteHighlightTiles();
		deletePieceToPanel(selectedTile.row, selectedTile.col);
		addPieceToPanel(selectedPiece.getImagePath(), targetTile.row, targetTile.col);
	}

	public void updateUIAfterCastlingMove(Tile selectedTile, Tile targetTile,Piece selectedPiece) {
		updateUIAfterRegularMove(selectedTile, targetTile, selectedPiece);
			if (selectedPiece.color.equals(ChessColor.white)) {
				pathImage = "src/Main/Resources/piece-image2/wr.png";
			} else {
				pathImage = "src/Main/Resources/piece-image2/br.png";
			}
			// nhập thành gần
			if (targetTile.col == 6) {
				deletePieceToPanel(targetTile.row,7);
				addPieceToPanel(pathImage, targetTile.row, 5);
			} else {
//				nhập thành xa
				deletePieceToPanel(targetTile.row, 0);
				addPieceToPanel(pathImage, targetTile.row, 3);
			} 
	} 
	
	public void updateUIAfterEnPassantMove(Tile selectedTile, Tile targetTile,Piece selectedPiece) {
		updateUIAfterRegularMove(selectedTile, targetTile, selectedPiece);
		if(selectedTile.row == 4) {
			deletePieceToPanel(4, targetTile.col );
		} else {
			deletePieceToPanel(3, targetTile.col );
		}
	}
	
	public void updateUIAfterPromotionMove(Tile selectedTile, Tile targetTile, Piece selectedPiece, MainController mainController, Move move, GameLogicHandler gameLogicHandler) {
	    updateUIAfterRegularMove(selectedTile, targetTile, selectedPiece);
	    
	    
	    // Hiển thị giao diện Promotion trong EDT
	    SwingUtilities.invokeLater(() -> {
	    	MainController.pnlPromotion.setBtnPiece(selectedPiece.color);
	        MainController.initPromotionFrame();
	    });

	    // Khởi chạy luồng phụ để chờ lựa chọn phong quân
	    new Thread(() -> {
	        synchronized (this) {
	            while (!isPromoted) {
	                try {
	                    Thread.sleep(100); // Đợi cho đến khi người chơi chọn quân
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	            // Sau khi chọn quân, cập nhật giao diện
	            SwingUtilities.invokeLater(() -> {
	            	 switch (PromoteController.pieceName) {
	     	        case "Bishop":
	     	            pathImage = selectedPiece.color.equals(ChessColor.white) ? "src/Main/Resources/piece-image2/wb.png" : "src/Main/Resources/piece-image2/bb.png";
	     	            break;
	     	        case "Knight":
	     	            pathImage = selectedPiece.color.equals(ChessColor.white) ? "src/Main/Resources/piece-image2/wn.png" : "src/Main/Resources/piece-image2/bn.png";
	     	            break;
	     	        case "Rook":
	     	            pathImage = selectedPiece.color.equals(ChessColor.white) ? "src/Main/Resources/piece-image2/wr.png" : "src/Main/Resources/piece-image2/br.png";
	     	            break;
	     	        default: // Queen
	     	            pathImage = selectedPiece.color.equals(ChessColor.white) ? "src/Main/Resources/piece-image2/wq.png" : "src/Main/Resources/piece-image2/bq.png";
	     	            break;
	     	    }
	     	    
	     	    // Cập nhật lại giao diện bàn cờ
	     	    deletePieceToPanel(targetTile.row, targetTile.col);
	     	    addPieceToPanel(pathImage, targetTile.row, targetTile.col);
	     		PromotionMove.setPieceToPromote(PromoteController.pieceName);
	     		gameLogicHandler.executeMove(move, mainController);
	     		isPromoted = false;
	            });
	        }
	    }).start();
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
