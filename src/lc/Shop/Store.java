package Shop;

import Creature.Player;
import Entity.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Store<T extends Item> {
    private String name;
    private List<T>items;

    public Store(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    abstract void sell(Player p, Item item);

    abstract String list(Player p);

    public String title() {
        return "-------------------------" + this.name + "-------------------------\n";
    }

    public String body() {
        String text = "";
        for(int i = 0; i < items.size(); i++) {
            text += "<" + (i + 1) + "> " + items.get(i).getName() + "\t\t" + items.get(i).getDescription() + "\t\t" + items.get(i).getPrice() + " coins" + "\n";
        }
        return text;
    }

    public String option(Player p) {
        return "Coins: " + p.getCoins() + "\n";
    }

    public boolean enoughMoney(Player p, T item) {
        if(p.getCoins() >= item.getPrice()) {
            return true;
        } else {
            System.out.println("You don't have enough money");
            return false;
        }
    }

    public String sellInfo(T item) {
        return (item.getName() + " Added !");
    }

    public String getName() {
        return name;
    }

    public List<T> getItems() {
        return items;
    }
}
