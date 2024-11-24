package Main.Pnl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import Main.Frame.GameFrame;
import Main.Utils.BackgroundPanel;
import Main.Utils.ButtonWithIcon;

public class PnlSideBar extends JPanel {

    public ButtonWithIcon btnBack, btnSound, btnSettings;

    // Thực hiện Singleton
    private static volatile PnlSideBar pnlSideBarInstance;

    // Constructor private để ngăn không cho tạo đối tượng bên ngoài lớp
    private PnlSideBar() {
        this.setPreferredSize(new Dimension(GameFrame.width / 4, GameFrame.height));
        this.setLayout(new BorderLayout());
        
        btnBack = new ButtonWithIcon("src/Main/Resources/Icons/left-arrow-1.png", GameFrame.width / 8, 100);
        JPanel pnlInner = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlInner.setOpaque(false);
        pnlInner.add(btnBack);

        JPanel pnlButtonBack = new BackgroundPanel("src/Main/Resources/Icons/bgrSideBar.jpg");
        pnlButtonBack.setLayout(new BorderLayout());
        pnlButtonBack.setPreferredSize(new Dimension(GameFrame.width / 4, GameFrame.height));
        pnlButtonBack.add(pnlInner, BorderLayout.CENTER);

        this.add(pnlButtonBack, BorderLayout.CENTER);
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
