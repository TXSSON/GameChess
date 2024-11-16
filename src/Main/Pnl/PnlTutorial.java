package Main.Pnl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Controller.MainController;

public class PnlTutorial extends JPanel {

	private static volatile PnlTutorial pnlTutorial;

	private PnlTutorial() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
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
