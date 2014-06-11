package de.comyoutech.cowboyandalien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Sound jump_sound;
    public static Sound getCoin_sound;
    public static Sound shot_sound;
    public static Sound explosion;
    public static Sound background_sound;

    private static final float RUNNING_FRAME_DURATION = 0.06f;

    public static Texture explosion_texture;
    public static TextureRegion playerLeft;

    /* Background Textures */

    public static Texture textureBackground_level1;
    public static Sprite background_sprite_level1;
    public static Texture textureBackground_level2;
    public static Sprite background_sprite_level2;
    // public static Texture textureBackground_level3;
    // public static Sprite background_sprite_level3;

    public static TextureRegion idleLeft;
    public static TextureRegion idleRight;
    public static TextureRegion blockTextureGround;
    public static TextureRegion blockTexturePlatform;
    public static Animation leftAnimation;
    public static Animation rightAnimation;
    public static TextureRegion jumpLeft;
    public static TextureRegion fallLeft;

    public static TextureRegion textureEnemy;

    public static TextureRegion textureShot;

    private TextureRegion playerRight;
    private TextureRegion block;
    private TextureRegion player;

    private static final int COLS = 6;
    private static final int ROWS = 2;

    public static TextureRegion[] playerFrames;

    private static Animation animationLeft;
    private static Animation animationRight;

    public Assets() {
        load();
    }

    public static void load() {
        // loadSounds();

        loadPlayerTextures();
        loadBackgrounds();

    }

    private static void loadPlayerTextures() {
        TextureAtlas atlas = new TextureAtlas(
                Gdx.files.internal("images/textures/textures.pack"));
        idleLeft = atlas.findRegion("bob-01");
        blockTextureGround = new TextureRegion(new Texture(
                Gdx.files.internal("images/block_neu3.png")));
        blockTexturePlatform = new TextureRegion(new Texture(
                Gdx.files.internal("images/block_neu2.png")));
        idleRight = new TextureRegion(idleLeft);
        idleRight.flip(true, false);
        TextureRegion[] walkLeftFrames = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            walkLeftFrames[i] = atlas.findRegion("bob-0" + (i + 2));
        }
        animationLeft = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);

        TextureRegion[] walkRightFrames = new TextureRegion[5];

        for (int i = 0; i < 5; i++) {
            walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
            walkRightFrames[i].flip(true, false);
        }
        animationRight = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);
        jumpLeft = atlas.findRegion("bob-up");
        fallLeft = atlas.findRegion("bob-down");

        textureShot = new TextureRegion(new Texture(
                Gdx.files.internal("images/bullet-flame.png")));
        textureEnemy = new TextureRegion(new Texture(
                Gdx.files.internal("enemy/Monster.png")));
    }

    private static void loadSounds() {
        jump_sound = getSound("sounds/jump.wav");
        getCoin_sound = getSound("sounds/coin2.wav");
        explosion = getSound("sounds/explosion.wav");
        shot_sound = getSound("sounds/shito1.wav");
        background_sound = getSound("sounds/background_music.mp3");
    }

    private static void loadBackgrounds() {
        textureBackground_level1 = new Texture(
                Gdx.files.internal("backgrounds/background.png"));

        background_sprite_level1 = new Sprite(textureBackground_level1);

        background_sprite_level1.flip(true, false);
        textureBackground_level2 = new Texture(
                Gdx.files.internal("backgrounds/background2.jpg"));

        background_sprite_level2 = new Sprite(textureBackground_level2);

        background_sprite_level2.flip(true, false);

    }

    public static TextureRegion getPlayerTextureRight() {

        explosion_texture = null;

        return null;
    }

    private static Sound getSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }

}
