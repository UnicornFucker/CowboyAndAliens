package de.comyoutech.cowboyandalien;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.CoinEntity;
import de.comyoutech.cowboyandalien.entities.EnemyEntity;
import de.comyoutech.cowboyandalien.entities.Entity;
import de.comyoutech.cowboyandalien.entities.HoleEntity;
import de.comyoutech.cowboyandalien.entities.MovableBlockEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity.State;
import de.comyoutech.cowboyandalien.entities.ShotEntity;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldRenderer {

    private final ShapeRenderer debugRenderer = new ShapeRenderer();

    private final OrthographicCamera cam;

    private TextureRegion idleLeft;
    private TextureRegion idleRight;
    private TextureRegion blockTextureGround;
    private TextureRegion blockTexturePlatform;
    private TextureRegion frame;
    private TextureRegion jumpLeft;
    private TextureRegion fallLeft;
    private TextureRegion jumpRight;
    private TextureRegion fallRight;
    private TextureRegion textureShot;
    private TextureRegion textureUfoEnemy;

    private TextureRegion textureEnemy;
    private TextureRegion textureBlockObstacle;

    private TextureRegion textureCoins;

    private final List<ShotEntity> shots;

    private final float CAMERA_WIDTH;
    private final float CAMERA_HEIGHT;
    private final int level = 1;

    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;

    private final SpriteBatch spriteBatch;
    private final BitmapFont font;
    private boolean debug = false;

    private TextureRegion background;

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public WorldRenderer(boolean debug, MyGdxGame game) {
        CAMERA_HEIGHT = EntityStore.levelHeight;
        CAMERA_WIDTH = EntityStore.levelWidth;
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        initializeTextures();
        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);
        shots = new ArrayList<ShotEntity>();

    }

    private void initializeTextures() {
        idleLeft = Assets.texturePlayerIdleLeft;
        idleRight = Assets.textureplayerIdleRight;

        blockTextureGround = Assets.textureBlockGround;
        blockTexturePlatform = Assets.textureBlockPlatform;

        walkLeftAnimation = Assets.animationWalkLeft;
        walkRightAnimation = Assets.animationWalkRight;

        jumpLeft = Assets.textureJumpLeft;
        jumpRight = new TextureRegion(jumpLeft);
        jumpRight.flip(true, false);

        fallLeft = Assets.textureFallLeft;
        fallRight = new TextureRegion(fallLeft);
        fallRight.flip(true, false);

        textureShot = Assets.textureShot;

        switch (level) {

        case 1:
            float x = 0;
            float y = 0;

            background = Assets.backgroundSpriteLevel1;

            break;

        case 2:
            x = 0;
            y = 0;
            background = Assets.backgroundSpriteLevel2;
            break;
//        case 3:
//            x = 0;
//            y = 0;
//            for (int i = 0; i < 5; i++) {
//                spriteBatch
//                        .draw(background1, x, y, CAMERA_WIDTH, CAMERA_HEIGHT);
//                x += CAMERA_WIDTH;
//            }
//            break;
        default:
            break;
        }

        textureEnemy = Assets.textureEnemy;

        textureCoins = Assets.spriteCoin;

        textureUfoEnemy = Assets.textureEnemyUfo;
        textureBlockObstacle = Assets.textureObstacle;
    }

    public void render() {

        if (!EntityStore.playerIsDead) {
            spriteBatch.begin();
            float xx = 0;
            float yy = 0;
            for (int i = 0; i < 5; i++) {
                spriteBatch.draw(background, xx, yy, CAMERA_WIDTH,
                        CAMERA_HEIGHT);
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
                    frame = player.isFacingLeft() ? jumpLeft : jumpRight;
                }
                else {
                    frame = player.isFacingLeft() ? fallLeft : fallRight;
                }
            }
            float xP, yP, wP, hP;
            Rectangle rectP = player.getBounds();

            xP = rectP.x;
            yP = rectP.y;
            wP = rectP.width;
            hP = rectP.height;

            spriteBatch.draw(frame, xP, yP, wP, hP);

            moveCamera(player.getPosition().x, CAMERA_HEIGHT / 2);
            for (Entity e : EntityStore.entityList) {
                if (e instanceof BlockEntity) {
                    BlockEntity block = (BlockEntity) e;

                    TextureRegion region;
                    if (block.getBounds().y > 0) {
                        region = blockTexturePlatform;
                    }
                    else {
                        region = blockTextureGround;
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
//                  TODO putzen
                }
                else if (e instanceof EnemyEntity) {
                    EnemyEntity enemy = (EnemyEntity) e;

                    drawEntity(spriteBatch, enemy.getBounds(), textureEnemy);

                }
                else if (e instanceof MovableBlockEntity) {
                    MovableBlockEntity block = (MovableBlockEntity) e;

                    drawEntity(spriteBatch, block.getBounds(), textureUfoEnemy);

                }
                else if (e instanceof HoleEntity) {
                    HoleEntity theHole = (HoleEntity) e;

                    drawEntity(spriteBatch, theHole.getBounds(),
                            textureBlockObstacle);

                }
                else if (e instanceof CoinEntity) {
                    CoinEntity coin = (CoinEntity) e;
                    float x, y, w, h;
                    Rectangle rect = coin.getBounds();
                    x = rect.x;
                    y = rect.y;
                    w = rect.width;
                    h = rect.height;
                    spriteBatch.draw(textureCoins, x, y, w, h);

                    drawEntity(spriteBatch, coin.getBounds(), textureCoins);

                }
            }

        }
        else {
//            TODO Deadscreen
        }
        EntityStore.entityList.removeAll(shots);
        debugRenderer.end();

        spriteBatch.end();
        if (debug) {
            drawDebug();
        }
    }

    private void drawEntity(SpriteBatch batch, Rectangle rect,
            TextureRegion region) {
        float x, y, w, h;

        x = rect.x;
        y = rect.y;
        w = rect.width;
        h = rect.height;

        batch.draw(region, x, y, w, h);

    }

    private void drawDebug() {

        debugRenderer.begin(ShapeType.Line);
        debugRenderer.setProjectionMatrix(cam.combined);
        for (BlockEntity block : EntityStore.getDrawableBlocks(
                (int) CAMERA_WIDTH, (int) CAMERA_HEIGHT)) {
            Rectangle rect = block.getBounds();
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        PlayerEntity player = EntityStore.player;
        Rectangle rect = player.getBounds();
        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        debugRenderer.end();
    }

    public void moveCamera(float x, float y) {
        PlayerEntity player = EntityStore.player;
        if ((player.getPosition().x > (CAMERA_WIDTH / 2))) {
            cam.position.set(x, y, 0);
            cam.update();
        }

    }

    public int getLevel() {
        return level;
    }
}
