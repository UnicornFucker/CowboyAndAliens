package de.comyoutech.cowboyandalien.items;

public class UnicornItem extends AbstractItem {

    public String name = "Unicorn";
    public String description = "Fickt alles";
    private int powerSpeed = 2000;
    private int price = 250;

    public UnicornItem() {
        setPicture("textures/shop/unicorn.png");
        setPower(powerSpeed);
        setPrice(price);
        setName(name);
        setDescription(description);
    }

}
