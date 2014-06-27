package de.comyoutech.cowboyandalien.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import de.comyoutech.cowboyandalien.entities.AbstractEntity;
import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.CactusEntity;
import de.comyoutech.cowboyandalien.entities.CoinEntity;
import de.comyoutech.cowboyandalien.entities.EnemyEntity;
import de.comyoutech.cowboyandalien.entities.GateEntity;
import de.comyoutech.cowboyandalien.entities.MovableBlockEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity.State;
import de.comyoutech.cowboyandalien.entities.ShotEntity;
import de.comyoutech.cowboyandalien.entities.SpikeEntity;
import de.comyoutech.cowboyandalien.model.Assets;
import de.comyoutech.cowboyandalien.model.EntityStore;

/**
 * The class that renders the game.
 * 
 * @author Leo, Felix, Thorben
 * 
 */
public class WorldRenderer {

    /**
     * A ShapeRenderer-object that draws.
     */
    private final ShapeRenderer debugRenderer = new ShapeRenderer();

    /**
     * An OrthopraphicCamera-object.
     */
    private final OrthographicCamera cam;

    /*
     * TextureRegions for all entities that can be drawn.
     */
    private TextureRegion idleLeft;
    private TextureRegion idleRight;

    private TextureRegion blockTextureIce;
    private TextureRegion blockTextureGround;
    private TextureRegion blockTexturePlatform;
    private TextureRegion frame;
    private TextureRegion jumpLeft;
    private TextureRegion fallLeft;
    private TextureRegion jumpRight;
    private TextureRegion fallRight;
    private TextureRegion textureShot;
    private TextureRegion textureUfoEnemy;
    private TextureRegion textureGate;

    private TextureRegion textureEnemy;
    private TextureRegion textureBlockObstacle;
    private TextureRegion textureBoss;
    private TextureRegion textureSpike;

    private TextureRegion textureCoins;

    /**
     * Width of the camera.
     */
    private final float CAMERA_WIDTH;
    /**
     * Height of the camera.
     */
    private final float CAMERA_HEIGHT;
    /**
     * The level that is loaded.
     */
    private final int level = 1;

    /**
     * Animation for the player walking left.
     */
    private Animation walkLeftAnimation;
    /**
     * Animation for the player walking left.
     */
    private Animation walkRightAnimation;

    /**
     * Batch for drawing.
     */
    private final SpriteBatch spriteBatch;

    /**
     * The background.
     */
    private TextureRegion background;

    // TODO
    boolean endBoss = false;

    public WorldRenderer() {

        CAMERA_HEIGHT = EntityStore.levelHeight;
        CAMERA_WIDTH = EntityStore.levelWidth;
        spriteBatch = new SpriteBatch();
        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        initialize();

    }

