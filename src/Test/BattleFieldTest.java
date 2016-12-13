import Creature.Archer;
import Creature.Enemy;
import Creature.Player;
import Entity.Inventory;
import Entity.Item;
import Entity.Key;
import Place.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattleFieldTest {
    Room r1 = new Room("a");
    Player archer = new Archer(50, "Archer", "Arrow", 10, 10, 10, 10, 10, 10, r1);
    Enemy enemy = new Enemy(100, 10, 10, 10, 10, 10,1, true);
    Enemy enemy2 = new Enemy(100, 10, 10, 10, 10, 10,1, true);
    BattleField battleField = new BattleField(archer);
    Inventory inventory = new Inventory("Inventory", true);
    Item key = new Key("Key", "Key", 0);

    @Test
    public void testChooseEnemy() throws Exception {
        battleField.chooseEnemy();
        assertEquals(battleField.numOfEnemy(), 0);

        r1.addEnemy(enemy);
        battleField.chooseEnemy();
        assertEquals(battleField.getEn(), enemy);
    }

    @Test
    public void testInventoryList() throws Exception {
        archer.addInventory(inventory);
        battleField.addBattleInventory();
        String list = "<1> Inventory\n";
        assertEquals(battleField.inventoryList(), list);
    }

    @Test
    public void testEnemyValis() throws Exception {
        r1.addEnemy(enemy2);
        r1.addEnemy(enemy);
        assertEquals(battleField.enemyValid(2), true);
        assertEquals(battleField.enemyValid(1), true);
        assertEquals(battleField.enemyValid(3), false);
    }

    @Test
    public void testBattleOption() throws Exception {
        archer.addInventory(inventory);
        battleField.addBattleInventory();
        String list = "Let's fight !!!\n<1> Inventory\n<2> Status";
        assertEquals(battleField.battleOption(), list);
    }

    @Test
    public void testChooseItemOption() throws Exception {
        archer.addInventory(inventory);
        inventory.add(key);
        battleField.addBattleInventory();
        battleField.chooseItemOption(1,1);
        assertEquals(inventory.getItems().size(), 1);
    }

    @Test
    public void testHitFirst() throws Exception {
        r1.addEnemy(enemy2);
        battleField.chooseEnemy();
        enemy2.setSP(0);
        archer.setSP(100);
        assertEquals(battleField.playerHitFirst(), true);
    }

    @Test
    public void testEnTookDamage() throws Exception {
        r1.addEnemy(enemy);
        battleField.chooseEnemy();
        archer.setATK(10);
        archer.setCRI(0);
        enemy.setDEF(0);
        enemy.setAVD(0);

        battleField.enDamageTaken();
        battleField.enTookDamage();
        assertEquals(enemy.getHealth(), (int)(100-10*1.1));
    }

    @Test
    public void testPTookDamage() throws Exception {
        r1.addEnemy(enemy);
        battleField.chooseEnemy();
        archer.setDEF(0);
        archer.setAVD(0);
        enemy.setATK(20);
        enemy.setCRI(100);
        battleField.pDamageTaken();
        battleField.pTookDamage();
        assertEquals(archer.getHealth(), 60);
    }

    @Test
    public void testPDamageTakenText() throws Exception {

    }

    @Test
    public void testWin() throws Exception {
        battleField.win();
        assertEquals(battleField.isStartBattle(), false);
    }

    @Test
    public void testSetBack() throws Exception {
        archer.setMaxATK(10);
        archer.setMaxDEF(10);
        archer.setMaxCRI(10);
        archer.setMaxAVD(10);
        archer.setMaxSP(10);

        archer.setATK(20);
        archer.setDEF(20);
        archer.setCRI(20);
        archer.setAVD(20);
        archer.setSP(20);

        battleField.setBack();
        assertEquals(archer.getATK(), 10);
        assertEquals(archer.getDEF(), 10);
        assertEquals(archer.getCRI(), 10);
        assertEquals(archer.getAVD(), 10);
        assertEquals(archer.getSP(), 10);
    }

    @Test
    public void testReward() throws Exception {
        archer.setXP(15);
        String text = "Enemy is defeated ! \nYou won 10 coins.\nXP earned: 15\nTotal XP: 15/100\nLV 1";
        assertEquals(battleField.rewardText(10,15), text);
    }

    @Test
    public void testLost() throws Exception {
        battleField.lost();
        assertEquals(battleField.isStartBattle(), false);
        assertEquals(GameEngine.game, false);
    }

}
