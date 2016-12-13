package Entity;

import Creature.Player;

public class CriPotion extends Item {
    public CriPotion(String name, String description, int price, int effect, int inventoryNo) {
        super(name, description, price, effect, inventoryNo);
    }

    @Override
    public void use(Player p) {
        p.setCRI(p.getCRI() + this.getEffect());
        p.getInventories().get(getInventoryNo()).remove(this);
    }
}
