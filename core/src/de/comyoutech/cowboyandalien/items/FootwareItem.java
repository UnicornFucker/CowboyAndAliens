package de.comyoutech.cowboyandalien.items;

public class FootwareItem extends AbstractItem {

    public String name = "Speedrunner";
    public String description = "schneller laufen";
    private int powerSpeed = 20;
    private int price = 200;

    public FootwareItem() {
        setPicture("textures/shop/schuheIcon.png");
        setPower(powerSpeed);
        setPrice(price);
        setName(name);
        setDescription(description);
    }

}
