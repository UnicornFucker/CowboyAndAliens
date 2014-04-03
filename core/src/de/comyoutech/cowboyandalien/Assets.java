package de.comyoutech.cowboyandalien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {

	public static Texture texture_figure;
	public static Sprite sprite_figure;
	public static Sound backgroundsound;

	public Assets() {

	}

	public static void load() {
		//backgroundsound = Gdx.audio.newSound(Gdx.files.internal("backgroundsoundhier!!"));
		
		/** Initial Player **/
		
		texture_figure = new Texture(Gdx.files.internal("mainmenu/toad.gif"));
		sprite_figure = new Sprite(texture_figure);
		sprite_figure.flip(false, true);
		
		
		
	}

}
