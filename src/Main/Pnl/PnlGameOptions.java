package Main.Pnl;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.GridLayout;
import Main.Utils.BackgroundPanel;
import Main.Utils.ButtonWithIcon;
import Main.Utils.ColorOption;
import Main.Utils.PieceOption;

import ChessEngine.ChessColor;

public class PnlGameOptions extends JPanel {
	public ButtonWithIcon btnOk;
	public ButtonWithIcon btnCancel;
	public JComboBox<ColorOption> cbBoardColor;
	public JComboBox<PieceOption> cbChoosePiece;
	public JTextField txtPlayTime;

	private static volatile PnlGameOptions pnlGameOptions;

	private PnlGameOptions() {
		Font bungeeShadeFont = null;
		try {
			File fontFile = new File("src/Main/Resources/font/BungeeSpice-Regular.ttf"); // Đường dẫn đến font
			bungeeShadeFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(bungeeShadeFont); // Đăng ký font vào hệ thống
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			bungeeShadeFont = new Font("Arial", Font.BOLD, 16);
		}

		// Sử dụng BackgroundPanel làm nền
		BackgroundPanel backgroundPanel = new BackgroundPanel("src/Main/Resources/Icons/bgroption.jpg");
		backgroundPanel.setLayout(new BorderLayout());

		// Panel chứa các thành phần UI
		JPanel pnlOptions = new JPanel(new GridLayout(3, 2, 5, 5));
		pnlOptions.setOpaque(false); // Trong suốt để thấy hình nền

		Font labelFont = new Font("Arial", Font.PLAIN, 16);
		Font inputFont = new Font("Arial", Font.BOLD, 20);

		// Tùy chọn màu bàn cờ
		JLabel lblBoardColor = new JLabel("BOARD COLOR:");
		lblBoardColor.setFont(labelFont);
		lblBoardColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoardColor.setForeground(Color.BLACK);
		lblBoardColor.setFont(bungeeShadeFont);
		cbBoardColor = new JComboBox<>(new ColorOption[] {
				new ColorOption("Xanh lá cây", "#739552,#EBECD0"),
				new ColorOption("Nâu", "#B88762,#EDD6B0"),
				new ColorOption("Đỏ", "#BB5746,#F5DBC3")
		});
		cbBoardColor.setFont(inputFont);
		((JLabel) cbBoardColor.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


		// Tùy chọn chọn quân cờ
		JLabel lblChoosePiece = new JLabel("CHOOSE PIECE:");
		lblChoosePiece.setFont(labelFont);
		lblChoosePiece.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoosePiece.setForeground(Color.BLACK);
		lblChoosePiece.setFont(bungeeShadeFont);
		cbChoosePiece = new JComboBox<>(new PieceOption[] {
				new PieceOption("Black", ChessColor.black)
		});
		cbChoosePiece.setFont(inputFont);
		cbChoosePiece.setPreferredSize(new Dimension(200, 30)); // Giảm kích thước
		((JLabel) cbChoosePiece.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa text

		// Tùy chọn thời gian chơi
		JLabel lblPlayTime = new JLabel("PLAY TIME:");
		lblPlayTime.setFont(labelFont);
		lblPlayTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayTime.setForeground(Color.BLACK);
		lblPlayTime.setFont(bungeeShadeFont);
		txtPlayTime = new JTextField();
		txtPlayTime.setFont(inputFont);
		txtPlayTime.setHorizontalAlignment(JTextField.CENTER); // Căn giữa chữ
		txtPlayTime.setPreferredSize(new Dimension(200, 30)); // Giảm kích thước

		// Thêm các thành phần vào pnlOptions
		pnlOptions.add(lblBoardColor);
		pnlOptions.add(cbBoardColor);
		pnlOptions.add(lblChoosePiece);
		pnlOptions.add(cbChoosePiece);
		pnlOptions.add(lblPlayTime);
		pnlOptions.add(txtPlayTime);

		// Panel chứa các nút OK và Cancel
		JPanel pnlButtons = new JPanel();
		pnlButtons.setOpaque(false); // Trong suốt
		btnOk = new ButtonWithIcon("src/Main/Resources/Icons/Ok.png", 80, 40);
		btnCancel = new ButtonWithIcon("src/Main/Resources/Icons/cancel.png", 80, 40);
		pnlButtons.add(btnOk);
		pnlButtons.add(btnCancel);

		// Thêm các thành phần vào BackgroundPanel
		backgroundPanel.add(pnlOptions, BorderLayout.CENTER);
		backgroundPanel.add(pnlButtons, BorderLayout.SOUTH);

		// Thêm BackgroundPanel vào PnlGameOptions
		this.setLayout(new BorderLayout());
		this.add(backgroundPanel, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(600, 450));
	}

	public static PnlGameOptions getPnlGameOptionsInstance() {
		if (pnlGameOptions == null) {
			synchronized (PnlGameOptions.class) {
				if (pnlGameOptions == null) {
					pnlGameOptions = new PnlGameOptions();
				}
			}
		}
		return pnlGameOptions;
	}
}