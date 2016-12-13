package Shop;

import Creature.Archer;
import Creature.Player;
import Entity.HealthPotion;
import Entity.Inventory;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class PotionStoreTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    PotionStore potionStore = new PotionStore("a");
    HealthPotion p = new HealthPotion("a", "A potion", 10, 10, 0);

    @Test
    public void testSell() throws Exception {
        archer.setCoins(20);
        archer.setDEF(5);
        potionStore.add(p);
        archer.addInventory(potionInventory);
        potionStore.sell(archer, p);

        assertEquals(archer.getCoins(), 10);

        assertEquals(potionInventory.getItems().size(), 1);

    }

    @Test
    public void testList() throws Exception {
        archer.setCoins(10);
        potionStore.add(p);
        String list = "-------------------------a-------------------------\n<1> a\t\tA potion\t\t10 coins\nCoins: 10\n";
        assertEquals(potionStore.list(archer),list);
    }
}
