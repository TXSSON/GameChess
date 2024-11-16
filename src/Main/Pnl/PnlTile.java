package Main.Pnl;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PnlTile extends JPanel {
    private boolean highlight = false; // Cờ xác định có vẽ dấu chấm không

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
        repaint(); // Yêu cầu vẽ lại ô
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (highlight) {
            Graphics2D g2d = (Graphics2D) g;

            // Điều chỉnh độ trong suốt cho dấu chấm
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Độ trong suốt 14%

            // Màu sắc của dấu chấm
            Color dotColor =  new Color(254, 40, 87, 255); // Màu nâu đậm

            // Vẽ dấu chấm ở giữa ô
            int size = Math.min(getWidth(), getHeight()) / 3; // Đường kính của dấu chấm
            int x = (getWidth() - size) / 2; // Tọa độ X cho dấu chấm
            int y = (getHeight() - size) / 2; // Tọa độ Y cho dấu chấm

            g2d.setColor(dotColor);
            g2d.fillOval(x, y, size, size);
        }
    }
}

