package de.comyoutech.cowboyandalien.shop;

import java.util.ArrayList;

import de.comyoutech.cowboyandalien.items.AbstractItem;
import de.comyoutech.cowboyandalien.items.FootwareItem;
import de.comyoutech.cowboyandalien.items.UnicornItem;

public class ItemStore {

    ArrayList<AbstractItem> itemlist = new ArrayList<AbstractItem>();

    public ItemStore() {
        FootwareItem item = new FootwareItem();
        itemlist.add(item);

        UnicornItem item2 = new UnicornItem();
        itemlist.add(item2);

    }

    public ArrayList<AbstractItem> getList() {
        return itemlist;
    }

}
