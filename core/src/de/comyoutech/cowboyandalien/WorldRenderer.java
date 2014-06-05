package de.comyoutech.cowboyandalien;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.EnemyEntity;
import de.comyoutech.cowboyandalien.entities.Entity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity.State;
import de.comyoutech.cowboyandalien.entities.ShotEntity;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldRenderer {

    private static final float RUNNING_FRAME_DURATION = 0.06f;

    private ShapeRenderer debugRenderer = new ShapeRenderer();

    private OrthographicCamera cam;

    private TextureRegion idleLeft;
    private TextureRegion idleRight;
    private TextureRegion blockTexture;
    private TextureRegion frame;
    private TextureRegion jumpLeft;
    private TextureRegion fallLeft;
    private TextureRegion jumpRight;
    private TextureRegion fallRight;
    private TextureRegion textureShot;

    private TextureRegion textureEnemy;

    private List<ShotEntity> shots;

    private final float CAMERA_WIDTH;
    private final float CAMERA_HEIGHT;

    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;

    private SpriteBatch spriteBatch;
    private boolean debug = false;
    private int width;
    private int height;

    public void setSize(int w, int h) {
        width = w;
        height = h;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public WorldRenderer(boolean debug) {
        CAMERA_HEIGHT = EntityStore.levelHeight;
        CAMERA_WIDTH = EntityStore.levelWidth;
        spriteBatch = new SpriteBatch();
        initializeTextures();
        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);
        shots = new ArrayList<ShotEntity>();
    }

    private void initializeTextures() {
        idleLeft = Assets.idleLeft;
        idleRight = Assets.idleRight;
        blockTexture = Assets.blockTexture;
        walkLeftAnimation = Assets.leftAnimation;
        walkRightAnimation = Assets.rightAnimation;
        jumpLeft = Assets.jumpLeft;
        jumpRight = new TextureRegion(jumpLeft);
        jumpRight.flip(true, false);
        fallLeft = Assets.fallLeft;
        fallRight = new TextureRegion(fallLeft);
        fallRight.flip(true, false);
        textureShot = Assets.textureShot;

        textureEnemy = Assets.textureEnemy;
    }

    public void render() {

        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();

        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeType.Line);

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
                float x, y, w, h;
                Rectangle rect = block.getBounds();
                x = rect.x;
                y = rect.y;
                w = rect.width;
                h = rect.height;
                spriteBatch.draw(blockTexture, x, y, w, h);

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
                spriteBatch.draw(textureShot, x, y, w, h);

            }
            else if (e instanceof EnemyEntity) {
                EnemyEntity enemy = (EnemyEntity) e;
                float x, y, w, h;
                Rectangle rect = enemy.getBounds();
                x = rect.x;
                y = rect.y;
                w = rect.width;
                h = rect.height;
                spriteBatch.draw(textureEnemy, x, y, w, h);
            }
        }
        EntityStore.entityList.removeAll(shots);
        debugRenderer.end();

        spriteBatch.end();
        if (debug) {
            drawDebug();
        }
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

}
