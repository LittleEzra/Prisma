package com.feliscape.prisma.data.datagen;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.data.worldgen.feature.PrismaTreeFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class PrismaGeneratedEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, PrismaTreeFeatures::bootstrap)
            //.add(Registries.PLACED_FEATURE, DeepwoodPlacedFeatures::bootstrap)
            //.add(ForgeRegistries.Keys.BIOME_MODIFIERS, DeepwoodBiomeModifiers::bootstrap)
            //.add(Registries.DAMAGE_TYPE, DeepwoodDamageTypes::bootstrap)
            //.add(Registries.NOISE, DeepwoodNoise::bootstrap)
            ;
    public PrismaGeneratedEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Prisma.MOD_ID));
    }
}
