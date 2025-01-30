package com.feliscape.prisma.data.datagen;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.data.datagen.advancement.PrismaAdvancements;
import com.feliscape.prisma.data.datagen.language.PrismaLanguageProvider;
import com.feliscape.prisma.data.datagen.loot.PrismaBlockLootTableProvider;
import com.feliscape.prisma.data.datagen.model.PrismaBlockModelProvider;
import com.feliscape.prisma.data.datagen.model.PrismaItemModelProvider;
import com.feliscape.prisma.data.datagen.model.PrismaModelProvider;
import com.feliscape.prisma.data.datagen.recipe.PrismaRecipeProvider;
import com.feliscape.prisma.data.datagen.tag.PrismaBlockTagGenerator;
import com.feliscape.prisma.data.datagen.tag.PrismaItemTagGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Prisma.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();



        PrismaGeneratedEntries generatedEntries = new PrismaGeneratedEntries(packOutput, lookupProvider);
        lookupProvider = generatedEntries.getRegistryProvider();
        generator.addProvider(true, generatedEntries);

        generator.addProvider(true, new PrismaRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(true, new PrismaAdvancements(packOutput, lookupProvider));

        var blockTags = new PrismaBlockTagGenerator(packOutput, lookupProvider);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new PrismaItemTagGenerator(packOutput, lookupProvider, blockTags.contentsGetter()));

        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(PrismaBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));


        generator.addProvider(true, new PrismaModelProvider(packOutput));

        generator.addProvider(true, new PrismaLanguageProvider(packOutput, "en_us"));
    }
    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        PrismaGeneratedEntries generatedEntries = new PrismaGeneratedEntries(packOutput, lookupProvider);
        lookupProvider = generatedEntries.getRegistryProvider();
        generator.addProvider(true, generatedEntries);

        generator.addProvider(true, new PrismaRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(true, new PrismaAdvancements(packOutput, lookupProvider));

        var blockTags = new PrismaBlockTagGenerator(packOutput, lookupProvider);
        generator.addProvider(true, blockTags);
        generator.addProvider(true, new PrismaItemTagGenerator(packOutput, lookupProvider, blockTags.contentsGetter()));

        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(PrismaBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
    }
}
