package gamechess;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Piece.*;
import java.awt.Color;
import java.awt.GridLayout;

/**
 *
 * @author SON
 */
public class Squares extends JPanel {


    private final int row; // Số hàng
    private final int col; // Số cột
    protected JPanel[][] arr; // Mảng 2 chiều của Squares
    public boolean[][] status;
    protected Piece[][] arrpiece;
    

    public Squares(int row, int col) {
        this.setLayout(new GridLayout(row, col, 0, 0)); // Không có khoảng cách giữa các ô
        this.setPreferredSize(new Dimension(800, 800)); // khởi tạo kích thước ô cờ
        
        this.row = row;
        this.col = col;
        this.arr = new JPanel[row][col];
        this.status = new boolean[row][col]; // Khởi tạo mảng boolean 2 chiều
        this.arrpiece = new Piece[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = new JPanel(); // Khởi tạo mỗi ô là một JPanel
                arr[i][j].setBorder(null); // Loại bỏ viền
                arr[i][j].setPreferredSize(new Dimension(100, 100)); // Đặt kích thước cho mỗi ô
                this.add(arr[i][j]); // Thêm ô vào JPanel hiện tại
                status[i][j] = false;
            }
        }
    }

    public void addPiece(Piece piece) {
        if (piece.positionX >= 0 && piece.positionX < this.row && piece.positionY >= 0 && piece.positionY < this.col) {
            URL url = getClass().getResource(piece.path);
            if (url != null) {
                // Xóa tất cả các thành phần trong ô trước khi thêm mới
                arr[piece.positionX][piece.positionY].removeAll();

                ImageIcon originalIcon = new ImageIcon(url);
                Image image = originalIcon.getImage(); // Lấy hình ảnh gốc
                Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Thay đổi kích thước
                JLabel label = new JLabel(new ImageIcon(resizedImage));
                label.setPreferredSize(new Dimension(100, 100)); // Đặt kích thước cho JLabel

                // Thêm JLabel vào ô cờ
                arr[piece.positionX][piece.positionY].add(label);
                arr[piece.positionX][piece.positionY].revalidate();
                arr[piece.positionX][piece.positionY].repaint();
                status[piece.positionX][piece.positionY] = true;
                arrpiece[piece.positionX][piece.positionY] = piece;
            } else {
                System.out.println("Không tìm thấy hình ảnh tại đường dẫn: " + piece.path);
            }
        } else {
            System.out.println("Tọa độ ô cờ không hợp lệ.");
        }
    }

    public void setColor(int row, int col, Color color) {
        arr[row][col].setBackground(color);
        arr[row][col].setPreferredSize(new Dimension(100, 100)); // Đặt kích thước ô cờ
    }
    
}
