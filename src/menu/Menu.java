package menu;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private final JButton newGameButton;
    private final JButton settingsButton;
    private final JButton exitButton;

    public Menu() {
        // Thiết lập tiêu đề cho cửa sổ
        setTitle("Game Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình

        // Tạo JPanel tùy chỉnh để hiển thị hình nền
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("images/menu.jpg"); // Đường dẫn hình nền của bạn
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Vẽ ảnh co giãn theo kích thước panel
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        // Đặt backgroundPanel làm nội dung chính của JFrame
        setContentPane(backgroundPanel);

        // Thiết lập bố cục GridBagLayout cho các nút
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Tạo các nút với thiết kế tùy chỉnh
        newGameButton = createCustomButton("New Game");
        JButton continueButton = createCustomButton("Continue");
        settingsButton = createCustomButton("Settings");
        exitButton = createCustomButton("Exit");

        // Thêm các nút vào backgroundPanel
        backgroundPanel.add(newGameButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(continueButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(settingsButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(exitButton, gbc);

        // Thêm ActionListener để mở cửa sổ NewGameSettings khi nhấn nút "New Game"
        newGameButton.addActionListener(e -> {
            NewGameSettings settings = new NewGameSettings();
            settings.setVisible(true);  // Hiển thị cửa sổ NewGameSettings
        });

        // Xử lý sự kiện cho nút Exit
        exitButton.addActionListener(e -> System.exit(0));
    }

    // Phương thức để tạo JButton tùy chỉnh
    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(59, 89, 182));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Thay đổi màu nền khi rê chuột vào nút
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(59, 89, 182));
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.setVisible(true);
        });
    }
}
