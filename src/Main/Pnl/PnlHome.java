package Main.Pnl;

import javax.swing.*;

import Main.Controller;
import Main.Frame.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlHome extends JPanel {

	public JButton newGameButton;
	public JButton tutorialGameButton;
	public JButton exitButton;

	public CardLayout cardLayout;
	public JPanel cardPanel;

	public PnlHome() {
		// Khởi tạo kích thước cho panel
		this.setPreferredSize(new Dimension(GameFrame.width, GameFrame.height));
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new BorderLayout()); // Sử dụng BorderLayout

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		// Tạo menu
		JPanel menu = createMenuPanel(); // Gọi phương thức tạo menu

		
		PnlTutorial pnlTutorial = new PnlTutorial();

		// Thêm menu và PnlNewGame vào cardPanel
		cardPanel.add(menu, "Menu"); // Thêm menu vào cardPanel
		cardPanel.add(pnlTutorial, "PnlTutorial");

		// Thêm cardPanel vào PnlHome
		this.add(cardPanel, BorderLayout.CENTER); // Thêm cardPanel vào giữa PnlHome
	}

	private JPanel createMenuPanel() {
		JPanel menu = new JPanel();
		menu.setBackground(Color.BLUE);
		menu.setLayout(new GridLayout(3, 1, 0, 0));

		// Sử dụng các nút đã khai báo
		newGameButton = new JButton("New Game");
		tutorialGameButton = new JButton("Tutorial Game");
		exitButton = new JButton("Exit");

		Dimension buttonSize = new Dimension(700, 250);
		newGameButton.setPreferredSize(buttonSize);
		tutorialGameButton.setPreferredSize(buttonSize);
		exitButton.setPreferredSize(buttonSize);

		menu.add(newGameButton);
		menu.add(tutorialGameButton);
		menu.add(exitButton);

		JPanel centeredMenu = new JPanel();
		centeredMenu.setLayout(new GridBagLayout());
		centeredMenu.add(menu);

		return centeredMenu;
	}
}
