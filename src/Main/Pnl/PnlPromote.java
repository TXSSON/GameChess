package Main.Pnl;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import ChessEngine.ChessColor;
import Main.Frame.GameFrame;
import Main.Utils.ButtonWithIcon;

public class PnlPromote extends JPanel {

	private volatile static PnlPromote pnlPromotionInstance;
	public volatile JButton btnRook = new ButtonWithIcon("src/Main/Resources/piece-image2/wr.png", GameFrame.height / 8, GameFrame.height / 8);
	public volatile JButton btnKnight = new ButtonWithIcon("src/Main/Resources/piece-image2/wn.png", GameFrame.height / 8, GameFrame.height / 8);
	public volatile JButton btnBishop = new ButtonWithIcon("src/Main/Resources/piece-image2/wb.png", GameFrame.height / 8, GameFrame.height / 8);
	public volatile JButton btnQueen = new ButtonWithIcon("src/Main/Resources/piece-image2/wq.png", GameFrame.height / 8, GameFrame.height / 8);
	
	private PnlPromote() {
		this.setLayout(new GridLayout(1, 4));  // 1 hàng, 4 cột
		this.setMinimumSize(new Dimension(GameFrame.height / 2, GameFrame.height / 8));
		btnRook.setSize(GameFrame.height / 8, GameFrame.height / 8);
		btnKnight.setSize(GameFrame.height / 8, GameFrame.height / 8);
		btnBishop.setSize(GameFrame.height / 8, GameFrame.height / 8);
		btnQueen.setSize(GameFrame.height / 8, GameFrame.height / 8);
		
		this.add(btnRook);
		this.add(btnKnight);
		this.add(btnBishop);
		this.add(btnQueen);
	}

	public static PnlPromote getPnlPromotionInstance() {
		if (pnlPromotionInstance == null) {
			synchronized (PnlPromote.class) {
				if (pnlPromotionInstance == null) {
					pnlPromotionInstance = new PnlPromote();
				}
			}
		}
		return pnlPromotionInstance;
	}
	
	public void setBtnPiece(ChessColor chessColor) {
		if (chessColor == ChessColor.white) {
			((ButtonWithIcon) btnRook).setIconPath("src/Main/Resources/piece-image2/wr.png");
			((ButtonWithIcon) btnBishop).setIconPath("src/Main/Resources/piece-image2/wn.png");
			((ButtonWithIcon) btnKnight).setIconPath("src/Main/Resources/piece-image2/wb.png");
			((ButtonWithIcon) btnQueen).setIconPath("src/Main/Resources/piece-image2/wq.png");
		} else {
			((ButtonWithIcon) btnRook).setIconPath("src/Main/Resources/piece-image2/br.png");
			((ButtonWithIcon) btnBishop).setIconPath("src/Main/Resources/piece-image2/bn.png");
			((ButtonWithIcon) btnKnight).setIconPath("src/Main/Resources/piece-image2/bb.png");
			((ButtonWithIcon) btnQueen).setIconPath("src/Main/Resources/piece-image2/bq.png");
		}	
	}
	

}
