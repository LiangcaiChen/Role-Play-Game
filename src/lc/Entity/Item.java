package Entity;

import Creature.Player;

public abstract class Item {
    /**
     * name: name of the item
     *
     * description: item detail
     *
     * inventoryNo: put item in which inventory
     *
     * positionNo: where to put in the inventory
     *
     * price: price of the item
     *
     * effectDouble: (double) special effect from the item
     *
     * effect: (int) special effect from the item
     *
     * againstType: for skills, deal extra damage to that type of enemy
     */
    private String name;
    private String description;
    private int inventoryNo;
    private int positionNo;
    private int price;
    private double effectDouble;
    private int effect;
    private String againstType;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //special item
    public Item(String name, String description, int inventoryNo) {
        this.name = name;
        this.description = description;
        this.inventoryNo = inventoryNo;
    }

    //armor & weapon
    public Item(String name, String description, int price, int effect, int inventoryNo, int positionNo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.effect = effect;
        this.inventoryNo = inventoryNo;
        this.positionNo = positionNo;
    }

    //potion
    public Item(String name, String description, int price, int effect, int inventoryNo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.effect = effect;
        this.inventoryNo = inventoryNo;
    }

    //skill
    public Item(String name, String description, String againstType, int price, double effectDouble, int inventoryNo) {
        this.name = name;
        this.description = description;
        this.againstType = againstType;
        this.price = price;
        this.effectDouble = effectDouble;
        this.inventoryNo = inventoryNo;
    }

    public abstract void use(Player p);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getEffect() {
        return effect;
    }

    double getEffectDouble() {
        return effectDouble;
    }

    public String getAgainstType() {
        return againstType;
    }

    public int getInventoryNo() {
        return inventoryNo;
    }

    public int getPositionNo() {
        return positionNo;
    }

    public void setName(String name) {
        this.name = name;
    }
}
