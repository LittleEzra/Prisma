package com.feliscape.prisma.client;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.client.renderer.AuroraRenderer;
import com.feliscape.prisma.util.ColorGradient;
import com.feliscape.prisma.util.ColorUtil;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

public class PrismaClient {
    private static PrismaClient instance;
    private final ColorGradient auroraGradient = ColorGradient.create(
            new ColorGradient.Node(ColorUtil.getVec3Color("#eb6594"), 0.0F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#7672c9"), 0.25F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#5af966"), 0.5F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#7672c9"), 0.75F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#eb6594"), 1.0F)
    );
    private final ColorGradient auroraLeavesGradient = ColorGradient.create(
            new ColorGradient.Node(ColorUtil.getVec3Color("#eb6594"), 0.0F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#948fee"), 0.25F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#5eeb6a"), 0.5F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#948fee"), 0.75F),
            new ColorGradient.Node(ColorUtil.getVec3Color("#eb6594"), 1.0F)
    );

    private final AuroraRenderer auroraRenderer = new AuroraRenderer();

    public PrismaClient(RegisterClientReloadListenersEvent event){
        instance = this;
        Prisma.LOGGER.info("AuroraClient instantiated");
    }

    public static PrismaClient getInstance() {
        return instance;
    }

    public AuroraRenderer getAuroraRenderer(){
        return auroraRenderer;
    }
    public ColorGradient getAuroraGradient(){
        return auroraGradient;
    }
    public ColorGradient getAuroraLeavesGradient(){
        return auroraLeavesGradient;
    }
}
