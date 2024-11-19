package Main.Pnl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import ChessEngine.ChessColor;
import Main.Frame.GameFrame;
import Main.Utils.ButtonWithIcon;

public class PnlPromotion extends JPanel {

	private volatile static PnlPromotion pnlPromotionInstance;
	private volatile JButton btnRook;
	private volatile JButton btnKnight;
	private volatile JButton btnBishop;
	private volatile JButton btnQueen;
	
	private PnlPromotion() {
		this.setPreferredSize(new Dimension(GameFrame.height / 2, GameFrame.height / 8));
		this.setLayout(new GridLayout(1, 4));
	}

	public static PnlPromotion getPnlPromotionInstance() {
		if (pnlPromotionInstance == null) {
			synchronized (PnlPromotion.class) {
				if (pnlPromotionInstance == null) {
					pnlPromotionInstance = new PnlPromotion();
				}
			}
		}
		return pnlPromotionInstance;
	}
	
	private void setBtnPiece(ChessColor chessColor) {
		if (chessColor == ChessColor.white) {
			((ButtonWithIcon) btnRook).setIconPath("src/Main/Resources/piece-image2/wr.png");
			((ButtonWithIcon) btnBishop).setIconPath("src/Main/Resources/piece-image2/wb.png");
			((ButtonWithIcon) btnKnight).setIconPath("src/Main/Resources/piece-image2/wn.png");
			((ButtonWithIcon) btnQueen).setIconPath("src/Main/Resources/piece-image2/wq.png");
		} else {
			((ButtonWithIcon) btnRook).setIconPath("src/Main/Resources/piece-image2/br.png");
			((ButtonWithIcon) btnBishop).setIconPath("src/Main/Resources/piece-image2/bb.png");
			((ButtonWithIcon) btnKnight).setIconPath("src/Main/Resources/piece-image2/bn.png");
			((ButtonWithIcon) btnQueen).setIconPath("src/Main/Resources/piece-image2/bq.png");
		}	
	}
	

}
