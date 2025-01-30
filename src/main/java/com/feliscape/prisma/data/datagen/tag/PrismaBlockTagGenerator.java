package com.feliscape.prisma.data.datagen.tag;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.registry.PrismaBlocks;
import com.feliscape.prisma.registry.PrismaTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class PrismaBlockTagGenerator extends BlockTagsProvider {
    public PrismaBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Prisma.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(PrismaBlocks.AUSTRALITE_BLOCK.get())
                .add(PrismaBlocks.BOREALITE_BLOCK.get())
        ;
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(PrismaBlocks.PRISMA_LOG.get())
                .add(PrismaBlocks.STRIPPED_PRISMA_LOG.get())
                .add(PrismaBlocks.PRISMA_WOOD.get())
                .add(PrismaBlocks.STRIPPED_PRISMA_WOOD.get())
                .add(PrismaBlocks.PRISMA_PLANKS.get())
                .add(PrismaBlocks.PRISMA_STAIRS.get())
                .add(PrismaBlocks.PRISMA_SLAB.get())
                .add(PrismaBlocks.PRISMA_FENCE.get())
                .add(PrismaBlocks.PRISMA_FENCE_GATE.get())
                .add(PrismaBlocks.PRISMA_BUTTON.get())
                .add(PrismaBlocks.PRISMA_PRESSURE_PLATE.get())
                .add(PrismaBlocks.PRISMA_DOOR.get())
                .add(PrismaBlocks.PRISMA_TRAPDOOR.get())
        ;
        this.tag(PrismaTags.Blocks.RUGGED_WOOL)
                .add(PrismaBlocks.WHITE_RUGGED_WOOL.get())
        ;
        this.tag(BlockTags.LEAVES)
                .add(PrismaBlocks.PRISMA_LEAVES.get())
        ;
        this.tag(BlockTags.WOODEN_STAIRS)
                .add(PrismaBlocks.PRISMA_STAIRS.get())
        ;
        this.tag(BlockTags.WOODEN_SLABS)
                .add(PrismaBlocks.PRISMA_SLAB.get())
        ;
        this.tag(BlockTags.PLANKS)
                .add(PrismaBlocks.PRISMA_PLANKS.get())
        ;
        this.tag(BlockTags.PRESSURE_PLATES)
                .add(PrismaBlocks.PRISMA_PRESSURE_PLATE.get())
        ;
        this.tag(BlockTags.WOODEN_BUTTONS)
                .add(PrismaBlocks.PRISMA_BUTTON.get())
        ;
        this.tag(BlockTags.WOODEN_DOORS)
                .add(PrismaBlocks.PRISMA_DOOR.get())
        ;
        this.tag(BlockTags.WOODEN_TRAPDOORS)
                .add(PrismaBlocks.PRISMA_TRAPDOOR.get())
        ;
        this.tag(PrismaTags.Blocks.PRISMA_LOGS)
                .add(PrismaBlocks.PRISMA_LOG.get())
                .add(PrismaBlocks.STRIPPED_PRISMA_LOG.get())
                .add(PrismaBlocks.PRISMA_WOOD.get())
                .add(PrismaBlocks.STRIPPED_PRISMA_WOOD.get())
        ;
        this.tag(BlockTags.LOGS_THAT_BURN)
                .addTag(PrismaTags.Blocks.PRISMA_LOGS)
        ;
        this.tag(BlockTags.WOODEN_FENCES)
                .add(PrismaBlocks.PRISMA_FENCE.get())
        ;
        this.tag(BlockTags.FENCE_GATES)
                .add(PrismaBlocks.PRISMA_FENCE_GATE.get())
        ;
    }
}
