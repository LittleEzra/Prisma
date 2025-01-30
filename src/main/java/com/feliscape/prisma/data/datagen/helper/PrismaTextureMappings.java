package com.feliscape.prisma.data.datagen.helper;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class PrismaTextureMappings {

    public static TextureMapping simpleStairs(Block block){
        return new TextureMapping()
                .put(TextureSlot.BOTTOM, getBlockTexture(block))
                .put(TextureSlot.SIDE, getBlockTexture(block))
                .put(TextureSlot.TOP, getBlockTexture(block));
    }


    public static ResourceLocation getBlockTexture(Block block) {
        ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourcelocation.withPrefix("block/");
    }

    public static ResourceLocation getBlockTexture(Block block, String suffix) {
        ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourcelocation.withPath(s -> "block/" + s + suffix);
    }
}
