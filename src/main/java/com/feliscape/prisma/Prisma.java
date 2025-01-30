package com.feliscape.prisma;

import com.feliscape.prisma.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Prisma.MOD_ID)
public class Prisma
{
    public static final String MOD_ID = "prisma";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Prisma(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        PrismaBlockEntityTypes.register(modEventBus);
        PrismaBlocks.register(modEventBus);
        PrismaItems.register(modEventBus);
        PrismaSounds.register(modEventBus);
        PrismaMobEffects.register(modEventBus);
        PrismaParticleTypes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation asResource(String path){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(PrismaBlocks.PRISMA_LOG);
            event.accept(PrismaBlocks.PRISMA_WOOD);
            event.accept(PrismaBlocks.STRIPPED_PRISMA_LOG);
            event.accept(PrismaBlocks.STRIPPED_PRISMA_WOOD);
            event.accept(PrismaBlocks.PRISMA_PLANKS);
            event.accept(PrismaBlocks.PRISMA_STAIRS);
            event.accept(PrismaBlocks.PRISMA_SLAB);
            event.accept(PrismaBlocks.PRISMA_FENCE);
            event.accept(PrismaBlocks.PRISMA_FENCE_GATE);
            event.accept(PrismaBlocks.PRISMA_DOOR);
            event.accept(PrismaBlocks.PRISMA_TRAPDOOR);
            event.accept(PrismaBlocks.PRISMA_PRESSURE_PLATE);
            event.accept(PrismaBlocks.PRISMA_BUTTON);
            event.accept(PrismaBlocks.AUSTRALITE_BLOCK);
            event.accept(PrismaBlocks.BOREALITE_BLOCK);
            event.accept(PrismaBlocks.AUSTRALITE_LANTERN);
            event.accept(PrismaBlocks.BOREALITE_LANTERN);
        } else if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(PrismaItems.PRISMA_SIGN);
            event.accept(PrismaItems.PRISMA_HANGING_SIGN);
        } else if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS){
            for (var block : PrismaBlocks.RUGGED_WOOL_BY_COLOR.get().values()){
                event.accept(block.asItem());
            }
        } else if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(PrismaBlocks.PRISMA_SAPLING);
            event.accept(PrismaBlocks.PRISMA_LEAVES);
        } else if (event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(PrismaItems.AUSTRALITE_CRYSTAL);
            event.accept(PrismaItems.BOREALITE_CRYSTAL);
        } else if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(PrismaItems.CRYSTAL_MELON_SLICE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }
}
