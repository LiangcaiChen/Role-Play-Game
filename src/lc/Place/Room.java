package Place;

import Creature.Enemy;
import Creature.Player;
import Entity.Chest;
import Entity.Item;
import Sound.Sound;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private boolean last = false;
    private String description;
    private List<Exit> exits;
    private List<Item> items;
    private List<Chest<Item>> chests;
    private List<Enemy> enemies;

    public Room(String description) {
        this.description = description;
        this.exits = new ArrayList<>();
        this.chests = new ArrayList<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public Room(String description, boolean last) {
        this.last = last;
        this.description = description;
        this.exits = new ArrayList<>();
        this.chests = new ArrayList<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public void addExit(Exit aExit) {
        exits.add(aExit);
    }

    public void addChest(Chest<Item> chest) {
        this.chests.add(chest);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeChest(int i) {
        this.chests.remove(i);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    /**
     * loop through all exits in the room
     * if direction matches a exit
     * show room info
     * get leadTo room from that exit
     *
     * @param direction "e s w n"
     * @return room
     */
    public Room getNextRoom(String direction) {
        for (Exit exit : exits) {
            if (exit.getDirection().equals(direction)) {
                return getNotLockedRoom(exit);
            }
        }

        System.out.println("You hit the wall");
        return this;
    }

    private Room getNotLockedRoom(Exit exit) {
        if(exit.isLocked()) {
            System.out.println("Door is locked");
            return this;
        } else {
            exit.getLeadTo().print();
            Sound.play(Sound.closeDoor);
            return exit.getLeadTo();
        }
    }


    public void print() {
        System.out.println(draw() + "\n" + this.description + printItem() + "\n\n" + printChest() + "\n" + printEnemy());
    }

    private String draw() {
        boolean north = false;
        boolean south = false;
        boolean east = false;
        boolean west = false;

        for (Exit exit : exits) {

            if (exit.getDirection().equals("w")) {
                west = true;
            }

            if (exit.getDirection().equals("e")) {
                east = true;
            }

            if (exit.getDirection().equals("n")) {
                north = true;
            }

            if (exit.getDirection().equals("s")) {
                south = true;
            }
        }

        return square(north, south, west, east);
    }

    private String square(boolean north, boolean south, boolean west, boolean east) {
        return lengthOfRow(25) + leftRightBoundary(10, north, south, west, east) + lengthOfRow(25);
    }

    private String lengthOfRow(int length) {
        String rowLength = "";
        for(int i = 0; i < length; i++) {
            rowLength += "-";
        }
        return rowLength + "\n";
    }

    private String leftRightBoundary(int numOfRow, boolean north, boolean south, boolean west, boolean east) {
        String bound = "";

        if(north) {
            bound += "|\t   n\t\t|\n";
        }

        for(int i = 0; i < numOfRow / 2; i++) {
            bound += "|\t\t\t|\n";
        }

        if(west && east) {
            bound += "|w\t\t\te|\n";
        }

        if(west && ! east) {
            bound += "|w\t\t\t   |\n";
        }

        if(!west && east) {
            bound += "|\t\t\te|\n";
        }

        for(int i = 0; i < numOfRow / 2; i++) {
            bound += "|\t\t\t|\n";
        }

        if(south) {
            bound += "|\t   s\t\t|\n";
        }

        return bound;
    }

//     list all unlocked chests
   public String chestsList() {
       String text = "";
       for (int i = 0; i < chests.size(); i++) {
           Chest chest = chests.get(i);

           if(!chest.isLocked()) {
               text += "<" + i + "> " + chest.getDescription() + "\n";
           }
       }
       return text;
   }

    public String printItem() {

        if(items.size() == 0) {
            return "";
        }

        String info = "You found: ";
        for (Item item : items) {
            info += item.getName() + ", ";
        }

        info = info.substring(0, info.length() - 2);
        info += " in the room";

        return "\n\n" + info;
    }

    public String chestDes(Player p) {
        String des = "";
        for(int i = 0; i < chests.size(); i++) {
            if(chests.get(i).isLocked()) {
                des += "<" + i + "> " +chests.get(i).getDescription() + "\n";
            }
        }
        return des;
    }

    private String printChest() {
        switch (chests.size()) {
            case 0: return "";

            case 1: return "There is one chest in the corner\n";

            default: return "There are " + this.chests.size() + " chests in the room\n";
        }
    }

    public String printEnemy() {
        switch (enemies.size()) {
            case 0: return "";

            case 1: return "1 enemy in the room: \n" + enemyInfo();

            default: return enemies.size() + " enemies in the room: \n" + enemyInfo();
        }
    }

    private String enemyInfo() {
        String enemyInfo = "";

        for(int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            enemyInfo += "<" + (i + 1) + "> " + "\n" + enemy;
        }

        return  enemyInfo;
    }

    /**
     * loop through all doors
     * searh for locked doors
     *
     * @return all locked doors in order and their description
     */
    public String lockedDoorInfo() {
        String text = "";
        for (Exit exit : exits) {
            if (exit.isLocked()) {
                text += "<" + exit.getDirection() + "> " + exit.getDescription() + "\n";
            }
        }
        return text;
    }

    public String getDescription() {
        return description;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public List<Chest<Item>> getChests() {
        return chests;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isLast() {
        return last;
    }

    public static void main(String[] args) {
        String x = " a, b, c, d, ";
        System.out.println(x.substring(0, x.length() - 2));
    }
}
