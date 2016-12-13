package Creature;

import java.util.Random;

public abstract class Fighter implements Fight{
    private int maxHealth, health;
    private int maxATK, ATK;
    private int maxDEF, DEF;
    private int maxCRI, CRI;
    private int maxAVD, AVD;
    private int maxSP, SP;
    private int level;
    Random ran = new Random();

    public Fighter(int maxHealth, int maxATK, int maxDEF, int maxCRI, int maxAVD, int maxSP, int level) {
        this.maxHealth = maxHealth;
        this.maxATK = maxATK;
        this.maxDEF = maxDEF;
        this.maxCRI = maxCRI;
        this.maxAVD = maxAVD;
        this.maxSP = maxSP;
        this.level = level;
        this.health = maxHealth;
        this.ATK = maxATK;
        this.DEF = maxDEF;
        this.CRI = maxCRI;
        this.AVD = maxAVD;
        this.SP = maxSP;
    }

    public int criticalHit() {
        return (ran.nextInt(100) <= this.CRI && ran.nextInt(100) != 0) ? 2 : 1;
    }

    public int AVDChecker() {
        return (ran.nextInt(100) <= this.AVD && ran.nextInt(100) != 0) ? 0 : 1;
    }

    public boolean isAlive() {
        if(this.health > 0) {
            return true;
        } else {
            setHealth(0);
            return false;
        }
    }


    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxATK() {
        return maxATK;
    }

    public void setMaxATK(int maxATK) {
        this.maxATK = maxATK;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getMaxDEF() {
        return maxDEF;
    }

    public void setMaxDEF(int maxDEF) {
        this.maxDEF = maxDEF;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public int getMaxCRI() {
        return maxCRI;
    }

    public void setMaxCRI(int maxCRI) {
        this.maxCRI = maxCRI;
    }

    public int getCRI() {
        return CRI;
    }

    public void setCRI(int CRI) {
        this.CRI = CRI;
    }

    public int getMaxAVD() {
        return maxAVD;
    }

    public void setMaxAVD(int maxAVD) {
        this.maxAVD = maxAVD;
    }

    public int getAVD() {
        return AVD;
    }

    public void setAVD(int AVD) {
        this.AVD = AVD;
    }

    public int getMaxSP() {
        return maxSP;
    }

    public void setMaxSP(int maxSP) {
        this.maxSP = maxSP;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
