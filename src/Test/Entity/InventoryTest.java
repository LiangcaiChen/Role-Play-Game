package Entity;

import Creature.Archer;
import Creature.Player;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {
    Room currentRoom = new Room("Start room");
    Player archer = new Archer(50, "Archer", "Arrow", 13, 10, 8, 5, 15, 20, currentRoom);
    Inventory inventory = new Inventory("A", true);
    Item key = new Key("Key", "a", 0);

    @Test
    public void testItemList() throws Exception {
        inventory.add(key);
        archer.addInventory(inventory);

        String text = "1 item in this inventory: \nKey\n";

        assertEquals(inventory.itemList(), text);
    }

    @Test
    public void testTitle() throws Exception {
        String text = "--------A--------\n";
        assertEquals(inventory.list(), text);
    }

    @Test
    public void testBody() throws Exception {
        inventory.add(key);
        archer.addInventory(inventory);
        String text = "<1> Key\n";
        assertEquals(inventory.body(), text);
    }

    @Test
    public void testForBattle() throws Exception {
        assertEquals(inventory.isForBattle(), true);
    }

    @Test
    public void testGetname() throws Exception {
        assertEquals(inventory.getName(), "A");
    }

}
