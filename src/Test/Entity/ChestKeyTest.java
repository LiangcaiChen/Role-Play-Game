package Entity;

import Creature.Archer;
import Creature.Player;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChestKeyTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    Item key = new ChestKey("a","a",0);
    Chest chest = new Chest("a",true);
    Chest chest2 = new Chest("a",false);

    @Test
    public void testUse() throws Exception {
        archer.addInventory(potionInventory);
        archer.addItem(key);
        r1.addChest(chest);
        key.use(archer);

        assertEquals(chest.isLocked(), false);
    }

    @Test
    public void testUse2() throws Exception {
        archer.addInventory(potionInventory);
        archer.addItem(key);
        r1.addChest(chest2);
        key.use(archer);

        assertEquals(potionInventory.getItems().size(), 1);
    }
}
