package Entity;

import Creature.Archer;
import Creature.Player;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    Weapon weapon2 = new Weapon("a","a",0, 10, 0,0);
    Weapon weapon1 = new Weapon("a","a",0, 0, 0,0);

    @Test
    public void testUse() throws Exception {
        archer.addInventory(potionInventory);
        potionInventory.add(weapon1);
        weapon2.use(archer);
        assertEquals(potionInventory.getItems().size(), 1);
        assertEquals(archer.getATK(), 20);
    }
}
