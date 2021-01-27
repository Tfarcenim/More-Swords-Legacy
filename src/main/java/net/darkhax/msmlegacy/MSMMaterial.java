package net.darkhax.msmlegacy;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeConfigSpec;

public class MSMMaterial implements IItemTier {

    private MSMMaterial(String name) {
        this.name = name;
    }

    private ForgeConfigSpec.IntValue harvestLevel;
    private ForgeConfigSpec.IntValue durability;
    private ForgeConfigSpec.IntValue efficiency;
    private ForgeConfigSpec.IntValue damage;
    private ForgeConfigSpec.IntValue enchantability;
    public final String name;

    public static MSMMaterial build(ForgeConfigSpec.Builder builder, String name, int harvestLevel, int durability, int efficiency, int damage, int enchantability) {
        builder.push(name);
        MSMMaterial msmMaterial = new MSMMaterial(name);
        msmMaterial.harvestLevel = builder.comment("The block harvesting level of the sword's material.").defineInRange("harvestLevel",harvestLevel,0,Integer.MAX_VALUE);
        msmMaterial.durability = builder.comment("The durability of the sword.").defineInRange("durability",durability,0,Integer.MAX_VALUE);
        msmMaterial.efficiency = builder.comment("The block efficiency of the sword's material.").defineInRange("efficiency",efficiency,0,Integer.MAX_VALUE);
        msmMaterial.damage = builder.comment("The damage of the sword.").defineInRange("damage",damage - 4,0,Integer.MAX_VALUE);
        msmMaterial.enchantability = builder.comment("The enchantability of the sword.").defineInRange("enchantability",enchantability,0,Integer.MAX_VALUE);
        builder.pop();
        return msmMaterial;
    }

    @Override
    public int getMaxUses() {
        return durability.get();
    }

    @Override
    public float getEfficiency() {
        return efficiency.get();
    }

    @Override
    public float getAttackDamage() {
        return damage.get();
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel.get();
    }

    @Override
    public int getEnchantability() {
        return enchantability.get();
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }
}
