package de.comyoutech.cowboyandalien;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Sound soundJump;
    public static Sound soundGetCoin;
    public static Sound soundShot;
    public static Sound soundExplosion;
    public static Sound soundBackground;

    private static final float RUNANNIMATIONDURRATION = 0.06f;

    public static Texture textureExplosion;

    public static TextureRegion texturePlayerLeft;

    public static Texture textureCoin;
    public static Sprite spriteCoin;

    public static Texture textureBackgroundLevel1;
    public static Sprite backgroundSpriteLevel1;
    public static Texture textureBackgroundLevel1_2;
    public static Sprite backgroundSpriteLevel1_2;
    public static Texture textureBackgroundLevel2;
    public static Sprite backgroundSpriteLevel2;
    public static Texture textureBackgroundLevel3;
    public static Sprite backgroundSpriteLevel3;

    public static TextureRegion texturePlayerIdleLeft;
    public static TextureRegion textureplayerIdleRight;
    public static Animation animationWalkLeft;
    public static Animation animationWalkRight;
    public static TextureRegion textureJumpLeft;
    public static TextureRegion textureFallLeft;
    public static TextureRegion[] playerFrames;

    public static TextureRegion textureEnemyUfo;
    public static TextureRegion textureObstacle;

    public static TextureRegion textureBlockGround;
    public static TextureRegion textureBlockPlatform;

    public static TextureRegion textureEnemy;

    public static TextureRegion textureShot;

    public Assets() {
        load();
    }

    public static void load() {
        loadSounds();

        loadPlayerTextures();

        loadCertainTextures();

        loadBackgroundTextures();

        loadCoins();
    }

    private static void loadSounds() {
        soundJump = getSound("sounds/jump.wav");
        soundGetCoin = getSound("sounds/coin2.wav");
        soundExplosion = getSound("sounds/explosion.wav");
        soundShot = getSound("sounds/shito1.wav");
        soundBackground = getSound("sounds/background_music.mp3");
    }

    private static void loadPlayerTextures() {

        TextureAtlas atlas = new TextureAtlas(
                Gdx.files.internal("images/textures/textures.pack"));

        texturePlayerIdleLeft = atlas.findRegion("bob-01");

        textureplayerIdleRight = new TextureRegion(texturePlayerIdleLeft);
        textureplayerIdleRight.flip(true, false);

        TextureRegion[] walkLeftFrames = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            walkLeftFrames[i] = atlas.findRegion("bob-0" + (i + 2));
        }

        animationWalkLeft = new Animation(RUNANNIMATIONDURRATION,
                walkLeftFrames);

        TextureRegion[] walkRightFrames = new TextureRegion[5];

        for (int i = 0; i < 5; i++) {
            walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
            walkRightFrames[i].flip(true, false);
        }

        animationWalkRight = new Animation(RUNANNIMATIONDURRATION,
                walkRightFrames);

        textureJumpLeft = atlas.findRegion("bob-up");
        textureFallLeft = atlas.findRegion("bob-down");

    }

    private static void loadCertainTextures() {

        textureShot = new TextureRegion(new Texture(
                Gdx.files.internal("images/bullet-flame.png")));
        textureEnemy = new TextureRegion(new Texture(
                Gdx.files.internal("enemy/Monster.png")));
        textureEnemyUfo = new TextureRegion(new Texture(
                Gdx.files.internal("enemy/ufo.png")));

        textureObstacle = new TextureRegion(new Texture(
                Gdx.files.internal("textures/Kacktus.png")));

    }

    private static void loadBackgroundTextures() {
        textureBackgroundLevel1 = new Texture(
                Gdx.files.internal("backgrounds/background.png"));

        backgroundSpriteLevel1 = new Sprite(textureBackgroundLevel1);
        backgroundSpriteLevel1.flip(true, false);

        textureBackgroundLevel2 = new Texture(
                Gdx.files.internal("backgrounds/background2.jpg"));
        backgroundSpriteLevel2 = new Sprite(textureBackgroundLevel2);
        backgroundSpriteLevel2.flip(true, false);

        textureBlockGround = new TextureRegion(new Texture(
                Gdx.files.internal("images/block_neu3.png")));
        textureBlockPlatform = new TextureRegion(new Texture(
                Gdx.files.internal("images/block_neu2.png")));
    }

    private static void loadCoins() {
        textureCoin = new Texture(Gdx.files.internal("textures/coin.gif"));
        spriteCoin = new Sprite(textureCoin);
        spriteCoin.flip(true, false);
    }

    private static Sound getSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }

}
