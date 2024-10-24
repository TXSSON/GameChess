package Main.Pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

import Main.Frame.GameFrame;
import Main.Utils.ColorOption;

public class PnlNewGame extends JPanel {

    public PnlNewGame() {
        // Sử dụng BorderLayout để chia bố cục
        this.setLayout(new BorderLayout());
        this.setBackground(Color.blue);


        // Tạo và thêm panel Tool ở bên phải
        JPanel pnlTool = createPnlTool();
        this.add(pnlTool, BorderLayout.WEST);
    }

    

    public JPanel createPnlTool() {
        JPanel pnlTool = new JPanel();
        pnlTool.setBackground(Color.yellow);
        pnlTool.setPreferredSize(new Dimension(GameFrame.width / 4, GameFrame.height)); // Kích thước cho Tool panel
        return pnlTool;
    }
    
    
}
