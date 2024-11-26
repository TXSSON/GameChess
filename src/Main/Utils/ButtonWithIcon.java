package Main.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Main.Frame.GameFrame;

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

			if (image != null) {
				Image scaledImage = image.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(scaledImage);

				this.setIcon(icon);
			} else {
				System.out.println("Ảnh không tồn tại hoặc không đọc được!");
			}
		} catch (Exception e) {
			System.out.println("Lỗi mở ảnh: " + e.getMessage());
		}

		this.setPreferredSize(new Dimension(this.width, this.height));

		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
	}


	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
		initButtonWithIcon();

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
