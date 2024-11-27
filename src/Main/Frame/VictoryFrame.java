package Main.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VictoryFrame extends JFrame {
	public VictoryFrame(String winningTeam) {
		// Thiết lập cửa sổ
		setTitle("Winner");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình

		// Thiết lập layout và thêm thông báo chiến thắng
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.WHITE);

		// Tạo thông báo chiến thắng
		JLabel lblVictory = new JLabel("Đội chiến thắng: " + winningTeam, JLabel.CENTER);
		lblVictory.setFont(new Font("Arial", Font.BOLD, 18));
		lblVictory.setForeground(Color.GREEN); // Màu chữ xanh lá cho chiến thắng

		// Thêm thông báo vào panel
		panel.add(lblVictory, BorderLayout.CENTER);
		// Thêm panel vào cửa sổ
		this.add(panel);
	}
	public static void initVictoryFrame(String winningTeam) {
		VictoryFrame victoryFrame = new VictoryFrame(winningTeam);
        victoryFrame.setVisible(true);
	}
}
