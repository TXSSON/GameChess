package Main.Controller;

import Main.Pnl.PnlSideBar;

public class SideBarController {

	public PnlSideBar pnlSideBar;
	public MainController mainController;

	public SideBarController(PnlSideBar pnlSideBar, MainController mainController) {
		this.pnlSideBar = pnlSideBar;
		this.mainController = mainController;
		addEventHandlers();
	}

	public void addEventHandlers() {
		pnlSideBar.btnBack.addActionListener(e ->{
			mainController.pnlHome.cardLayout.show (mainController.pnlHome.cardPanel, "menu");
		});
	}

}
