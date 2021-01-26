package net.darkhax.msmlegacy;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.bookshelf.util.OreDictUtils;
import net.darkhax.msmlegacy.init.ModEnchantments;
import net.darkhax.msmlegacy.init.ModItems;
import net.darkhax.msmlegacy.item.ItemSwordRelic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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

        ModEnchantments.ignite = this.registry.registerEnchantment(this.config.getSwordEnchantment("ignite", ModItems.dawnStar, Rarity.COMMON, 1, 3), "ignite");
        ModEnchantments.sparks = this.registry.registerEnchantment(this.config.getSwordEnchantment("sparks", ModItems.dawnStar, Rarity.RARE, 1, 1), "sparks");
        this.registry.addShapedRecipe("dawn_star", new ItemStack(ModItems.dawnStar), "bcm", "cmc", "rcb", 'b', Items.BLAZE_POWDER, 'c', Items.FIRE_CHARGE, 'm', Items.MAGMA_CREAM, 'r', Items.BLAZE_ROD);

        ModEnchantments.feast = this.registry.registerEnchantment(this.config.getSwordEnchantment("feast", ModItems.vampiricBlade, Rarity.COMMON, 1, 3), "feast");
        ModEnchantments.vitality = this.registry.registerEnchantment(this.config.getSwordEnchantment("vitality", ModItems.vampiricBlade, Rarity.RARE, 1, 1), "vitality");
        this.registry.addShapedRecipe("vampiric_blade", new ItemStack(ModItems.vampiricBlade), " ir", "ori", "so ", 'i', OreDictUtils.INGOT_IRON, 'r', OreDictUtils.DUST_REDSTONE, 'o', OreDictUtils.OBSIDIAN, 's', OreDictUtils.STICK_WOOD);

        ModEnchantments.venomousAspect = this.registry.registerEnchantment(this.config.getSwordEnchantment("venomous_aspect", ModItems.gladiolus, Rarity.COMMON, 1, 3), "venomous_aspect");
        ModEnchantments.absorb = this.registry.registerEnchantment(this.config.getSwordEnchantment("absorb", ModItems.gladiolus, Rarity.RARE, 1, 1), "absorb");
        this.registry.addShapedRecipe("gladiolus", new ItemStack(ModItems.gladiolus), " lv", "sfl", "ts ", 'l', OreDictUtils.TREE_LEAVES, 'v', OreDictUtils.VINE, 's', OreDictUtils.TREE_SAPLING, 'f', new ItemStack(Blocks.RED_FLOWER, 1, 1), 't', OreDictUtils.STICK_WOOD);

        ModEnchantments.keenEdge = this.registry.registerEnchantment(this.config.getKeenEdge("keen_edge", ModItems.draconicBlade, Rarity.COMMON, 1, 3), "keen_edge");
        ModEnchantments.scorn = this.registry.registerEnchantment(this.config.getSwordEnchantment("scorn", ModItems.draconicBlade, Rarity.RARE, 1, 1), "scorn");
        this.registry.addShapedRecipe("draconic_blade", new ItemStack(ModItems.draconicBlade), " ir", "ldi", "sl ", 'i', OreDictUtils.INGOT_IRON, 'r', OreDictUtils.DUST_REDSTONE, 'l', OreDictUtils.GEM_LAPIS, 'd', OreDictUtils.GEM_DIAMOND, 's', OreDictUtils.STICK_WOOD);

        ModEnchantments.enderPulse = this.registry.registerEnchantment(this.config.getSwordEnchantment("ender_pulse", ModItems.eyeEndBlade, Rarity.COMMON, 1, 3), "ender_pulse");
        ModEnchantments.enderAura = this.registry.registerEnchantment(this.config.getSwordEnchantment("ender_aura", ModItems.eyeEndBlade, Rarity.RARE, 1, 1), "ender_aura");
        this.registry.addShapedRecipe("eye_end_blade", new ItemStack(ModItems.eyeEndBlade), " po", "dep", "sd ", 'p', OreDictUtils.ENDERPEARL, 'o', OreDictUtils.OBSIDIAN, 'd', OreDictUtils.GEM_DIAMOND, 'e', Items.ENDER_EYE, 's', OreDictUtils.STICK_WOOD);

        ModEnchantments.greed = this.registry.registerEnchantment(this.config.getSwordEnchantment("greed", ModItems.crystalineBlade, Rarity.COMMON, 1, 3), "greed");
        ModEnchantments.wisdom = this.registry.registerEnchantment(this.config.getSwordEnchantment("wisdom", ModItems.crystalineBlade, Rarity.RARE, 1, 1), "wisdom");
        this.registry.addShapedRecipe("crystaline", new ItemStack(ModItems.crystalineBlade), " gg", "qpg", "sq ", 'g', OreDictUtils.BLOCK_GLASS, 'p', OreDictUtils.PANE_GLASS, 'q', OreDictUtils.GEM_QUARTZ, 's', OreDictUtils.STICK_WOOD);

        ModEnchantments.frozenAspect = this.registry.registerEnchantment(this.config.getSwordEnchantment("frozen_aspect", ModItems.glacialBlade, Rarity.COMMON, 1, 3), "frozen_aspect");
        ModEnchantments.frostWave = this.registry.registerEnchantment(this.config.getSwordEnchantment("frost_wave", ModItems.glacialBlade, Rarity.RARE, 1, 1), "frost_wave");
        this.registry.addShapedRecipe("glacial", new ItemStack(ModItems.glacialBlade), " ip", "ipi", "si ", 'i', Blocks.ICE, 'p', Blocks.PACKED_ICE, 's', OreDictUtils.STICK_WOOD);

        ModEnchantments.ascension = this.registry.registerEnchantment(this.config.getSwordEnchantment("ascension", ModItems.aethersGuard, Rarity.COMMON, 1, 3), "ascension");
        ModEnchantments.descension = this.registry.registerEnchantment(this.config.getSwordEnchantment("descension", ModItems.aethersGuard, Rarity.RARE, 1, 1), "descension");
        this.registry.addShapedRecipe("aethers_guard", new ItemStack(ModItems.aethersGuard), "fdg", "dgi", "sif", 'f', OreDictUtils.FEATHER, 'd', OreDictUtils.GEM_DIAMOND, 'g', OreDictUtils.GLOWSTONE, 'i', OreDictUtils.INGOT_IRON, 's', OreDictUtils.STICK_WOOD);

        ModEnchantments.decay = this.registry.registerEnchantment(this.config.getSwordEnchantment("decay", ModItems.withersBane, Rarity.COMMON, 1, 3), "decay");
        ModEnchantments.consumingShadows = this.registry.registerEnchantment(this.config.getSwordEnchantment("consuming_shadows", ModItems.withersBane, Rarity.RARE, 1, 1), "consuming_shadows");
        this.registry.addShapedRecipe("wither_bane", new ItemStack(ModItems.withersBane), " ss", "qns", "kq ", 's', Blocks.SOUL_SAND, 'q', OreDictUtils.GEM_QUARTZ, 'n', OreDictUtils.NETHER_STAR, 'k', OreDictUtils.STICK_WOOD);

        ModEnchantments.stealth = this.registry.registerEnchantment(this.config.getSwordEnchantment("stealth", ModItems.adminiumArk, Rarity.COMMON, 1, 1, false), "stealth");
        ModEnchantments.extinction = this.registry.registerEnchantment(this.config.getSwordEnchantment("extinction", ModItems.adminiumArk, Rarity.RARE, 1, 1, false), "extinction");
        this.registry.addShapedRecipe("adminium_ark", new ItemStack(ModItems.adminiumArk), " bb", "fcb", "sf ", 'b', Blocks.BEDROCK, 'f', Blocks.END_PORTAL_FRAME, 'c', Blocks.COMMAND_BLOCK, 's', OreDictUtils.STICK_WOOD);

        for (final String relicName : this.relicNames) {

            this.relics.add(this.registry.registerItem(new ItemSwordRelic(), "relic_" + relicName));
        }

        this.config.syncConfigData();
    }
}