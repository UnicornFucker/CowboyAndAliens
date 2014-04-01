package de.comyoutech.cowboyandalien.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.comyoutech.cowboyandalien.MyGdxGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "CowboysAndAliens";
		config.width = 480;
		config.height = 320;

		new LwjglApplication(new MyGdxGame(), config);
	}
}
