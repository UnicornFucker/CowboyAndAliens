package de.comyoutech.cowboyandalien.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.comyoutech.cowboyandalien.controller.MyGdxGame;

/**
 * The abstract screen class.
 * 
 * @author Kevin
 * 
 */
public abstract class AbstractScreen implements Screen, InputProcessor {

    /**
     * The batch for drawing.
     */
    protected SpriteBatch batch;
    /**
     * Game-object.
     */
    protected MyGdxGame game;

    public AbstractScreen(MyGdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
    }

}
