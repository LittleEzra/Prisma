package com.feliscape.prisma.registry;

import com.feliscape.prisma.Prisma;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PrismaTags {
    public static class Blocks{
        public static final TagKey<Block> PRISMA_LOGS = create("prisma_logs");
        public static final TagKey<Block> RUGGED_WOOL = create("rugged_wool");

        private static TagKey<Block> create(String name){
            return TagKey.create(Registries.BLOCK, Prisma.asResource(name));
        }
    }
    public static class Items{
        public static final TagKey<Item> RUGGED_WOOL = create("rugged_wool");
        public static final TagKey<Item> PRISMA_LOGS = create("prisma_logs");
        public static final TagKey<Item> AURORA_CRYSTALS = create("aurora_crystals");

        private static TagKey<Item> create(String name){
            return TagKey.create(Registries.ITEM, Prisma.asResource(name));
        }
    }
}
