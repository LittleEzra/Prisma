package com.feliscape.prisma.registry;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.content.block.*;
import com.feliscape.prisma.data.worldgen.tree.PrismaTreeGrowers;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.datafixers.types.Func;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class PrismaBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Prisma.MOD_ID);

    public static DeferredBlock<CrystalBlock> AUSTRALITE_BLOCK = registerBlockWithItem("australite_block",
            b -> new CrystalBlock(b.lightLevel(state -> 10).mapColor(DyeColor.PINK).strength(1.5F).sound(SoundType.AMETHYST))); // TODO: Custom Sound
    public static DeferredBlock<CrystalBlock> BOREALITE_BLOCK = registerBlockWithItem("borealite_block",
            b -> new CrystalBlock(b.lightLevel(state -> 10).mapColor(DyeColor.LIME).strength(1.5F).sound(SoundType.AMETHYST))); // TODO: Custom Sound

    public static DeferredBlock<LeavesBlock> PRISMA_LEAVES = registerBlockWithItem("prisma_leaves",
            b -> new LeavesBlock(leavesProperties(b.lightLevel(state -> 7), SoundType.GRASS)));

    public static final DeferredBlock<PrismaLogBlock> PRISMA_LOG = registerBlockWithItem("prisma_log",
            b -> new PrismaLogBlock(logProperties(b, MapColor.SNOW, MapColor.STONE, SoundType.WOOD)));
    public static final DeferredBlock<PrismaLogBlock> STRIPPED_PRISMA_LOG = registerBlockWithItem("stripped_prisma_log",
            b -> new PrismaLogBlock(logProperties(b, MapColor.SNOW, MapColor.SNOW, SoundType.WOOD)));
    public static final DeferredBlock<PrismaLogBlock> PRISMA_WOOD = registerBlockWithItem("prisma_wood",
            b -> new PrismaLogBlock(logProperties(b, MapColor.STONE, MapColor.STONE, SoundType.WOOD)));
    public static final DeferredBlock<PrismaLogBlock> STRIPPED_PRISMA_WOOD = registerBlockWithItem("stripped_prisma_wood",
            b -> new PrismaLogBlock(logProperties(b, MapColor.SNOW, MapColor.SNOW, SoundType.WOOD)));

    public static final DeferredBlock<FlammableBlock> PRISMA_PLANKS = registerBlockWithItem("prisma_planks",
            b -> new FlammableBlock(b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)));

    public static final DeferredBlock<FlammableStairBlock> PRISMA_STAIRS = registerBlockWithItem("prisma_stairs",
            b -> flammableStair(b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD),
                    PRISMA_PLANKS.get()));
    public static final DeferredBlock<FlammableSlabBlock> PRISMA_SLAB = registerBlockWithItem("prisma_slab",
            b -> new FlammableSlabBlock(b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
            ));
    public static final DeferredBlock<FlammableFenceBlock> PRISMA_FENCE = registerBlockWithItem("prisma_fence",
            b -> new FlammableFenceBlock(b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
            ));
    public static final DeferredBlock<FlammableFenceGateBlock> PRISMA_FENCE_GATE = registerBlockWithItem("prisma_fence_gate",
            b -> new FlammableFenceGateBlock(PrismaWoodTypes.PRISMA, b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<ButtonBlock> PRISMA_BUTTON = registerBlockWithItem("prisma_button",
            b -> new ButtonBlock(PrismaBlockSetTypes.PRISMA, 30, buttonProperties(b)));
    public static final DeferredBlock<PressurePlateBlock> PRISMA_PRESSURE_PLATE = registerBlockWithItem("prisma_pressure_plate",
            b -> new PressurePlateBlock(PrismaBlockSetTypes.PRISMA, b.mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .forceSolidOn()
                    .strength(0.5F)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<DoorBlock> PRISMA_DOOR = registerBlockWithItem("prisma_door",
            b -> new DoorBlock(PrismaBlockSetTypes.PRISMA, b
                    .mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<TrapDoorBlock> PRISMA_TRAPDOOR = registerBlockWithItem("prisma_trapdoor",
            b -> new TrapDoorBlock(PrismaBlockSetTypes.PRISMA, b
                    .mapColor(MapColor.SNOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<ModStandingSignBlock> PRISMA_SIGN = BLOCKS.registerBlock("prisma_sign",
            b -> new ModStandingSignBlock(PrismaWoodTypes.PRISMA,
                    b.mapColor(PRISMA_PLANKS.get().defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollission()
                            .strength(1.0F)
                            .ignitedByLava()
            ));
    public static final DeferredBlock<ModWallSignBlock> PRISMA_WALL_SIGN = BLOCKS.registerBlock("prisma_wall_sign",
            b -> new ModWallSignBlock(PrismaWoodTypes.PRISMA,
                    b.mapColor(PRISMA_PLANKS.get().defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollission()
                            .strength(1.0F)
                            .ignitedByLava()
            ));

    public static final DeferredBlock<ModHangingSignBlock> PRISMA_HANGING_SIGN = BLOCKS.registerBlock("prisma_hanging_sign",
            b -> new ModHangingSignBlock(PrismaWoodTypes.PRISMA,
                    b.mapColor(PRISMA_PLANKS.get().defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollission()
                            .strength(1.0F)
                            .ignitedByLava()
            ));
    public static final DeferredBlock<ModWallHangingSignBlock> PRISMA_WALL_HANGING_SIGN = BLOCKS.registerBlock("prisma_wall_hanging_sign",
            b -> new ModWallHangingSignBlock(PrismaWoodTypes.PRISMA,
                    b.mapColor(PRISMA_PLANKS.get().defaultMapColor())
                            .forceSolidOn()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollission()
                            .strength(1.0F)
                            .ignitedByLava()
            ));
    public static final DeferredBlock<SaplingBlock> PRISMA_SAPLING = registerBlockWithItem("prisma_sapling",
            b -> new SaplingBlock(PrismaTreeGrowers.PRISMA,
                    b.mapColor(PRISMA_PLANKS.get().defaultMapColor())
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredBlock<FlowerPotBlock> POTTED_PRISMA_SAPLING = BLOCKS.registerBlock("potted_prisma_sapling",
            b -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PRISMA_SAPLING,
                    b
                            .instabreak()
                            .noOcclusion()
                            .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<CrystalLanternBlock> AUSTRALITE_LANTERN = registerBlockWithItem("australite_lantern",
            b -> new CrystalLanternBlock(b
                    .mapColor(DyeColor.PINK)
                    .forceSolidOn()
                    .strength(0.5F)
                    .instabreak()
                    .sound(SoundType.COPPER)
                    .lightLevel(state -> 12)
                    .noOcclusion()
                    .noCollission()
                    .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredBlock<CrystalLanternBlock> BOREALITE_LANTERN = registerBlockWithItem("borealite_lantern",
            b -> new CrystalLanternBlock(b
                    .mapColor(DyeColor.LIME)
                    .forceSolidOn()
                    .strength(0.5F)
                    .instabreak()
                    .sound(SoundType.COPPER)
                    .lightLevel(state -> 12)
                    .noOcclusion()
                    .noCollission()
                    .pushReaction(PushReaction.DESTROY)
            ));



    public static DeferredBlock<Block> WHITE_RUGGED_WOOL = registerBlockWithItem("white_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.SNOW))); // TODO: Custom Sound
    public static DeferredBlock<Block> LIGHT_GRAY_RUGGED_WOOL = registerBlockWithItem("light_gray_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_LIGHT_GRAY)));
    public static DeferredBlock<Block> GRAY_RUGGED_WOOL = registerBlockWithItem("gray_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_GRAY)));
    public static DeferredBlock<Block> BLACK_RUGGED_WOOL = registerBlockWithItem("black_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_BLACK)));
    public static DeferredBlock<Block> BROWN_RUGGED_WOOL = registerBlockWithItem("brown_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_BROWN)));
    public static DeferredBlock<Block> RED_RUGGED_WOOL = registerBlockWithItem("red_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_RED)));
    public static DeferredBlock<Block> ORANGE_RUGGED_WOOL = registerBlockWithItem("orange_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_ORANGE)));
    public static DeferredBlock<Block> YELLOW_RUGGED_WOOL = registerBlockWithItem("yellow_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_YELLOW)));
    public static DeferredBlock<Block> LIME_RUGGED_WOOL = registerBlockWithItem("lime_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_LIGHT_GREEN)));
    public static DeferredBlock<Block> GREEN_RUGGED_WOOL = registerBlockWithItem("green_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_GREEN)));
    public static DeferredBlock<Block> CYAN_RUGGED_WOOL = registerBlockWithItem("cyan_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_CYAN)));
    public static DeferredBlock<Block> LIGHT_BLUE_RUGGED_WOOL = registerBlockWithItem("light_blue_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_LIGHT_BLUE)));
    public static DeferredBlock<Block> BLUE_RUGGED_WOOL = registerBlockWithItem("blue_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_BLUE)));
    public static DeferredBlock<Block> PURPLE_RUGGED_WOOL = registerBlockWithItem("purple_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_PURPLE)));
    public static DeferredBlock<Block> MAGENTA_RUGGED_WOOL = registerBlockWithItem("magenta_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_MAGENTA)));
    public static DeferredBlock<Block> PINK_RUGGED_WOOL = registerBlockWithItem("pink_rugged_wool",
            b -> new Block(ruggedWoolProperties(b, MapColor.COLOR_PINK)));


    public static final Supplier<ImmutableMap<DyeColor, DeferredBlock<Block>>> RUGGED_WOOL_BY_COLOR = Suppliers.memoize(
            () -> ImmutableMap.<DyeColor, DeferredBlock<Block>>builder()
            .put(DyeColor.WHITE,        WHITE_RUGGED_WOOL)
            .put(DyeColor.LIGHT_GRAY,   LIGHT_GRAY_RUGGED_WOOL)
            .put(DyeColor.GRAY,         GRAY_RUGGED_WOOL)
            .put(DyeColor.BLACK,        BLACK_RUGGED_WOOL)
            .put(DyeColor.BROWN,        BROWN_RUGGED_WOOL)
            .put(DyeColor.RED,          RED_RUGGED_WOOL)
            .put(DyeColor.ORANGE,       ORANGE_RUGGED_WOOL)
            .put(DyeColor.YELLOW,       YELLOW_RUGGED_WOOL)
            .put(DyeColor.LIME,         LIME_RUGGED_WOOL)
            .put(DyeColor.GREEN,        GREEN_RUGGED_WOOL)
            .put(DyeColor.CYAN,         CYAN_RUGGED_WOOL)
            .put(DyeColor.LIGHT_BLUE,   LIGHT_BLUE_RUGGED_WOOL)
            .put(DyeColor.BLUE,         BLUE_RUGGED_WOOL)
            .put(DyeColor.PURPLE,       PURPLE_RUGGED_WOOL)
            .put(DyeColor.MAGENTA,      MAGENTA_RUGGED_WOOL)
            .put(DyeColor.PINK,         PINK_RUGGED_WOOL)
                    .build()
    );

    public static final List<DyeColor> VANILLA_COLORS = List.of(
            DyeColor.WHITE,
            DyeColor.LIGHT_GRAY,
            DyeColor.GRAY,
            DyeColor.BLACK,
            DyeColor.BROWN,
            DyeColor.RED,
            DyeColor.ORANGE,
            DyeColor.YELLOW,
            DyeColor.LIME,
            DyeColor.GREEN,
            DyeColor.CYAN,
            DyeColor.LIGHT_BLUE,
            DyeColor.BLUE,
            DyeColor.PURPLE,
            DyeColor.MAGENTA,
            DyeColor.PINK
    );

    private static BlockBehaviour.Properties ruggedWoolProperties(BlockBehaviour.Properties properties, MapColor color) {
        return properties
                .mapColor(color)
                .instrument(NoteBlockInstrument.GUITAR)
                .strength(1.2F).ignitedByLava()
                .sound(SoundType.WOOL);
    }
    private static BlockBehaviour.Properties leavesProperties(BlockBehaviour.Properties properties, SoundType sound) {
        return properties
                .mapColor(MapColor.PLANT)
                .strength(0.2F)
                .randomTicks()
                .sound(sound)
                .noOcclusion()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(PrismaBlocks::never)
                .isViewBlocking(PrismaBlocks::never)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(PrismaBlocks::never);
    }

    private static BlockBehaviour.Properties logProperties(BlockBehaviour.Properties properties, MapColor topColor, MapColor sideColor, SoundType soundType) {
        return properties
                .mapColor(blockState -> blockState.getValue(PrismaLogBlock.AXIS) == Direction.Axis.Y ? topColor : sideColor)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F)
                .sound(soundType)
                .ignitedByLava();
    }

    private static BlockBehaviour.Properties buttonProperties(BlockBehaviour.Properties properties) {
        return properties.noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
    }

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return true;
    }

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }
    public static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Function<BlockBehaviour.Properties, ? extends T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block)
    {
        return PrismaItems.ITEMS.registerItem(name, p -> new BlockItem(block.get(), p));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private static StairBlock stair(BlockBehaviour.Properties properties, Block baseBlock) {
        return new StairBlock(baseBlock.defaultBlockState(), properties);
    }
    private static FlammableStairBlock flammableStair(BlockBehaviour.Properties properties, Block baseBlock) {
        return new FlammableStairBlock(baseBlock.defaultBlockState(), properties);
    }
}
