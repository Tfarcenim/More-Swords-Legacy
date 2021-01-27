package net.darkhax.msmlegacy.init;

import net.darkhax.msmlegacy.enchantment.EnchantmentKeenEdge;
import net.darkhax.msmlegacy.enchantment.EnchantmentSwordLegacy;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.ForgeConfigSpec;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModEnchantments {
    public static Enchantment ignite;
    public static Enchantment sparks;
    public static Enchantment feast;
    public static Enchantment vitality;
    public static Enchantment venomousAspect;
    public static Enchantment absorb;
    public static Enchantment keenEdge;
    public static Enchantment scorn;
    public static Enchantment enderPulse;
    public static Enchantment enderAura;
    public static Enchantment greed;
    public static Enchantment wisdom;
    public static Enchantment frozenAspect;
    public static Enchantment frostWave;
    public static Enchantment ascension;
    public static Enchantment descension;
    public static Enchantment consumingShadows;
    public static Enchantment decay;
    public static Enchantment stealth;
    public static Enchantment extinction;

    public static void init(ForgeConfigSpec.Builder builder) {
        ModEnchantments.ignite = EnchantmentSwordLegacy.build(builder,"ignite", () -> ModItems.dawnStar, Enchantment.Rarity.COMMON, 1,3);
        ModEnchantments.sparks = EnchantmentSwordLegacy.build(builder,"sparks", () -> ModItems.dawnStar, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.feast = EnchantmentSwordLegacy.build(builder,"feast", () ->ModItems.vampiricBlade, Enchantment.Rarity.COMMON, 1, 3);
        ModEnchantments.vitality = EnchantmentSwordLegacy.build(builder,"vitality",() ->  ModItems.vampiricBlade, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.venomousAspect = EnchantmentSwordLegacy.build(builder,"venomous_aspect", () -> ModItems.gladiolus, Enchantment.Rarity.COMMON, 1, 3);
        ModEnchantments.absorb = EnchantmentSwordLegacy.build(builder,"absorb", () -> ModItems.gladiolus, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.keenEdge = EnchantmentKeenEdge.build1(builder,"keen_edge", () -> ModItems.draconicBlade, Enchantment.Rarity.COMMON, 1, 3,true);
        ModEnchantments.scorn = EnchantmentSwordLegacy.build(builder,"scorn", () -> ModItems.draconicBlade, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.enderPulse = EnchantmentSwordLegacy.build(builder,"ender_pulse", () -> ModItems.eyeEndBlade, Enchantment.Rarity.COMMON, 1, 3);
        ModEnchantments.enderAura = EnchantmentSwordLegacy.build(builder,"ender_aura", () -> ModItems.eyeEndBlade, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.greed = EnchantmentSwordLegacy.build(builder,"greed", () -> ModItems.crystalineBlade, Enchantment.Rarity.COMMON, 1, 3);
        ModEnchantments.wisdom = EnchantmentSwordLegacy.build(builder,"wisdom", () -> ModItems.crystalineBlade, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.frozenAspect = EnchantmentSwordLegacy.build(builder,"frozen_aspect", () -> ModItems.glacialBlade, Enchantment.Rarity.COMMON, 1, 3);
        ModEnchantments.frostWave = EnchantmentSwordLegacy.build(builder,"frost_wave", () -> ModItems.glacialBlade, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.ascension = EnchantmentSwordLegacy.build(builder,"ascension", () -> ModItems.aethersGuard, Enchantment.Rarity.COMMON, 1, 3);
        ModEnchantments.descension = EnchantmentSwordLegacy.build(builder,"descension", () -> ModItems.aethersGuard, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.decay = EnchantmentSwordLegacy.build(builder,"decay", () -> ModItems.withersBane, Enchantment.Rarity.COMMON, 1, 3);
        ModEnchantments.consumingShadows = EnchantmentSwordLegacy.build(builder,"consuming_shadows", () -> ModItems.withersBane, Enchantment.Rarity.RARE, 1, 1);

        ModEnchantments.stealth = EnchantmentSwordLegacy.build(builder,"stealth", () -> ModItems.adminiumArk, Enchantment.Rarity.COMMON, 1, 1, false);
        ModEnchantments.extinction = EnchantmentSwordLegacy.build(builder,"extinction", () -> ModItems.adminiumArk, Enchantment.Rarity.RARE, 1, 1, false);
    }

    private static List<Enchantment> enchantments;

    public static List<Enchantment> getEnchantments() {
        if (enchantments == null) {
            Field[] fields = ModEnchantments.class.getFields();
            enchantments = new ArrayList<>();
            for (Field field : fields) {
                try {
                    enchantments.add((Enchantment) field.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return enchantments;
    }
}
