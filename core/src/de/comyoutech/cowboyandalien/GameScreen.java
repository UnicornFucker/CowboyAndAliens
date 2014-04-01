package de.comyoutech.cowboyandalien;

/** Import **/

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	/** Initiate Variables **/

	MyGdxGame game;
	OrthographicCamera camera;
	SpriteBatch batch;

	/** Variables for Character Movements **/

	public float ply_x = 250;
	public float ply_y = 250;
	public float f_dx = 5;
	public float f_dy = -9;
	public float gravity = 0.5f;

	/** Konstruktor der Klasse **/

	public GameScreen(MyGdxGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		batch = new SpriteBatch();

	}

	/** GeneralUpdate Methode fuer Keylistener etc. **/

	public void generalUpdate(OrthographicCamera camera) {

		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			ply_x -= 5;
		} else if (Gdx.input.isKeyPressed(Keys.D)
				|| Gdx.input.isKeyPressed(Keys.RIGHT)) {
			ply_x += 5;
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {

		} else if (Gdx.input.isKeyPressed(Keys.W)
				|| Gdx.input.isKeyPressed(Keys.UP)) {

		} else if (Gdx.input.isKeyPressed(Keys.SPACE)) {

			if (ply_y == 450) {
				f_dy = -9;
			}

			f_dy += gravity;
			ply_y += f_dy;

		}

	}

	/** Render Methode wird immer aufgerufen um das Spiel zu updaten **/

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 0.95F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		generalUpdate(camera);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(Assets.sprite_figure, ply_x, ply_y);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
