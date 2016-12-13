import Creature.Archer;
import Creature.Enemy;
import Creature.Player;
import Entity.*;
import Place.Exit;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTest {
    private Room r1 = new Room("Room1");
    private Room r2 = new Room("Room2");
    private Room r3 = new Room("Room3");

    private Exit r1Tr2 = new Exit("e", "a", r2, false);
    private Exit r2Tr1 = new Exit("w", "a", r1, false);
    private Exit r1Tr3 = new Exit("n", "a", r3, false);
    private Exit r3Tr1 = new Exit("s", "a", r1, false);

    private Enemy enemyAwake = new Enemy(100, 10, 10,10,10,10,10,true);

    private Player archer = new Archer(50, "Archer", "Arrow", 13, 10, 8, 5, 15, 20, r1);

    private Inventory<Item> potionInventory = new Inventory<>("Potion Inventory", true);
    private Item potion = new HealthPotion("Small Potion", "A small Potion", 10, 10, 0);
    private Command command = new Command(archer);



    private Chest<Item> chest = new Chest<>("a", false);
    private Chest<Item> chest3 = new Chest<>("a", false);
    private Chest<Item> chest2 = new Chest<>("a", true);

    private Item key = new Key("Key", "A key",0);
    private Item chestKey = new ChestKey("Chest key","", 0);

    @Test
    public void testUse() throws Exception {
        potionInventory.add(potion);
        archer.addInventory(potionInventory);
        archer.setHealth(90);
        command.use("use small potion");

        assertEquals(archer.getHealth(), 100);
    }

    @Test
    public void testMove() throws Exception {
        r1.addExit(r1Tr2);
        r1.addExit(r1Tr3);
        r2.addExit(r2Tr1);
        r3.addExit(r3Tr1);

        command.move("e");
        assertEquals(r2, archer.getCurrentRoom());

        command.move("w");
        assertEquals(r1, archer.getCurrentRoom());

        command.move("n");
        assertEquals(r3, archer.getCurrentRoom());

        command.move("s");
        assertEquals(r1, archer.getCurrentRoom());

        r1.addEnemy(enemyAwake);
        command.move("e");
        assertEquals(r1, archer.getCurrentRoom());
    }

    @Test
    public void testAwakeEnemy() throws Exception {
        r1.addEnemy(enemyAwake);
        assertEquals(command.hasAwakeEnemy(), true);
    }

    @Test
    public void testNumOfUnlockedChest() throws Exception {
        r1.addChest(chest);
        r1.addChest(chest2);
        r1.addChest(chest2);

        assertEquals(command.numOfUnlockedChest(), 1);
    }

    @Test
    public void testOpenChest() throws Exception {
        archer.addInventory(potionInventory);
        r1.addChest(chest2);
        command.openChest();
        assertEquals(chest2.isLocked(), true);
        assertEquals(potionInventory.getItems().size(), 0);

        r1.addChest(chest);
        chest.add(key);
        command.openChest();
        assertEquals(r1.getChests().size(), 1);
        assertEquals(potionInventory.getItems().get(0), key);
    }

    @Test
    public void testAddItemToPlayer() throws Exception {
        archer.addInventory(potionInventory);
        chest.add(key);
        chest3.add(chestKey);
        chest3.add(potion);
        r1.addChest(chest);
        r1.addChest(chest3);

        command.addItemToPlayer(1);

        assertEquals(potionInventory.getItems().size(), 2);
        assertEquals(potionInventory.getItems().get(0), chestKey);
        assertEquals(potionInventory.getItems().get(1), potion);
    }

    @Test
    public void testPickAll() throws Exception {
        archer.addInventory(potionInventory);
        r1.addItem(key);
        r1.addItem(chestKey);
        r1.addItem(potion);

        command.pick();

        assertEquals(potionInventory.getItems().size(), 3);
        assertEquals(potionInventory.getItems().get(0), key);
        assertEquals(potionInventory.getItems().get(1), chestKey);
        assertEquals(potionInventory.getItems().get(2), potion);
    }

    @Test
    public void testPickOne() throws Exception {
        archer.addInventory(potionInventory);
        r1.addItem(key);
        r1.addItem(chestKey);
        r1.addItem(potion);

        command.pick("pick key");

        assertEquals(potionInventory.getItems().size(), 1);
        assertEquals(potionInventory.getItems().get(0), key);
    }

    @Test
    public void testHasItem() throws Exception {
        assertEquals(command.hasItem(), false);
        r1.addItem(key);
        assertEquals(command.hasItem(), true);

    }

    @Test
    public void testHasChests() throws Exception {
        assertEquals(command.hasChests(), false);
        r1.addChest(chest);
        assertEquals(command.hasChests(), true);
    }


}
