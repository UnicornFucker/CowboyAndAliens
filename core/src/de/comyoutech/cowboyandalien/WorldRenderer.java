package de.comyoutech.cowboyandalien;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.Entity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.ShotEntity;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldRenderer {

    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;

    private SpriteBatch batch;

    private final EntityStore store;
    private final OrthographicCamera cam;

    private int width;
    private int height;

    private float ppuX;
    private float ppuY;

    public void setSize(int w, int h) {
        width = w;
        height = h;
        ppuX = width / CAMERA_WIDTH;
        ppuY = height / CAMERA_HEIGHT;
    }

    /** for debug rendering **/
    private final ShapeRenderer debugRenderer = new ShapeRenderer();

    public WorldRenderer(EntityStore store) {
        this.store = store;
        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        cam.update();
    }

    public void render() {

        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeType.Line);

        for (Entity e : store.getEntityList()) {

            if (e instanceof PlayerEntity) {

                PlayerEntity player = (PlayerEntity) e;

                Rectangle rect = player.getBounds();
                float x1 = player.getPosition().x + rect.x;
                float y1 = player.getPosition().y + rect.y;
                debugRenderer.setColor(new Color(Color.CYAN));
                debugRenderer.rect(x1, y1, rect.width, rect.height);

                moveCamera(player);
            } else if (e instanceof BlockEntity) {

                BlockEntity block = (BlockEntity) e;
                Rectangle rect = block.getBounds();

                float x1 = block.getPosition().x + rect.x;
                float y1 = block.getPosition().y + rect.y;

                debugRenderer.setColor(new Color(Color.YELLOW));
                debugRenderer.rect(x1, y1, rect.width, rect.height);

            } else if (e instanceof ShotEntity) {
                ShotEntity shot = (ShotEntity) e;
                Rectangle rect = shot.getBounds();
                Vector2 position = shot.getPosition();

                float x1 = position.x + rect.x;
                float y1 = position.y + rect.y;

                debugRenderer.setColor(Color.GREEN);
                debugRenderer.rect(x1, y1, rect.width, rect.height);
            }
        }
        debugRenderer.end();
    }

    public void moveCamera(PlayerEntity player) {
        if ((player.getPosition().x > (CAMERA_WIDTH / 2))) {
            cam.position.set(player.getPosition().x, CAMERA_HEIGHT / 2, 0);
            cam.update();
        }
    }

}
