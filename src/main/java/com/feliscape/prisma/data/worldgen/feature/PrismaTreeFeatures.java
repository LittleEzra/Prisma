package com.feliscape.prisma.data.worldgen.feature;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.registry.PrismaBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

import java.util.OptionalInt;

public class PrismaTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PRISMA_TREE = createKey("prisma_tree");

    private static TreeConfiguration.TreeConfigurationBuilder createAuroraTree() {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(PrismaBlocks.PRISMA_LOG.get()),
                new FancyTrunkPlacer(5, 11, 0),
                BlockStateProvider.simple(PrismaBlocks.PRISMA_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(2),
                        ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, PRISMA_TREE, Feature.TREE, createAuroraTree().build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Prisma.asResource(name));
    }
}
