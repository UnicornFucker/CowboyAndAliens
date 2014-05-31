package de.comyoutech.cowboyandalien.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import de.comyoutech.cowboyandalien.entities.PlayerEntity;

public abstract class AbstractItem {

    private int price;
    private int power;
    private Texture picture;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Texture getPicture() {
        return picture;
    }

    public void setPicture(String string) {
        this.picture = new Texture(Gdx.files.internal(string));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void modifyPlayer(PlayerEntity player) {

    }

}
