package de.comyoutech.cowboyandalien.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import de.comyoutech.cowboyandalien.entities.MovableBlockEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity.State;
import de.comyoutech.cowboyandalien.entities.ShotEntity;
import de.comyoutech.cowboyandalien.entities.SpikeEntity;
import de.comyoutech.cowboyandalien.model.Assets;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldRenderer {

    private final ShapeRenderer debugRenderer = new ShapeRenderer();

    private final OrthographicCamera cam;

    private BitmapFont font;
    private CharSequence str = "YOU ARE DEAD";

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
    private TextureRegion textureSpike;

    private TextureRegion textureCoins;

    private List<ShotEntity> shots;

    private final float CAMERA_WIDTH;
    private final float CAMERA_HEIGHT;
    private final int level = 1;

    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;

    private final SpriteBatch spriteBatch;

    private TextureRegion background;

    public WorldRenderer() {

        CAMERA_HEIGHT = EntityStore.levelHeight;
        CAMERA_WIDTH = EntityStore.levelWidth;
        spriteBatch = new SpriteBatch();
        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        initialize();

    }

    private void initialize() {
        initializeTextures();
        shots = new ArrayList<ShotEntity>();
        cam.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);
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

        textureCoins = Assets.spriteCoin;

        textureUfoEnemy = Assets.textureEnemyUfo;
        textureBlockObstacle = Assets.textureObstacle;
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
                else if (e instanceof CactusEntity) {
                    CactusEntity theHole = (CactusEntity) e;
                    drawEntity(spriteBatch, theHole.getBounds(),
                            textureBlockObstacle);
                }
                else if (e instanceof CoinEntity) {
                    CoinEntity coin = (CoinEntity) e;
                    drawEntity(spriteBatch, coin.getBounds(), textureCoins);
                }
                else if (e instanceof SpikeEntity) {
                    SpikeEntity spike = (SpikeEntity) e;
                    drawEntity(spriteBatch, spike.getBounds(), textureSpike);
                }
            }
        }

        font = new BitmapFont();
        font.draw(spriteBatch, str, 100, 100);

        EntityStore.entityList.removeAll(shots);
        debugRenderer.end();

        spriteBatch.end();
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
