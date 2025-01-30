package com.feliscape.prisma.registry;

import com.feliscape.prisma.Prisma;
import net.minecraft.world.level.block.state.properties.WoodType;

public class PrismaWoodTypes {
    public static final WoodType PRISMA = WoodType.register(new WoodType(Prisma.asResource("prisma").toString(), PrismaBlockSetTypes.PRISMA));
}
