package Main.Controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ChessEngine.board.Gameplay;
import Main.Frame.GameFrame;
import Main.Pnl.PnlBoardChess;
import Main.Pnl.PnlGameOptions;
import Main.Utils.ColorOption;
import Main.Utils.PieceOption;

public class GameOptionsController {

	private PnlGameOptions pnlGameOptions;
	private MainController mainController;

	public GameOptionsController(PnlGameOptions pnlGameOptions, MainController mainController) {
		this.pnlGameOptions = pnlGameOptions;
		this.mainController = mainController;
		addEventHandlers();
	}

	private void addEventHandlers() {
		pnlGameOptions.btnCancel.addActionListener(e -> mainController.gameOptionsFrame.dispose());

		pnlGameOptions.btnOk.addActionListener(e -> {
		
				mainController.selectedColor = (ColorOption) pnlGameOptions.cbBoardColor.getSelectedItem();
				mainController.selectedPiece = (PieceOption) pnlGameOptions.cbChoosePiece.getSelectedItem();

				mainController.gameplay = new Gameplay(GameFrame.height / 8, mainController.selectedPiece.getColor());

				mainController.initNewGame();
				mainController.addPice();
				
				mainController.gameOptionsFrame.dispose();
				mainController.pnlHome.cardLayout.show(mainController.pnlHome.cardPanel, "pnlBoardChess");
				mainController.pnlSideBar.cardLayout.show(mainController.pnlSideBar.pnlNorthInner, "pnlBtnBack");

				
		});
	}
}
