package Main.Controller;

import ChessEngine.ChessColor;
import ChessEngine.board.Tile;
import ChessEngine.board.move.Move;
import ChessEngine.piece.Piece;
import Main.Pnl.PnlBoardChess;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BoardChessController {
	private final PnlBoardChess pnlBoardChess;
	private final MainController mainController;

	private final MoveValidator moveValidator = new MoveValidator();
	private final GameLogicHandler gameLogicHandler = new GameLogicHandler();

	public Piece selectedPiece;
	private Tile selectedTile;
	private List<Move> availableMoves;

	public BoardChessController(PnlBoardChess pnlBoardChess, MainController mainController) {
		this.pnlBoardChess = pnlBoardChess;
		this.mainController = mainController;

		addEventHandlers();
	}

	private void addEventHandlers() {
		pnlBoardChess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleTileClick(e.getX(), e.getY());
			}
		});
	}

	private void handleTileClick(int x, int y) {
		int row = y / mainController.gameplay.board.SQUARE_SIZE;
		int col = x / mainController.gameplay.board.SQUARE_SIZE;

		Tile clickedTile = mainController.gameplay.board.tiles[row][7 - col];

		if (selectedPiece == null
				|| (clickedTile.getPiece() != null && clickedTile.getPiece().color.equals(selectedPiece.color))) {
			handlePieceSelection(clickedTile);
		} else {
			handleMove(clickedTile);
		}
	}

	private void handlePieceSelection(Tile tile) {
		Piece clickedPiece = tile.getPiece();

		if (clickedPiece == null
				|| clickedPiece.color != (gameLogicHandler.isTurnWhite() ? ChessColor.white : ChessColor.black)) {
			System.out.println("Lựa chọn không hợp lệ.");
			return;
		}

		selectedPiece = clickedPiece;
		selectedTile = tile;
		availableMoves = moveValidator.calculateAvailableMoves(selectedPiece, mainController);

		if (availableMoves != null && !availableMoves.isEmpty()) {
			SwingUtilities.invokeLater(() -> pnlBoardChess.deleteHighlightTiles());
			SwingUtilities.invokeLater(() -> pnlBoardChess.addHighlightTiles(availableMoves));
		} else {
			System.out.println("Không có nước đi hợp lệ.");
			resetSelection();
		}
	}

	private void handleMove(Tile targetTile) {
		if (moveValidator.isValidMove(selectedPiece, targetTile, availableMoves)) {
			for (Move move : availableMoves) {
				if (move.tileTo.equals(targetTile)) {

					SwingUtilities.invokeLater(() -> {
						pnlBoardChess.updateUIAfterMove(selectedTile, targetTile, selectedPiece);
						gameLogicHandler.executeMove(move, mainController);
						resetSelection();

						if (gameLogicHandler.isGameOver(mainController)) {
							System.out.println("Trò chơi kết thúc.");
							return;
						}
						gameLogicHandler.toggleTurn();
					});
					break;
				}
				
			}
		} else {
			System.out.println("Nước đi không hợp lệ.");
		}

	}

	private void resetSelection() {
		selectedPiece = null;
		selectedTile = null;
		availableMoves = null;
		SwingUtilities.invokeLater(() -> pnlBoardChess.repaint());
	}
}
