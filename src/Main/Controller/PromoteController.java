package Main.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ChessEngine.piece.Piece;
import Main.Pnl.PnlBoardChess;
import Main.Pnl.PnlPromote;

public class PromoteController {
	private PnlPromote pnlPromotion;
	private MainController mainController;

	public volatile static String pieceName = "";

	public PromoteController(PnlPromote pnlPromotion, MainController mainController) {
		// TODO Auto-generated constructor stub
		this.pnlPromotion = pnlPromotion;
		this.mainController = mainController;
		addEvenHanlder();
	}

	private void addEvenHanlder() {
		pnlPromotion.btnBishop.addActionListener(e -> selectPiece("Bishop"));
		pnlPromotion.btnRook.addActionListener(e -> selectPiece("Rook"));
		pnlPromotion.btnKnight.addActionListener(e -> selectPiece("Knight"));
		pnlPromotion.btnQueen.addActionListener(e -> selectPiece("Queen"));
	}

	private synchronized void selectPiece(String pieceName) {
		this.pieceName = pieceName;
		mainController.promotionFrame.dispose();
		PnlBoardChess.isPromoted = true;
	}

}
