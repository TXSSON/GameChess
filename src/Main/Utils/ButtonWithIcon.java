package Main.Utils;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonWithIcon extends JButton {
	
	private String iconPath;
	private int width;
	private int height;
	
	public ButtonWithIcon(String iconPath, int width, int height) {
		// TODO Auto-generated constructor stub
		this.iconPath = iconPath;
		this.width = width;
		this.height = height;	
		initButtonWithIcon();
	}
	
    // Phương thức khởi tạo nút với hình ảnh
    private void initButtonWithIcon() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(this.iconPath));
        } catch (Exception e) {
            System.out.println("Lỗi mở ảnh: " + e.getMessage());
        }
        ImageIcon icon = new ImageIcon(image);
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(this.width, this.height));
    }

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
    
    
}
