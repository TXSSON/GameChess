package Main.Controller;

import Main.Pnl.PnlHome;

public class HomeScreenController {

	private PnlHome pnlHome;
	private MainController mainController;

	public HomeScreenController(PnlHome pnlHome, MainController mainController) {
		this.pnlHome = pnlHome;
		this.mainController = mainController;
		addEventHandlers();
	}

	private void addEventHandlers() {
		pnlHome.BtnNewGame.addActionListener(e -> {
			// Logic khi nhấn nút New Game
			if (!mainController.playingChess) {
				mainController.initGameOptionsFrame();
				mainController.gameOptionsFrame.setVisible(true);
			} else {
				mainController.pnlHome.cardLayout.show(mainController.pnlHome.cardPanel, "pnlBoardChess");
			}
		});

		pnlHome.tutorialGameButton.addActionListener(e -> {
			if (!mainController.readedTutorial) {
			mainController.initTutorial();
			}
			mainController.pnlHome.cardLayout.show(mainController.pnlHome.cardPanel, "pnlTutorial");
		});

		pnlHome.exitButton.addActionListener(e -> {
			System.exit(0);
		});
	}
}
