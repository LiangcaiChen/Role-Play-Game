package Entity;

import Creature.Player;

public class HealthPotion extends Item {
    public HealthPotion(String name, String description, int price, int effect, int inventoryNo) {
        super(name, description, price, effect, inventoryNo);
    }

    @Override
    public void use(Player p ) {
        healthExceed(p, getEffect());
        System.out.println(healthRestoreText(getEffect()));
        p.getInventories().get(getInventoryNo()).remove(this);
    }

    private void healthExceed(Player p, int amount) {
        if(p.getHealth() + amount > p.getMaxHealth()) {
            p.setHealth(p.getMaxHealth());
        } else {
            p.setHealth(p.getHealth() + amount);
        }
    }

    private String healthRestoreText(int amount) {
        return "Restore " + amount + " health";
    }
}
