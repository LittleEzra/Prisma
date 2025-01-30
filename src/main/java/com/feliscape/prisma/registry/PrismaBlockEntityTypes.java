package com.feliscape.prisma.registry;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.content.block.entity.ModHangingSignBlockEntity;
import com.feliscape.prisma.content.block.entity.ModSignBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class PrismaBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Prisma.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITY_TYPES.register("mod_sign", () -> new BlockEntityType<>(ModSignBlockEntity::new,
                    Set.of(PrismaBlocks.PRISMA_SIGN.get(), PrismaBlocks.PRISMA_WALL_SIGN.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITY_TYPES.register("mod_hanging_sign", () -> new BlockEntityType<>(ModHangingSignBlockEntity::new,
                    Set.of(PrismaBlocks.PRISMA_HANGING_SIGN.get(), PrismaBlocks.PRISMA_WALL_HANGING_SIGN.get())));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
