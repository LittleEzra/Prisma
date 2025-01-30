package com.feliscape.prisma.data.datagen.tag;

import com.feliscape.prisma.registry.PrismaItems;
import com.feliscape.prisma.registry.PrismaTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class PrismaItemTagGenerator extends ItemTagsProvider {
    public PrismaItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.copy(PrismaTags.Blocks.PRISMA_LOGS, PrismaTags.Items.PRISMA_LOGS);
        this.copy(PrismaTags.Blocks.RUGGED_WOOL, PrismaTags.Items.RUGGED_WOOL);
        this.tag(PrismaTags.Items.AURORA_CRYSTALS)
                .add(PrismaItems.AUSTRALITE_CRYSTAL.get())
                .add(PrismaItems.BOREALITE_CRYSTAL.get());
    }
}
