package Main.Pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnlVictory extends JPanel{
	
	private static PnlVictory pnlVictoryInstance;
	
	public JButton btnBackHome = new JButton("Kết thúc ván đấu");
	public static JLabel lblVictory;
	
	private  volatile String nameWiner;
	
	private PnlVictory() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(500, 300));
		
		lblVictory = new JLabel();
		lblVictory.setFont(new Font("Arial", Font.BOLD, 18));
		lblVictory.setForeground(Color.GREEN); 
		lblVictory.setPreferredSize(new Dimension(500, 200));
		
		btnBackHome.setPreferredSize(new Dimension(500, 100));
		this.add(lblVictory, BorderLayout.CENTER);
		this.add(btnBackHome, BorderLayout.SOUTH);
	}
	
	public static PnlVictory getPnlVictoryInstance () {
		
		if(pnlVictoryInstance == null) {
			synchronized (PnlVictory.class) {
				if (pnlVictoryInstance == null) {
					pnlVictoryInstance = new PnlVictory();
					return pnlVictoryInstance;
				}
			}
		}
		return pnlVictoryInstance;
	}

	public void setNameWiner(String nameWiner) {
		this.nameWiner = nameWiner;
		lblVictory.setText("Đội chiến thắng là: " + this.nameWiner);
	}
	
	
}
