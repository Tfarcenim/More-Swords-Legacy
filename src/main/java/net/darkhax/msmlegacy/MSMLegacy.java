package net.darkhax.msmlegacy;

import java.io.File;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "msmlegacy", name = "More Swords: Legacy", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.3.577,)", certificateFingerprint = "@FINGERPRINT@")
public class MSMLegacy {

    private final CreativeTabs creativeTab = new CreativeTabMSMLegacy();
    private final RegistryHelper registry = new RegistryHelper("msmlegacy").setTab(this.creativeTab).enableAutoRegistration();
    private final ConfigurationHandler config = new ConfigurationHandler(new File("config/msmlegacy.cfg"));

    public Item dawnStar;
    public Enchantment ignite;
    public Enchantment sparks;

    public Item vampiricBlade;
    public Enchantment feast;
    public Enchantment vitality;

    public Item gladiolus;
    public Enchantment venomousAspect;
    public Enchantment absorb;

    public Item draconicBlade;
    public Enchantment keenEdge;
    public Enchantment scorn;

    public Item eyeEndBlade;
    public Enchantment enderPulse;
    public Enchantment enderAura;

    public Item crystalineBlade;
    public Enchantment greed;
    public Enchantment wisdom;

    public Item glacialBlade;
    public Enchantment frozenAspect;
    public Enchantment frostWave;

    public Item aeithersGuard;
    public Enchantment ascension;
    public Enchantment descension;

    public Item withersBane;
    public Enchantment consumingShadows;
    public Enchantment decay;

    public Item adminiumArk;
    public Enchantment stealth;
    public Enchantment extinction;

    @EventHandler
    public void onPreInit (FMLPreInitializationEvent event) {

        this.dawnStar = this.registry.registerItem(new ItemSword(this.config.getMaterial("dawn_star", 3, 1286, 8, 6, 22)), "dawn_star");
        this.ignite = this.registry.registerEnchantment(this.config.getSwordEnchantment("ignite", this.dawnStar, Rarity.COMMON, 1, 3), "ignite");
        this.sparks = this.registry.registerEnchantment(this.config.getSwordEnchantment("sparks", this.dawnStar, Rarity.RARE, 1, 1), "sparks");

        this.vampiricBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("vampiric_blade", 3, 812, 8, 7, 12)), "vampiric_blade");
        this.feast = this.registry.registerEnchantment(this.config.getSwordEnchantment("feast", this.vampiricBlade, Rarity.COMMON, 1, 3), "feast");
        this.vitality = this.registry.registerEnchantment(this.config.getSwordEnchantment("vitality", this.vampiricBlade, Rarity.RARE, 1, 1), "vitality");

        this.gladiolus = this.registry.registerItem(new ItemSword(this.config.getMaterial("gladiolus", 3, 645, 8, 6, 10)), "gladiolus");
        this.venomousAspect = this.registry.registerEnchantment(this.config.getSwordEnchantment("venomous_aspect", this.gladiolus, Rarity.COMMON, 1, 3), "venomous_aspect");
        this.absorb = this.registry.registerEnchantment(this.config.getSwordEnchantment("absorb", this.gladiolus, Rarity.RARE, 1, 1), "absorb");

        this.draconicBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("draconic_blade", 3, 1089, 8, 7, 16)), "draconic_blade");
        this.keenEdge = this.registry.registerEnchantment(this.config.getSwordEnchantment("keen_edge", this.draconicBlade, Rarity.COMMON, 1, 3), "keen_edge");
        this.scorn = this.registry.registerEnchantment(this.config.getSwordEnchantment("scorn", this.draconicBlade, Rarity.RARE, 1, 1), "scorn");

        this.eyeEndBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("eye_end_blade", 3, 1580, 8, 8, 22)), "eye_end_blade");
        this.enderPulse = this.registry.registerEnchantment(this.config.getSwordEnchantment("ender_pulse", this.eyeEndBlade, Rarity.COMMON, 1, 3), "ender_pulse");
        this.enderAura = this.registry.registerEnchantment(this.config.getSwordEnchantment("ender_aura", this.eyeEndBlade, Rarity.RARE, 1, 1), "ender_aura");

        this.crystalineBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("crystaline_blade", 3, 570, 8, 5, 28)), "crystaline_blade");
        this.greed = this.registry.registerEnchantment(this.config.getSwordEnchantment("greed", this.crystalineBlade, Rarity.COMMON, 1, 3), "greed");
        this.wisdom = this.registry.registerEnchantment(this.config.getSwordEnchantment("wisdom", this.crystalineBlade, Rarity.RARE, 1, 1), "wisdom");

        this.glacialBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("glacial_blade", 3, 680, 8, 6, 15)), "glacial_blade");
        this.frozenAspect = this.registry.registerEnchantment(this.config.getSwordEnchantment("frozen_aspect", this.glacialBlade, Rarity.COMMON, 1, 3), "frozen_aspect");
        this.frostWave = this.registry.registerEnchantment(this.config.getSwordEnchantment("frost_wave", this.glacialBlade, Rarity.RARE, 1, 1), "frost_wave");

        this.aeithersGuard = this.registry.registerItem(new ItemSword(this.config.getMaterial("aethers_guard", 3, 1796, 8, 8, 22)), "aethers_guard");
        this.ascension = this.registry.registerEnchantment(this.config.getSwordEnchantment("ascension", this.aeithersGuard, Rarity.COMMON, 1, 3), "ascension");
        this.descension = this.registry.registerEnchantment(this.config.getSwordEnchantment("descension", this.aeithersGuard, Rarity.RARE, 1, 1), "descension");

        this.withersBane = this.registry.registerItem(new ItemSword(this.config.getMaterial("wither_bane", 3, 1869, 8, 6, 16)), "wither_bane");
        this.decay = this.registry.registerEnchantment(this.config.getSwordEnchantment("decay", this.withersBane, Rarity.COMMON, 1, 3), "decay");
        this.consumingShadows = this.registry.registerEnchantment(this.config.getSwordEnchantment("consuming_shadows", this.withersBane, Rarity.RARE, 1, 1), "consuming_shadows");

        this.adminiumArk = this.registry.registerItem(new ItemSword(this.config.getMaterial("adminium_ark", 3, 9999999, 8, 99999, 999)), "adminium_ark");
        this.stealth = this.registry.registerEnchantment(this.config.getSwordEnchantment("stealth", this.adminiumArk, Rarity.COMMON, 1, 3), "stealth");
        this.extinction = this.registry.registerEnchantment(this.config.getSwordEnchantment("extinction", this.adminiumArk, Rarity.RARE, 1, 1), "extinction");

        this.config.syncConfigData();
    }
}