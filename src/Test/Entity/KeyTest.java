package Entity;

import Creature.Archer;
import Creature.Player;
import Place.Exit;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyTest {
    Item key = new Key("Key", "A key", 0);

    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    Room r2 = new Room("a");

    Exit exit = new Exit("e", "first", r2, true);
    Exit exit1 = new Exit("w", "second", r2, true);
    Exit exit4 = new Exit("e", "third", r2, false);

    @Test
    public void testUse() throws Exception {
        potionInventory.add(key);
        archer.addInventory(potionInventory);

        r1.addExit(exit4);

        key.use(archer);

        assertEquals(potionInventory.getItems().size(), 1);

    }

    @Test
    public void testUse1() throws Exception {
        potionInventory.add(key);
        archer.addInventory(potionInventory);

        r1.addExit(exit);

        key.use(archer);

        assertEquals(potionInventory.getItems().size(), 0);
        assertEquals(exit.isLocked(), false);
    }

    @Test
    public void testUnlockDoor() throws Exception {
        Key nextKey = new Key("a","a",0);
        potionInventory.add(nextKey);
        archer.addInventory(potionInventory);

        r1.addExit(exit);
        r1.addExit(exit1);
        r1.addExit(exit4);
        assertEquals(exit.isLocked(), true);
        assertEquals(exit1.isLocked(), true);
        nextKey.unlockDoor(archer,"e");
        assertEquals(exit.isLocked(), false);
        assertEquals(exit1.isLocked(), true);
        nextKey.unlockDoor(archer,"w");
        assertEquals(exit.isLocked(), false);
        assertEquals(exit1.isLocked(), false);
        assertEquals(exit4.isLocked(), false);


    }
}
