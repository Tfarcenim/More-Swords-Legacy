package net.darkhax.msmlegacy.enchantment;

import net.darkhax.msmlegacy.ConfigurationHandler;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Locale;
import java.util.function.Supplier;

public class EnchantmentKeenEdge extends EnchantmentSwordLegacy {


	protected EnchantmentKeenEdge(Supplier<Item> item, EnchantmentType typeIn, boolean isVanillaAllowed) {
		super(item, typeIn, isVanillaAllowed);
	}

	public static EnchantmentKeenEdge build1(ForgeConfigSpec.Builder builder, String name, Supplier<Item> sword, Rarity rarity, int maxLevel, int minLevel, boolean vanillaAllowed) {
		builder.push(name);
		final EnchantmentType type = /*ConfigurationHandler.allowEnchOnAllSwords.get() &&*/ vanillaAllowed ? EnchantmentType.WEAPON :
				EnchantmentType.create(name.toUpperCase(Locale.ROOT), item -> item == sword.get());
		EnchantmentKeenEdge enchantmentSwordLegacy = new EnchantmentKeenEdge(sword, type, vanillaAllowed);
		enchantmentSwordLegacy.setRegistryName(name);
		enchantmentSwordLegacy.rarity = builder.comment("The rarity for the " + name + " enchantment").defineEnum("rarity", rarity);
		enchantmentSwordLegacy.minLevel = builder.comment("The min level for the " + name + " enchantment.").defineInRange("minLevel", minLevel, 1, 128);
		enchantmentSwordLegacy.maxLevel = builder.comment("The max level for the " + name + " enchantment.").defineInRange("maxLevel", maxLevel, minLevel, 128);
		builder.pop();
		return enchantmentSwordLegacy;
	}
	
	@Override
    public float calcDamageByCreature(int level, CreatureAttribute creatureType) {
    	
        return level * 1.5f;
    }
}