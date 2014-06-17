package de.comyoutech.cowboyandalien;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.comyoutech.cowboyandalien.items.AbstractItem;
import de.comyoutech.cowboyandalien.shop.ItemStore;

public class ItemShopScreen implements ApplicationListener {

    Texture unicorn;
    SpriteBatch batch;
    OrthographicCamera camera;
    Stage stage;
    ArrayList<AbstractItem> itemList;
    BitmapFont font;
    Skin skin;
    Button buyItem;

    @Override
    public void create() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // FileHandle exoFile = Gdx.files.local("goHome.fnt");

        font = new BitmapFont(); // no parameters - thus default 15-pt Arial
        font.setColor(0.0f, 0.0f, 1.0f, 1.0f); // blue font
        font.setScale(1); // font scaled

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ItemStore store = new ItemStore();
        itemList = store.getList();
        stage = new Stage();

        skin = new Skin();
        buyItem = new Button();

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        int x = 0;

        // 7Drawable drawable = skin.getDrawable("enemy");

        for (AbstractItem item : itemList) {

            Image nextItemPicture = new Image(item.getPicture());
            String nextItemName = item.getName();
            String nextItemDescription = item.getDescription();
            int nextItemPrice = item.getPrice();
            nextItemPicture.setSize(50, 50); // scale the tree to the right size

            x += 120;
            nextItemPicture.setPosition(x, 460); // center the tree
            stage.addActor(nextItemPicture);

            stage.getSpriteBatch().begin();
            font.draw(stage.getSpriteBatch(), nextItemName, x, 440);

            font.draw(stage.getSpriteBatch(), nextItemDescription, x, 400);
            String wurst = String.valueOf(nextItemPrice);
            font.draw(stage.getSpriteBatch(), wurst, x, 370);

            final TextButton buttonBuy = new TextButton("Kaufen", skin);
            buttonBuy.setPosition(x, 200);

            buttonBuy.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    buttonBuy.addAction(Actions.fadeOut(4.7f));

                }
            });

            Table table = new Table(skin);
            table.setFillParent(true);

            TextField nameText = new TextField(nextItemName, skin);

            table.add("Name : ").width(100);
            table.add(nameText).width(100).padRight(500 + x + 100);

            stage.addActor(table);

            Gdx.input.setInputProcessor(stage);
            stage.getSpriteBatch().end();

        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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
        batch.dispose(); // remove batch when app ending
        font.dispose(); // remove font when app ending
        stage.dispose();
        skin.dispose();
    }

}
