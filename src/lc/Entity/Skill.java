package Entity;

import Creature.Player;

public class Skill extends Item {
    public Skill(String name, String description, String againstType, int price, double effectDouble, int inventoryNo) {
        super(name, description, againstType, price, effectDouble, inventoryNo);
    }

    @Override
    public void use(Player p) {
        p.setATK((int) (p.getATK() * this.getEffectDouble()));
        p.getInventories().get(getInventoryNo()).remove(this);
    }
}
