package Entity;

import java.util.ArrayList;
import java.util.List;

public class Chest<T extends Item> {
    private List<T> items;
    private boolean locked;
    private String description;

    public Chest(String description, boolean locked) {
        this.locked = locked;
        this.description = description;
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }

    public boolean isLocked() {
        return locked;
    }

    void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getDescription() {
        return description;
    }
}
