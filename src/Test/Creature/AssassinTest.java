package Creature;

import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssassinTest {
    private Room currentRoom = new Room("Start room");
    private Player assassin = new Assassin(50, "Archer", "Arrow", 13, 10, 8, 5, 15, 20, currentRoom);

    @Test
    public void testDoDamage() throws Exception {
        assassin.setCRI(100);
        int damage = (int) (13 * 2 * 1.2);
        assertEquals(assassin.doDamage(), damage);

        assassin.setCRI(0);
        assertEquals(assassin.doDamage(), 13);
    }

    @Test
    public void testDamageTaken() throws Exception {
        assassin.setAVD(100);
        assertEquals(assassin.damageTaken(100), 0);

        int taken = (int) ((1 - 0.05) * 100 * 1.05);
        assassin.setAVD(0);
        assertEquals(assassin.damageTaken(100), taken);
    }
}
