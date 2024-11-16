package Main.Frame;

import javax.swing.*;

import ChessEngine.ChessColor;
import Main.Pnl.PnlGameOptions;
import Main.Utils.ColorOption;
import Main.Utils.PieceOption;

import java.awt.*;
import java.util.AbstractMap.SimpleEntry;

public class GameOptionsFrame extends JFrame {

	public PnlGameOptions pnlGameOptions;
	
	private static volatile GameOptionsFrame gameOptionsFrame;
	
	private GameOptionsFrame(PnlGameOptions pnlGameOptions) {
		this.setTitle("Game Options");
		this.setSize(700, 450);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		this.setResizable(false); 
		this.setLayout(new BorderLayout());
		
		this.pnlGameOptions = pnlGameOptions;
		this.add(this.pnlGameOptions, BorderLayout.CENTER);
		this.pack();
	}
	
	public static GameOptionsFrame getgameOptionsFrameInstance(PnlGameOptions pnlGameOptions) {
		
		if (gameOptionsFrame == null) {
			synchronized (GameOptionsFrame.class) {
				if (gameOptionsFrame == null) {
					gameOptionsFrame = new GameOptionsFrame(pnlGameOptions);
				}
			}
		}
		return gameOptionsFrame;
	}
	
}