    /**
     * Initializes textures and the camera.
     */
    private void initialize() {
        initializeTextures();
        cam.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);
    }

    /**
     * Initializes all textures.
     */
    private void initializeTextures() {
        idleLeft = Assets.texturePlayerIdleLeft;
        idleRight = Assets.texturePlayerIdleRight;

        blockTextureGround = Assets.textureBlockGround;
        blockTexturePlatform = Assets.textureBlockPlatform;
        blockTextureIce = Assets.textureIceBlock;

        walkLeftAnimation = Assets.animationWalkLeft;
        walkRightAnimation = Assets.animationWalkRight;

        textureBoss = Assets.textureBoss;

        jumpLeft = Assets.textureJumpLeft;
        jumpRight = new TextureRegion(jumpLeft);
        jumpRight.flip(true, false);

        fallLeft = Assets.textureFallLeft;
        fallRight = new TextureRegion(fallLeft);
        fallRight.flip(true, false);

        textureShot = Assets.textureShot;

        textureGate = Assets.textureGate;

        textureSpike = Assets.textureSpike;

        switch (level) {

        case 1:
            background = Assets.backgroundSpriteLevel1;
            break;

        case 2:
            background = Assets.backgroundSpriteLevel2;
            break;
        default:
            break;
        }

        textureEnemy = Assets.textureEnemy;

        textureCoins = Assets.textureCoin;

        textureUfoEnemy = Assets.textureEnemyUfo;
        textureBlockObstacle = Assets.textureObstacle;
    }

    /**
     * Renders the screen based on the data.
     */
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        float xx = 0;
        float yy = 0;
        for (int i = 0; i < 15; i++) {
            spriteBatch.draw(background, xx, yy, CAMERA_WIDTH, CAMERA_HEIGHT);
            xx += CAMERA_WIDTH;
        }

        spriteBatch.setProjectionMatrix(cam.combined);

        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeType.Line);

        /** Loading level Background **/

        PlayerEntity player = EntityStore.player;

        frame = player.isFacingLeft() ? idleLeft : idleRight;
        if (player.getState().equals(State.WALKING)) {
            frame = player.isFacingLeft() ? walkLeftAnimation.getKeyFrame(
                    player.getStateTime(), true) : walkRightAnimation
                    .getKeyFrame(player.getStateTime(), true);
        }
        else if (player.getState().equals(State.JUMPING)) {
            if (player.getVelocity().y > 0) {
                frame = player.isFacingLeft() ? jumpRight : jumpLeft;
            }
            else {
                frame = player.isFacingLeft() ? fallRight : fallLeft;
            }
        }
        float xP, yP, wP, hP;
        Rectangle rectP = player.getBounds();

        xP = rectP.x;
        yP = rectP.y;
        wP = rectP.width;
        hP = rectP.height;

        spriteBatch.draw(frame, xP, yP, wP, hP);
        List<ShotEntity> shots = new ArrayList<ShotEntity>();

        moveCamera(player.getPosition().x, CAMERA_HEIGHT / 2);
        for (AbstractEntity e : EntityStore.entityList) {
            if (e instanceof BlockEntity) {
                BlockEntity block = (BlockEntity) e;

                TextureRegion region;
                if (block.getBounds().y > 0) {
                    region = blockTexturePlatform;
                }
                else {
                    region = blockTextureGround;
                }

                if (block.isIce) {
                    region = blockTextureIce;
                }

                drawEntity(spriteBatch, block.getBounds(), region);

            }
            else if (e instanceof ShotEntity) {
                ShotEntity shot = (ShotEntity) e;

                float x, y, w, h;
                Rectangle rect = shot.getBounds();
                x = rect.x;
                if (x > (cam.position.x + (CAMERA_WIDTH / 2))) {
                    shots.add(shot);
                    continue;
                }
                if (x < (cam.position.x - (CAMERA_WIDTH / 2))) {
                    shots.add(shot);
                    continue;
                }
                y = rect.y;

                w = rect.width;
                h = rect.height;

                if (shot.isFacingLeft()) {
                    textureShot.flip(true, false);
                }
                spriteBatch.draw(textureShot, x, y, w, h);
                if (shot.isFacingLeft()) {
                    textureShot.flip(true, false);
                }
            }
            else if (e instanceof EnemyEntity) {

                EnemyEntity enemy = (EnemyEntity) e;

                TextureRegion regionEnemy;

                if (enemy.iAmSuper) {
                    regionEnemy = textureBoss;
                }
                else {
                    regionEnemy = textureEnemy;
                }

                drawEntity(spriteBatch, e.getBounds(), regionEnemy);
            }
            else if (e instanceof MovableBlockEntity) {
                drawEntity(spriteBatch, e.getBounds(), textureUfoEnemy);
            }
            else if (e instanceof CactusEntity) {
                drawEntity(spriteBatch, e.getBounds(), textureBlockObstacle);
            }
            else if (e instanceof CoinEntity) {
                drawEntity(spriteBatch, e.getBounds(), textureCoins);
            }
            else if (e instanceof SpikeEntity) {
                drawEntity(spriteBatch, e.getBounds(), textureSpike);
            }
            else if (e instanceof GateEntity) {
                drawEntity(spriteBatch, e.getBounds(), textureGate);
            }
        }

        EntityStore.entityList.removeAll(shots);
        debugRenderer.end();

        spriteBatch.end();
    }

    /**
     * Draws an Entity.
     * 
     * @param batch
     *            The batch that draws.
     * @param rect
     *            The rect that specifies position and size.
     * @param region
     *            The texture for the entity.
     */
    private void drawEntity(SpriteBatch batch, Rectangle rect,
            TextureRegion region) {
        float x, y, w, h;

        x = rect.x;
        y = rect.y;
        w = rect.width;
        h = rect.height;

        batch.draw(region, x, y, w, h);

    }

    /**
     * Moves the camera based on the player position.
     */
    public void moveCamera(float x, float y) {
        PlayerEntity player = EntityStore.player;
        if ((player.getPosition().x > (CAMERA_WIDTH / 2))) {

            if (x > 102) {
                // System.out.println("1");
                // if (tempPos == 0) {
                // System.out.println("2");
                // tempPos = x;
                // max = tempPos -= 4F;
                // }
                // if (tempPos < max) {
                // System.out.println("3");
                // tempPos += 0.05F;
                // }
                // }
                // else {
                // tempPos = x;

                x += 4F;

                try {
                    if (!endBoss) {
                        Assets.soundBackground.stop();
                        Assets.soundBackgroundBoss.loop();
                        endBoss = true;
                    }
                } catch (Exception e) {
                    System.out.println("1");
                }

            }
        }

        cam.position.set(x, y, 0);
        cam.update();
    }

    /**
     * Returns the current level.
     * 
     * @return
     */
    public int getLevel() {
        return level;
    }
}
