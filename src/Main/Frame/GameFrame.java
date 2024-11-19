package Main.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;


import Main.Pnl.PnlHome;
import Main.Pnl.PnlSideBar;

public class GameFrame extends JFrame {
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth = screenSize.width;
	int screenHeight = screenSize.height;

	public static int height = 1200;
	public static int width = height * 4 / 3;
	
	
	
	public PnlSideBar pnlSideBar;
	public PnlHome pnlHome;

	public GameFrame() {
		// Đảm bảo cửa sổ không vượt quá kích thước màn hình
		if (height > screenHeight) {
			height = screenHeight / (1980 / 1200 );
			width = height * 4 / 3;
		}
		
		System.out.println("screenWidth" + screenWidth + ", " + "screenHeight" +  screenHeight);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng cửa sổ
		this.setSize(width, height); // Khởi tạo kích thước
		this.setResizable(false); // Cấm điều chỉnh kích thước của cửa sổ
		this.setTitle("Game Chess"); // Đặt tên cho cửa sổ
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null); // Căn giữa màn hình
		this.pack();
	}
	
	public void setPnlHome(PnlHome pnlHome) {
		this.pnlHome = pnlHome;
		this.add(pnlHome, BorderLayout.EAST);
		this.pack();
	}
	public void setPnlSideBar(PnlSideBar pnlSideBar) {
		this.pnlSideBar = pnlSideBar;
		this.add(pnlSideBar, BorderLayout.WEST);
		this.pack();
	}
	
	
}
