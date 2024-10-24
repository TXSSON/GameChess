package Main.Frame;

import javax.swing.*;

import ChessEngine.ChessColor;
import Main.Utils.ColorOption;
import Main.Utils.PieceOption;

import java.awt.*;
import java.util.AbstractMap.SimpleEntry;

public class GameOptionsFrame extends JFrame {

	public JButton btnOk;
	public JButton btnCancel;
	public JComboBox<ColorOption> cbBoardColor;
	public JComboBox<PieceOption> cbChoosePiece;
	public JTextField txtPlayTime;

	public GameOptionsFrame() {
		// Cài đặt tiêu đề và kích thước cho Frame
		this.setTitle("Game Options");
		this.setSize(700, 450);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); // Để frame xuất hiện ở giữa màn hình

		// Sử dụng BorderLayout
		this.setLayout(new BorderLayout());

		// Tạo panel để chứa các tùy chọn
		JPanel pnlOptions = new JPanel(new GridLayout(3, 2));

		// Tùy chọn màu bàn cờ
		JLabel lblBoardColor = new JLabel("Board Color:");
		cbBoardColor = new JComboBox<>(new ColorOption[] { new ColorOption("Xanh lá cây", "#739552,#EBECD0"),
				new ColorOption("Nâu", "#B88762,#EDD6B0"), new ColorOption("Đỏ", "#BB5746,#F5DBC3") });

		// Tùy chọn chọn quân cờ
		JLabel lblChoosePiece = new JLabel("Choose Piece:");
		cbChoosePiece = new JComboBox<>(new PieceOption[] {
			    new PieceOption("White", ChessColor.white),
			    new PieceOption("Black", ChessColor.black)
			});


		// Tùy chọn thời gian chơi
		JLabel lblPlayTime = new JLabel("Play Time (min):");
		txtPlayTime = new JTextField();

		// Thêm các thành phần vào panel
		pnlOptions.add(lblBoardColor);
		pnlOptions.add(cbBoardColor);
		pnlOptions.add(lblChoosePiece);
		pnlOptions.add(cbChoosePiece);
		pnlOptions.add(lblPlayTime);
		pnlOptions.add(txtPlayTime);

		// Thêm panel vào JFrame
		this.add(pnlOptions, BorderLayout.CENTER);

		// Tạo nút OK và Cancel
		JPanel pnlButtons = new JPanel();
		btnOk = new JButton("OK");
		btnCancel = new JButton("Cancel");

		pnlButtons.add(btnOk);
		pnlButtons.add(btnCancel);

		// Thêm các nút vào frame
		this.add(pnlButtons, BorderLayout.SOUTH);
	}
}
