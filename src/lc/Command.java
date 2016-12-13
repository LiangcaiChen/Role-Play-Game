import Creature.Player;
import Entity.Chest;
import Entity.Inventory;
import Entity.Item;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Command {
    private Player p;
    private Scanner scanner = new Scanner(System.in);
    private List<Chest<Item>> chests;

    public Command(Player p) {
        this.p = p;
        this.chests = p.getCurrentRoom().getChests();
    }

    /**
     * loop through all inventories
     * loop though all items in inventory
     * if player has the item
     * use it
     * remove it
     *
     * @param input "use itemName"
     */
    public void use(String input) {
        FIRST:
        for(Inventory<Item> inventory : p.getInventories()) {
            for(int i = 0; i < inventory.getItems().size(); i++) {
                if(inventory.getItems().get(i).getName().equalsIgnoreCase(input.substring(4))) {
                    inventory.getItems().get(i).use(p);
                    break FIRST;
                }
            }
        }
    }

    /**
     * change room depends on direction
     * @param direction w, e, n, s
     */
     void move(String direction) {
        if(!hasAwakeEnemy()) {
            p.setCurrentRoom(p.getCurrentRoom().getNextRoom(direction));
        }
    }

     boolean hasAwakeEnemy() {
        for(int i = 0; i < p.getCurrentRoom().getEnemies().size(); i++) {
            if(p.getCurrentRoom().getEnemies().get(i).isWake()) {
                System.out.println("Found awake enemy in the room, you can't move to other room");
                return true;
            }
        }
        return false;
    }

     void openChest() {
        if(hasChests()) {
            switch (numOfUnlockedChest()) {
                case 0: System.out.println("Chest is locked, you can't open it\n"); break;

                case 1: openOneChest(); break;

                default: openMoreChests(); break;
            }
        }
    }

    /**
     * loop through all chests in the room
     *
     * @return the number of chests which are not locked
     */
     int numOfUnlockedChest() {
        chests = p.getCurrentRoom().getChests();
        int numChests = 0;
        for (Chest chest : chests) {
            if (!chest.isLocked()) {
                numChests++;
            }
        }
        return numChests;
    }

    /**
     * loop through all chests in the room
     * find unlocked chest
     * open it, add items to player
     * remove this chest from the room
     */
     private void openOneChest() {
        int openedChest = 0;
        for(int i = 0; i < chests.size(); i++) {
            if(!chests.get(i).isLocked()) {
                addItemToPlayer(i);
                openedChest = i;
            }
        }
        p.getCurrentRoom().removeChest(openedChest);
        System.out.println();
    }

     void addItemToPlayer(int chestNum) {
        List<Item> items = chests.get(chestNum).getItems();

        if(items.size() == 0) {
            System.out.println("nothing in it...");
        }

        for (Item item : items) {
            p.addItem(item);
            System.out.println(item.getName() + " Added");
        }
    }

    /**
     * if room has item
     * player pick it
     * remove it from the room
     * until there is no item left
     */
     void pick() {
        if(hasItem()) {
            while (p.getCurrentRoom().getItems().size() != 0) {
                Item item = p.getCurrentRoom().getItems().get(0);
                p.addItem(item);
                System.out.println(item.getName() + " Added");
                p.getCurrentRoom().removeItem(item);
            }
        }
    }

    /**
     * loop through all items in the room
     * if found then add it to player
     * remove the item
     * jump out the loop
     *
     * @param input pick itemName
     */
     void pick(String input) {
        for(Item item : p.getCurrentRoom().getItems()) {
            if(item.getName().equalsIgnoreCase(input.substring(5))) {
                p.addItem(item);
                System.out.println(item.getName() + " Added");
                p.getCurrentRoom().removeItem(item);
                break;
            }
        }
    }

    /**
     *
     * @return true if room has 1 or more items
     *          false if room has 0 item
     */
     boolean hasItem() {
        if(p.getCurrentRoom().getItems().size() > 0) {
            return true;
        } else {
            System.out.println("No item found");
            return false;
        }
    }


    /**
     *
     * @return true if has chest
     *          false if no chest
     */
     boolean hasChests() {
        List<Chest<Item>> chests = p.getCurrentRoom().getChests();
        if(chests.size() == 0) {
            System.out.println("Cannot find any chest in the room\n");
            return false;
        }
         return false;
    }

    /**
     * print all chests which can be open
     * get use input, knowing which chest to open
     * open chest, add items to player
     */
     private void openMoreChests() {
        try {
            System.out.println("Choose one to open: " + "\n" + p.getCurrentRoom().chestsList());
            int inputInt = scanner.nextInt();
            addItemToPlayer(inputInt);
            p.getCurrentRoom().removeChest(inputInt);
            System.out.println();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
    }

     void status() {
        System.out.println(p.status());
    }

     void help() {
        System.out.println(helpText());
    }

     private String helpText() {
        return "----------------Welcome to helpPage------------------\n" +
                "Command\t\tWhat it does\t\t\tExample\n\n" +
                "help\t\thelp page\n\n" +
                "e\t\tgo east\n\n" +
                "w\t\tgo west\n\n" +
                "n\t\tgo north\n\n" +
                "s\t\tgo south\n\n" +
                "pick\t\tpick up item\t\t\tpick key\n\n" +
                "pick *\t\tpick up all items in the room\n\n" +
                "i\t\tcheck inventory\n\n" +
                "fight\t\tfight the enemy\n\n" +
                "open\t\topen chest\t\t\topen chest\n\n" +
                "buy\t\tgo to shop\n\n" +
                "info\t\tget item description\t\tinfo chest key\n\n" +
                "use\t\tuse item\t\t\tuse chest key\n\n" +
                "status\t\tcheck player status\n\n" +
                "look\t\tget room description\n\n" +
                "end\t\tend the game";
    }


    void look() {
        p.getCurrentRoom().print();
    }

    void info(String input) {
        String itemName = input.substring(5);

        for (Inventory<Item> inventory : p.getInventories()) {
            if (p.checkItemsAndPrint(itemName, inventory)) break;
        }
    }

    void printInventory() {
        p.printInventoryItems();
    }
}
