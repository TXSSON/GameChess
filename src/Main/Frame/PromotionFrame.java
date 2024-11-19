package Main.Frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Main.Pnl.PnlPromotion;

public class PromotionFrame extends JFrame{
	private PnlPromotion pnlPromotion;
	
	public PromotionFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng cửa sổ
		this.setSize(GameFrame.width / 2, GameFrame.height / 4); // Khởi tạo kích thước
		this.setResizable(false); // Cấm điều chỉnh kích thước của cửa sổ
		this.setTitle("Promotion"); // Đặt tên cho cửa sổ
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null); // Căn giữa màn hình
        this.pack();
	}
	
	public void setPnlPromotion(PnlPromotion pnlPromotion) {
		this.pnlPromotion = pnlPromotion;
		this.add(pnlPromotion, BorderLayout.CENTER);
		this.pack();
	}
}
