package Main.Utils;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class PlayerSound {

	private volatile static PlayerSound playerSound;
	private String filePath = "";
	public static volatile boolean isMute = false;

	private PlayerSound() {
	}

	public static PlayerSound getInstacePlaySound() {
		if (playerSound == null) {
			synchronized (PlayerSound.class) {
				if (playerSound == null) {
					playerSound = new PlayerSound();
				}
			}
		}
		return playerSound;
	}

	public void useSound(String filePath) {
		this.filePath = filePath;
		if (!isMute) {
			try {
				FileInputStream fileInputStream = new FileInputStream(this.filePath);
				Player player = new Player(fileInputStream);
				player.play();
			} catch (Exception e) {
				System.out.println("Lỗi khi phát file MP3!");
				e.printStackTrace();
			}
		}
	}
}
