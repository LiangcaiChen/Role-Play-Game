package Entity;

import Creature.Player;

public class AtkPotion extends Item {
    public AtkPotion(String name, String description, int price, int effect, int inventoryNo) {
        super(name, description, price, effect, inventoryNo);
    }

    @Override
    public void use(Player p) {
        p.setATK(p.getATK() + this.getEffect());
        p.getInventories().get(getInventoryNo()).remove(this);
    }
}
