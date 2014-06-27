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
import de.comyoutech.cowboyandalien.entities.GateEntity;
import de.comyoutech.cowboyandalien.entities.MovableBlockEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity.State;
import de.comyoutech.cowboyandalien.entities.ShotEntity;
import de.comyoutech.cowboyandalien.entities.SpikeEntity;
import de.comyoutech.cowboyandalien.model.Assets;
import de.comyoutech.cowboyandalien.model.DataCollector;
import de.comyoutech.cowboyandalien.model.EntityStore;

public class WorldController {

    /**
     * Enum for all pressable keys.
     * 
     * @author BrookZ
     * 
     */
    enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }

    /**
     * List of actual pressed keys.
     */
    static Map<Keys, Boolean> keysPressed = new HashMap<WorldController.Keys, Boolean>();

    /**
     * 
     */
    private final long JUMPTIMEGAP = 150l;
    /**
     * Acceleration of the player.
     */
    private final float PLAYER_ACCELERATION = 20f;
    /**
     * Gravity of the world.
     */
    private final float GRAVITY = -17f;
    /**
     * Maximum jump Speed.
     */
    private final float MAX_JUMP_SPEED = 7f;
    /**
     * Sliding range of the player if the ground is stone.
     */
    private final float SLIDINGRANGEBLOCK = 0.90f;
    /**
     * Sliding range of the player if the ground is ice.
     */
    private final float SLIDINGRANGEICE = 0.98f;

    /**
     * Limit of the player speed.
     */
    private final float SPEEDLIMIT = 4f;

    /**
     * If the player is currently slidung.
     */
    private boolean sliding = false;

    /**
     * Game Object for switching screens.
     */
    private MyGdxGame game;

    /**
     * Player object.
     */
    private PlayerEntity player;

    /**
     * Collects data like coins.
     */
    private DataCollector collector;

    /**
     * Time between 2 Shots.
     */
    private final float shotPressedTimeMax = 1F;

    /**
     * Time between 2 shots of an enemy.
     */
    private final float shotTimeMaxEnemy = 1F;

    /**
     * Time when the last shot was fired.
     */
    private float shotPressedTime = 0;

    /**
     * Time since the last jump.
     */
    private long jumpPressedTime;
    /**
     * If the player is jumping.
     */
    private boolean jumpingPressed;
    /**
     * If the player is standing on the ground.
     */
    private boolean grounded = false;

    /**
     * The width of the level.
     */
    private static final float WIDTH = 1000f;

    /**
     * If the player is shooting.
     */
    private boolean shooting;

    /**
     * List of all blocks the player can collide with.
     */
    private final List<BlockEntity> collidable = new ArrayList<BlockEntity>();

    /**
     * Constructor.
     * 
     * @param game
     */
    public WorldController(MyGdxGame game) {
        player = EntityStore.player;
        keysPressed.put(Keys.LEFT, false);
        keysPressed.put(Keys.RIGHT, false);
        keysPressed.put(Keys.JUMP, false);
        keysPressed.put(Keys.FIRE, false);
        this.game = game;
        collector = DataCollector.getInstance();
    }

    /**
     * The main update method
     * */
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
                    performPlayerDied();
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

                                EnemyEntity enemy = (EnemyEntity) en;
                                enemy.wasShooten();

                                if (enemy.getLives() < 1) {

                                    removeList.add(en);
                                }
                            }
                        }
                    }
                    else if (en instanceof MovableBlockEntity) {
                        if (en.getBounds().overlaps(shot.getBounds())) {
                            if (!shot.killsPlayer) {

                                removeList.add(en);
                                removeList.add(shot);
                            }
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

                BlockEntity collidedBlock = null;

                for (AbstractEntity en : EntityStore.entityList) {
                    if (en instanceof BlockEntity) {
                        if (en.getBounds().overlaps(enemy.getBounds())) {
                            collidedBlock = (BlockEntity) en;
                            enemy.switchDirection();
                        }
                    }
                    if (en instanceof SpikeEntity) {
                        if (en.getBounds().overlaps(enemy.getBounds())) {
                            enemy.switchDirection();
                        }
                    }
                }
                if (enemy.getBounds().overlaps(player.getBounds())) {
                    performPlayerDied();
                }
                if (enemy.facingLeft) {
                    if (collidedBlock != null) {
                        while (collidedBlock.getBounds().overlaps(
                                enemy.getBounds())) {
                            enemy.getPosition().x -= enemy.getSpeed() * delta;
                        }
                    }
                    else {
                        enemy.getPosition().x -= enemy.getSpeed() * delta;
                    }
                }
                else {
                    if (collidedBlock != null) {
                        while (collidedBlock.getBounds().overlaps(
                                enemy.getBounds())) {
                            enemy.getPosition().x += enemy.getSpeed() * delta;
                        }
                    }
                    else {
                        enemy.getPosition().x += enemy.getSpeed() * delta;
                    }
                }

            }
            else if (e instanceof MovableBlockEntity) {
                MovableBlockEntity block = (MovableBlockEntity) e;

                if (block.getBounds().overlaps(player.getBounds())) {
                    performPlayerDied();
                }

                if (block.vertical) {
                    if (block.moveForward) {
                        float difference = block.SPEEDFINAL * delta;
                        block.getPosition().y += difference;
                    }
                    else {
                        float difference = block.SPEEDFINAL * delta;
                        block.getPosition().y -= difference;
                    }
                }
                else {
                    if (block.moveForward) {
                        block.getPosition().x += block.SPEEDFINAL * delta;
                    }
                    else {
                        block.getPosition().x -= block.SPEEDFINAL * delta;
                    }
                }
                block.checkRange();
            }
            else if (e instanceof CactusEntity) {
                CactusEntity hole = (CactusEntity) e;
                if (hole.getBounds().overlaps(player.getBounds())) {
                    performPlayerDied();
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
                    performPlayerDied();
                }
            }
            else if (e instanceof CactusEntity) {
                if (e.getBounds().overlaps(player.getBounds())) {
                    performPlayerDied();
                }
            }
            else if (e instanceof GateEntity) {
                if (e.getBounds().overlaps(player.getBounds())) {
                    performPlayerSolvedLevel();
                }
            }
        }

        for (AbstractEntity e : newShots) {
            if (e instanceof EnemyEntity) {
                if (!((EnemyEntity) e).iAmSuper) {
                    createShot(e, true);
                }
            }
        }

        EntityStore.entityList.removeAll(removeList);
        checkCollisionWithBlocks(delta);

        float range;

        if (sliding) {
            range = SLIDINGRANGEICE;
        }
        else {
            range = SLIDINGRANGEBLOCK;
        }

        player.getVelocity().x *= range;

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

    /**
     * Processes the User input.
     */
    private void processInput() {
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
    }

    /**
     * What happens if the player dies.
     */
    private void performPlayerDied() {
        game.setDeadScreen();
        Assets.soundBackground.stop();
        Assets.soundBackgroundBoss.stop();
    }

    /**
     * What happes if the player solves the level.
     */
    private void performPlayerSolvedLevel() {
        game.setLevelSolvedScreen();
    }

    /**
     * Creates a shot based on the position of the entity.
     * 
     * @param entity
     * @param killsPlayer
     */
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

    /**
     * Checks if the player collides with a block.
     * 
     * @param delta
     */
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
                    if (block.isIce) {
                        if (!sliding) {
                            sliding = true;
                        }
                    }
                    else {
                        if (sliding) {
                            sliding = false;
                        }
                    }
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
     * Adds all collidable blocks to the list.
     */
    private void populateCollidableBlocks(int startX, int startY, int endX,
            int endY) {
        collidable.clear();
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                collidable.add(EntityStore.get(x, y));
            }
        }
    }

    /**
     * Called if left key is pressed.
     */
    public void leftPressed() {
        keysPressed.get(keysPressed.put(Keys.LEFT, true));
    }

    /**
     * Called if right key is pressed.
     */
    public void rightPressed() {
        keysPressed.get(keysPressed.put(Keys.RIGHT, true));
    }

    /**
     * Called if jump key is pressed.
     */
    public void jumpPressed() {
        jumpingPressed = true;
        keysPressed.get(keysPressed.put(Keys.JUMP, true));
    }

    /**
     * Called if fire key is pressed.
     */
    public void firePressed() {
        keysPressed.get(keysPressed.put(Keys.FIRE, true));
    }

    /**
     * Called if left key is released.
     */
    public void leftReleased() {
        keysPressed.get(keysPressed.put(Keys.LEFT, false));
        if (grounded) {
            player.setState(State.IDLE);
        }
    }

    /**
     * Called if right key is released.
     */
    public void rightReleased() {
        keysPressed.get(keysPressed.put(Keys.RIGHT, false));
        if (grounded) {
            player.setState(State.IDLE);
        }
    }

    /**
     * Called if jump key is released.
     */
    public void jumpReleased() {
        keysPressed.get(keysPressed.put(Keys.JUMP, false));
        jumpingPressed = false;
    }

    /**
     * Called if fire key is released.
     */
    public void fireReleased() {
        keysPressed.get(keysPressed.put(Keys.FIRE, false));
    }

}
