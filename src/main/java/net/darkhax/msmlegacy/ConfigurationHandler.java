package net.darkhax.msmlegacy;

import net.darkhax.msmlegacy.enchantment.EnchantmentKeenEdge;
import net.darkhax.msmlegacy.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigurationHandler {

    public static ForgeConfigSpec.BooleanValue allowEnchOnAllSwords;
    private static ForgeConfigSpec.DoubleValue spawnChance;

	private static ForgeConfigSpec.BooleanValue allowRelics;

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
        allowEnchOnAllSwords = builder.comment("When enabled, all new enchantments will be available for all swords, including vanilla and modded swords.")
                .define("allowEnchOnAllSwords", false);

        spawnChance = builder.comment("The chance of a mob spawning with one of the items from this mod.")
                .defineInRange("spawnChance",0.01f, 0f, 1f);

        allowRelics = builder.comment("Whether or not mobs can spawn with the relic swords.")
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

        ModEnchantments.init(builder);
        builder.pop();

    }

    public static float getSpawnChance() {
		return spawnChance.get().floatValue();
	}

	public static boolean isAllowRelics() {
		return allowRelics.get();
	}
}