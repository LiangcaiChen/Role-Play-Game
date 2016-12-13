package Entity;

import Creature.Archer;
import Creature.Player;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArmorTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);

    Item ironArmor = new Armor("Iron Armor", "DEF + 7", 10, 10, 0, 0);
    Item armor = new Armor("Iron Armor", "DEF + 7");

    @Test
    public void testUse() {
        archer.addInventory(potionInventory);
        archer.addItem(armor);
        ironArmor.use(archer);
        assertEquals(archer.getDEF(), 20);
    }
}
