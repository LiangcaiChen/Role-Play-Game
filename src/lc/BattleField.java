import Creature.Enemy;
import Creature.Player;
import Entity.Inventory;
import Entity.Item;
import Entity.Skill;

import java.util.*;

public class BattleField {
    private Player p;
    private Enemy en;
    private boolean startBattle = true;
    private List<Inventory<Item>> inventories = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int pDamageTaken;
    private int enDamageTaken;

     BattleField(Player p) {
        this.p = p;
        addBattleInventory();
    }

    /**
     * add battle type inventories to inventory list
     */
     void addBattleInventory() {
        for(int i = 0; i < p.getInventories().size(); i++) {
            Inventory<Item> inventory = p.getInventories().get(i);
            if(inventory.isForBattle()) {
                inventories.add(inventory);
            }
        }
    }

     void chooseEnemy() {
        switch (numOfEnemy()) {
            case 0:
                System.out.println("No enemy found");
                break;

            case 1:{
                this.en = p.getCurrentRoom().getEnemies().get(0);
                break;
            }

            default: pickEnemy(); break;
        }
    }

     int numOfEnemy() {
        return p.getCurrentRoom().getEnemies().size();
    }


    private void pickEnemy() {
        int enemyChoice = enemyChoiceInput();
        if (enemyValid(enemyChoice)) {
            this.en = p.getCurrentRoom().getEnemies().get(enemyChoice - 1);
        } else {
            chooseEnemy();
        }
    }

     boolean enemyValid(int input) {
        return input <= p.getCurrentRoom().getEnemies().size();
    }

