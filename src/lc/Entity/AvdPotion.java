package Entity;

import Creature.Player;

public class AvdPotion extends Item {
    public AvdPotion(String name, String description, int price, int effect, int inventoryNo) {
        super(name, description, price, effect, inventoryNo);
    }

    @Override
    public void use(Player p) {
        p.setAVD(p.getAVD() + this.getEffect());
        p.getInventories().get(getInventoryNo()).remove(this);
    }
}
