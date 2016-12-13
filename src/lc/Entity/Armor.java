package Entity;

import Creature.Player;

public class Armor extends Item {
    public Armor(String name, String description, int price, int effect, int inventoryNo, int positionNo) {
        super(name, description, price, effect, inventoryNo, positionNo);
    }

    public Armor(String name, String description) {
        super(name, description);
    }

    /**
     * player's DEF - old defense item DEF + new defense item DEF
     * @param p player
     */
    @Override
    public void use(Player p) {
        p.setDEF(p.getDEF() - p.getInventories().get(getInventoryNo()).getItems().get(getPositionNo()).getEffect() + getEffect());
    }
}
