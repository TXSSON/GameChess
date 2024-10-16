package com.mycompany.menu;

import javax.swing.*;
import java.awt.*;

/**
 * Menu Game với các tùy chọn New Game, Continue, Settings, và Exit.
 */
public class Menu extends JFrame {
    private JButton newGameButton;
    private JButton continueButton;
    private JButton settingsButton;
    private JButton exitButton;

    public Menu() {
        // Thiết lập tiêu đề cho cửa sổ
        setTitle("Game Menu");
        setSize(800, 400);  // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình

        // Sử dụng BorderLayout để dễ dàng quản lý các khu vực
        setLayout(new BorderLayout());

        // Panel chính giữa chứa các nút
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());  // Dùng GridBagLayout để căn giữa các nút theo ý muốn

        // Tạo các nút menu với kích thước mong muốn
        newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(200, 40)); // Chiều rộng và chiều cao nút

        continueButton = new JButton("Continue");
        continueButton.setPreferredSize(new Dimension(200, 40));

        settingsButton = new JButton("Settings");
        settingsButton.setPreferredSize(new Dimension(200, 40));

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(200, 40));

        // Sử dụng GridBagConstraints để căn giữa các nút trong panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0); // Tạo khoảng cách giữa các nút

        // Thêm các nút vào panel chính giữa
        centerPanel.add(newGameButton, gbc);
        centerPanel.add(continueButton, gbc);
        centerPanel.add(settingsButton, gbc);
        centerPanel.add(exitButton, gbc);

        // Tạo khoảng trống ở bên trái và bên phải
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        // Thêm panel vào các khu vực của BorderLayout
        add(centerPanel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // Hiển thị cửa sổ
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();  // Tạo và hiển thị menu
    }
}
