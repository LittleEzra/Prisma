package com.feliscape.prisma.data.datagen.advancement;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PrismaAdvancements extends AdvancementProvider {
    public PrismaAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new PrismaAdventureAdvancements()));
    }
}
