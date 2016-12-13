package Creature;

import Entity.*;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Room currentRoom = new Room("Start room");
    private Player archer = new Archer(50, "Archer", "Arrow", 13, 10, 8, 5, 15, 20, currentRoom);
    private Inventory<Item> inventory = new Inventory<>("Inventory", false);
    private Item key = new Key("Key", "A key", 0);

    @Test
    public void testAddInventory() throws Exception {
        archer.addInventory(inventory);
        assertEquals(inventory, archer.getInventories().get(0));
    }

    @Test
    public void testAddItem() throws Exception {
        archer.addInventory(inventory);
        archer.addItem(key);
        assertEquals(key, archer.getInventories().get(0).getItems().get(0));

    }

    @Test
    public void testReplaceItem() throws Exception {
        Item chestKey = new ChestKey("C", "c", 0);
        archer.addInventory(inventory);
        inventory.add(key);
        archer.replaceItem(0,0,chestKey);
        assertEquals(chestKey, archer.getInventories().get(0).getItems().get(0));
    }

    @Test
    public void testStatus() throws Exception {
        String status = "-------Status-------\n" +
                "#Class:\t\t" + "Archer" +
                "\n#Health:\t" + 100 +
                "\n#ATk:\t\t" + 13 +
                "\n#DEF:\t\t" + 10 +
                "\n#CRI:\t\t" + 8 +
                "\n#AVD:\t\t" + 5 +
                "\n#SP:\t\t" + 15 +
                "\n#XP:\t\t" + 0 +
                "\n#level:\t\t" + 1 +
                "\n#Coin(s):\t" + 20;

        assertEquals(archer.status(), status);
    }

    @Test
    public void testInventoryChoice() throws Exception {
        archer.addInventory(inventory);
        String text = "Choose an Inventory: \n<1> Inventory\n";
        assertEquals(archer.inventoryChoice(), text);
    }


    @Test
    public void testCheckItemsAndPrint() throws Exception {
        inventory.add(key);
        archer.addInventory(inventory);
        boolean answer = archer.checkItemsAndPrint("Key", inventory);
        assertEquals(answer, true);
    }

    @Test
    public void testLevelUp() throws Exception {
        archer.setXP(90);
        archer.levelUp(30);
        assertEquals(archer.getXP(), 20);

        archer.setXP(40);
        archer.levelUp(30);
        assertEquals(archer.getXP(), 70);
    }

    @Test
    public void testCriticalHit() throws Exception {
        archer.setCRI(100);
        assertEquals(archer.criticalHit(), 2);

        archer.setCRI(0);
        assertEquals(archer.criticalHit(), 1);
    }

    @Test
    public void testAVD() throws Exception{
        archer.setAVD(0);
        assertEquals(archer.AVDChecker(), 1);

        archer.setAVD(100);
        assertEquals(archer.AVDChecker(), 0);
    }
}