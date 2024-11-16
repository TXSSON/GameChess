package Main.Pnl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ChessEngine.ChessColor;
import Main.Utils.ColorOption;
import Main.Utils.PieceOption;

public class PnlGameOptions extends JPanel{
	public JButton btnOk;
	public JButton btnCancel;
	public JComboBox<ColorOption> cbBoardColor;
	public JComboBox<PieceOption> cbChoosePiece;
	public JTextField txtPlayTime;
	
	private static volatile PnlGameOptions pnlGameOptions;
	
	private PnlGameOptions() {
		// TODO Auto-generated constructor stub
		// Tạo panel để chứa các tùy chọn
		this.setLayout(new BorderLayout());
		
		JPanel pnlOptions = new JPanel(new GridLayout(3, 2));

		// Tùy chọn màu bàn cờ
		JLabel lblBoardColor = new JLabel("Board Color:");
		cbBoardColor = new JComboBox<>(new ColorOption[] { new ColorOption("Xanh lá cây", "#739552,#EBECD0"),
				new ColorOption("Nâu", "#B88762,#EDD6B0"), new ColorOption("Đỏ", "#BB5746,#F5DBC3") });

		// Tùy chọn chọn quân cờ
		JLabel lblChoosePiece = new JLabel("Choose Piece:");
		cbChoosePiece = new JComboBox<>(new PieceOption[] {
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
		this.setPreferredSize(new Dimension(700, 450));
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
