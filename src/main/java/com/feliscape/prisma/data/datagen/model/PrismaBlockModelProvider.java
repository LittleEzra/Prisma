package com.feliscape.prisma.data.datagen.model;

import com.feliscape.prisma.data.PrismaBlockFamilies;
import com.feliscape.prisma.registry.PrismaBlocks;
import com.feliscape.prisma.util.ColorUtil;
import net.minecraft.client.color.item.Constant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Supplier;

import static com.feliscape.prisma.registry.PrismaBlocks.*;
import static net.minecraft.client.data.models.BlockModelGenerators.createBooleanModelDispatch;

public class PrismaBlockModelProvider {

    //NOTES:
    /*
    blockModels.createTrivialCube = cube_all
     */

    public static void registerModels(BlockModelGenerators blockModels) {
        blockModels.createTrivialCube(AUSTRALITE_BLOCK.get());
        blockModels.createTrivialCube(BOREALITE_BLOCK.get());
        //blockModels.createTintedLeaves(PRISMA_LEAVES.get(), TexturedModel.CUBE, ColorUtil.getIntColor("#eb6594"));
        ResourceLocation prismaLeavesLocation = TexturedModel.LEAVES.create(PRISMA_LEAVES.get(), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(PRISMA_LEAVES.get(), prismaLeavesLocation));
        blockModels.registerSimpleTintedItemModel(PRISMA_LEAVES.get(), prismaLeavesLocation, new Constant(ColorUtil.getIntColor("#eb6594")));

        for (DyeColor color : VANILLA_COLORS){
            if (RUGGED_WOOL_BY_COLOR.get().containsKey(color)) {
                blockModels.createTrivialCube(RUGGED_WOOL_BY_COLOR.get().get(color).get());
            }
        }

        crystalLantern(blockModels, AUSTRALITE_LANTERN);
        crystalLantern(blockModels, BOREALITE_LANTERN);

        blockModels.createTrivialCube(PRISMA_PLANKS.get());
        blockModels.woodProvider(PRISMA_LOG.get()).log(PRISMA_LOG.get());
        blockModels.woodProvider(STRIPPED_PRISMA_LOG.get()).log(STRIPPED_PRISMA_LOG.get());
        blockModels.woodProvider(PRISMA_WOOD.get()).wood(PRISMA_WOOD.get());
        blockModels.woodProvider(STRIPPED_PRISMA_WOOD.get()).wood(STRIPPED_PRISMA_WOOD.get());
        var prismaBlockFamilyProvider = blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get())
                .generateFor(PrismaBlockFamilies.PRISMA_PLANKS);
        /*blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get()).stairs(PRISMA_STAIRS.get());
        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get()).slab(PRISMA_SLAB.get());
        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get()).fence(PRISMA_FENCE.get());
        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get()).fenceGate(PRISMA_FENCE_GATE.get());
        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get()).button(PRISMA_BUTTON.get());
        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get()).pressurePlate(PRISMA_PRESSURE_PLATE.get());
        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get()).door(PRISMA_DOOR.get());
        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get()).trapdoor(PRISMA_TRAPDOOR.get());

        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get())
                .generateFor(PrismaBlockFamilies.PRISMA_PLANKS)
                .sign(PRISMA_SIGN.get());
        blockModels.familyWithExistingFullBlock(PRISMA_PLANKS.get())
                .generateFor(PrismaBlockFamilies.PRISMA_PLANKS)
                .sign(PRISMA_WALL_SIGN.get());*/
        blockModels.createHangingSign(PRISMA_PLANKS.get(), PRISMA_HANGING_SIGN.get(), PRISMA_WALL_HANGING_SIGN.get());

        blockModels.createPlantWithDefaultItem(
                PRISMA_SAPLING.get(),
                POTTED_PRISMA_SAPLING.get(),
                BlockModelGenerators.PlantType.NOT_TINTED);

    }

    private static void createPlantWithDefaultItem(BlockModelGenerators blockModels, Block block, Block pottedBlock, BlockModelGenerators.PlantType plantType) {
        blockModels.registerSimpleItemModel(block.asItem(), plantType.createItemModel(blockModels, block));
        blockModels.createPlant(block, pottedBlock, plantType);
    }

    private static void crystalLantern(BlockModelGenerators blockModels, Supplier<? extends Block> block){
        TextureMapping texture = new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(block.get()));
        ResourceLocation defaultModel = PrismaModelTemplates.CRYSTAL_LANTERN.create(block.get(), texture, blockModels.modelOutput);
        ResourceLocation hangingModel = PrismaModelTemplates.HANGING_CRYSTAL_LANTERN.create(block.get(), texture, blockModels.modelOutput);
        blockModels.registerSimpleFlatItemModel(block.get().asItem());
        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block.get()).with(createBooleanModelDispatch(BlockStateProperties.HANGING, hangingModel, defaultModel))
        );
    }
}
