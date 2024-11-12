package Main.Pnl;

import javax.swing.*;
import java.awt.*;

public class PnlHome extends JPanel {

	public static int height = 1600;
	public static int width = height * 4 / 3;

	public JButton newGameButton;
	public JButton tutorialButton;
	public JButton exitButton;

	public CardLayout cardLayout;
	public JPanel cardPanel;

	public PnlHome() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(width, height));

		// Khởi tạo CardLayout
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		// Tạo background panel và thêm vào cardPanel
		JPanel backgroundPanel = createBackgroundPanel();
		cardPanel.add(backgroundPanel, "Menu");

		this.add(cardPanel, BorderLayout.CENTER);
	}

	// Phương thức tạo background panel
	private JPanel createBackgroundPanel() {
		JPanel backgroundPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Kiểm tra nếu đường dẫn hình ảnh hợp lệ
				ImageIcon icon = loadImageIcon("/Main/images/main01.png");
					Image image = icon.getImage();
					g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		backgroundPanel.setLayout(new GridBagLayout());  // Căn giữa menu vào background

		// Tạo menu panel và thêm vào backgroundPanel
		JPanel menuPanel = createMenuPanel();
		backgroundPanel.add(menuPanel);

		return backgroundPanel;
	}

	// Phương thức tải hình ảnh từ đường dẫn
	private ImageIcon loadImageIcon(String path) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(getClass().getResource(path));
		} catch (Exception e) {
			System.out.println("Error loading image: " + e.getMessage());
		}
		return icon;
	}

	// Tạo menu panel với các nút điều khiển
	private JPanel createMenuPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setOpaque(true);
		menuPanel.setBackground(new Color(0, 0, 0, 150)); // Màu nền trong suốt
		menuPanel.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 235), 10));
		menuPanel.setPreferredSize(new Dimension(400, 500));

		JLabel titleLabel = new JLabel("Main Menu:");
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(135, 206, 235));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(titleLabel);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 80)));

		// Tạo các nút
		newGameButton = createMenuButton("NEW GAME");
		tutorialButton = createMenuButton("TUTORIAL");
		exitButton = createMenuButton("EXIT");

		menuPanel.add(newGameButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		menuPanel.add(tutorialButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		menuPanel.add(exitButton);

		JPanel centeredMenu = new JPanel();
		centeredMenu.setLayout(new GridBagLayout());
		centeredMenu.add(menuPanel);

		return centeredMenu;
	}

	// Tạo các nút trong menu
	private JButton createMenuButton(String text) {
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setMaximumSize(new Dimension(300, 60));
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);

		button.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 4),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)
		));

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(255, 255, 200)); // Màu khi hover
				button.setForeground(Color.BLACK); // Màu chữ khi hover
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(Color.BLACK); // Màu khi không hover
				button.setForeground(Color.WHITE); // Màu chữ khi không hover
			}
		});
		return button;
	}
}
