package net.darkhax.msmlegacy.datagen.data;

import net.darkhax.msmlegacy.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        ShapedRecipeBuilder.shapedRecipe(ModItems.dawnStar)
                .patternLine("bcm")
                .patternLine("cmc")
                .patternLine("rcb")
                .key('b', Items.BLAZE_POWDER)
                .key('c', Items.FIRE_CHARGE)
                .key('m', Items.MAGMA_CREAM)
                .key('r', Items.BLAZE_ROD)
                .addCriterion("has_fire_charge",hasItem(Items.FIRE_CHARGE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.vampiricBlade)
                .patternLine(" ir")
                .patternLine("ori")
                .patternLine("so ")
                .key('i', Tags.Items.INGOTS_IRON)
                .key('r', Tags.Items.DUSTS_REDSTONE)
                .key('o', Tags.Items.OBSIDIAN)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_obsidian",hasItem(Items.OBSIDIAN))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.gladiolus)
                .patternLine(" lv")
                .patternLine("sfl")
                .patternLine("ts ")
                .key('l', ItemTags.LEAVES)
                .key('v', Items.VINE)
                .key('s', ItemTags.SAPLINGS)
                .key('f', Items.POPPY)
                .key('t', Tags.Items.RODS_WOODEN)
                .addCriterion("has_poppy",hasItem(Items.POPPY))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.draconicBlade)
                .patternLine(" ir")
                .patternLine("ldi")
                .patternLine("sl ")
                .key('i', Tags.Items.INGOTS_IRON)
                .key('r', Tags.Items.DUSTS_REDSTONE)
                .key('l', Tags.Items.GEMS_LAPIS)
                .key('d', Tags.Items.GEMS_DIAMOND)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_diamond",hasItem(Items.DIAMOND))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.eyeEndBlade)
                .patternLine(" po")
                .patternLine("dep")
                .patternLine("sd ")
                .key('p', Tags.Items.ENDER_PEARLS)
                .key('o', Tags.Items.OBSIDIAN)
                .key('d', Tags.Items.GEMS_DIAMOND)
                .key('e', Items.ENDER_EYE)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_diamond",hasItem(Items.DIAMOND))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.crystalineBlade)
                .patternLine(" gg")
                .patternLine("qpg")
                .patternLine("sq ")
                .key('g', Tags.Items.GLASS)
                .key('p', Tags.Items.GLASS_PANES)
                .key('q', Tags.Items.GEMS_QUARTZ)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_quartz",hasItem(Items.QUARTZ))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.glacialBlade)
                .patternLine(" ip")
                .patternLine("ipi")
                .patternLine("si ")
                .key('i', Items.ICE)
                .key('p', Items.PACKED_ICE)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_packed_ice",hasItem(Items.PACKED_ICE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.aethersGuard)
                .patternLine("fdg")
                .patternLine("dgi")
                .patternLine("sif")
                .key('f', Tags.Items.FEATHERS)
                .key('d', Tags.Items.GEMS_DIAMOND)
                .key('g', Items.GLOWSTONE)
                .key('i', Tags.Items.INGOTS_IRON)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_diamond",hasItem(Items.DIAMOND))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.withersBane)
                .patternLine(" ss")
                .patternLine("qns")
                .patternLine("kq ")
                .key('k', Items.SOUL_SAND)
                .key('q', Tags.Items.GEMS_QUARTZ)
                .key('n', Items.NETHER_STAR)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_nether_star",hasItem(Items.NETHER_STAR))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.adminiumArk)
                .patternLine(" bb")
                .patternLine("fcb")
                .patternLine("sf ")
                .key('b', Items.BEDROCK)
                .key('f', Items.END_PORTAL_FRAME)
                .key('c', Items.COMMAND_BLOCK)
                .key('s', Tags.Items.RODS_WOODEN)
                .addCriterion("has_command_block",hasItem(Items.COMMAND_BLOCK))
                .build(consumer);
    }
}
