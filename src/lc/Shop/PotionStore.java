package Shop;

import Creature.Player;
import Entity.Item;
import Sound.Sound;

public class PotionStore extends Store<Item> {
    public PotionStore(String name) {
        super(name);
    }

    @Override
    void sell(Player p, Item item) {
        if(enoughMoney(p,item)) {
            p.addItem(item);
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
