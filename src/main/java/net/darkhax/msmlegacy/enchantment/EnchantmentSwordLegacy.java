package net.darkhax.msmlegacy.enchantment;

import net.darkhax.msmlegacy.ConfigurationHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Locale;
import java.util.function.Supplier;

public class EnchantmentSwordLegacy extends Enchantment {

    private static final EquipmentSlotType[] slots = new EquipmentSlotType[]{EquipmentSlotType.MAINHAND};

    private final Supplier<Item> intendedItem;
    public ForgeConfigSpec.IntValue minLevel;
    public ForgeConfigSpec.IntValue maxLevel;
    public ForgeConfigSpec.EnumValue<Rarity> rarity;
    private final boolean isVanillaAllowed;

    protected EnchantmentSwordLegacy(Supplier<Item> item, EnchantmentType typeIn, boolean isVanillaAllowed) {

        super(null, typeIn, slots);
        this.intendedItem = item;
        this.isVanillaAllowed = isVanillaAllowed;
    }

    public static EnchantmentSwordLegacy build(ForgeConfigSpec.Builder builder, String name, Supplier<Item> sword, Rarity rarity, int maxLevel, int minLevel) {
        return build(builder, name, sword, rarity, maxLevel, minLevel,true);
    }

    public static EnchantmentSwordLegacy build(ForgeConfigSpec.Builder builder, String name, Supplier<Item> sword, Rarity rarity, int maxLevel, int minLevel, boolean vanillaAllowed) {
        builder.push(name);
        final EnchantmentType type = ConfigurationHandler.allowEnchOnAllSwords.get() && vanillaAllowed ? EnchantmentType.WEAPON :
                EnchantmentType.create(name.toUpperCase(Locale.ROOT), item -> item == sword.get());
        EnchantmentSwordLegacy enchantmentSwordLegacy = new EnchantmentSwordLegacy(sword, type, vanillaAllowed);
        enchantmentSwordLegacy.rarity = builder.comment("The rarity for the " + name + " enchantment").defineEnum("rarity", rarity);
        enchantmentSwordLegacy.minLevel = builder.comment("The min level for the " + name + " enchantment.").defineInRange("minLevel", minLevel, 1, 128);
        enchantmentSwordLegacy.maxLevel = builder.comment("The max level for the " + name + " enchantment.").defineInRange("maxLevel", maxLevel, minLevel, 128);
        builder.pop();
        return enchantmentSwordLegacy;
    }

    @Override
    public int getMinLevel() {

        return this.minLevel.get();
    }

    @Override
    public int getMaxLevel() {

        return this.maxLevel.get();
    }

    @Override
    public Rarity getRarity() {
        return rarity.get();
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return (!(stack.getItem() instanceof BookItem) || isVanillaAllowed) && super.canApplyAtEnchantingTable(stack);
    }

    public void refresh() {
        this.type = ConfigurationHandler.allowEnchOnAllSwords.get() && isVanillaAllowed ? EnchantmentType.WEAPON :
                EnchantmentType.create(name.toUpperCase(Locale.ROOT), item -> item == intendedItem.get());
    }
}