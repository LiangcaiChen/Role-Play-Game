package Creature;

import Place.Room;

public class Thief extends Player {
    public Thief(int energy, String className, String weaponTitle, int maxATK, int maxDEF, int maxCRI, int maxAVD, int maxSP, int coins, Room currentRoom) {
        super(energy, className, weaponTitle, maxATK, maxDEF, maxCRI, maxAVD, maxSP, coins, currentRoom);
    }

    @Override
    public int doDamage() {
        return getATK() * criticalHit();
    }

    //if player did not take any damage from enemy then add 10 coins
    @Override
    public int damageTaken(int amount) {
        int avd = AVDChecker();
        if(avd == 0) {
            System.out.println("10 coins added");
            super.setCoins(getCoins() + 10);
        }

        return (int) (avd * (1 - getDEF() / 10 * 0.05) * amount);
    }

}
