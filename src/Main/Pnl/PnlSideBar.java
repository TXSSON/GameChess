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

    public ButtonWithIcon btnBack, btnSound, btnNoSound;
    public JPanel pnlBtnBack, pnlBtnSound, pnlNorthInner, pnlSouthInner, pnlEmpty;

    // Thực hiện Singleton
    private static volatile PnlSideBar pnlSideBarInstance;

    public CardLayout cardLayout;
    public CardLayout cardLayout2;

    // Constructor private để ngăn không cho tạo đối tượng bên ngoài lớp
    private PnlSideBar() {
        this.setPreferredSize(new Dimension(GameFrame.width / 4, GameFrame.height));
        this.setLayout(new BorderLayout());
        
        JPanel pnlButtonBack = new BackgroundPanel("src/Main/Resources/Icons/bgrSideBar.png");
        pnlButtonBack.setLayout(new BorderLayout()); 
        pnlButtonBack.setPreferredSize(new Dimension(GameFrame.width / 4, GameFrame.height));
        
        btnBack = new ButtonWithIcon("src/Main/Resources/Icons/undo.png", GameFrame.width / 6, 100);
        cardLayout = new CardLayout();
        cardLayout2 = new CardLayout();
      
        pnlNorthInner = new JPanel(cardLayout);
        JPanel pnlEmpty = new JPanel();
        pnlBtnBack = new JPanel();

        pnlNorthInner.setOpaque(false);	// Đặt panel không trong suốt
        pnlEmpty.setOpaque(false);  // Đặt panel không trong suốt
        pnlBtnBack.setOpaque(false); // Đặt panel không trong suốt
        
        pnlBtnBack.add(this.btnBack);
        
        pnlNorthInner.add(pnlEmpty, "pnlEmpty");
        pnlNorthInner.add(pnlBtnBack, "pnlBtnBack");
        
        
        pnlSouthInner = new JPanel();
        pnlBtnSound = new JPanel(cardLayout2);
        
        btnSound = new ButtonWithIcon("src/Main/Resources/Icons/sound.png", 128, 128);
        btnNoSound = new ButtonWithIcon("src/Main/Resources/Icons/no-sound.png", 128, 128);
        
        pnlSouthInner.setOpaque(false);
        pnlBtnSound.setOpaque(false);
        
        pnlBtnSound.add(btnSound, "btnSound");
        pnlBtnSound.add(btnNoSound, "btnNoSound");
        pnlSouthInner.add(pnlBtnSound);
        

        pnlButtonBack.add(pnlNorthInner, BorderLayout.NORTH);
        pnlButtonBack.add(pnlSouthInner, BorderLayout.SOUTH);
        this.add(pnlButtonBack, BorderLayout.CENTER);
        cardLayout.show(pnlNorthInner, "pnlEmpty");
        cardLayout2.show(pnlBtnSound, "btnSound");
        
        
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
