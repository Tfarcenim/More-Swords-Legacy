package net.darkhax.msmlegacy;

import net.darkhax.msmlegacy.init.ModEnchantments;
import net.darkhax.msmlegacy.init.ModItems;
import net.darkhax.msmlegacy.item.ItemSwordRelic;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod(MSMLegacy.MODID)
public class MSMLegacy {

    public static final String MODID = "msmlegacy";
    public static final ItemGroup creativeTab = new CreativeTabMSMLegacy();
    private final String[] relicNames = { "aqueous", "candy", "infinity", "keyblade", "master", "molten", "pie" };
    public final List<Item> relics = new ArrayList<>();

    public MSMLegacy() {

    }

    private void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.items);
        relics.forEach(string -> event.getRegistry().register(new ItemSwordRelic(new Item.Properties().group(MSMLegacy.creativeTab))));
    }

    public void onPreInit (FMLPreInitializationEvent event) {


        this.registry.addShapedRecipe("dawn_star", new ItemStack(ModItems.dawnStar), "bcm", "cmc", "rcb", 'b', Items.BLAZE_POWDER, 'c', Items.FIRE_CHARGE, 'm', Items.MAGMA_CREAM, 'r', Items.BLAZE_ROD);


        this.registry.addShapedRecipe("vampiric_blade", new ItemStack(ModItems.vampiricBlade), " ir", "ori", "so ", 'i', OreDictUtils.INGOT_IRON, 'r', OreDictUtils.DUST_REDSTONE, 'o', OreDictUtils.OBSIDIAN, 's', OreDictUtils.STICK_WOOD);


        this.registry.addShapedRecipe("gladiolus", new ItemStack(ModItems.gladiolus), " lv", "sfl", "ts ", 'l', OreDictUtils.TREE_LEAVES, 'v', OreDictUtils.VINE, 's', OreDictUtils.TREE_SAPLING, 'f', new ItemStack(Blocks.RED_FLOWER, 1, 1), 't', OreDictUtils.STICK_WOOD);


        this.registry.addShapedRecipe("draconic_blade", new ItemStack(ModItems.draconicBlade), " ir", "ldi", "sl ", 'i', OreDictUtils.INGOT_IRON, 'r', OreDictUtils.DUST_REDSTONE, 'l', OreDictUtils.GEM_LAPIS, 'd', OreDictUtils.GEM_DIAMOND, 's', OreDictUtils.STICK_WOOD);


        this.registry.addShapedRecipe("eye_end_blade", new ItemStack(ModItems.eyeEndBlade), " po", "dep", "sd ", 'p', OreDictUtils.ENDERPEARL, 'o', OreDictUtils.OBSIDIAN, 'd', OreDictUtils.GEM_DIAMOND, 'e', Items.ENDER_EYE, 's', OreDictUtils.STICK_WOOD);


        this.registry.addShapedRecipe("crystaline", new ItemStack(ModItems.crystalineBlade), " gg", "qpg", "sq ", 'g', OreDictUtils.BLOCK_GLASS, 'p', OreDictUtils.PANE_GLASS, 'q', OreDictUtils.GEM_QUARTZ, 's', OreDictUtils.STICK_WOOD);


        this.registry.addShapedRecipe("glacial", new ItemStack(ModItems.glacialBlade), " ip", "ipi", "si ", 'i', Blocks.ICE, 'p', Blocks.PACKED_ICE, 's', OreDictUtils.STICK_WOOD);


        this.registry.addShapedRecipe("aethers_guard", new ItemStack(ModItems.aethersGuard), "fdg", "dgi", "sif", 'f', OreDictUtils.FEATHER, 'd', OreDictUtils.GEM_DIAMOND, 'g', OreDictUtils.GLOWSTONE, 'i', OreDictUtils.INGOT_IRON, 's', OreDictUtils.STICK_WOOD);


        this.registry.addShapedRecipe("wither_bane", new ItemStack(ModItems.withersBane), " ss", "qns", "kq ", 's', Blocks.SOUL_SAND, 'q', OreDictUtils.GEM_QUARTZ, 'n', OreDictUtils.NETHER_STAR, 'k', OreDictUtils.STICK_WOOD);


        this.registry.addShapedRecipe("adminium_ark", new ItemStack(ModItems.adminiumArk), " bb", "fcb", "sf ", 'b', Blocks.BEDROCK, 'f', Blocks.END_PORTAL_FRAME, 'c', Blocks.COMMAND_BLOCK, 's', OreDictUtils.STICK_WOOD);

        for (final String relicName : this.relicNames) {

            this.relics.add(this.registry.registerItem(new ItemSwordRelic(), "relic_" + relicName));
        }

        this.config.syncConfigData();
    }
}