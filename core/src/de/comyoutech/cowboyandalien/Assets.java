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
	public static Sound getCoin_sound;
	public static Sound shot_sound;
	public static Sound explosion;

	public Assets() {
	}

	public static void load() {
		jump_sound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
//		getCoin_sound = Gdx.audio.newSound(Gdx.files.internal("coin2.wav"));
//		explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
//		shot_sound = Gdx.audio.newSound(Gdx.files.internal("shito1.wav"));

	}
}
