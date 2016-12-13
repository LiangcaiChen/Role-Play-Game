package Creature;

import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArcherTest {
    Room currentRoom = new Room("Start room");
    Player archer = new Archer(50, "Archer", "Arrow", 13, 10, 8, 5, 15, 20, currentRoom);

    @Test
    public void testDoDamage() throws Exception {
        archer.setCRI(100);
        int damage = (int) (13 * 2 * 1.1);
        assertEquals(archer.doDamage(), damage);
    }

    @Test
    public void testDamageTaken() throws Exception {
        archer.setAVD(100);
        assertEquals(archer.damageTaken(100), 0);

        int taken = (int) ((1 - 1*0.05)*100);
        archer.setAVD(0);
        assertEquals(archer.damageTaken(100), taken);
    }
}