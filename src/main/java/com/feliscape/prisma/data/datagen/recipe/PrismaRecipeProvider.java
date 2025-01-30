package com.feliscape.prisma.data.datagen.recipe;

import com.feliscape.prisma.data.PrismaBlockFamilies;
import com.feliscape.prisma.registry.PrismaBlocks;
import com.feliscape.prisma.registry.PrismaItems;
import com.feliscape.prisma.registry.PrismaTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class PrismaRecipeProvider extends RecipeProvider {
    List<Item> ALL_DYES = List.of(
            Items.WHITE_DYE,
            Items.LIGHT_GRAY_DYE,
            Items.GRAY_DYE,
            Items.BLACK_DYE,
            Items.BROWN_DYE,
            Items.RED_DYE,
            Items.ORANGE_DYE,
            Items.YELLOW_DYE,
            Items.LIME_DYE,
            Items.GREEN_DYE,
            Items.CYAN_DYE,
            Items.LIGHT_BLUE_DYE,
            Items.BLUE_DYE,
            Items.PURPLE_DYE,
            Items.MAGENTA_DYE,
            Items.PINK_DYE
    );

    public PrismaRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, pOutput);
    }

    @Override
    protected void buildRecipes() {
        this.generateRecipes(PrismaBlockFamilies.PRISMA_PLANKS, FeatureFlagSet.of(FeatureFlags.VANILLA));

        this.createRuggedWoolRecipes();

        this.shapeless(RecipeCategory.MISC, PrismaItems.PRISMATIC_DUST)
                .requires(PrismaItems.AUSTRALITE_CRYSTAL, 2)
                .requires(PrismaItems.BOREALITE_CRYSTAL, 2)
                .unlockedBy("has_aurora_crystal", has(PrismaTags.Items.AURORA_CRYSTALS))
                .save(output);

        this.twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, PrismaBlocks.AUSTRALITE_BLOCK, PrismaItems.AUSTRALITE_CRYSTAL);
        this.twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, PrismaBlocks.BOREALITE_BLOCK, PrismaItems.BOREALITE_CRYSTAL);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, PrismaBlocks.AUSTRALITE_LANTERN.get())
                .define('#', Items.COPPER_INGOT)
                .define('o', PrismaItems.AUSTRALITE_CRYSTAL)
                .pattern("#")
                .pattern("o")
                .unlockedBy(getHasName(PrismaItems.AUSTRALITE_CRYSTAL), has(PrismaItems.AUSTRALITE_CRYSTAL))
                .save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, PrismaBlocks.BOREALITE_LANTERN.get())
                .define('#', Items.COPPER_INGOT)
                .define('o', PrismaItems.BOREALITE_CRYSTAL)
                .pattern("#")
                .pattern("o")
                .unlockedBy(getHasName(PrismaItems.BOREALITE_CRYSTAL), has(PrismaItems.BOREALITE_CRYSTAL))
                .save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, PrismaItems.CRYSTAL_MELON_SLICE.get())
                .define('A', PrismaItems.AUSTRALITE_CRYSTAL)
                .define('B', PrismaItems.BOREALITE_CRYSTAL)
                .define('#', Items.MELON_SLICE)
                .pattern(" A ")
                .pattern("A#B")
                .pattern(" B ")
                .unlockedBy("has_aurora_crystal", has(PrismaTags.Items.AURORA_CRYSTALS))
                .save(this.output);

        /*planksFromLog(PrismaBlocks.PRISMA_PLANKS.get(), PrismaTags.Items.PRISMA_LOGS, 4);
        woodFromLogs(PrismaBlocks.PRISMA_WOOD.get(), PrismaBlocks.PRISMA_LOG.get());
        woodFromLogs(PrismaBlocks.STRIPPED_PRISMA_WOOD.get(), PrismaBlocks.STRIPPED_PRISMA_LOG.get());

        this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, PrismaBlocks.PRISMA_SLAB, Ingredient.of(PrismaBlocks.PRISMA_PLANKS))
                .unlockedBy("has_aurora_planks", this.has(PrismaBlocks.PRISMA_PLANKS))
                .save(this.output);
        this.stairBuilder(PrismaBlocks.PRISMA_STAIRS, Ingredient.of(PrismaBlocks.PRISMA_PLANKS))
                .unlockedBy("has_aurora_planks", this.has(PrismaBlocks.PRISMA_PLANKS))
                .save(this.output);
        this.fenceBuilder(PrismaBlocks.PRISMA_FENCE, Ingredient.of(PrismaBlocks.PRISMA_PLANKS))
                .unlockedBy(getHasName(PrismaBlocks.PRISMA_PLANKS), has(PrismaBlocks.PRISMA_PLANKS))
                .save(this.output);
        this.fenceGateBuilder(PrismaBlocks.PRISMA_FENCE_GATE, Ingredient.of(PrismaBlocks.PRISMA_PLANKS))
                .unlockedBy(getHasName(PrismaBlocks.PRISMA_PLANKS), has(PrismaBlocks.PRISMA_PLANKS))
                .save(this.output);
        this.buttonBuilder(PrismaBlocks.PRISMA_BUTTON, Ingredient.of(PrismaBlocks.PRISMA_PLANKS))
                .unlockedBy(getHasName(PrismaBlocks.PRISMA_PLANKS), has(PrismaBlocks.PRISMA_PLANKS))
                .save(this.output);
        this.pressurePlate(PrismaBlocks.PRISMA_PRESSURE_PLATE, PrismaBlocks.PRISMA_PLANKS);
        this.doorBuilder(PrismaBlocks.PRISMA_DOOR, Ingredient.of(PrismaBlocks.PRISMA_PLANKS))
                .unlockedBy(getHasName(PrismaBlocks.PRISMA_PLANKS), has(PrismaBlocks.PRISMA_PLANKS))
                .save(this.output);
        this.trapdoorBuilder(PrismaBlocks.PRISMA_TRAPDOOR, Ingredient.of(PrismaBlocks.PRISMA_PLANKS))
                .unlockedBy(getHasName(PrismaBlocks.PRISMA_PLANKS), has(PrismaBlocks.PRISMA_PLANKS))
                .save(this.output);*/
    }

    private void createRuggedWoolRecipes() {

        ruggedWoolToNormalWool(PrismaBlocks.WHITE_RUGGED_WOOL, Blocks.WHITE_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.LIGHT_GRAY_RUGGED_WOOL, Blocks.LIGHT_GRAY_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.GRAY_RUGGED_WOOL, Blocks.GRAY_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.BLACK_RUGGED_WOOL, Blocks.BLACK_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.BROWN_RUGGED_WOOL, Blocks.BROWN_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.RED_RUGGED_WOOL, Blocks.RED_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.ORANGE_RUGGED_WOOL, Blocks.ORANGE_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.YELLOW_RUGGED_WOOL, Blocks.YELLOW_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.LIME_RUGGED_WOOL, Blocks.LIME_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.GREEN_RUGGED_WOOL, Blocks.GREEN_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.CYAN_RUGGED_WOOL, Blocks.CYAN_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.LIGHT_BLUE_RUGGED_WOOL, Blocks.LIGHT_BLUE_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.BLUE_RUGGED_WOOL, Blocks.BLUE_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.PURPLE_RUGGED_WOOL, Blocks.PURPLE_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.MAGENTA_RUGGED_WOOL, Blocks.MAGENTA_WOOL);
        ruggedWoolToNormalWool(PrismaBlocks.PINK_RUGGED_WOOL, Blocks.PINK_WOOL);

        colorBlockWithDye(ALL_DYES, PrismaBlocks.RUGGED_WOOL_BY_COLOR.get().values().stream().map(DeferredBlock::asItem).toList(), "rugged_wool");
    }
    private void ruggedWoolToNormalWool(ItemLike ruggedWool, ItemLike normalWool){
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, normalWool, 2)
                .requires(ruggedWool)
                .unlockedBy(getHasName(PrismaBlocks.WHITE_RUGGED_WOOL), this.has(PrismaBlocks.WHITE_RUGGED_WOOL))
                .save(output, getHasName(normalWool) + "_from_rugged_wool");
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(packOutput, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider recipeProvider, RecipeOutput output) {
            return new PrismaRecipeProvider(recipeProvider, output);
        }

        @Override
        public String getName() {
            return "Deepwood Recipes";
        }
    }
}