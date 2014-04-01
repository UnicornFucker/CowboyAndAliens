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
	public float ply_y = 672;
	public float f_dy = -9;
	public float f_dx = 5;
	public float gravity = 0.5f;
	boolean inAir = false;

	/** Konstruktor der Klasse **/

	public GameScreen(MyGdxGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		batch = new SpriteBatch();

	}

	/** GeneralUpdate Methode fuer Keylistener etc. **/

	public void generalUpdate(OrthographicCamera camera) {
		
		gravity();
		/** Setzt die Größe der Welt wo der Spieler sich bewegen kann **/

		if (getPly_x() >= 672) {
			ply_x = 672;
		}
		if (getPly_x() <= 0) {
			ply_x = 0;
		}

		if (getPly_y() >= 672) {
			ply_y = 672;
		}

		if (getPly_y() <= 0) {
			ply_y = 0;
		}
		if (getF_dy() <= -9) {
			f_dy = -9;
		}

		/** Key Inputs **/

		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			ply_x -= 5;
		} else if (Gdx.input.isKeyPressed(Keys.D)
				|| Gdx.input.isKeyPressed(Keys.RIGHT)) {
			ply_x += 5;
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			ply_y += 5;
		} else if (Gdx.input.isKeyPressed(Keys.W)
				|| Gdx.input.isKeyPressed(Keys.UP)) {
			ply_y -= 5;
		} else if (Gdx.input.isKeyPressed(Keys.SPACE)) {

			inAir = true;
			if (inAir == true) {
				if (ply_y == 672) {
					f_dy = -9;
					inAir = false;
				}

				f_dy += gravity;
				ply_y += f_dy;

			}
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
	
	public void gravity(){
		
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

	public float getPly_x() {
		return ply_x;
	}

	public void setPly_x(float ply_x) {
		this.ply_x = ply_x;
	}

	public float getPly_y() {
		return ply_y;
	}

	public void setPly_y(float ply_y) {
		this.ply_y = ply_y;
	}

	public float getF_dx() {
		return f_dx;
	}

	public void setF_dx(float f_dx) {
		this.f_dx = f_dx;
	}

	public float getF_dy() {
		return f_dy;
	}

	public void setF_dy(float f_dy) {
		this.f_dy = f_dy;
	}

}
