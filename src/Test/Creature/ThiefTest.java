package Creature;

import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThiefTest {
    Room currentRoom = new Room("Start room");
    Player thief = new Thief(50, "Archer", "Arrow", 13, 10, 8, 5, 15, 20, currentRoom);

    @Test
    public void testDoDamage() throws Exception {
        thief.setCRI(100);
        int damage = 13 * 2;
        assertEquals(thief.doDamage(), damage);
    }

    @Test
    public void testDamageTaken() throws Exception {
        thief.setAVD(100);
        thief.setCoins(10);
        assertEquals(thief.damageTaken(100), 0);
        assertEquals(thief.getCoins(), 20);

        int taken = (int) ((1 - 0.05)*100);
        thief.setAVD(0);
        assertEquals(thief.damageTaken(100), taken);
    }
}
