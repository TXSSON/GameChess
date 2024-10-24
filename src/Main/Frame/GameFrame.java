package Main.Frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


import Main.Pnl.PnlHome;

public class GameFrame extends JFrame {

	public static int height = 1600;
	public static int width = height * 4 / 3;

	public GameFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng cửa sổ
		this.setSize(width, height); // Khởi tạo kích thước
		this.setResizable(false); // Cấm điều chỉnh kích thước của cửa sổ
		this.setTitle("Game Chess"); // Đặt tên cho cửa sổ
		this.setLayout(new BorderLayout());
		
		
		this.setLocationRelativeTo(null); // Căn giữa màn hình
		this.setVisible(true); // Hiển thị cửa sổ
	}
	
	
}
