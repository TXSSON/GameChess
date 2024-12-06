package Main.Controller;

import Main.Pnl.PnlSideBar;
import Main.Utils.PlayerSound;

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
			mainController.pnlSideBar.cardLayout.show(mainController.pnlSideBar.pnlNorthInner, "pnlEmpty");
		});
		pnlSideBar.btnSound.addActionListener(e ->{
			mainController.pnlSideBar.cardLayout2.show(mainController.pnlSideBar.pnlBtnSound, "btnNoSound");
			PlayerSound.isMute = true;
		});
		pnlSideBar.btnNoSound.addActionListener(e ->{
			mainController.pnlSideBar.cardLayout2.show(mainController.pnlSideBar.pnlBtnSound, "btnSound");
			PlayerSound.isMute = false;

		});
	}

}
