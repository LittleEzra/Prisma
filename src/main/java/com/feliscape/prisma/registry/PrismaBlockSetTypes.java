package com.feliscape.prisma.registry;

import com.feliscape.prisma.Prisma;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class PrismaBlockSetTypes {
    public static final BlockSetType PRISMA = BlockSetType.register(new BlockSetType(Prisma.asResource("prisma").toString()));
}
