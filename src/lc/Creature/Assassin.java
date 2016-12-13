package Creature;

import Place.Room;

public class Assassin extends Player {
    public Assassin(int energy, String className, String weaponTitle, int maxATK, int maxDEF, int maxCRI, int maxAVD, int maxSP, int coins, Room currentRoom) {
        super(energy, className, weaponTitle, maxATK, maxDEF, maxCRI, maxAVD, maxSP, coins, currentRoom);
    }

    //if player critical hit then add 20% extra damage
    @Override
    public int doDamage() {
        int criHit = criticalHit();
        if(criHit == 2) {
            return (int)(getATK() * criticalHit() * 1.20);
        }

        return getATK() * criticalHit();
    }

    //player will take 5% extra damage
    @Override
    public int damageTaken(int amount) {
        return (int) (AVDChecker() * (1 - getDEF() / 10 * 0.05) * amount * 1.05);
    }

}
