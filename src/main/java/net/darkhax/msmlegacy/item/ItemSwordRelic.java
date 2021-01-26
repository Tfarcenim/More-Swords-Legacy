package net.darkhax.msmlegacy.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSwordRelic extends SwordItem {

    private static int maxRelics = 0;
    private final int relicIndex;

    public ItemSwordRelic(Properties properties) {
        super(ItemTier.IRON,3, -2.4F,properties);
        maxRelics++;
        this.relicIndex = maxRelics;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("jei." + this.getTranslationKey()).mergeStyle(TextFormatting.LIGHT_PURPLE));
        tooltip.add(new TranslationTextComponent("msmlegacy.relic.count", this.relicIndex, maxRelics).mergeStyle(TextFormatting.GOLD));
    }
}