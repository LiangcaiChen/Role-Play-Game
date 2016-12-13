import Creature.Enemy;
import Creature.Player;
import Entity.*;
import Place.Exit;
import Place.Room;
import Shop.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) {
        /**
         * create potions
         *
         * param (name, description, price, effect, inventoryNo)
         */
        Item smallPotion = new HealthPotion("Small Potion", "A potion that can restore 10 health", 10, 10, 0);
        Item mediumPotion = new HealthPotion("Medium Potion", "A potion that can restore 20 health", 18, 20, 0);
        Item largePotion = new HealthPotion("Large Potion", "A potion that can restore 40 health", 25, 40, 0);
        Item atkPotion = new AtkPotion("ATK Potion", "A potion that can increase 10 ATK", 10, 10, 0);
        Item avdPotion = new AtkPotion("AVD Potion", "A potion that can increase 10 AVD", 10, 10, 0);
        Item criPotion = new AtkPotion("CRI Potion", "A potion that can increase 10 CRI", 10, 10, 0);
        Item defPotion = new AtkPotion("DEF Potion", "A potion that can increase 10 DEF", 10, 10, 0);
        Item spPotion = new AtkPotion("SP Potion", "A potion that can increase 10 SP", 10, 10, 0);

        /**
         * create weapons
         *
         * param (name_sub, description, price, effect, inventoryNo, positionNo)
         */
        Item fist = new Weapon("Fist", "Fist...", 0, 0, 1, 0);
        Item ironWeapon = new Weapon("Iron", "A weapon made by iron", 30, 15, 1, 0);
        Item goldWeapon = new Weapon("Golden", "A weapon made by gold", 60, 30, 1, 0);
        Item diamondWeapon = new Weapon("Diamond", "A weapon made by diamond", 100, 60, 1, 0);

        /**
         * create armors
         *
         * param (name, description)
         *
         * param (name, description, price, effect, inventoryNo, positionNo)
         */
        Item armor = new Armor("Armor", "A default armor");
        Item ironArmor = new Armor("Iron Armor", "DEF + 7", 15, 5, 2, 1);
        Item goldenArmor = new Armor("Golden Armor", "DEF + 14", 30, 14, 2, 1);
        Item diamondArmor = new Armor("Diamond Armor", "DEF + 28", 20, 28, 2, 1);
        Item helmet = new Armor("Helmet", "A default helmet");
        Item ironHelmet = new Armor("Iron Helmet", "DEF + 5", 15, 5, 2, 0);
        Item goldenHelmet = new Armor("Golden Helmet", "DEF + 10", 20, 10, 2, 0);
        Item diamondHelmet = new Armor("Diamond Helmet", "DEF + 20", 25, 20, 2, 0);
        Item boots = new Armor("Boots", "A default boots");
        Item ironBoots = new Armor("Iron Boots", "DEF + 5", 15, 5, 2, 2);
        Item goldenBoots = new Armor("Golden Boots", "DEF + 10", 20, 10, 2, 2);
        Item diamondBoots = new Armor("Diamond Boots", "DEF + 20", 25, 20, 2, 2);

        /**
         * create skills
         *
         * param (name, description, againstType, price, effect, inventoryNo)
         */
        Item fireAttack = new Skill("Fire Attack", "A skill that deal extra damage to water type enemy", "Water", 10, 1.1, 1);
        Item waterAttack = new Skill("Water Attack", "A skill that deal extra damage to fire type enemy", "Fire", 10, 1.1, 1);
        Item lightningAttack = new Skill("Lightning Attack", "A skill that deal extra damage to darkness type enemy", "Darkness", 10, 1.1, 1);
        Item darknessAttack = new Skill("Darkness Attack", "A skill that deal extra damage to lightning type enemy", "Lightning", 10, 1.1, 1);

        /**
         * create special items
         *
         * param (name, description, inventoryNo)
         */
        Item key = new Key("Key", "A key that is used to unlock door", 3);
        Item chestKey = new ChestKey("Chest Key", "A key that is used to unlock chest", 3);

        /**
         * create inventories
         *
         * param (name, forBattle)
         */
        Inventory<Item> potionInventory = new Inventory<>("Potion Inventory", true);
        Inventory<Item> weaponSkillInventorhy = new Inventory<>("Weapon & skill inventory", true);
        Inventory<Item> armorInventory = new Inventory<>("Armor Inventory", false);
        Inventory<Item> spicialItemInventory = new Inventory<>("Special Item Inventory", false);

        //add default items in inventory
        weaponSkillInventorhy.add(fist);
        armorInventory.add(helmet);
        armorInventory.add(armor);
        armorInventory.add(boots);

        List<Inventory<Item>> inventories = new ArrayList<>();
        inventories.add(potionInventory);
        inventories.add(weaponSkillInventorhy);
        inventories.add(armorInventory);
        inventories.add(spicialItemInventory);

        //create stores
        Store<Item> potionStore = new PotionStore("Potion Store");
        Store<Item> weaponStore = new WeaponStore("Weapon Store");
        Store<Item> skillStore = new SkillStore("Skill Store");
        Store<Item> defStore = new ArmorStore("Armor Store");


        //add items in store
        potionStore.add(smallPotion);
        potionStore.add(mediumPotion);
        potionStore.add(largePotion);
        potionStore.add(atkPotion);
        potionStore.add(avdPotion);
        potionStore.add(criPotion);
        potionStore.add(defPotion);
        potionStore.add(spPotion);

        weaponStore.add(ironWeapon);
        weaponStore.add(goldWeapon);
        weaponStore.add(diamondWeapon);

        skillStore.add(fireAttack);
        skillStore.add(waterAttack);
        skillStore.add(lightningAttack);
        skillStore.add(darknessAttack);

        defStore.add(ironHelmet);
        defStore.add(ironArmor);
        defStore.add(ironBoots);
        defStore.add(goldenHelmet);
        defStore.add(goldenArmor);
        defStore.add(goldenBoots);
        defStore.add(diamondHelmet);
        defStore.add(diamondArmor);
        defStore.add(diamondBoots);

        Shop<Store<Item>> shop = new Shop<Store<Item>>();

        //add stores in the shop
        shop.addStore(potionStore);
        shop.addStore(weaponStore);
        shop.addStore(skillStore);
        shop.addStore(defStore);

        Chest<Item> smallChest = new Chest<>("A small chest", true);
        Chest<Item> smallPotionChest = new Chest<>("A medium sized chest", false);
        Chest<Item> mediumPotionChest = new Chest<>("A large sized chest", false);
        Chest<Item> largeChest = new Chest<>("A large sized chest", true);
        Chest<Item> keyChest = new Chest<>("A large sized chest", false);
        Chest<Item> emptyChest = new Chest<>("A medium sized chest", false);
        Chest<Item> skillChest = new Chest<>("A yello chest", true);
        Chest<Item> superLargeChest = new Chest<>("Super large chest", true);

        smallChest.add(key);
        smallPotionChest.add(smallPotion);
        largeChest.add(defPotion);
        largeChest.add(key);
        skillChest.add(lightningAttack);
        skillChest.add(lightningAttack);
        superLargeChest.add(largePotion);
        superLargeChest.add(mediumPotion);


        mediumPotionChest.add(mediumPotion);
        mediumPotionChest.add(avdPotion);

        Room bedRoom = new Room("A big bedroom with a large bed in it");
        Room room2 = new Room("A small room");
        Room room3 = new Room("A kitchen");
        Room room4 = new Room("A large room");
        Room room5 = new Room("");
        Room room6 = new Room("");
        Room room7 = new Room("");
        Room room8 = new Room("", true);
        Room room9 = new Room("");
        Room room10 = new Room("");
        Room room11 = new Room("");
        Room room12 = new Room("");
        Room room13 = new Room("");
        Room room14 = new Room("");
        Room room15 = new Room("");
        Room room16 = new Room("");


        /**
         * create exits
         *
         * r1Tr2 --> room1 to room2
         *
         * param (direction, description, leadTo, locked)
         */
        Exit r1Tr2 = new Exit("e","A white door", room2, false);
        Exit r1Tr9 = new Exit("s", "A brown door", room9, true);

        Exit r2Tr1 = new Exit("w", "A white door", bedRoom, false);
        Exit r2Tr3 = new Exit("n", "A brown door", room3, false);
        Exit r2Tr10 = new Exit("e", "A white door", room10, false);

        Exit r3Tr2 = new Exit("s", "A brown door", room2, false);
        Exit r3Tr4 = new Exit("n", "A black door", room4, false);

        Exit r4Tr3 = new Exit("s", "A black door", room3, false);
        Exit r4Tr5 = new Exit("e", "A white door", room5, true);
        Exit r4Tr11 = new Exit("w", "A pink door", room11, false);
        Exit r4Tr12 = new Exit("n", "An orange door", room12, true);

        Exit r5Tr4 = new Exit("w", "A white door", room4, false);
        Exit r5Tr6 = new Exit("n", "A black door", room6, false);

        Exit r6Tr5 = new Exit("s", "A black door", room5, false);
        Exit r6Tr7 = new Exit("e", "A red door", room7, true);
        Exit r6Tr14 = new Exit("n", "A blue door", room14, true);

        Exit r7Tr6 = new Exit("w", "A blue door", room6, false);
        Exit r7Tr8 = new Exit("n", "A Gate", room8, true);

        Exit r9Tr1 = new Exit("n", "A brown door", bedRoom, false);

        Exit r10Tr2 = new Exit("w", "A white door", room2, false);
        Exit r10Tr15 = new Exit("e", "A white door", room15, false);

        Exit r11Tr1 = new Exit("s", "A green door", bedRoom, false);

        Exit r12Tr4 = new Exit("s", "An orange door", room4, true);
        Exit r12Tr13 = new Exit("n", "A yellow door", room13, false);

        Exit r13Tr12 = new Exit("s", "A yellow door", room12, false);

        Exit r14Tr6 = new Exit("s", "A blue door", room6, false);

        Exit r15Tr10 = new Exit("w", "A white door", room10, false);
        Exit r15Tr16 = new Exit("s", "A gray door", room16, true);

        Exit r16Tr15 = new Exit("n", "A gray door", room15, false);

        //add exits
        bedRoom.addExit(r1Tr2);
        bedRoom.addExit(r1Tr9);

        room2.addExit(r2Tr1);
        room2.addExit(r2Tr3);
        room2.addExit(r2Tr10);

        room3.addExit(r3Tr2);
        room3.addExit(r3Tr4);

        room4.addExit(r4Tr3);
        room4.addExit(r4Tr5);
        room4.addExit(r4Tr11);
        room4.addExit(r4Tr12);

        room5.addExit(r5Tr4);
        room5.addExit(r5Tr6);

        room6.addExit(r6Tr5);
        room6.addExit(r6Tr7);
        room6.addExit(r6Tr14);

        room7.addExit(r7Tr6);
        room7.addExit(r7Tr8);

        room9.addExit(r9Tr1);

        room10.addExit(r10Tr2);
        room10.addExit(r10Tr15);

        room11.addExit(r11Tr1);

        room12.addExit(r12Tr4);
        room12.addExit(r12Tr13);

        room13.addExit(r13Tr12);

        room14.addExit(r14Tr6);

        room15.addExit(r15Tr10);
        room15.addExit(r15Tr16);

        room16.addExit(r16Tr15);

        //add chest in the room
        room2.addChest(smallPotionChest);
        room4.addChest(largeChest);
        room4.addChest(emptyChest);
        room4.addChest(mediumPotionChest);
        room6.addChest(skillChest);
        room6.addChest(superLargeChest);
        room12.addChest(mediumPotionChest);
        room15.addChest(keyChest);
        room15.addChest(keyChest);

        //add item in the room
        room3.addItem(chestKey);
        room4.addItem(chestKey);
        room5.addItem(smallPotion);
        room6.addItem(criPotion);
        room10.addItem(chestKey);
        room11.addItem(chestKey);
        room11.addItem(chestKey);
        room11.addItem(key);
        room15.addItem(key);
        room16.addItem(chestKey);
        room16.addItem(key);

        Enemy firstRoomEnemy = new Enemy(100, 10, 10, 10, 10, 10, 1, true);
        Enemy room2Enemy = new Enemy(100, 10, 10, 10, 10, 10, 1, false);
        Enemy room3Enemy1 = new Enemy(100, 13, 10, 10, 10, 10, 1, false);
        Enemy room3Enemy2 = new Enemy(100, 10, 13, 10, 10, 10, 1, true);
        Enemy room4Enemy1 = new Enemy(100, 13, 13, 13, 13, 13, 1, true);
        Enemy room4Enemy2 = new Enemy(100, 13, 13, 13, 13, 13, 1, true);

        Enemy room5Enemy = new Enemy(100, 13, 13, 10, 10, 10, 1, true);
        Enemy room6Enemy1 = new Enemy(100, 13, 12, 11, 11, 11, 1, true);
        Enemy room6Enemy2 = new Enemy(100, 13, 12, 11, 11, 11, 1, true);
        Enemy room6Enemy3 = new Enemy(100, 13, 12, 11, 11, 11, 1, true);
        Enemy room8Enemy = new Enemy(100, 12, 13, 10, 10, 10, 1, true);
        Enemy room10Enemy = new Enemy(100, 13, 10, 10, 10, 10, 1, true);
        Enemy room11Enemy = new Enemy(100, 14, 10, 10, 10, 10, 1, true);
        Enemy room12Enemy = new Enemy(100, 15, 10, 10, 10, 10, 1, true);
        Enemy room14Enemy = new Enemy(100, 10, 11, 10, 12, 20, 1, true);
        Enemy room15Enemy = new Enemy(100, 10, 11, 12, 10, 20, 1, true);

        Enemy subBoss1 = new Enemy(100, 20, 20, 15, 15, 20, 3, true);
        Enemy subBoss2 = new Enemy(100, 20, 20, 15, 15, 20, 3, true);
        Enemy boss = new Enemy(200, 30, 25, 20, 15, 20, 5, true);


        //add enemy to the room
        bedRoom.addEnemy(firstRoomEnemy);
        room2.addEnemy(room2Enemy);

        room3.addEnemy(room3Enemy1);
        room3.addEnemy(room3Enemy2);

        room4.addEnemy(room4Enemy1);
        room4.addEnemy(room4Enemy2);

        room5.addEnemy(room5Enemy);

        room6.addEnemy(room6Enemy1);
        room6.addEnemy(room6Enemy2);
        room6.addEnemy(room6Enemy3);

        room8.addEnemy(room8Enemy);

        room10.addEnemy(room10Enemy);
        room11.addEnemy(room11Enemy);
        room12.addEnemy(room12Enemy);
        room14.addEnemy(room14Enemy);
        room15.addEnemy(room15Enemy);

        room13.addEnemy(subBoss1);
        room16.addEnemy(subBoss2);

        room7.addEnemy(boss);

        GameEngine gameEngine = new GameEngine(shop, bedRoom, inventories);

        gameEngine.run();
    }
}
