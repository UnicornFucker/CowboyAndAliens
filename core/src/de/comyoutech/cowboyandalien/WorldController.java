package de.comyoutech.cowboyandalien;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Rectangle;

import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.CoinEntity;
import de.comyoutech.cowboyandalien.entities.EnemyEntity;
import de.comyoutech.cowboyandalien.entities.Entity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity.State;
import de.comyoutech.cowboyandalien.entities.ShotEntity;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldController {

    enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }

    static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
    private final long LONG_JUMP_PRESS = 150l;
    private final float ACCELERATION = 20f;
    private final float GRAVITY = -20f;
    private final float MAX_JUMP_SPEED = 7f;
    private final float DAMP = 0.90f;
    private final float MAX_VEL = 4f;

    private final PlayerEntity player;

    private final float shotPressedTimeMax = 2F;
    private float shotPressedTime = 0;

    private long jumpPressedTime;
    private boolean jumpingPressed;
    private boolean grounded = false;

    private static final float WIDTH = 100f;

    private final float SHOTSPEED = 20F;
    private boolean shooting;
    private int score = 0;
    private int lives = 3;
    private int extraLiveScore = 10000;
    private int scoreUp;
    private final List<BlockEntity> collidable = new ArrayList<BlockEntity>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.FIRE, false);
    };

    public WorldController() {
        player = EntityStore.player;
    }

    /** The main update method **/
    public void update(float delta) {

        shotPressedTime += delta;

        processInput();
        if (grounded && player.getState().equals(State.JUMPING)) {
        }
        player.getAcceleration().y = GRAVITY;

        player.getAcceleration().scl(delta);

        player.getVelocity().add(player.getAcceleration().x,
                player.getAcceleration().y);

        if (shooting) {
            shotPressedTime += delta;
            if (shotPressedTime > shotPressedTimeMax) {
                shotPressedTime = 0;
                shooting = false;
            }
        }
        List<Entity> removeList = new ArrayList<Entity>();
        for (Entity e : EntityStore.entityList) {
            if (e instanceof ShotEntity) {
                ShotEntity shot = (ShotEntity) e;
                shot.getAcceleration().scl(delta);
                shot.getVelocity().add(shot.getAcceleration());
                shot.getPosition().add(shot.getVelocity());
                for (Entity en : EntityStore.entityList) {
                    if (en instanceof BlockEntity) {
                        if (en.getBounds().overlaps(shot.getBounds())) {
                            removeList.add(shot);
                        }
                    }
                }
            } else if (e instanceof EnemyEntity) {
                EnemyEntity enemy = (EnemyEntity) e;
                if (enemy.facingLeft) {
                    enemy.getAcceleration().x = -enemy.getSpeed();
                } else {
                    enemy.getAcceleration().x = enemy.getSpeed();
                }
                enemy.getAcceleration().scl(delta);
                enemy.getVelocity().add(enemy.getAcceleration());
                enemy.getPosition().add(enemy.getVelocity());
                for (Entity en : EntityStore.entityList) {
                    if (en instanceof BlockEntity) {
                        if (en.getBounds().overlaps(enemy.getBounds())) {
                            if (enemy.facingLeft) {
                                enemy.switchDirection();
                            }
                        }
                    }
                }
            }
        }
        EntityStore.entityList.removeAll(removeList);
        checkCollisionWithBlocks(delta);
        checkCollisionScore(score);
        player.getVelocity().x *= DAMP;

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

        if (player.getPosition().y == 0) {
            EntityStore.playerIsDead = true;
        }

    }

    /** Change Bob's state and parameters based on input controls **/
    private boolean processInput() {
        if (keys.get(Keys.JUMP)) {
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.JUMPING);
                jumpingPressed = true;
                jumpPressedTime = System.currentTimeMillis();
                player.getVelocity().y = MAX_JUMP_SPEED;
                grounded = false;
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

            player.setFacingLeft(true);
            if (!player.getState().equals(State.JUMPING)) {

            }
            player.getAcceleration().x = -ACCELERATION;
        } else if (keys.get(Keys.RIGHT)) {

            player.setFacingLeft(false);
            if (!player.getState().equals(State.JUMPING)) {

            }
            player.getAcceleration().x = ACCELERATION;
        } else {
            player.getAcceleration().x = 0;

        }
        if (keys.get(Keys.FIRE)) {
            if (!shooting) {
                shooting = true;
                float x = 0, y = 0, speed = 0;

                if (player.isFacingLeft()) {
                    x = player.getPosition().x;
                    speed = -SHOTSPEED;
                } else {
                    x = player.getPosition().x + player.getBounds().width;
                    speed = SHOTSPEED;
                }
                y = player.getPosition().y + (player.getBounds().height / 2);
                ShotEntity shot = new ShotEntity(x, y);
                shot.getAcceleration().x = speed;
                EntityStore.entityList.add(shot);
            }
        }
        return false;
    }

    /** Collision checking **/
    private void checkCollisionWithBlocks(float delta) {

        player.getVelocity().scl(delta);

        Rectangle myRect = new Rectangle();

        myRect.set(player.getBounds().x, player.getBounds().y,
                player.getBounds().width, player.getBounds().height);

        int startX, endX;
        int startY = (int) player.getPosition().y;
        int endY = (int) (player.getPosition().y + player.getBounds().height);

        if (player.getVelocity().x < 0) {
            startX = endX = (int) Math.floor(player.getPosition().x
                    + player.getVelocity().x);
        } else {
            startX = endX = (int) Math.floor(player.getPosition().x
                    + player.getBounds().width + player.getVelocity().x);
        }

        populateCollidableBlocks(startX, startY, endX, endY);

        myRect.x += player.getVelocity().x;

        EntityStore.collisionRects.clear();

        for (BlockEntity block : collidable) {
            if (block == null) {
                continue;
            }
            if (myRect.overlaps(block.getBounds())) {
                player.getVelocity().x = 0;
                EntityStore.collisionRects.add(block.getBounds());
                break;
            }
        }

        myRect.x = player.getPosition().x;

        startX = (int) player.getPosition().x;
        endX = (int) (player.getPosition().x + player.getBounds().width);
        if (player.getVelocity().y < 0) {
            startY = endY = (int) Math.floor(player.getPosition().y
                    + player.getVelocity().y);
            if (startY < 0) {
                startY = 0;
            }
        } else {
            startY = endY = (int) Math.floor(player.getPosition().y
                    + player.getBounds().height + player.getVelocity().y);
        }

        populateCollidableBlocks(startX, startY, endX, endY);

        myRect.y += player.getVelocity().y;

        for (BlockEntity block : collidable) {
            if (block == null) {
                continue;
            }
            if (myRect.overlaps(block.getBounds())) {
                if (player.getVelocity().y < 0) {
                    grounded = true;
                    player.setState(State.IDLE);
                }
                player.getVelocity().y = 0;
                EntityStore.collisionRects.add(block.getBounds());
                break;
            }
        }

        myRect.y = player.getPosition().y;

        player.getPosition().add(player.getVelocity());
        player.getBounds().x = player.getPosition().x;
        player.getBounds().y = player.getPosition().y;

        player.getVelocity().scl(1 / delta);
    }

    /**
     * populate the collidable array with the blocks found in the enclosing
     * coordinates
     **/
    private void populateCollidableBlocks(int startX, int startY, int endX,
            int endY) {
        collidable.clear();
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                collidable.add(EntityStore.get(x, y));
            }
        }
    }

    /*
     * private void checkExtraLive() {
     * 
     * if (score >= extraLiveScore) { lives++; extraLiveScore += 10000; }
     * 
     * }
     * 
     * private void loseLife() { lives--; }
     */

    private void incrementScore(int scoreUp) {
        switch (scoreUp) {
        case 1:
            score += 50;
            break;
        case 2:
            score += 100;
            break;
        case 3:
            score += 500;
            break;

        default:
            break;
        }
    }

    private void checkCollisionScore(int setScoreUp) {

        Rectangle myRectForScore = new Rectangle();

        myRectForScore.set(player.getBounds().x, player.getBounds().y,
                player.getBounds().width, player.getBounds().height);

        List<CoinEntity> collidedCoins = new ArrayList();

        for (Entity entity : EntityStore.entityList) {
            if (entity instanceof CoinEntity) {
                CoinEntity coin = (CoinEntity) entity;

                // Kollision mit irgendwas fuer Score + 50
                if (myRectForScore.overlaps(coin.getBounds())) {
                    setScoreUp(1);
                    incrementScore(scoreUp);
                    System.out.println(score);
                    collidedCoins.add(coin);
                }/*
                  * // Kollision mit irgendwas fuer Score + 100 if
                  * (myRectForScore.overlaps(coin.getBounds())) { setScoreUp(2);
                  * incrementScore(scoreUp); }// Kollision mit irgendwas fuer
                  * Score + 500 if (myRectForScore.overlaps(block.getBounds()))
                  * { setScoreUp(3); incrementScore(scoreUp); }
                  */
            }
        }
        EntityStore.entityList.removeAll(collidedCoins);

    }

    void loadLevelTime() {

        for (int i = 10000; i <= 0; i--) {
            System.out.println(i);
            if (i == 0) {
                // player Dead
            }
            System.out.println(i);
        }
    }

    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    public void jumpPressed() {
        jumpingPressed = true;
        keys.get(keys.put(Keys.JUMP, true));
    }

    public void firePressed() {
        keys.get(keys.put(Keys.FIRE, true));
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

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScoreUp() {
        return scoreUp;
    }

    public void setScoreUp(int scoreUp) {
        this.scoreUp = scoreUp;
    }

}
