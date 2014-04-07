package de.comyoutech.cowboyandalien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static Sound jump_sound;
	public static Sound jump_sound;
	public static Sound jump_sound;
	public static Sound explosion;

	public Assets() {
	}

	public static void load() {
		coolsound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));

	}
}
