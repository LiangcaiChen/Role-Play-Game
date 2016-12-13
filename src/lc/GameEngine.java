import Creature.*;
import Entity.*;
import Place.Exit;
import Place.Room;
import Shop.Shop;
import Shop.Store;
import Shop.WeaponStore;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameEngine {
    public static boolean game = true;
    private Player p;
    private Shop<Store<Item>> shop;
    private BattleField battleField;
    private Command command;
    private Room currentRoom;
    private List<Inventory<Item>> inventories;
    private Scanner s = new Scanner(System.in);

    public GameEngine(Shop<Store<Item>> shop, Room currentRoom, List<Inventory<Item>> inventories) {
        this.shop = shop;
        this.currentRoom = currentRoom;
        this.inventories = inventories;
    }

    public String playerChoice() {
        return classInfo() + "Pick a class: \n<1> Archer\n<2> Knight\n<3> Assassin\n<4> Thief";
    }

    public String classInfo() {
        return "Archer deals 10% extra damage\n" +
                "Knight reduced 10% damage taken\n" +
                "Assassin deals 20% extra damage when critical hit\n" +
                "Thief gains 10 coins when skip enemy's attack\n\n";
    }

    public void buildPlayer() {
        try {
            System.out.println(playerChoice());
            int choice = s.nextInt();
            p = choosePlayer(choice);
            p.setInventories(inventories);
            battleField = new BattleField(p);
            command = new Command(p);

        } catch (InputMismatchException en) {
            s.nextLine();
            buildPlayer();
        }
    }

    public Player choosePlayer(int choice) {
        if(choice == 1) {
            return new Archer(50, "Archer", "Arrow", 13, 10, 8, 5, 15, 20, currentRoom);
        }

        if(choice == 2) {
            return new Knight(50, "Knight", "Lance", 10, 14, 8, 5, 15, 20, currentRoom);
        }

        if(choice == 3) {
            return new Assassin(50, "Assassin", "Sword", 11, 10, 11, 6, 15, 20, currentRoom);
        }

        return new Thief(50, "Thief", "Knife", 10, 8, 9, 10, 25, 20, currentRoom);
    }

    public void run() {
        buildPlayer();
        System.out.println(p.status());
        System.out.println("\n\n!!!!!! Type help !!!!!!");
        while(game) {
            System.out.println("Your move --->");
            String input = s.nextLine();

            if(input.equalsIgnoreCase("help")) {
                command.help();
            }

            if(input.equalsIgnoreCase("w") || input.equalsIgnoreCase("e") || input.equalsIgnoreCase("s") || input.equalsIgnoreCase("n")) {
                command.move(input);
                p.setEnergy(p.getEnergy() - 1);
            }

            if(input.length() > 4 && input.substring(0, 4).equalsIgnoreCase("use ")) {
                command.use(input);
            }

            if(input.length() > 5 && input.substring(0,5).equalsIgnoreCase("pick ")) {
                command.pick(input);
            }

            if(input.equalsIgnoreCase("pick *")) {
                command.pick();
            }

            if(input.equalsIgnoreCase("open chest")) {
                command.openChest();
            }

            if(input.equalsIgnoreCase("look")) {
                command.look();
            }

            if(input.equalsIgnoreCase("status")) {
                command.status();
            }

            if(input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("info ")) {
                command.info(input);
            }

            if(input.equalsIgnoreCase("i")) {
                command.printInventory();
            }

            if(input.equalsIgnoreCase("buy")) {
                shop.trade(p);
            }

            if(input.equalsIgnoreCase("fight")) {
                battleField.setStartBattle(true);
                battleField.fight();
            }

            if(p.getEnergy() == 0) {
                System.out.println("Game Over");
                game = false;
            }

            if(p.getCurrentRoom().isLast()) {
                System.out.println("Congratulation! You have finished the game");
                game = false;
            }

            if(input.equals("backdoor")) {
                p.setATK(10000000);
                p.setDEF(10000000);
            }

            if(input.equalsIgnoreCase("end")) {
                game = false;
            }

            System.out.println("\nEnergy left: " + p.getEnergy() + "\n" + "#######################################\n");
        }
    }
}
