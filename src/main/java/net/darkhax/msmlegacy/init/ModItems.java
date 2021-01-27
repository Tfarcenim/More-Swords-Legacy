package net.darkhax.msmlegacy.init;

import com.google.common.collect.Lists;
import net.darkhax.msmlegacy.ConfigurationHandler;
import net.darkhax.msmlegacy.MSMLegacy;
import net.darkhax.msmlegacy.MSMMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

import java.util.List;

public class ModItems {
    public static Item dawnStar = buildSword(ConfigurationHandler.DAWN_STAR);
    public static Item vampiricBlade = buildSword(ConfigurationHandler.VAMPIRIC_BLADE);
    public static Item gladiolus = buildSword(ConfigurationHandler.GLADIOLUS);
    public static Item draconicBlade = buildSword(ConfigurationHandler.DRACONIC_BLADE);
    public static Item eyeEndBlade = buildSword(ConfigurationHandler.EYE_END_BLADE);
    public static Item crystalineBlade = buildSword(ConfigurationHandler.CRYSTALINE_BLADE);
    public static Item glacialBlade = buildSword(ConfigurationHandler.GLACIAL_BLADE);
    public static Item aethersGuard = buildSword(ConfigurationHandler.AETHERS_GUARD);
    public static Item withersBane = buildSword(ConfigurationHandler.WITHER_BANE);
    public static Item adminiumArk = buildSword(ConfigurationHandler.ADMINIUM_ARK);

    private static Item buildSword(MSMMaterial msmMaterial) {
        return new SwordItem(msmMaterial,3, -2.4F, new Item.Properties().group(MSMLegacy.creativeTab)).setRegistryName(msmMaterial.name);
    }

    public static final List<Item> items = Lists.newArrayList(dawnStar,vampiricBlade,gladiolus,draconicBlade,eyeEndBlade,crystalineBlade,glacialBlade, aethersGuard,withersBane,adminiumArk);
}
