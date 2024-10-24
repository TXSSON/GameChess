package Main.Pnl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Frame.GameFrame;
import Main.Utils.ColorOption;

public class CreatePnlBoardChess extends JPanel {
    private JPanel[][] squares = new JPanel[8][8]; // Lưu trữ các ô của bàn cờ

    public CreatePnlBoardChess(ColorOption selectedColor) {
        this.setLayout(new GridLayout(8, 8)); // Bàn cờ 8x8
        this.setPreferredSize(new Dimension(GameFrame.width * 3 / 4, GameFrame.height)); // Chia diện tích hợp lý

        boolean isWhite = true; // Bắt đầu từ ô trắng

        Color color1 = Color.decode(selectedColor.getHexCode().split(",")[0].trim()); // Màu 1
        Color color2 = Color.decode(selectedColor.getHexCode().split(",")[1].trim()); // Màu 2

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                if (isWhite) {
                    square.setBackground(color1);
                } else {
                    square.setBackground(color2);
                }
                this.add(square);
                squares[row][col] = square; // Lưu lại ô hiện tại
                isWhite = !isWhite; // Chuyển đổi màu cho ô tiếp theo
            }
            isWhite = !isWhite; // Chuyển đổi màu khi sang hàng tiếp theo
        }
    }

    public void addPieceToPanel(String imagePath1, int row, int col) {
        try {
            // Đọc hình ảnh từ đường dẫn
            BufferedImage pieceImage = ImageIO.read(new File("D:/CodeJava/GameChess-ver2/src/ChessEngine/piece/piece-image/rook-w.png"));
            
            // Tính kích thước hình ảnh mới
            int squareSize = squares[row][col].getWidth(); // Lấy kích thước của ô cờ
            Image scaledImage = pieceImage.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH); // Thay đổi kích thước hình ảnh

            // Tạo JLabel chứa hình ảnh đã được thay đổi kích thước
            JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage)); 
            squares[row][col].add(pieceLabel); // Thêm JLabel vào ô tương ứng
            squares[row][col].revalidate(); // Cập nhật lại giao diện
            squares[row][col].repaint(); // Vẽ lại để hiện hình ảnh
        } catch (IOException e) {
            System.out.println("ảnh lỗi");
        }
    }

}
