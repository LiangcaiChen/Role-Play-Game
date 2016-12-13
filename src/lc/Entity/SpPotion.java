package Entity;

import Creature.Player;

public class SpPotion extends Item {
    public SpPotion(String name, String description, int price, int effect, int inventoryNo) {
        super(name, description, price, effect, inventoryNo);
    }

    @Override
    public void use(Player p) {
        p.setSP(p.getSP() + this.getEffect());
        p.getInventories().get(getInventoryNo()).remove(this);
    }
}
