package Entity;

import Creature.Player;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ChestKey extends Item{
    private Scanner scanner = new Scanner(System.in);

    public ChestKey(String name, String description, int inventoryNo) {
        super(name, description, inventoryNo);
    }

    public void use(Player p) {
        switch (lockedChest(p)) {
            case 0: System.out.println("No locked chest\n"); break;

            case 1: unlockChest(p); p.getInventories().get(getInventoryNo()).remove(this); break;

            default: chooseChestToUnlock(p); p.getInventories().get(getInventoryNo()).remove(this); break;
        }
    }

    /**
     * @param p player
     * @return the number of chests which are locked
     */
    private int lockedChest(Player p) {
        List<Chest<Item>> chests = p.getCurrentRoom().getChests();
        int numChests = 0;
        for (Chest chest1 : chests) {
            if (chest1.isLocked()) {
                numChests++;
            }
        }
        return numChests;
    }

    /**
     * loop through all chests in the room
     * find all chests which are locked
     * unlock them
     *
     * @param p player
     */
    private void unlockChest(Player p) {
        List<Chest<Item>> chests = p.getCurrentRoom().getChests();
        chests.stream().filter(Chest::isLocked).forEach(chest -> {
            chest.setLocked(false);
            System.out.println("Chest is unlocked\n");
        });
    }



    /**
     * list all chests which can be unlocked
     * get user input
     * unlock the chest
     * print success msg
     *
     * @param p player
     */
    private void chooseChestToUnlock(Player p) {
        List<Chest<Item>> chests =  p.getCurrentRoom().getChests();

        try {
            System.out.println("Choose a chest to unlock: " + "\n" + p.getCurrentRoom().chestDes(p));
            int input = scanner.nextInt();
            chests.get(input).setLocked(false);
            System.out.println("Chest is unlocked\n");
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }

    }

}