    private int enemyChoiceInput() {
        while (true) {
            try {
                System.out.println(p.getCurrentRoom().printEnemy());
                System.out.println("Pick one\n");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

     String inventoryList() {
        String list = "";
        for(int i = 0; i < inventories.size(); i++) {
            list += "<" + (i + 1) + "> " + inventories.get(i).getName() + "\n";
        }
        return list;
    }

    //print all inventories that player can use
     String battleOption() {
        return "Your turn !!!\n" + inventoryList();
    }

    /**
     * choose inventory to use
     * select item in inventory to use
     * fight enemy
     */
    void fight() {
        chooseEnemy();
        doBattle();
    }

    private void doBattle() {
        while(startBattle) {
            int inventoryChoice = pickBattleOption();
            int itemChoice = itemOptionInput(inventoryChoice);
            chooseItemOption(inventoryChoice, itemChoice);

            duel();
        }

        p.getCurrentRoom().getEnemies().remove(en);
    }


    /**
     * print all battle options then get user input
     * @return user input
     */
    private int pickBattleOption() {
        while (true) {
            try {
                System.out.println(battleOption());
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    /**
     * print all choices in the inventory
     * then get user input
     * @param choice inventory no
     * @return user input
     */
    private int itemOptionInput(int choice) {
        while (true) {
            try {
                battleOptionInput(choice);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    /**
     * check whether input is out of range
     * print list of items in the inventory
     * @param choice inventory no
     */
    private void battleOptionInput(int choice) {
        if(choice <= inventories.size()) {
            System.out.println(inventories.get(choice - 1).list());
        } else {
            doBattle();
        }
    }

    //choose an item in the inventory to use
     void chooseItemOption(int inventoryChoice, int choice) {
        int size = inventories.get(inventoryChoice-1).getItems().size();

        if (choice <= size && size > 0) {
            useItem(inventoryChoice, choice);
        } else {
            pickBattleOption();
        }

    }

    /**
     * if player uses skill attack and the type matches enemy's type then use skill ability, remove skill
     *
     * if player uses skill attack but the type doesn't match enemy's type then use basic attack, remove skill
     *
     * if not using skill attack, use item
     *
     * @param inventoryChoice the no of inventory
     * @param choice which item in the inventory
     */
    private void useItem(int inventoryChoice, int choice) {
        List<Item> items = inventories.get(inventoryChoice - 1).getItems();
        Item item = items.get(choice - 1);
        if(item instanceof Skill && item.getAgainstType().equals(en.getType())) {
            item.use(p);
        } else if(item instanceof Skill && !item.getAgainstType().equals(en.getType())) {
            items.get(0).use(p);
            items.remove(choice - 1);
        } else {
            item.use(p);
        }
    }



    private void duel() {
        if(playerHitFirst()) {
            playerStart();
        } else {
            enemyStart();
        }
    }

    /**
     *
     * @return true if player is faster than enemy
     *          false if enemy is faster than player
     */
     boolean playerHitFirst() {
        if(p.getSP() >= en.getSP()) {
            System.out.println("You hit first");
            return true;
        } else {
            System.out.println("Enemy hits first");
            return false;
        }
    }

    private String breakLine() {
        return "-------------------------------";
    }

    /**
     * player hits first
     * enemy took damage
     * if enemy is not killed then attack the player, if enemy is killed then the player win the battle
     * if player is killed, game over
     */
    private void playerStart() {
        enDamageTaken();
        enTookDamage();
        if(en.isAlive()) {
            pDamageTaken();
            pTookDamage();
            if(!p.isAlive()) {
                lost();
            }
            System.out.println(breakLine());
        } else {
            win();
        }
    }

    private void enemyStart() {
        pDamageTaken();
        pTookDamage();
        if(p.isAlive()) {
            enDamageTaken();
            enTookDamage();
            if(!en.isAlive()) {
                win();
            }
            System.out.println(breakLine());
        } else {
            lost();
        }
    }

    void enTookDamage() {
        en.setHealth(en.getHealth() - enDamageTaken);
        System.out.println(enDamageTakenText());
    }

    void pTookDamage() {
        p.setHealth(p.getHealth() - pDamageTaken);
        System.out.println((pDamageTakenText()));
    }

    void enDamageTaken() {
        this.enDamageTaken = en.damageTaken(p.doDamage());
    }

    void pDamageTaken() {
        this.pDamageTaken = p.damageTaken(en.doDamage());
    }

    private String enDamageTakenText() {
        return "Enemy took " + enDamageTaken + " damage from you.\n" +
                "Enemy health left: " + en.getHealth() + "\n";
    }

    private String pDamageTakenText() {
        return "You receive " + pDamageTaken + " damage.\n" +
                "Health left: " + p.getHealth() + "\n";
    }

     void win() {
        showReward();
        startBattle = false;
        setBack();
    }

    /**
     * player might drink special potions in the battle such as: ATK potion
     * which can increase player's attack power
     * the effect of special potion will not remain if the battle is finished
     * so removing potion effects
     */
     void setBack() {
        p.setATK(p.getMaxATK());
        p.setDEF(p.getMaxDEF());
        p.setCRI(p.getMaxCRI());
        p.setAVD(p.getMaxAVD());
        p.setSP(p.getMaxSP());
    }

    private void showReward() {
        int coin = p.gainCoin();
        int xp = p.gainXP();

        p.setCoins(p.getCoins() + coin);

        p.levelUp(xp);

        System.out.println(rewardText(coin, xp));
    }

     String rewardText (int coin, int xp) {
        String text;
        if(coin > 1) {
            text = " coins.";
        } else {
            text = " coin.";
        }

        return "Enemy is defeated ! " +
                "\nYou won " + coin + text  +
                "\nXP earned: " + xp +
                "\nTotal XP: " + p.getXP() + "/100" +
                "\nLV " + p.getLevel();
    }

     void lost() {
        startBattle = false;
        GameEngine.game = false;
    }

     void setStartBattle(boolean startBattle) {
        this.startBattle = startBattle;
    }

     boolean isStartBattle() {
        return startBattle;
    }

     Enemy getEn() {
        return en;
    }
}
