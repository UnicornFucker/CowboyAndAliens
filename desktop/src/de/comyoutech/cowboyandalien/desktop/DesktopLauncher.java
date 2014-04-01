package de.comyoutech.cowboyandalien.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.comyoutech.cowboyandalien.MyGdxGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "CowboysAndAliens";
		config.width = 1024;
		config.height = 768;

		new LwjglApplication(new MyGdxGame(), config);
	}
}
