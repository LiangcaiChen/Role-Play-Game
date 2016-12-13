package Entity;

import Creature.Player;

public class Weapon extends Item {
    public Weapon(String name, String description, int price, int effect, int inventoryNo, int positionNo) {
        super(name, description, price, effect, inventoryNo, positionNo);
    }

    @Override
    public void use(Player p) {
        p.setATK(p.getATK() + super.getEffect());
    }

}
