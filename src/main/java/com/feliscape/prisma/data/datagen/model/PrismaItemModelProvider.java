package com.feliscape.prisma.data.datagen.model;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

import static com.feliscape.prisma.registry.PrismaItems.*;

public class PrismaItemModelProvider {

    public static void registerModels(ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(AUSTRALITE_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(BOREALITE_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(CRYSTAL_MELON_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(PRISMATIC_DUST.get(), ModelTemplates.FLAT_ITEM);
    }
}
