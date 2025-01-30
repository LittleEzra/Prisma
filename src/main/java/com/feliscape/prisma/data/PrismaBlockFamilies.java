package com.feliscape.prisma.data;

import com.feliscape.prisma.registry.PrismaBlocks;
import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class PrismaBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    private static final String RECIPE_GROUP_PREFIX_WOODEN = "wooden";
    private static final String RECIPE_UNLOCKED_BY_HAS_PLANKS = "has_planks";
    public static final BlockFamily PRISMA_PLANKS = familyBuilder(PrismaBlocks.PRISMA_PLANKS.get())
            .button(PrismaBlocks.PRISMA_BUTTON.get())
            .fence(PrismaBlocks.PRISMA_FENCE.get())
            .fenceGate(PrismaBlocks.PRISMA_FENCE_GATE.get())
            .pressurePlate(PrismaBlocks.PRISMA_PRESSURE_PLATE.get())
            .sign(PrismaBlocks.PRISMA_SIGN.get(), PrismaBlocks.PRISMA_WALL_SIGN.get())
            .slab(PrismaBlocks.PRISMA_SLAB.get())
            .stairs(PrismaBlocks.PRISMA_STAIRS.get())
            .door(PrismaBlocks.PRISMA_DOOR.get())
            .trapdoor(PrismaBlocks.PRISMA_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    private static BlockFamily.Builder familyBuilder(Block baseBlock) {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockfamily = MAP.put(baseBlock, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return blockfamily$builder;
        }
    }
}
