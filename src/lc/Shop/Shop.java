package Shop;

import Creature.Player;
import Entity.Item;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Shop<T extends Store<Item>> {
    private List<T> stores;
    private Scanner scanner = new Scanner(System.in);

    public Shop() {
        this.stores = new ArrayList<>();
    }

    public void addStore(T store) {
        this.stores.add(store);
    }

    private String ask() {
        return "Dealer: Want buy something ?????\n";
    }

     String storeOptions() {
        String text = "";
        for(int i = 0; i < stores.size(); i++) {
            text += "<" + (i + 1) + "> " + stores.get(i).getName() + "\n";
        }
        return text;
    }

    public void trade(Player p) {
        int input = storeChoice();
        String storeItems = stores.get(input - 1).list(p);

        try {
            System.out.println(storeItems);
            int itemChoice = scanner.nextInt();
            stores.get(input - 1).sell(p, stores.get(input - 1).getItems().get(itemChoice - 1));
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
    }

    private int storeChoice() {
        while(true) {
            try {
                System.out.println(ask() + storeOptions());
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                storeChoice();
            }
        }
    }

     List<T> getStores() {
        return stores;
    }
}
