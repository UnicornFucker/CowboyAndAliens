package de.comyoutech.cowboyandalien.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.comyoutech.cowboyandalien.ItemShopScreen;
import de.comyoutech.cowboyandalien.MyGdxGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new ItemShopScreen(), config);
        config.title = "World";
        config.width = 1200;
        config.height = 640;

    }

}
