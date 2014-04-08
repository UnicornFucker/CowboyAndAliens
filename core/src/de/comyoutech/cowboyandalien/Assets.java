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
	public static Sound background_sound;
	public static Texture explosion_texture;
	public static Sprite explosion_sprite;

	public Assets() {
	
	}
	
	public static void load() {
		/** Textures **/
		explosion_texture = new Texture(
				Gdx.files.internal("textures/SpriteExplison 2.png"));
		
		explosion_sprite = new Sprite(explosion_texture);
		
		/** Loading Animation Sounds **/
		jump_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.wav"));
		getCoin_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin2.wav"));
		explosion = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
		shot_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/shito1.wav"));
		background_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/background_music.mp3"));
	}
}
