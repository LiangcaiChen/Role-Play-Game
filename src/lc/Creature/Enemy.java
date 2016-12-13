package Creature;

public class Enemy extends Fighter {
    private String name;
    private String type;
    private boolean wake;
    private String[] nameArray = {"Skeleton", "Blaze", "Cave Spider", "Creeper", "Zombie", "Slime", "Wither"};
    private String[] typeArray = {"Fire", "Water", "Lightning", "Darkness"};

    public Enemy(int maxHealth, int maxATK, int maxDEF, int maxCRI, int maxAVD, int maxSP, int level, boolean wake) {
        super(maxHealth, maxATK, maxDEF, maxCRI, maxAVD, maxSP, level);
        this.wake = wake;
        this.name = nameArray[ran.nextInt(nameArray.length - 1)];
        this.type = typeArray[ran.nextInt(typeArray.length - 1)];
    }

    @Override
    public int doDamage() {
        return getATK() * criticalHit();
    }

    /**
     * 10 DEF point will reduce 5% damage taken
     * @param amount attack damage from player
     * @return damage taken
     */
    @Override
    public int damageTaken(int amount) {
        return (int) (AVDChecker() * (1 - getDEF() / 10 * 0.05) * amount);
    }

    public boolean isWake() {
        return wake;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Enemy name: " + this.name + "\n" +
                "Wake: " + this.wake + "\n" +
                "Type: " + this.type + "\n" +
                "Health: " + super.getHealth() + "\n" +
                "ATK: " + super.getATK() + "\n" +
                "DEF: " + super.getDEF() + "\n" +
                "Speed: " + super.getSP() + "\n" +
                "Critical hit chance: " + super.getCRI() + "%\n\n";
    }
}
