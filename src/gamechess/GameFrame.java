/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamechess;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author SON
 */
public class GameFrame extends JFrame{

    private int  widthBoard = 800;
    private int  heightBoard = 800;
    public GameFrame()  {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng cửa sổ 
            this.setSize(widthBoard, heightBoard); // Khởi tạo kích thước
            this.setResizable(false);    // Cấm điều chỉnh kích thước của cửa sổ
            this.setTitle("Game Chess"); // Đặt tên cho cửa sổ
//            this.setUndecorated(true); // Để loại bỏ viền JFrame


            Controller controller = new Controller("player1");
            this.add(controller); 
            this.pack(); // Cài đặt cho cửa sổ phù hợp với kích thước của GamePanel

            this.setLocationRelativeTo(null); // Căn giữa màn hình
            this.setVisible(true);            // Hiển thị cửa sổ
    }
    
}
