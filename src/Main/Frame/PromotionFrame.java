package Main.Frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Main.Pnl.PnlPromote;

public class PromotionFrame extends JFrame{
	private PnlPromote pnlPromotion;
	
	public PromotionFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng cửa sổ
		this.setSize(GameFrame.height / 2, GameFrame.height / 8); // Khởi tạo kích thước
		this.setResizable(false); // Cấm điều chỉnh kích thước của cửa sổ
		this.setTitle("Promote"); // Đặt tên cho cửa sổ
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null); // Căn giữa màn hình
		this.pack();    
	}
	
	public void setPnlPromotion(PnlPromote pnlPromotion) {
		this.pnlPromotion = pnlPromotion;
		this.add(pnlPromotion, BorderLayout.CENTER);
		this.pack();
	}
}
