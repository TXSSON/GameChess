package Main.Pnl;

import javax.swing.*;

import Main.Frame.GameFrame;
import Main.Utils.ButtonWithIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlHome extends JPanel {

	public JButton BtnNewGame;
	public JButton tutorialGameButton;
	public JButton exitButton;

	public CardLayout cardLayout;
	public JPanel cardPanel;
	public PnlBoardChess pnlBoardChess;
	public PnlTutorial pnlTutorial;

	private static volatile PnlHome pnlHome;

	private PnlHome() {
		// Khởi tạo kích thước cho panel
        this.setPreferredSize(new Dimension(GameFrame.width * 3 / 4, GameFrame.height)); // Chia diện tích hợp lý
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new BorderLayout());

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		JPanel menu = createMenuPanel();
		cardPanel.add(menu, "menu");

		this.add(cardPanel, BorderLayout.CENTER);
		
	}

	private JPanel createMenuPanel() {
		JPanel menu = new JPanel();
		menu.setBackground(Color.BLUE);
		menu.setLayout(new GridLayout(3, 1, 0, 0));

		// Sử dụng các nút đã khai báo
		BtnNewGame = new JButton("BtnNewGame");
		tutorialGameButton = new JButton("Tutorial Game");
		exitButton = new JButton("Exit");

		Dimension buttonSize = new Dimension(700, 250);
//		BtnNewGame.setPreferredSize(buttonSize);
		tutorialGameButton.setPreferredSize(buttonSize);
		exitButton.setPreferredSize(buttonSize);

		menu.add(BtnNewGame);
		menu.add(tutorialGameButton);
		menu.add(exitButton);

		JPanel centeredMenu = new JPanel();
		centeredMenu.setLayout(new GridBagLayout());
		centeredMenu.add(menu);

		return centeredMenu;
	}

	public void setPnlTutorial(PnlTutorial pnlTutorial) {
		this.pnlTutorial = pnlTutorial;
		cardPanel.add(this.pnlTutorial, "pnlTutorial");
	}

	public void setPnlNewGame(PnlBoardChess pnlBoardChess) {
		this.pnlBoardChess = pnlBoardChess;
		cardPanel.add(this.pnlBoardChess, "pnlBoardChess");
	}

	public static PnlHome getPnlHomeInstance() {

		if (pnlHome == null) {
			synchronized (PnlHome.class) {
				if (pnlHome == null) {
					pnlHome = new PnlHome();
				}
			}
		}
		return pnlHome;
	}

}
