package Shop;

import Creature.Player;
import Entity.Item;
import Sound.Sound;

public class ArmorStore extends Store<Item> {
    public ArmorStore(String name) {
        super(name);
    }

    @Override
    void sell(Player p, Item item) {
        if(enoughMoney(p,item)) {
            p.setDEF(p.getDEF() - p.getInventories().get(item.getInventoryNo()).getItems().get(item.getPositionNo()).getEffect());
            item.use(p);
            p.replaceItem(item.getInventoryNo(), item.getPositionNo(), item);
            sellInfo(item);
            p.setCoins(p.getCoins() - item.getPrice());
            Sound.play(Sound.buy);
        }
    }

    @Override
    String list(Player p) {
        return title() + body() + option(p);
    }
}
