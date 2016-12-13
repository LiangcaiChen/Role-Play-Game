package Entity;

import Creature.Player;

public class DefPotion extends Item {
    public DefPotion(String name, String description, int price, int effect, int inventoryNo) {
        super(name, description, price, effect, inventoryNo);
    }

    @Override
    public void use(Player p) {
        p.setDEF(p.getDEF() + this.getEffect());
        p.getInventories().get(getInventoryNo()).remove(this);
    }
}
