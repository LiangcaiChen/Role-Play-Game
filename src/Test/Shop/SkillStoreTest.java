package Shop;

import Creature.Archer;
import Creature.Player;
import Entity.Inventory;
import Entity.Skill;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkillStoreTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Inventory potionInventory = new Inventory("Potion Inventory", true);
    SkillStore skillStore = new SkillStore("a");
    Skill skill = new Skill("a", "A skill", "", 10, 2,0);

    @Test
    public void testSell() throws Exception {
        archer.setCoins(20);
        archer.setDEF(5);
        skillStore.add(skill);
        archer.addInventory(potionInventory);
        skillStore.sell(archer, skill);

        assertEquals(archer.getCoins(), 10);

        assertEquals(potionInventory.getItems().size(), 1);

    }

    @Test
    public void testList() throws Exception {
        archer.setCoins(10);
        skillStore.add(skill);
        String list = "-------------------------a-------------------------\n<1> a\t\tA skill\t\t10 coins\nCoins: 10\n";
        assertEquals(skillStore.list(archer),list);
    }
}