package Creature;

import Place.Room;

public class Knight extends Player {
    public Knight(int energy, String className, String weaponTitle, int maxATK, int maxDEF, int maxCRI, int maxAVD, int maxSP, int coins, Room currentRoom) {
        super(energy, className, weaponTitle, maxATK, maxDEF, maxCRI, maxAVD, maxSP, coins, currentRoom);
    }

    @Override
    public int doDamage() {
        return getATK() * criticalHit();
    }

    //reduce 10% extra damage
    @Override
    public int damageTaken(int amount) {
        return (int) (AVDChecker() * (1 - getDEF() / 10 * 0.05) * amount * 0.9);
    }

}
