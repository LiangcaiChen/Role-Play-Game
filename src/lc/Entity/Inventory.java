package Entity;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T extends Item> {
    private List<T> items;
    private String name;
    private boolean forBattle;

    public Inventory(String name, boolean forBattle) {
        this.name = name;
        this.forBattle = forBattle;
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public String itemList() {
        String text = itemNumInInventory();
        for (Item item : items) {
            text += item.getName() + "\n";
        }

        return text;
    }

    private String itemNumInInventory() {
        return items.size() > 1 ? items.size() + " items in this inventory: \n" : items.size() + " item in this inventory: \n";
    }

    public String title() {
        return "--------" + this.name + "--------\n";
    }

    public String body() {
        String x = "";
        for(int i = 0; i < items.size(); i++) {
            x += "<" + (i + 1) + "> " + items.get(i).getName() + "\n";
        }
        return x;
    }

    public String list() {
        return title() + body();
    }

    public List<T> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public boolean isForBattle() {
        return forBattle;
    }
}