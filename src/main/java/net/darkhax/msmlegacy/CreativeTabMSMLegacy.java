package net.darkhax.msmlegacy;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class CreativeTabMSMLegacy extends ItemGroup {

    private ItemStack iconStack;

    public CreativeTabMSMLegacy () {

        super(MSMLegacy.MODID);
    }

    @Override
    public ItemStack createIcon () {

        if (this.iconStack == null) {

            final Item item = Registry.ITEM.getOrDefault(new ResourceLocation(MSMLegacy.MODID, "adminium_ark"));
            this.iconStack = new ItemStack(item);
        }

        return this.iconStack;
    }
}