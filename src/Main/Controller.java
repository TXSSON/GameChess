package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JButton;
import javax.swing.JPanel;

import ChessEngine.ChessColor;
import ChessEngine.board.Board;
import ChessEngine.board.Gameplay;
import ChessEngine.board.Tile;
import ChessEngine.piece.Piece;
import Main.Frame.GameFrame;
import Main.Frame.GameOptionsFrame;
import Main.Pnl.CreatePnlBoardChess;
import Main.Pnl.PnlHome;
import Main.Pnl.PnlNewGame;
import Main.Utils.ColorOption;
import Main.Utils.PieceOption;

public class Controller {

	private Gameplay gameplay;
	private GameFrame frame;
	private GameOptionsFrame optionsFrame;
	private PnlHome pnlHome;
	private PnlNewGame pnlNewGame;
	public ColorOption selectedColor;
	public PieceOption selectedPiece;
	private String playTime;

	private CreatePnlBoardChess createPnlBoardChess;

	public Controller() {
		// Khởi tạo JFrame của Game
		frame = new GameFrame();
		optionsFrame = new GameOptionsFrame();

		// Gọi tới màn hình chính của game
		pnlHome = new PnlHome();
		frame.add(pnlHome, BorderLayout.CENTER);
		frame.pack();

		// Thêm xử lý sự kiện thông qua controller
		addEventHandlers(pnlHome);
		addEventHandlers(optionsFrame);

	}

	private void addEventHandlers(PnlHome pnlHome) {
		pnlHome.newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				optionsFrame.setVisible(true); // Hiển thị cửa sổ
			}
		});

		pnlHome.tutorialGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlHome.cardLayout.show(pnlHome.cardPanel, "PnlTutorial");
			}
		});

		pnlHome.exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void addEventHandlers(GameOptionsFrame optionsFrame) {
		// Đóng frame khi bấm Cancel
		optionsFrame.btnCancel.addActionListener(e -> optionsFrame.dispose());

		// Xử lý khi bấm OK (ở đây bạn có thể lấy các giá trị từ các tùy chọn)
		optionsFrame.btnOk.addActionListener(e -> {
			selectedColor = (ColorOption) optionsFrame.cbBoardColor.getSelectedItem();
			selectedPiece = (PieceOption) optionsFrame.cbChoosePiece.getSelectedItem();
			playTime = optionsFrame.txtPlayTime.getText();
			
			System.out.println(selectedColor + " " + selectedPiece.getName() + " " + playTime);
			gameplay = new Gameplay(GameFrame.height / 8, selectedPiece.getColor());

			pnlNewGame = new PnlNewGame();
			createPnlBoardChess = new CreatePnlBoardChess(selectedColor);
			addPice();
			pnlNewGame.add(createPnlBoardChess, BorderLayout.EAST);
			
			pnlHome.cardPanel.add(pnlNewGame, "PnlNewGame");
			

			// Sau đó đóng cửa sổ
			optionsFrame.dispose();
			pnlHome.cardLayout.show(pnlHome.cardPanel, "PnlNewGame");

		});
	}

	private void addPice() {
	    // Thêm quân cờ vào bàn cờ
	    if (selectedPiece.getName().equals("White")) {
	        for (int row = 0; row < Board.MAX_ROW; row++) {
	            for (int col = 0; col < Board.MAX_COL; col++) {
	                Tile tile = gameplay.board.tiles[row][col]; // Lấy ô tương ứng
	                try {
	                    Piece piece = tile.getPiece(); // Lấy quân cờ ở ô đó
	                    if (piece != null) {
	                        String path = piece.getImagePath();
	                        System.out.println("Adding piece at (" + row + ", " + col + "): " + path);
	                        try {
	                        createPnlBoardChess.addPieceToPanel(path, row, col); // Thêm quân cờ vào panel
	                        }
	                        catch(Exception ex) {
	                        	ex.printStackTrace();
	                        }
	                        } else {
	                        System.out.println("No piece at (" + row + ", " + col + ")");
	                    }
	                } catch (Exception ex) {
	                    ex.printStackTrace(); // Ghi lại ngoại lệ
	                }
	            }
	        }
	    } else {
	        for (int row = 0; row < Board.MAX_ROW; row++) {
	            for (int col = 0; col < Board.MAX_COL; col++) {
	                Tile tile = gameplay.board.tiles[Board.MAX_ROW - row - 1][col]; // Lấy ô tương ứng cho quân đen
	                try {
	                    Piece piece = tile.getPiece(); // Lấy quân cờ ở ô đó
	                    if (piece != null) {
	                        String path = piece.getImagePath();
	                        System.out.println("Adding piece at (" + row + ", " + col + "): " + path);
	                        createPnlBoardChess.addPieceToPanel(path, row, col); // Thêm quân cờ vào panel
	                    } else {
	                        System.out.println("No piece at (" + row + ", " + col + ")");
	                    }
	                } catch (Exception ex) {
	                    ex.printStackTrace(); // Ghi lại ngoại lệ
	                }
	            }
	        }
	    }
	}


}
