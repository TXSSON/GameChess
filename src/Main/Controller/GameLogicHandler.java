package Main.Controller;

import java.util.Stack;

import ChessEngine.ChessEnding;
import ChessEngine.board.move.CastlingMove;
import ChessEngine.board.move.EnPassantMove;
import ChessEngine.board.move.Move;
import ChessEngine.board.move.PromotionMove;
import ChessEngine.piece.King;
import Main.Utils.PlayerSound;

public class GameLogicHandler {
	private boolean isTurnWhite = true;
	private final Stack<Move> moveHistory = new Stack<>();
	private  King opponentKing;
	ChessEnding ending;
	public  static Thread promotionThread;
	public Thread currentThread;
	public boolean isTurnWhite() {
		return isTurnWhite;
	}
	public void toggleTurn() {
		isTurnWhite = !isTurnWhite;
	}
	public void executeMove(Move move, MainController mainController) {
		
		mainController.gameplay.movePiece(move);
		
	}
	public boolean isGameOver(MainController mainController) {
		opponentKing = isTurnWhite ? (King) mainController.gameplay.board.blackPieces.get(0) :(King) mainController.gameplay.board.whitePieces.get(0);
		ending = opponentKing.isEnded(mainController.gameplay);
		if (ending == ChessEnding.whiteWin) {
			System.out.println("Quân trắng thắng");
			return true;
		} else if (ending == ChessEnding.blackWin) {
			System.out.println("Quân đen thắng");
			return true;
		} else if (ending == ChessEnding.stalemate) {
			System.out.println("Hòa cờ");
			return true;
		}
		return false;
	}

}















