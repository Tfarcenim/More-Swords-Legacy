package net.darkhax.msmlegacy.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

public class EnchantmentSwordLegacy extends Enchantment {

    private static final EquipmentSlotType[] slots = new EquipmentSlotType[] { EquipmentSlotType.MAINHAND };

    private final Item intendedItem;
    public ForgeConfigSpec.IntValue minLevel;
    public ForgeConfigSpec.IntValue maxLevel;
    private final boolean isVanillaAllowed;

    protected EnchantmentSwordLegacy (Rarity rarityIn, Item item, EnchantmentType typeIn, boolean isVanillaAllowed) {

        super(rarityIn, typeIn, slots);
        this.intendedItem = item;
        this.isVanillaAllowed = isVanillaAllowed;
    }

    public static EnchantmentSwordLegacy build(int minLevel,int maxLevel) {

    }

    @Override
    public int getMinLevel () {

        return this.minLevel.get();
    }

    @Override
    public int getMaxLevel () {

        return this.maxLevel.get();
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return (!(stack.getItem() instanceof BookItem) || isVanillaAllowed) && super.canApplyAtEnchantingTable(stack);
    }
}