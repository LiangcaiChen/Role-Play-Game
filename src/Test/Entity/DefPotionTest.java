package Entity;

import Creature.Archer;
import Creature.Player;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefPotionTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    Item p = new DefPotion("a","a",0,10,0);

    @Test
    public void testUse() {
        archer.addInventory(potionInventory);
        p.use(archer);

        assertEquals(archer.getDEF(), 20);
        assertEquals(potionInventory.getItems().size(), 0);
    }
}
