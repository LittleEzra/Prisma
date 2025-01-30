package com.feliscape.prisma.data.datagen.model;

import com.feliscape.prisma.Prisma;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

public class PrismaModelProvider extends ModelProvider {
    public PrismaModelProvider(PackOutput output) {
        super(output, Prisma.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        PrismaBlockModelProvider.registerModels(blockModels);
        PrismaItemModelProvider.registerModels(itemModels);
    }
}
