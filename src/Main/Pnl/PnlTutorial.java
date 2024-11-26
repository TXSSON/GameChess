package Main.Pnl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import Main.Utils.BackgroundPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Controller.MainController;

public class PnlTutorial extends JPanel {

	private static volatile PnlTutorial pnlTutorial;
	private CardLayout cardLayout;
	private PnlTutorial() {
		// TODO Auto-generated constructor stub
		BackgroundPanel backgroundPanel = new BackgroundPanel("src/Main/Resources/Icons/tutorials.png");
		backgroundPanel.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());
		this.add(backgroundPanel, BorderLayout.CENTER);
		JLabel label = new JLabel("Tutorial");
		PnlSideBar.getSideBarInstance();

	}

	public static PnlTutorial getPnlTutorialInstance() {
		if (pnlTutorial == null) {
			synchronized (PnlTutorial.class) {
				if (pnlTutorial == null) {
					pnlTutorial = new PnlTutorial();
				}
			}
		}
		return pnlTutorial;
	}
}
