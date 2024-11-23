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
	private PlayerSound playerSound = PlayerSound.getInstacePlaySound();
	private String fileSoundPathCastle = "src/Main/Resources/sound/castle.mp3";
	private String fileSoundPathMove = "src/Main/Resources/sound/move.mp3";
	private String fileSoundPathCapture = "src/Main/Resources/sound/capture.mp3";
	public boolean isTurnWhite() {
		return isTurnWhite;
	}
	public void toggleTurn() {
		isTurnWhite = !isTurnWhite;
	}
	public void executeMove(Move move, MainController mainController) {
		if (move instanceof CastlingMove) {
			playerSound.useSound(fileSoundPathCastle);
			((CastlingMove) move).make(mainController.gameplay);
		} else if (move instanceof EnPassantMove) {
			playerSound.useSound(fileSoundPathCapture);
			((EnPassantMove) move).make(mainController.gameplay);
		} else if (move instanceof PromotionMove) {
			playerSound.useSound(fileSoundPathMove);
			mainController.pnlPromotion.setBtnPiece(mainController.selectedPiece.getColor());
			promotionThread =  new Thread(()-> mainController.initPromotionFrame());  
			promotionThread.start();
			
			synchronized (this) {
				try {
					this.wait();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
			
			((PromotionMove) move).setPieceToPromote(PromoteController.pieceName);
			((PromotionMove) move).make(mainController.gameplay);
		} else {
			playerSound.useSound(fileSoundPathMove);
			move.make(mainController.gameplay);
		}
		
//		moveHistory.push(move);
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
//	public void undoLastMove(MainController mainController) {
//        if (!moveHistory.isEmpty()) {
//            Move lastMove = moveHistory.pop();
//            lastMove.undo(mainController.gameplay);
//            toggleTurn();
//        }
//    }
}















