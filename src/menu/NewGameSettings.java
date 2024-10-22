package menu;

import javax.swing.*;
import java.awt.*;

public class NewGameSettings extends JFrame {
    private JComboBox<String> timeDropdown;
    private JComboBox<String> pieceDropdown;
    private JButton startButton;

    public NewGameSettings() {
        // Cài đặt cửa sổ
        setTitle("Cài đặt Trò chơi mới");
        setSize(800, 600);
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng cửa sổ này mà không tắt toàn bộ chương trình

        // Tạo JPanel tùy chỉnh để hiển thị hình nền
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("images/newgame.jpg"); // Đường dẫn hình nền
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Vẽ ảnh co giãn theo kích thước panel
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để bố trí các thành phần

        // Font cho các thành phần
        Font font = new Font("Arial", Font.BOLD, 18);  // Font Arial, cỡ 18, đậm

        // Lựa chọn thời gian
        JLabel timeLabel = new JLabel("Chọn thời gian:");
        timeLabel.setFont(font);  // Tăng cỡ chữ cho JLabel
        timeLabel.setForeground(Color.WHITE);  // Đặt màu chữ là trắng
        timeDropdown = new JComboBox<>(new String[]{"5 phút", "10 phút", "15 phút"});
        timeDropdown.setPreferredSize(new Dimension(150, 40)); // Tăng kích thước của JComboBox
        timeDropdown.setFont(font);  // Tăng cỡ chữ cho JComboBox

        // Lựa chọn quân cờ
        JLabel pieceLabel = new JLabel("Chọn quân cờ:");
        pieceLabel.setFont(font);  // Tăng cỡ chữ cho JLabel
        pieceLabel.setForeground(Color.WHITE);  // Đặt màu chữ là trắng
        pieceDropdown = new JComboBox<>(new String[]{"Trắng", "Đen"});
        pieceDropdown.setPreferredSize(new Dimension(150, 40)); // Tăng kích thước của JComboBox
        pieceDropdown.setFont(font);  // Tăng cỡ chữ cho JComboBox

        // Nút Bắt đầu
        startButton = new JButton("Bắt đầu");
        startButton.setPreferredSize(new Dimension(200, 50)); // Tăng kích thước của JButton
        startButton.setFont(font);  // Tăng cỡ chữ cho JButton

        // Bố trí các thành phần với khoảng cách dãn ra
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 20, 30, 20); // Tạo khoảng cách giữa các thành phần
        gbc.gridx = 0;
        gbc.gridy = 0;

        backgroundPanel.add(timeLabel, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(timeDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(pieceLabel, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(pieceDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        backgroundPanel.add(startButton, gbc);

        // Đặt backgroundPanel làm nội dung chính của JFrame
        setContentPane(backgroundPanel);
    }
}
