package com.feliscape.prisma.registry;

import com.feliscape.prisma.Prisma;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PrismaItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Prisma.MOD_ID);

    public static final DeferredItem<Item> AUSTRALITE_CRYSTAL = ITEMS.registerItem("australite_crystal", Item::new);
    public static final DeferredItem<Item> BOREALITE_CRYSTAL = ITEMS.registerItem("borealite_crystal", Item::new);
    public static final DeferredItem<Item> PRISMATIC_DUST = ITEMS.registerItem("prismatic_dust", Item::new);

    public static final DeferredItem<Item> CRYSTAL_MELON_SLICE = ITEMS.registerItem("crystal_melon_slice",
            p -> new Item(p.food(PrismaFoods.CRYSTAL_MELON_SLICE, PrismaConsumables.CRYSTAL_MELON_SLICE)));
    public static final DeferredItem<SignItem> PRISMA_SIGN = ITEMS.registerItem("prisma_sign",
            p -> new SignItem(PrismaBlocks.PRISMA_SIGN.get(), PrismaBlocks.PRISMA_WALL_SIGN.get(), p.stacksTo(16)));
    public static final DeferredItem<HangingSignItem> PRISMA_HANGING_SIGN = ITEMS.registerItem("prisma_hanging_sign",
            p -> new HangingSignItem(PrismaBlocks.PRISMA_HANGING_SIGN.get(), PrismaBlocks.PRISMA_WALL_HANGING_SIGN.get(), p.stacksTo(16)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
