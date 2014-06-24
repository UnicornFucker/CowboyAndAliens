package de.comyoutech.cowboyandalien.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import de.comyoutech.cowboyandalien.model.DataCollector;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldController {

    enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }

    static Map<Keys, Boolean> keysPressed = new HashMap<WorldController.Keys, Boolean>();
    private final long JUMPTIMEGAP = 150l;
    private final float PLAYER_ACCELERATION = 20f;
    private final float GRAVITY = -17f;
    private final float MAX_JUMP_SPEED = 7f;
    private final float SLIDINGRANGE = 0.90f;
    private final float SPEEDLIMIT = 4f;

    private MyGdxGame game;

    private PlayerEntity player;
    private DataCollector collector;

    private final float shotPressedTimeMax = 1F;
    private final float shotTimeMaxEnemy = 1F;
    private float shotPressedTime = 0;

    private long jumpPressedTime;
    private boolean jumpingPressed;
    private boolean grounded = false;

    private static final float WIDTH = 1000f;

    private boolean shooting;
    private int lives = 3;
    private final List<BlockEntity> collidable = new ArrayList<BlockEntity>();

    public WorldController(MyGdxGame game) {
        player = EntityStore.player;
        keysPressed.put(Keys.LEFT, false);
        keysPressed.put(Keys.RIGHT, false);
        keysPressed.put(Keys.JUMP, false);
        keysPressed.put(Keys.FIRE, false);
        this.game = game;
        collector = DataCollector.getInstance();
    }

    /** The main update method **/
    public void update(float delta) {

        processInput();

        Random rndm = new Random();

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

        List<AbstractEntity> removeList = new ArrayList<AbstractEntity>();
        List<AbstractEntity> newShots = new ArrayList<AbstractEntity>();

        for (AbstractEntity e : EntityStore.entityList) {
            if (e instanceof ShotEntity) {
                ShotEntity shot = (ShotEntity) e;

                if (shot.getBounds().overlaps(player.getBounds())
                        && shot.killsPlayer) {
                    playerDied();
                }

                if (shot.isFacingLeft()) {
                    shot.getPosition().x -= shot.getSPEED() * delta;
                }
                else {
                    shot.getPosition().x += shot.getSPEED() * delta;
                }
                for (AbstractEntity en : EntityStore.entityList) {
                    if (en instanceof BlockEntity) {
                        if (en.getBounds().overlaps(shot.getBounds())) {
                            removeList.add(shot);
                        }
                    }
                    else if (en instanceof EnemyEntity) {
                        if (en.getBounds().overlaps(shot.getBounds())) {
                            if (!shot.killsPlayer) {
                                removeList.add(shot);
                                removeList.add(en);
                            }
                        }
                    }
                    else if (en instanceof MovableBlockEntity) {
                        if (en.getBounds().overlaps(shot.getBounds())) {
                            removeList.add(en);
                            removeList.add(shot);
                        }
                    }

                }
            }
            else if (e instanceof EnemyEntity) {
                EnemyEntity enemy = (EnemyEntity) e;

                enemy.setTimeSinceLastShot(enemy.getTimeSinceLastShot() + delta);

                if (enemy.getTimeSinceLastShot() > (shotTimeMaxEnemy + rndm
                        .nextFloat())) {
                    enemy.setTimeSinceLastShot(0);
                    newShots.add(enemy);
                }

                for (AbstractEntity en : EntityStore.entityList) {
                    if (en instanceof BlockEntity) {
                        if (en.getBounds().overlaps(enemy.getBounds())) {
                            enemy.switchDirection();
                        }
                    }
                }

                if (enemy.getBounds().overlaps(player.getBounds())) {
                    playerDied();
                }

                if (enemy.facingLeft) {
                    enemy.getPosition().x -= enemy.getSpeed() * delta;
                }
                else {
                    enemy.getPosition().x += enemy.getSpeed() * delta;
                }

            }
            else if (e instanceof MovableBlockEntity) {
                MovableBlockEntity block = (MovableBlockEntity) e;

                if (block.getBounds().overlaps(player.getBounds())) {
                    playerDied();
                }

                if (block.vertical) {
                    if (block.moveForward) {
                        float difference = block.SPEED * delta;
                        block.getPosition().y += difference;
                    }
                    else {
                        float difference = block.SPEED * delta;
                        block.getPosition().y -= difference;
                    }
                }
                else {
                    if (block.moveForward) {
                        block.getPosition().x += block.SPEED * delta;
                    }
                    else {
                        block.getPosition().x -= block.SPEED * delta;
                    }
                }
                block.checkRange();
            }
            else if (e instanceof CactusEntity) {
                CactusEntity hole = (CactusEntity) e;
                if (hole.getBounds().overlaps(player.getBounds())) {
                    playerDied();
                }
            }
            else if (e instanceof CoinEntity) {
                CoinEntity coin = (CoinEntity) e;
                if (player.getBounds().overlaps(coin.getBounds())) {
                    Assets.soundGetCoin.play();
                    int score;
                    if (coin.iAmSuper) {
                        score = 5;
                    }
                    else {
                        score = 1;
                    }
                    collector.addTempCoins(score);
                    removeList.add(coin);
                }
            }
            else if (e instanceof SpikeEntity) {
                if (player.getBounds().overlaps(e.getBounds())) {
                    playerDied();
                }
            }
            else if (e instanceof CactusEntity) {
                if (e.getBounds().overlaps(player.getBounds())) {
                    playerDied();
                }
            }
        }

        for (AbstractEntity e : newShots) {
            createShot(e, true);
        }

        EntityStore.entityList.removeAll(removeList);
        checkCollisionWithBlocks(delta);
        player.getVelocity().x *= SLIDINGRANGE;

        if (player.getVelocity().x > SPEEDLIMIT) {
            player.getVelocity().x = SPEEDLIMIT;
        }
        if (player.getVelocity().x < -SPEEDLIMIT) {
            player.getVelocity().x = -SPEEDLIMIT;
        }

        player.update(delta);

        if (player.getPosition().y < 0) {
            player.getPosition().y = 0f;
            player.setPosition(player.getPosition());
            grounded = true;
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

    private boolean processInput() {
        if (keysPressed.get(Keys.JUMP)) {
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.JUMPING);
                jumpingPressed = true;
                jumpPressedTime = System.currentTimeMillis();
                player.getVelocity().y = MAX_JUMP_SPEED;
                Assets.soundJump.play();
                grounded = false;
            }
            else {
                if (jumpingPressed
                        && ((System.currentTimeMillis() - jumpPressedTime) >= JUMPTIMEGAP)) {
                    jumpingPressed = false;
                }
                else {
                    if (jumpingPressed) {
                        player.getVelocity().y = MAX_JUMP_SPEED;
                    }
                }
            }
        }
        if (keysPressed.get(Keys.LEFT)) {

            player.setFacingLeft(true);
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.WALKING);
            }
            player.getAcceleration().x = -PLAYER_ACCELERATION;
        }
        else if (keysPressed.get(Keys.RIGHT)) {
            player.setFacingLeft(false);
            if (!player.getState().equals(State.JUMPING)) {
                player.setState(State.WALKING);
            }
            player.getAcceleration().x = PLAYER_ACCELERATION;
        }
        else {
            player.getAcceleration().x = 0;

        }
        if (keysPressed.get(Keys.FIRE)) {
            if (!shooting) {
                createShot(player, false);
                shooting = true;
            }
        }
        return false;
    }

    private void playerDied() {
        game.setDeadScreen();
    }

    private void createShot(AbstractEntity entity, boolean killsPlayer) {

        float x = 0, y = 0;

        boolean left = false;

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (player.isFacingLeft()) {
                x = player.getPosition().x;
                left = true;
            }
            else {
                x = player.getPosition().x + player.getBounds().width;
                left = false;
            }
            y = player.getPosition().y + (player.getBounds().height / 2);
        }
        else if (entity instanceof EnemyEntity) {

            EnemyEntity enemy = (EnemyEntity) entity;
            if (enemy.isFacingLeft()) {
                x = enemy.getPosition().x;
                left = true;
            }
            else {
                x = enemy.getPosition().x + enemy.getBounds().width;
                left = false;
            }
            y = enemy.getPosition().y + (enemy.getBounds().height / 2);

        }

        ShotEntity shot = new ShotEntity(x, y, killsPlayer);
        shot.setFacingLeft(left);
        EntityStore.entityList.add(shot);

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
        }
        else {
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
        }
        else {
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
                    if (!grounded) {
                        player.setState(State.IDLE);
                    }
                    grounded = true;
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

    /** Checks the Collision for incrementScore **/

//    private void checkCollisionScore(int setScoreUp) {
//
//        Rectangle myRectForScore = new Rectangle();
//
//        myRectForScore.set(player.getBounds().x, player.getBounds().y,
//                player.getBounds().width, player.getBounds().height);
//
//
//
//    }

    /** returns true of LEFT is pressed **/

    public void leftPressed() {
        keysPressed.get(keysPressed.put(Keys.LEFT, true));
    }

    public void rightPressed() {
        keysPressed.get(keysPressed.put(Keys.RIGHT, true));
    }

    public void jumpPressed() {
        jumpingPressed = true;
        keysPressed.get(keysPressed.put(Keys.JUMP, true));
    }

    public void firePressed() {
        keysPressed.get(keysPressed.put(Keys.FIRE, true));
    }

    public void leftReleased() {
        keysPressed.get(keysPressed.put(Keys.LEFT, false));
        if (grounded) {
            player.setState(State.IDLE);
        }
    }

    public void rightReleased() {
        keysPressed.get(keysPressed.put(Keys.RIGHT, false));
        if (grounded) {
            player.setState(State.IDLE);
        }
    }

    public void jumpReleased() {
        keysPressed.get(keysPressed.put(Keys.JUMP, false));
        jumpingPressed = false;
    }

    public void fireReleased() {
        keysPressed.get(keysPressed.put(Keys.FIRE, false));
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

}
