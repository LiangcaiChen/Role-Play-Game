package Entity;

import Creature.Player;
import Place.Exit;
import Place.Room;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Liangcai Chen on 3/22/16.
 */
public class Key extends Item {
    public Key(String name, String description, int inventoryNo) {
        super(name, description, inventoryNo);
    }

    @Override
    public void use(Player p) {

        switch (getNumLockedDoors(p)) {
            case 0: System.out.println(noLockedDoorText()); break;

            case 1: unlockDoor(p); p.getInventories().get(getInventoryNo()).remove(this); break;

            default: {
                System.out.println(unlockDoorText() + p.getCurrentRoom().lockedDoorInfo());
                chooseLockedDoorToOpen(p);
                p.getInventories().get(getInventoryNo()).remove(this);
                break;
            }
        }

        p.setEnergy(p.getEnergy() - 1);
    }

    /**
     *
     * @param p player
     * @return number of doors which are locked
     */
    private int getNumLockedDoors(Player p) {
        int numLockedDoors = 0;

        for(int i = 0; i < p.getCurrentRoom().getExits().size(); i++) {
            if(p.getCurrentRoom().getExits().get(i).isLocked()) {
                numLockedDoors++;
            }
        }
        return numLockedDoors;
    }

    /**
     * loop through all exits which are locked
     * then change it to unlocked state
     * print msg to show door is unlocked
     *
     * @param p player
     */
    private void unlockDoor(Player p) {
        List<Exit> exits = p.getCurrentRoom().getExits();
        exits.stream().filter(Exit::isLocked).forEach(exit -> {
            exit.setLocked(false);
            System.out.println(exit.getDirection() + " " + unlockDoorText());
        });
    }



    /**
     * player can choose which door to open
     *
     * @param p player
     */
    public void chooseLockedDoorToOpen(Player p) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        unlockDoor(p, input);
    }

    /**
     * unlock door matches input
     *
     * @param p player
     * @param input direction of exits e.g "w e s n"
     */
    public void unlockDoor(Player p, String input) {
        List<Exit> exits = p.getCurrentRoom().getExits();

        exits.stream().filter(exit -> exit.getDirection().equals(input)).forEach(exit -> {
            exit.setLocked(false);
            System.out.println(exit.getDirection() + " " + unlockDoorText());
        });
    }

    public String unlockDoorText() {
        return "Door is unlocked\n";
    }

    public String noLockedDoorText() {
        return "There is no locked door\n";
    }

}
