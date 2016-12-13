package Shop;

import Creature.Archer;
import Creature.Player;
import Entity.Armor;
import Entity.Inventory;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArmorStoreTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    Armor armor = new Armor("A","a armor");
    Armor armor1 = new Armor("Armor", "A armor", 10, 10, 0, 0);
    ArmorStore armorStore = new ArmorStore("a");

    @Test
    public void testSell() throws Exception {
        archer.setCoins(20);
        archer.setDEF(5);
        armorStore.add(armor1);
        potionInventory.add(armor);
        archer.addInventory(potionInventory);
        armorStore.sell(archer, armor1);

        assertEquals(archer.getCoins(), 10);

        assertEquals(archer.getDEF(), 15);

        assertEquals(potionInventory.getItems().get(0), armor1);

    }

    @Test
    public void testList() throws Exception {
        archer.setCoins(10);
        armorStore.add(armor1);
        potionInventory.add(armor);
        String list = "-------------------------a-------------------------\n<1> Armor\t\tA armor\t\t10 coins\nCoins: 10\n";
        assertEquals(armorStore.list(archer),list);
    }
}
