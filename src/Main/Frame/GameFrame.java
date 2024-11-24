package Main.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;


import Main.Pnl.PnlHome;
import Main.Pnl.PnlSideBar;

public class GameFrame extends JFrame {

	public static int height = setSizeFrame().height;
	public static int width = setSizeFrame().width;
	
	public PnlSideBar pnlSideBar;
	public PnlHome pnlHome;

	public GameFrame() {
		
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
	
	public static Dimension setSizeFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.print("Kích thước màn hình là: ");
		System.out.println( screenSize.width + " " + screenSize.height );
		if (screenSize.height >= 1200) {
			screenSize.height = 1200;
		} else if (screenSize.height >= 800) {
			screenSize.height = 800;
		} else {
			screenSize.height = 600;
		} 
		screenSize.width = screenSize.height * 4 / 3;
		System.out.print("Kích thước bàn cờ là: ");
		System.out.println(screenSize.width + " " + screenSize.height );
		return screenSize;
	}
	
	
}
