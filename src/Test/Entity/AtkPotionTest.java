package Entity;

import Creature.Archer;
import Creature.Player;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class AtkPotionTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    Item p = new AtkPotion("a","a",0,10,0);
    @Test
    public void testUse() throws Exception {
        archer.addInventory(potionInventory);
        p.use(archer);

        assertEquals(archer.getATK(), 20);
        assertEquals(potionInventory.getItems().size(),0);
    }
}
