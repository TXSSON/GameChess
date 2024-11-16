package Main.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

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

	private final AtomicBoolean isProcessing = new AtomicBoolean(false);

	private boolean isTurnWhite = true;

	private ExecutorService executorService;
	private ExecutorService logExecutor;
	ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

	public BoardChessController(PnlBoardChess pnlBoardChess, MainController mainController) {
		this.pnlBoardChess = pnlBoardChess;
		this.mainController = mainController;
		this.executorService = Executors.newSingleThreadExecutor();
		this.logExecutor = Executors.newSingleThreadExecutor();
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
		if (isProcessing.get()) {
			logExecutor.submit(() -> {
				System.out.println("Task đang xử lý, bỏ qua click");
			});
			return; // Bỏ qua click nếu đang xử lý tác vụ
		}
		isProcessing.set(true); // Đánh dấu là đang xử lý

		// Kiểm tra trạng thái của executorService
		if (executorService.isShutdown() || executorService.isTerminated()) {
			// Nếu executor đã tắt, khởi tạo lại
			executorService = Executors.newSingleThreadExecutor(); // Tạo lại executorService
			scheduledExecutor = Executors.newScheduledThreadPool(1);
		}

		// Tạo Future cho tác vụ xử lý
		Future<Void> future = executorService.submit(() -> {
			try {
				processPlayerMove(x, y); // Xử lý di chuyển quân cờ
			} catch (Exception ex) {
				ex.printStackTrace(); // Bắt và in lỗi nếu có
			} finally {
				// Đảm bảo đánh dấu là không còn đang xử lý khi tác vụ hoàn thành
				isProcessing.set(false); // Cập nhật trực tiếp
			}
			return null;
		});

		// Lên lịch hủy tác vụ sau 3 giây
		scheduledExecutor.schedule(() -> {
			if (!future.isDone()) {
				System.out.println("Tác vụ đã bị hủy vì quá thời gian.");
				// Sử dụng shutdownNow để đảm bảo dọn dẹp kịp thời trước khi hủy
				executorService.shutdownNow();
				scheduledExecutor.shutdownNow();
				isProcessing.set(false); // Cập nhật trực tiếp
			}
		}, 3, TimeUnit.SECONDS);
	}

	private synchronized void processPlayerMove(int x, int y) {
		if (Thread.currentThread().isInterrupted()) {
			System.out.println("Tác vụ bị hủy");
			return; // Dừng ngay lập tức nếu thread bị hủy
		}

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
				System.out.println("Nước đi không hợp lệ");
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

		for (Move move : availableMoves) {
			if (move.tileTo == clickedTile) {
				SwingUtilities.invokeLater(() -> {
					if (selectedTile != null) {
						availableMoves = null;
						pnlBoardChess.highlightTiles(availableMoves);
						pnlBoardChess.deletePieceToPanel(selectedTile);
					} else {
						System.out.println("SelectedTile null");
					}

					move.make(mainController.gameplay);

					pnlBoardChess.addPieceToPanel(selectedPiece2.getImagePath(), clickedTile.row, clickedTile.col);
					System.out.println("Cập nhật giao diện hoàn tất.");

					resetSelection();
				});

				break;
			}
		}
	}

	private boolean isValidMove(Piece selectedPiece2, Tile clickedTile) {
		// TODO Auto-generated method stub
		for (Move move : availableMoves) {
			if (move.tileTo.equals(clickedTile)) {
				return true;
			}
		}
		return false;
	}

	private void highlightAvailableMoves(Piece selectedPiece2) {
		try {
			availableMoves = selectedPiece2.calculateLegalMoves(mainController.gameplay);
			if (availableMoves != null && !availableMoves.isEmpty()) {
				 System.out.println("Số nước đi hợp lệ à: " + availableMoves.size());
				SwingUtilities.invokeLater(() -> pnlBoardChess.highlightTiles(availableMoves));
			} else {
				System.out.println("Không có nước đi hợp lệ.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}