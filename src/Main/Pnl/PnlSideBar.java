package Main.Pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import Main.Frame.GameFrame;
import Main.Utils.ButtonWithIcon;

public class PnlSideBar extends JPanel {

    public ButtonWithIcon btnBack, btnSound, btnSettings;

    // Thực hiện Singleton
    private static volatile PnlSideBar pnlSideBarInstance;

    // Constructor private để ngăn không cho tạo đối tượng bên ngoài lớp
    private PnlSideBar() {
        this.setBackground(Color.yellow);
        this.setPreferredSize(new Dimension(GameFrame.width / 4, GameFrame.height));
        this.setLayout(new BorderLayout());
        
       btnBack = new ButtonWithIcon("src/Main/Resources/Icons/left-arrow-1.png", GameFrame.width / 8, 100);
       JPanel pnlButtonBack = new JPanel();
       pnlButtonBack.add(btnBack);
       pnlButtonBack.setBackground(Color.YELLOW);
        // Panel cho các nút chức năng khác
//       JPanel pnlControls = new JPanel();
//       btnSound = initButtonWithIcon("src/Main/Resources/Icons/left-arrow-1.png", 100, 50);
//       btnSettings = initButtonWithIcon("src/Main/Resources/Icons/left-arrow-1.png", 100, 50);
//
//       pnlControls.add(btnSound);
//        pnlControls.add(btnSettings);
//
        this.add(pnlButtonBack, BorderLayout.NORTH);
//        this.add(pnlControls, BorderLayout.CENTER);
    }

    // Phương thức Singleton để lấy instance duy nhất của PnlSideBar
    public static PnlSideBar getSideBarInstance() {
        if (pnlSideBarInstance == null) {
            synchronized (PnlSideBar.class) {
                if (pnlSideBarInstance == null) {
                    pnlSideBarInstance = new PnlSideBar();
                }
            }
        }
        return pnlSideBarInstance;
    }
}
