package Main.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import ChessEngine.ChessColor;
import ChessEngine.board.Tile;
import ChessEngine.board.move.Move;
import ChessEngine.piece.Piece;
import Main.Pnl.PnlBoardChess;

public class BoardChessController {

	private PnlBoardChess pnlBoardChess;
	private MainController mainController;
	private Piece selectedPiece;
	private Tile selectedTile;
	private List<Move> availableMoves;
	
	
	private boolean isProcessing = false;
	private boolean isTurnWhite = true;
	

	private ExecutorService executorService;

	public BoardChessController(PnlBoardChess pnlBoardChess, MainController mainController) {
		this.pnlBoardChess = pnlBoardChess;
		this.mainController = mainController;
		this.executorService = Executors.newSingleThreadExecutor();
		addEvenHandlers();
	}

	private void addEvenHandlers() {
		pnlBoardChess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub 
				int x = e.getX();
				int y = e.getY();
				System.out.println("bắt sự kiện chuột thành công");
				handleTileClick(x, y);
			}
		});
	}

	private void handleTileClick(int x, int y) {
		if (isProcessing) {
			System.out.println("Task đang xử lý, bỏ qua click");
			return;
		}
		isProcessing = true;

		executorService.submit(() -> {
			try {
				processPlayerMove(x, y);
			} catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				isProcessing = false; // Đảm bảo task được giải phóng sau khi hoàn thành
			}
		});
	}

	private synchronized void processPlayerMove(int x, int y) {
		int row = y / mainController.gameplay.board.SQUARE_SIZE;
		int col = x / mainController.gameplay.board.SQUARE_SIZE;
		
		System.out.println("Click ô ở hàng " + row + " cột " + col);

		Tile clickedTile = mainController.gameplay.board.tiles[row][col];
		
		if (clickedTile.getPiece() == null && selectedPiece == null) {
			System.out.println("Ô chọn không hợp lệ vì chưa chọn quân để chơi");
			return;
		} else if (clickedTile.getPiece() != null && selectedPiece == null) {
			if (clickedTile.getPiece().color == ChessColor.white && !isTurnWhite) {
				System.out.println("Ô chọn không hợp lệ vì đến lượt quân đen đi");
				return;
			} else if (clickedTile.getPiece().color != ChessColor.white && isTurnWhite) {
				System.out.println("Ô chọn không hợp lệ vì đến lượt quân trắng đi");
				return;
			}
		}
		
		
		Piece clickedPiece = clickedTile.getPiece();
		if (clickedPiece != null) {
			System.out.println(clickedPiece.getImagePath());
		}
		 if (selectedPiece == null || (clickedPiece != null && clickedPiece.color == selectedPiece.color)) {
	            if (clickedTile.isOccupied()) {
	                selectedPiece = clickedPiece;
	                selectedTile = clickedTile;
	                highlightAvailableMoves(selectedPiece);
	            }
	        } else {
	            if (isValidMove(selectedPiece, clickedTile)) {
	                executeMove(selectedPiece, clickedTile);
	                isTurnWhite = !isTurnWhite; // Đổi lượt sau nước đi hợp lệ
	            } else {
	                return;
	            }
	        }
	}

	private void resetSelection() {
		// TODO Auto-generated method stub
		 selectedPiece = null;
	     selectedTile = null;
	     SwingUtilities.invokeLater(() -> pnlBoardChess.repaint()); // Cập nhật lại giao diện
		
	}

	private void executeMove(Piece selectedPiece2, Tile clickedTile) {
	    SwingUtilities.invokeLater(() -> {
	        if (selectedTile != null) {
	            pnlBoardChess.deletePieceToPanel(selectedTile);                
	        } else {
	            System.out.println("SelectedTile null");
	        }
	        
	        for (Move move : availableMoves) {
	        	if (move.tileTo == clickedTile) {
	        		move.make(mainController.gameplay);
	        		break;
	        	}    	
	        }

	        pnlBoardChess.addPieceToPanel(selectedPiece2.getImagePath(), clickedTile.row, clickedTile.col);
	        System.out.println("Cập nhật giao diện hoàn tất.");
	        
	        // Đặt resetSelection() sau khi giao diện đã được cập nhật
	        resetSelection();
	    });
	}


	private boolean isValidMove(Piece selectedPiece2, Tile clickedTile) {
		// TODO Auto-generated method stub
		  List<Move> availableMoves = selectedPiece2.calculateLegalMoves(mainController.gameplay);
	        for (Move move : availableMoves) {
	            if (move.tileTo.equals(clickedTile)) {
	                return true;
	            }
	        }
	        return true;
	}

	private void highlightAvailableMoves(Piece selectedPiece2) {
		// TODO Auto-generated method stub
		try {
			availableMoves = selectedPiece2.calculateLegalMoves(mainController.gameplay);
			SwingUtilities.invokeLater(() -> pnlBoardChess.highlightTiles(availableMoves));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}