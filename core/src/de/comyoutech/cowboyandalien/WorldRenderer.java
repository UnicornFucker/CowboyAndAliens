package de.comyoutech.cowboyandalien;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldRenderer {

    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;

    private EntityStore store;
    private OrthographicCamera cam;

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
    private ShapeRenderer debugRenderer = new ShapeRenderer();

    public WorldRenderer(EntityStore store) {
        this.store = store;
        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        cam.update();
    }

    public void render() {
        PlayerEntity player = store.getPlayer();
        moveCamera(player.getPosition().x, CAMERA_HEIGHT / 2);
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeType.Line);
        for (BlockEntity block : store.getBlockList()) {
            Rectangle rect = block.getBounds();
            float x1 = block.getPosition().x + rect.x;
            float y1 = block.getPosition().y + rect.y;
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }

        // render Bob

        Rectangle rect = player.getBounds();
        float x1 = player.getPosition().x + rect.x;
        float y1 = player.getPosition().y + rect.y;
        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(x1, y1, rect.width, rect.height);
        debugRenderer.end();
    }

    public void moveCamera(float x, float y) {
        PlayerEntity player = store.getPlayer();
        if ((player.getPosition().x > CAMERA_WIDTH / 2)) {
            cam.position.set(x, y, 0);
            cam.update();
        }

    }

}
