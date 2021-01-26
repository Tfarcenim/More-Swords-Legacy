package net.darkhax.msmlegacy.init;

import net.darkhax.msmlegacy.ConfigurationHandler;
import net.darkhax.msmlegacy.MSMLegacy;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class ModItems {
    public static Item dawnStar = new SwordItem(ConfigurationHandler.DAWN_STAR,3, -2.4F, new Item.Properties().group(MSMLegacy.creativeTab));
    public static Item vampiricBlade;
    public static Item gladiolus;
    public static Item draconicBlade;
    public static Item eyeEndBlade;
    public static Item crystalineBlade;
    public static Item glacialBlade;
    public static Item aethersGuard;
    public static Item withersBane;
    public static Item adminiumArk;

    public static final Item[] items = new Item[]{dawnStar,vampiricBlade,gladiolus,draconicBlade,eyeEndBlade,crystalineBlade,glacialBlade, aethersGuard,withersBane,adminiumArk};
}
