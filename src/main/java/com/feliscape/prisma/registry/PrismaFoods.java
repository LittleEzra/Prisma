package com.feliscape.prisma.registry;

import net.minecraft.world.food.FoodProperties;

public class PrismaFoods {
    public static final FoodProperties CRYSTAL_MELON_SLICE = new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(4)
            .saturationModifier(3.0F)
            .build();
}
