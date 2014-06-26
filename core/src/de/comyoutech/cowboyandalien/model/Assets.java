package de.comyoutech.cowboyandalien.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Zuständig für das Laden aller Bilder/Sounds/Animationen etc.
 * 
 * @author All
 * 
 */
public class Assets {

    /**
     * Sound-Objekte die verschiedene Sounds repräsentieren.
     * Static für einfachen Zugriff.
     */
    public static Sound soundJump;
    public static Sound soundGetCoin;
    public static Sound soundShot;
    public static Sound soundExplosion;
    public static Sound soundBackground;
    public static Sound soundBackgroundBoss;

    /**
     * Die zeitliche Länge in der ein einzelnes Bild in der Laufanimation
     * angezeigt wird
     */
    private static final float RUNANNIMATIONDURRATION = 0.12f;

    public static Texture textureExplosion;

    public static TextureRegion texturePlayerLeft;

    public static TextureRegion textureCoin;

    public static Texture textureBackgroundLevel1;
    public static Sprite backgroundSpriteLevel1;
    public static Texture textureBackgroundLevel1_2;
    public static Sprite backgroundSpriteLevel1_2;
    public static Texture textureBackgroundLevel2;
    public static Sprite backgroundSpriteLevel2;
    public static Texture textureBackgroundLevel3;
    public static Sprite backgroundSpriteLevel3;

    public static TextureRegion textureGate;

    public static TextureRegion textureSpike;

    public static TextureRegion textureBoss;

    public static TextureRegion texturePlayerIdleLeft;
    public static TextureRegion texturePlayerIdleRight;
    public static Animation animationWalkLeft;
    public static Animation animationWalkRight;
    public static TextureRegion textureJumpLeft;
    public static TextureRegion textureFallLeft;
    public static TextureRegion[] playerFrames;
    public static TextureRegion textureIceBlock;

    public static TextureRegion textureEnemyUfo;
    public static TextureRegion textureObstacle;

    public static TextureRegion textureBlockGround;
    public static TextureRegion textureBlockPlatform;

    public static TextureRegion textureEnemy;

    public static TextureRegion textureShot;

    /**
     * Startet das Laden aller Elemente.
     * Danach ist jede Klassenvariable initialisiert und ansprechbar.
     */
    public static void load() {
        loadSounds();

        loadPlayerTextures();

        loadCertainTextures();

        loadBackgroundTextures();
    }

    /**
     * Läd alle Sounds
     */
    private static void loadSounds() {
        soundJump = getSound("sounds/jump.wav");
        soundGetCoin = getSound("sounds/coin2.wav");
        soundExplosion = getSound("sounds/explosion.wav");
        soundShot = getSound("sounds/shito1.wav");
        soundBackground = getSound("sounds/background_music.mp3");
        soundBackgroundBoss = getSound("sounds/boss.mp3");
    }

    /**
     * Läd Texturen für den Spieler.
     */
    private static void loadPlayerTextures() {

        texturePlayerIdleRight = getTextureRegion("player/stand.png");

        texturePlayerIdleLeft = new TextureRegion(texturePlayerIdleRight);
        texturePlayerIdleLeft.flip(true, false);

        TextureRegion[] walkRightFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            walkRightFrames[i] = new TextureRegion(new Texture(
                    Gdx.files.internal("player/walking" + (i + 1) + ".png")));
        }

        animationWalkRight = new Animation(RUNANNIMATIONDURRATION,
                walkRightFrames);

        TextureRegion[] walkLeftFrames = new TextureRegion[3];

        for (int i = 0; i < 3; i++) {
            walkLeftFrames[i] = new TextureRegion(walkRightFrames[i]);
            walkLeftFrames[i].flip(true, false);
        }

        animationWalkLeft = new Animation(RUNANNIMATIONDURRATION,
                walkLeftFrames);

        textureJumpLeft = getTextureRegion("player/jump1.png");
        textureFallLeft = getTextureRegion("player/jump2.png");

    }

    /**
     * Läd verschiedene Bilder für z.Bsp. Gegner/Schuss/Hindernis
     */
    private static void loadCertainTextures() {

        textureShot = getTextureRegion("images/bullet-flame.png");
        textureEnemy = getTextureRegion("enemy/Monster.png");
        textureEnemyUfo = getTextureRegion("enemy/ufo.png");

        textureObstacle = getTextureRegion("textures/Kacktus.png");

        textureCoin = getTextureRegion("textures/coin.gif");

        textureGate = getTextureRegion("textures/gate.png");

        textureSpike = getTextureRegion("textures/spike.png");

        textureBlockGround = getTextureRegion("images/block_neu3.png");

        textureBlockPlatform = getTextureRegion("images/block_neu2.png");

        textureBoss = getTextureRegion("enemy/final_boss.png");

        textureIceBlock = getTextureRegion("textures/iceblock.png");
    }

    private static TextureRegion getTextureRegion(String string) {
        return new TextureRegion(new Texture(Gdx.files.internal(string)));
    }

    /**
     * Läd Bilder die als Hintergrund angezeigt werden.
     */
    private static void loadBackgroundTextures() {
        textureBackgroundLevel1 = new Texture(
                Gdx.files.internal("backgrounds/background.png"));

        backgroundSpriteLevel1 = new Sprite(textureBackgroundLevel1);
        backgroundSpriteLevel1.flip(true, false);

        textureBackgroundLevel2 = new Texture(
                Gdx.files.internal("backgrounds/background2.jpg"));
        backgroundSpriteLevel2 = new Sprite(textureBackgroundLevel2);
        backgroundSpriteLevel2.flip(true, false);

    }

    /**
     * Läd einen Sound aus einem angegebenen Pfad.
     * 
     * @param path Pfad unter dem der Sound zu finden ist.
     * @return Den Sound.
     */
    private static Sound getSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }

}
