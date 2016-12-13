package Creature;

import Entity.Inventory;
import Entity.Item;
import Place.Room;
import Sound.Sound;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class Player extends Fighter{
    private Room currentRoom;
    private List<Inventory<Item>>inventories;
    private String className;
    private String weaponTitle;
    private int coins;
    private int XP;
    private int energy;

    private Scanner scanner = new Scanner(System.in);

    public Player(int energy, String className, String weaponTitle, int maxATK, int maxDEF, int maxCRI, int maxAVD, int maxSP, int coins, Room currentRoom) {
        super(100, maxATK, maxDEF, maxCRI, maxAVD, maxSP, 1);
        this.energy = energy;
        this.className = className;
        this.coins = coins;
        this.weaponTitle = weaponTitle;
        this.XP = 0;
        this.currentRoom = currentRoom;
        this.inventories = new ArrayList<>();
    }

    public void addInventory(Inventory<Item> inventory) {
        inventories.add(inventory);
    }

    public void addItem(Item item) {
        this.inventories.get(item.getInventoryNo()).add(item);
    }

    public void replaceItem(int inventoryNo, int itemNumber, Item item) {
        this.inventories.get(inventoryNo).getItems().set(itemNumber, item);
    }

    public String status() {
        return ("-------Status-------\n" +
                "#Class:\t\t" + this.className +
                "\n#Health:\t" + getHealth() +
                "\n#ATk:\t\t" + getATK() +
                "\n#DEF:\t\t" + getDEF() +
                "\n#CRI:\t\t" + getCRI() +
                "\n#AVD:\t\t" + getAVD() +
                "\n#SP:\t\t" + getSP() +
                "\n#XP:\t\t" + this.XP +
                "\n#level:\t\t" + getLevel() +
                "\n#Coin(s):\t" + this.coins
        );
    }

    /**
     * get user input
     * print all items in the inventory
     */
    public void printInventoryItems() {
        int input = inventoryInput();

        if(inventories != null) {
            String inventoryItem = inventories.get(input - 1).itemList();
            System.out.println(inventoryItem);
        }
    }

    private int inventoryInput() {
        while (true) {
            try {
                System.out.println(inventoryChoice());
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again\n");
                scanner.nextLine();
            }
        }
    }

    //list all inventories in order
    String inventoryChoice() {
        String text = "Choose an Inventory: \n";
        for(int i = 0; i < inventories.size(); i++) {
            text += "<" + (i + 1) + "> " + inventories.get(i).getName() + "\n";
        }
        return text;
    }

    /**
     * loop though all items
     * if item exits
     *
     * @param itemName the name of the item
     * @param inventory the inventory where the item should be
     * @return description of the item
     */
    public boolean checkItemsAndPrint(String itemName, Inventory<Item> inventory) {
        List<Item> items = inventory.getItems();
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                System.out.println(item.getDescription() + "\n");
                return true;
            }
        }
        return false;
    }

    public int gainCoin() {
        return ran.nextInt(10);
    }

    public int gainXP() {
        return 20 + ran.nextInt(15);
    }

    /**
     * gain xp
     * level up if reaches 100 xp then
     * enhance all abilities
     *
     * @param xp experience
     */
    public void levelUp(int xp) {
        this.XP += xp;
        int x = this.XP - 100;
        if(this.XP >= 100) {
            this.XP = x;
            setLevel(getLevel() + 1);
            System.out.println("Level up");
            System.out.println(levelUpEnhance());
            Sound.play(Sound.lvUp);
        }

    }

    private String arrow() {
        return "  ----->  ";
    }

    /**
     * enhance all abilities
     * @return ability enhanced detail
     */
    private String levelUpEnhance() {
        int hea = ran.nextInt(10);
        int at = ran.nextInt(5);
        int de = ran.nextInt(5);
        int cr = ran.nextInt(5);
        int av = ran.nextInt(5);
        int sp = ran.nextInt(5);

        String levelupText ="Ability Enhance: \n" +
                "Max health " + getMaxHealth() + arrow() + (getMaxHealth() + hea) + "\n" +
                "Max ATK " + getMaxATK() + arrow() + (getMaxATK() + at) + "\n" +
                "Max DEF " + getMaxDEF() + arrow() + (getMaxDEF() + de) + "\n" +
                "Max CRI " + getMaxCRI() + arrow() + (getMaxCRI() + cr) + "\n" +
                "Max AVD " + getMaxAVD() + arrow() + (getMaxAVD() + av) + "\n" +
                "Max SP " + getMaxSP() + arrow() + (getMaxSP() + sp) + "\n";

        this.setMaxHealth(getMaxHealth() + hea);
        this.setMaxATK(getMaxATK() + at);
        this.setMaxDEF(getMaxDEF() + de);
        this.setMaxCRI(getMaxCRI() + cr);
        this.setMaxAVD(getMaxAVD() + av);
        this.setMaxSP(getMaxSP() + sp);

        return levelupText;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Inventory<Item>> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory<Item>> inventories) {
        this.inventories = inventories;
    }

    public String getClassName() {
        return className;
    }

    public String getWeaponTitle() {
        return weaponTitle;
    }

    public int getCoins() {
        return coins;
    }

    public int getXP() {
        return XP;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
}
