package Creature;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {
    Enemy enemy = new Enemy(100, 10, 10, 10,10,10,10,true);

    @Test
    public void testDodamage() throws Exception {
        enemy.setCRI(100);
        int damage = 10 * 2;
        assertEquals(enemy.doDamage(), damage);

        enemy.setCRI(0);
        assertEquals(enemy.doDamage(), 10);
    }

    @Test
    public void testDamageTaken() throws Exception {
        enemy.setAVD(100);
        assertEquals(enemy.damageTaken(100), 0);

        enemy.setAVD(0);
        int taken = (int) ((1 - 0.05) * 100);
        assertEquals(enemy.damageTaken(100), taken);

    }

    @Test
    public void testAwake() throws Exception {
        assertEquals(enemy.isWake(), true);
    }
}
