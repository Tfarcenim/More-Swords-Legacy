package net.darkhax.msmlegacy;

import net.darkhax.msmlegacy.enchantment.EnchantmentKeenEdge;
import net.darkhax.msmlegacy.enchantment.EnchantmentSwordLegacy;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.EnumUtils;

public class ConfigurationHandler {

    private ForgeConfigSpec.BooleanValue allowEnchOnAllSwords;
    private ForgeConfigSpec.DoubleValue spawnChance;

	private ForgeConfigSpec.BooleanValue allowRelics;

	public static MSMMaterial DAWN_STAR;
    public static MSMMaterial VAMPIRIC_BLADE;
    public static MSMMaterial GLADIOLUS;
    public static MSMMaterial DRACONIC_BLADE;
    public static MSMMaterial EYE_END_BLADE;
    public static MSMMaterial CRYSTALINE_BLADE;
    public static MSMMaterial GLACIAL_BLADE;
    public static MSMMaterial AETHERS_GUARD;
    public static MSMMaterial WITHER_BANE;
    public static MSMMaterial ADMINIUM_ARK;




    //this occurs long before any items are registered
    public ConfigurationHandler (ForgeConfigSpec.Builder builder) {
        builder.push("general");
        this.allowEnchOnAllSwords = builder.comment("When enabled, all new enchantments will be available for all swords, including vanilla and modded swords.")
                .define("allowEnchOnAllSwords", false);

        this.spawnChance = builder.comment("The chance of a mob spawning with one of the items from this mod.")
                .defineInRange("spawnChance",0.01f, 0f, 1f);

        this.allowRelics = builder.comment("Whether or not mobs can spawn with the relic swords.")
                .define("allowRelicSpawning", true);

        DAWN_STAR = MSMMaterial.build(builder,"dawn_star", 3, 1286, 8, 6, 22);
        VAMPIRIC_BLADE = MSMMaterial.build(builder,"vampiric_blade", 3, 812, 8, 7, 12);
        GLADIOLUS = MSMMaterial.build(builder,"gladiolus", 3, 645, 8, 6, 10);
        DRACONIC_BLADE = MSMMaterial.build(builder,"draconic_blade", 3, 1089, 8, 7, 16);
        EYE_END_BLADE = MSMMaterial.build(builder,"eye_end_blade",3, 1580, 8, 8, 22);
        CRYSTALINE_BLADE = MSMMaterial.build(builder,"crystaline_blade",3, 570, 8, 5, 28);
        GLACIAL_BLADE = MSMMaterial.build(builder,"glacial_blade", 3, 680, 8, 6, 15);
        AETHERS_GUARD = MSMMaterial.build(builder,"aethers_guard", 3, 1796, 8, 8, 22);
        WITHER_BANE = MSMMaterial.build(builder,"wither_bane", 3, 1869, 8, 6, 16);
        ADMINIUM_ARK = MSMMaterial.build(builder,"adminium_ark",  3, 9999999, 8, 99999, 999);
    }

    public Enchantment getSwordEnchantment (String id, Item sword, Rarity rarity, int min, int max) {
        
        return this.getSwordEnchantment(id, sword, rarity, min, max, true);
    }
    
    public Enchantment getSwordEnchantment (String id, Item sword, Rarity rarity, int min, int max, boolean survivalAllowed) {

        final String category = sword.getRegistryName().getPath();
        final EnchantmentType type = this.allowEnchOnAllSwords && survivalAllowed ? EnumEnchantmentType.WEAPON : EnumHelper.addEnchantmentType("MSM_LEGACY_" + id.toUpperCase(), item -> item == sword);
        rarity = this.getRarity(id, rarity, category, "The rarity for the " + id + " enchantment. Accepts COMMON, UNCOMMON, RARE, VERY_RARE");
        min = this.config.getInt("minLevel_" + id, category, min, 1, 128, "The min level for the " + id + " enchantment.");
        max = this.config.getInt("maxLevel_" + id, category, max, min, 128, "The max level for the " + id + " enchantment.");
        final Enchantment enchant = new EnchantmentSwordLegacy(rarity, sword, type, min, max, survivalAllowed);
        enchant.setName("msmlegacy." + id);
        return enchant;
    }
    
    public Enchantment getKeenEdge (String id, Item sword, Rarity rarity, int min, int max) {

        final String category = sword.getRegistryName().getPath();
        final EnchantmentType type = this.allowEnchOnAllSwords && true ? EnumEnchantmentType.WEAPON : EnumHelper.addEnchantmentType("MSM_LEGACY_" + id.toUpperCase(), item -> item == sword);
        rarity = this.getRarity(id, rarity, category, "The rarity for the " + id + " enchantment. Accepts COMMON, UNCOMMON, RARE, VERY_RARE");
        min = this.config.getInt("minLevel_" + id, category, min, 1, 128, "The min level for the " + id + " enchantment.");
        max = this.config.getInt("maxLevel_" + id, category, max, min, 128, "The max level for the " + id + " enchantment.");
        final Enchantment enchant = new EnchantmentKeenEdge(rarity, sword, type, min, max, true);
        enchant.setName("msmlegacy." + id);
        return enchant;
    }

    private Rarity getRarity (String id, Rarity defaultRarity, String category, String comment) {

        final String rarityName = this.config.getString("rarity_" + id, category, defaultRarity.name(), comment);
        Rarity rarity = EnumUtils.getEnum(Rarity.class, rarityName);

        if (rarity == null) {

            rarity = Rarity.RARE;
        }

        return rarity;
    }

    public float getSpawnChance() {
		return spawnChance.get();
	}

	public boolean isAllowRelics() {
		return allowRelics.get();
	}
}