package Shop;

import Creature.Archer;
import Creature.Player;
import Entity.Inventory;
import Entity.Item;
import Entity.Weapon;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponStoreTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    WeaponStore weaponStore = new WeaponStore("a");
    Item iron = new Weapon("Iron", "a", 10, 10, 0, 0);
    Item ironArrow = new Weapon("Iron Arrow", "a", 10, 10, 0, 0);
    Item arrow = new Weapon("Arrow", "a", 0, 0, 0, 0);

    @Test
    public void testMathchWeaponTitle() throws Exception {
        Item newItem = weaponStore.matchWeaponTitle(archer, iron);

        assertEquals(newItem.getName(), "Iron Arrow");

        assertEquals(weaponStore.matchWeaponTitle(archer, newItem).getName(), ironArrow.getName());

    }

    @Test
    public void testBody() throws Exception {
        weaponStore.add(iron);

        String body = "<1> Iron Arrow\t\ta\t\t10 coins\n";

        assertEquals(weaponStore.body(archer), body);
    }

    @Test
    public void testSell() throws Exception {
        archer.setCoins(20);
        archer.setDEF(5);
        weaponStore.add(iron);
        archer.addInventory(potionInventory);
        potionInventory.add(arrow);
        weaponStore.sell(archer, iron);

        assertEquals(archer.getCoins(), 10);

        assertEquals(potionInventory.getItems().size(), 1);

        assertEquals(potionInventory.getItems().get(0), iron);
    }

    @Test
    public void testList() throws Exception {
        weaponStore.add(iron);

        String body = "<1> Iron Arrow\t\ta\t\t10 coins\n";

        String title = "-------------------------a-------------------------\n";

        String option = "Coins: 10\n";

        String all = title + body + option;

        assertEquals(weaponStore.list(archer), all);


    }
}
