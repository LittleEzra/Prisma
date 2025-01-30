package com.feliscape.prisma.data.datagen.model;

import com.feliscape.prisma.Prisma;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;

import java.util.Optional;

public class PrismaModelTemplates {
    public static final ModelTemplate CRYSTAL_LANTERN = new ModelTemplate(
            Optional.of(Prisma.asResource("block/crystal_lantern_template")),
            Optional.empty(),
            TextureSlot.ALL
    );
    public static final ModelTemplate HANGING_CRYSTAL_LANTERN = new ModelTemplate(
            Optional.of(Prisma.asResource("block/hanging_crystal_lantern_template")),
            Optional.of("_hanging"),
            TextureSlot.ALL
    );
}
