package Creature;

import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {
    Room currentRoom = new Room("a");
    Knight knight = new Knight(50, "Knight", "Lance", 13, 14, 8, 5, 15, 20, currentRoom);

    @Test
    public void testDoDamage() throws Exception {
        knight.setCRI(100);
        int damage = 13 * 2;
        assertEquals(knight.doDamage(), damage);
    }

    @Test
    public void testDamageTaken() throws Exception {
        knight.setAVD(100);
        assertEquals(knight.damageTaken(100), 0);

        int taken = (int) ((1 - 0.05) * 100 * 0.9);
        knight.setAVD(0);
        assertEquals(knight.damageTaken(100), taken);
    }
}
