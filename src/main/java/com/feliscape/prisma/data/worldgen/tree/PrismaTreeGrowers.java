package com.feliscape.prisma.data.worldgen.tree;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.data.worldgen.feature.PrismaTreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class PrismaTreeGrowers {
    public static final TreeGrower PRISMA =
            new TreeGrower(Prisma.asResource("prisma").toString(),
                    Optional.empty(),
                    Optional.of(PrismaTreeFeatures.PRISMA_TREE),
                    Optional.empty());

}
