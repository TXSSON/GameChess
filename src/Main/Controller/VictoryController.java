package Main.Controller;

import Main.Pnl.PnlVictory;

public class VictoryController {
	
	MainController mainController;
	PnlVictory pnlVictory;
	public VictoryController(PnlVictory pnlVictory, MainController mainController) {
		this.mainController = mainController;
		this.pnlVictory = pnlVictory;
		addEventHandlers();
	}
	
	public void addEventHandlers() {
		pnlVictory.btnBackHome.addActionListener(e ->{
			System.exit(0);
		});
	}
}
