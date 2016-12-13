package Shop;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShopTest {
    Store skillStore = new SkillStore("Skill Store");
    Shop shop = new Shop();

    @Test
    public void testAddStore() throws Exception {
        shop.addStore(skillStore);
        assertEquals(shop.getStores().size(), 1);
    }

    @Test
    public void testStoreOptions() {
        shop.addStore(skillStore);
        String text = "<1> Skill Store\n";
        assertEquals(shop.storeOptions(), text);
    }


}
