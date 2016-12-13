package Shop;

import Creature.Player;
import Entity.Item;
import Sound.Sound;

public class WeaponStore extends Store<Item> {

    public WeaponStore(String name) {
        super(name);
    }

    public Item matchWeaponTitle(Player p, Item item) {
        if(item.getName().contains(p.getWeaponTitle())) {
            return item;
        } else {
            item.setName(item.getName() + " " + p.getWeaponTitle());
            return item;
        }
    }

    public String body(Player p) {
        String text = "";
        for(int i = 0; i < getItems().size(); i++) {
            Item item = getItems().get(i);
            item = matchWeaponTitle(p, item);
            text += "<" + (i + 1) + "> " + item.getName() + "\t\t" + item.getDescription() + "\t\t" + item.getPrice() + " coins\n";
        }
        return text;
    }

    /**
     * if player buy a weapon then
     * player ATK - old weapon ATK + new weapon ATK
     * set weapon name matches player's class
     * replace old weapon by new weapon
     *
     * @param p player
     * @param item weapon
     */

    @Override
    void sell(Player p, Item item) {
        if(enoughMoney(p,item)) {
            p.setATK(p.getATK() - item.getEffect());
            item.use(p);
            p.replaceItem(item.getInventoryNo(), item.getPositionNo(), item);
            System.out.println(sellInfo(item));
            p.setCoins(p.getCoins() - item.getPrice());
            Sound.play(Sound.buy);
        }
    }

    @Override
    String list(Player p) {
        return super.title() + body(p) + super.option(p);
    }
}
