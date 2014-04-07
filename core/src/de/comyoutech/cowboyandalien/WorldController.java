package de.comyoutech.cowboyandalien;

import java.util.HashMap;
import java.util.Map;

import sun.org.mozilla.javascript.internal.ast.Block;

import com.badlogic.gdx.utils.Array;

import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity.State;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldController {

    enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }

    private static final long LONG_JUMP_PRESS = 150l;
    private static final float ACCELERATION = 20f;
    private static final float GRAVITY = -20f;
    private static final float MAX_JUMP_SPEED = 10f;
    private static final float DAMP = 0.90f;
    private static final float MAX_VEL = 4f;

    private static final float WIDTH = 100f;

    private PlayerEntity player;
    private long jumpPressedTime;
    private boolean jumpingPressed;

    private Array<Block> collidable = new Array<Block>();

    static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.FIRE, false);
    };

    public WorldController(EntityStore store) {
        player = store.getPlayer();
    }

    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    public void jumpPressed() {
        keys.get(keys.put(Keys.JUMP, true));
    }

    public void firePressed() {
        keys.get(keys.put(Keys.FIRE, false));
    }

    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }

    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }

    public void jumpReleased() {
        keys.get(keys.put(Keys.JUMP, false));
        jumpingPressed = false;
    }

    public void fireReleased() {
        keys.get(keys.put(Keys.FIRE, false));
    }

    /** The main update method **/
    public void update(float delta) {
        processInput();

        player.getAcceleration().y = GRAVITY;
        player.getAcceleration().scl(delta);
        player.getVelocity().add(player.getAcceleration().x,
                player.getAcceleration().y);

        if (player.getAcceleration().x == 0) {
            player.getVelocity().x *= DAMP;
        }
        if (player.getVelocity().x > MAX_VEL) {
            player.getVelocity().x = MAX_VEL;
        }
        if (player.getVelocity().x < -MAX_VEL) {
            player.getVelocity().x = -MAX_VEL;
        }

        player.update(delta);

        if (player.getPosition().y < 0) {
            player.getPosition().y = 0f;
            player.setPosition(player.getPosition());
            if (player.getState().equals(State.JUMPING)) {
                player.setState(State.IDLE);
            }
        }
        if (player.getPosition().x < 0) {
            player.getPosition().x = 0;
            player.setPosition(player.getPosition());
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.IDLE);
            }
        }
        if (player.getPosition().x > (WIDTH - player.getBounds().width)) {
            player.getPosition().x = WIDTH - player.getBounds().width;
            player.setPosition(player.getPosition());
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.IDLE);
            }
        }
    }

    /** Change Bob's state and parameters based on input controls **/
    private boolean processInput() {
        if (keys.get(Keys.JUMP)) {
            if (!player.getState().equals(State.JUMPING)) {
                jumpingPressed = true;
                jumpPressedTime = System.currentTimeMillis();
                player.setState(State.JUMPING);
                player.getVelocity().y = MAX_JUMP_SPEED;
            } else {
                if (jumpingPressed
                        && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
                    jumpingPressed = false;
                } else {
                    if (jumpingPressed) {
                        player.getVelocity().y = MAX_JUMP_SPEED;
                    }
                }
            }
        }
        if (keys.get(Keys.LEFT)) {
            // left is pressed
            player.setFacingLeft(true);
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.WALKING);
            }
            player.getAcceleration().x = -ACCELERATION;
        } else if (keys.get(Keys.RIGHT)) {
            // left is pressed
            player.setFacingLeft(false);
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.WALKING);
            }
            player.getAcceleration().x = ACCELERATION;
        } else {
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.IDLE);
            }
            player.getAcceleration().x = 0;

        }
        return false;
    }

    // private void checkCollisionWithBlocks(float delta) {
    // bob.getVelocity().scl(delta);
    // Rectangle bobRect = rectPool.obtain();
    // bobRect.set(bob.getBounds().x, bob.getBounds().y,
    // bob.getBounds().width, bob.getBounds().height);
    // int startX, endX;
    // int startY = (int) bob.getBounds().y;
    // int endY = (int) (bob.getBounds().y + bob.getBounds().height);
    // if (bob.getVelocity().x < 0) {
    // startX = endX = (int) Math.floor(bob.getBounds().x
    // + bob.getVelocity().x);
    // }
    // else {
    // startX = endX = (int) Math.floor(bob.getBounds().x
    // + bob.getBounds().width + bob.getVelocity().x);
    // }
    // populateCollidableBlocks(startX, startY, endX, endY);
    // bobRect.x += bob.getVelocity().x;
    // world.getCollisionRects().clear();
    // for (Block block : collidable) {
    // if (block == null) {
    // continue;
    // }
    // if (bobRect.overlaps(block.getBounds())) {
    // bob.getVelocity().x = 0;
    // world.getCollisionRects().add(block.getBounds());
    // break;
    // }
    // }
    // bobRect.x = bob.getPosition().x;
    // startX = (int) bob.getBounds().x;
    // endX = (int) (bob.getBounds().x + bob.getBounds().width);
    // if (bob.getVelocity().y < 0) {
    // startY = endY = (int) Math.floor(bob.getBounds().y
    // + bob.getVelocity().y);
    // }
    // else {
    // startY = endY = (int) Math.floor(bob.getBounds().y
    // + bob.getBounds().height + bob.getVelocity().y);
    // }
    // populateCollidableBlocks(startX, startY, endX, endY);
    // bobRect.y += bob.getVelocity().y;
    // for (Block block : collidable) {
    // if (block == null) {
    // continue;
    // }
    // if (bobRect.overlaps(block.getBounds())) {
    // if (bob.getVelocity().y < 0) {
    // grounded = true;
    // }
    // bob.getVelocity().y = 0;
    // world.getCollisionRects().add(block.getBounds());
    // break;
    // }
    // }
    // bobRect.y = bob.getPosition().y;
    // bob.getPosition().add(bob.getVelocity());
    // bob.getBounds().x = bob.getPosition().x;
    // bob.getBounds().y = bob.getPosition().y;
    // bob.getVelocity().scl(1 / delta);
    // }

    // private void populateCollidableBlocks(int startX, int startY, int endX,
    // int endY) {
    // collidable.clear();
    // for (int x = startX; x <= endX; x++) {
    // for (int y = startY; y <= endY; y++) {
    // if ((x >= 0) && (x < world.getLevel().getWidth()) && (y >= 0)
    // && (y < world.getLevel().getHeight())) {
    // collidable.add(world.getLevel().get(x, y));
    // }
    // }
    // }
    // }

}
