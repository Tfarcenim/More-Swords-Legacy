package net.darkhax.msmlegacy.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.darkhax.msmlegacy.MSMLegacy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class MSMLegacyJEI implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registry) {

        for (final Item item : MSMLegacy.getItems()) {

            registry.addIngredientInfo(new ItemStack(item), VanillaTypes.ITEM, "jei." + item.getTranslationKey());
        }
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MSMLegacy.MODID,MSMLegacy.MODID);
    }
}