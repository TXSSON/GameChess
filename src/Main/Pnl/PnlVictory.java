package Main.Pnl;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import Main.Utils.BackgroundPanel;
import Main.Utils.ButtonWithIcon;

public class PnlVictory extends JPanel {

	private static PnlVictory pnlVictoryInstance;
	public ButtonWithIcon btnBackHome;
	public static JLabel lblVictory;
	private volatile String nameWiner;
	private Image backgroundImage;

	private PnlVictory() {
		// Tải hình nền
		this.backgroundImage = new ImageIcon("src/Main/Resources/Icons/bgrvictory.png").getImage();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500, 400));

		// Tải và cài đặt font
		Font bungeeShadeFont = null;
		try {
			File fontFile = new File("src/Main/Resources/font/BungeeSpice-Regular.ttf");
			bungeeShadeFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(18f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(bungeeShadeFont); // Đăng ký font vào hệ thống
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			bungeeShadeFont = new Font("Arial", Font.BOLD, 13);
		}

		// Cài đặt nhãn chiến thắng
		lblVictory = new JLabel();
		lblVictory.setFont(bungeeShadeFont);
		lblVictory.setForeground(Color.WHITE);
		lblVictory.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nội dung
		lblVictory.setPreferredSize(new Dimension(500, 200));
		lblVictory.setBorder(BorderFactory.createEmptyBorder(110, 0, 0, 0));
		// Cài đặt nút quay về
		btnBackHome = new ButtonWithIcon("src/Main/Resources/Icons/buttonEndGame.png", 500, 70);

		// Thêm các thành phần vào panel
		this.add(lblVictory, BorderLayout.CENTER);
		this.add(btnBackHome, BorderLayout.SOUTH);
	}

	public static PnlVictory getPnlVictoryInstance() {
		if (pnlVictoryInstance == null) {
			synchronized (PnlVictory.class) {
				if (pnlVictoryInstance == null) {
					pnlVictoryInstance = new PnlVictory();
				}
			}
		}
		return pnlVictoryInstance;
	}

	public void setNameWiner(String nameWiner) {
		this.nameWiner = nameWiner;
		lblVictory.setText("Đội chiến thắng là: " + this.nameWiner);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Vẽ hình nền với kích thước đầy đủ
		}
	}
}