package com.feliscape.prisma.data.datagen.loot;

import com.feliscape.prisma.registry.PrismaBlocks;
import com.feliscape.prisma.registry.PrismaItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class PrismaBlockLootTableProvider extends BlockLootSubProvider {
    public PrismaBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.add(PrismaBlocks.AUSTRALITE_BLOCK.get(), b -> createCrystalDrops(b, PrismaItems.AUSTRALITE_CRYSTAL));
        this.add(PrismaBlocks.BOREALITE_BLOCK.get(), b -> createCrystalDrops(b, PrismaItems.BOREALITE_CRYSTAL));
        this.add(PrismaBlocks.PRISMA_LEAVES.get(), b -> createLeavesDrops(b, PrismaBlocks.PRISMA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        for (var block : PrismaBlocks.RUGGED_WOOL_BY_COLOR.get().values()){
            this.dropSelf(block.get());
        }

        this.dropSelf(PrismaBlocks.PRISMA_LOG.get());
        this.dropSelf(PrismaBlocks.STRIPPED_PRISMA_LOG.get());
        this.dropSelf(PrismaBlocks.PRISMA_WOOD.get());
        this.dropSelf(PrismaBlocks.STRIPPED_PRISMA_WOOD.get());
        this.dropSelf(PrismaBlocks.PRISMA_PLANKS.get());

        /*this.add(AuroraBlocks.AURORA_SIGN.get(), block ->
                createSingleItemTable(AuroraItems.AURORA_SIGN.get()));
        this.add(AuroraBlocks.AURORA_WALL_SIGN.get(), block ->
                createSingleItemTable(AuroraItems.AURORA_SIGN.get()));
        this.add(AuroraBlocks.AURORA_HANGING_SIGN.get(), block ->
                createSingleItemTable(AuroraItems.AURORA_HANGING_SIGN.get()));
        this.add(AuroraBlocks.AURORA_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(AuroraItems.AURORA_HANGING_SIGN.get()));*/

        dropSelf(PrismaBlocks.PRISMA_STAIRS.get());
        dropSelf(PrismaBlocks.PRISMA_BUTTON.get());
        dropSelf(PrismaBlocks.PRISMA_PRESSURE_PLATE.get());
        dropSelf(PrismaBlocks.PRISMA_TRAPDOOR.get());
        dropSelf(PrismaBlocks.PRISMA_FENCE.get());
        dropSelf(PrismaBlocks.PRISMA_FENCE_GATE.get());

        dropOther(PrismaBlocks.PRISMA_SIGN.get(), PrismaItems.PRISMA_SIGN);
        dropOther(PrismaBlocks.PRISMA_WALL_SIGN.get(), PrismaItems.PRISMA_SIGN);
        dropOther(PrismaBlocks.PRISMA_HANGING_SIGN.get(), PrismaItems.PRISMA_HANGING_SIGN);
        dropOther(PrismaBlocks.PRISMA_WALL_HANGING_SIGN.get(), PrismaItems.PRISMA_HANGING_SIGN);
        dropSelf(PrismaBlocks.PRISMA_SAPLING.get());
        dropPottedContents(PrismaBlocks.POTTED_PRISMA_SAPLING.get());

        dropSelf(PrismaBlocks.AUSTRALITE_LANTERN.get());
        dropSelf(PrismaBlocks.BOREALITE_LANTERN.get());

        this.add(PrismaBlocks.PRISMA_SLAB.get(),
                block -> createSlabItemTable(PrismaBlocks.PRISMA_SLAB.get()));
        this.add(PrismaBlocks.PRISMA_DOOR.get(),
                block -> createDoorTable(PrismaBlocks.PRISMA_DOOR.get()));
    }

    protected LootTable.Builder createCrystalDrops(Block block, ItemLike crystal){
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(crystal)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                ));
    }

    protected void dropOtherWithoutSilkTouch(Block block, ItemLike other){
        this.add(block, b -> this.createSingleItemTableWithSilkTouch(b, other));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return PrismaBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
