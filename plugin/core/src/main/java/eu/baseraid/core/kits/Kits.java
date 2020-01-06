package eu.baseraid.core.kits;

import eu.baseraid.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public enum Kits {

    BOW(0, "Bow Kit", new BowKit(), "Du kriegst einen Bogen mit dem du schießen kannst", new ItemBuilder(Material.BOW,1)),
    ASSASINE(1, "Assasine Kit", new AssasineKit(), "Du kriegst ein Schwert", new ItemBuilder(Material.IRON_SWORD,1)),
    HEALER(2, "Healer Kit", new HealerKit(), "Du kannst dich und andere Healen", new ItemBuilder(Material.GOLD_NUGGET,1)),
    TANK(3, "Tank Kit", new TankKit(), "Du hälst mehr aus! Greifst aber schlechter an.", new ItemBuilder(Material.DIAMOND_CHESTPLATE,1)),
    BUILDER(4, "Builder Kit", new BuilderKit(), "Du kriegst tolle Blöcke!.", new ItemBuilder(Material.STAINED_CLAY,1)),
    WITCH(4, "Witch Kit", new WitchKit(), "Du kriegst Gegner schwächene Wurf-Tränke!.", new ItemBuilder(Material.STAINED_CLAY,1).setPotion(new Potion(PotionType.INSTANT_DAMAGE).splash())),
    SKELETON_SUMMONER(5, "Skeleton Summoner", new SkeletonSummoner(), "Du kannst Skelette beschwören.", new ItemBuilder(Material.BONE,1));

    private int id;
    private Kit kit;
    private String name;
    private String description;
    private ItemBuilder icon;

    Kits(int id, String name, Kit kit, String description, ItemBuilder icon){
        this.id   = id;
        this.kit  = kit;
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public int getId(){
        return this.id;
    }

    public Kit getKit(){
        return kit;
    }

    public String getDescription() {
        return description;
    }

    public ItemBuilder getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
