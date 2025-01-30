package com.feliscape.prisma.client.renderer;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.client.PrismaRenderTypes;
import com.feliscape.prisma.util.ColorUtil;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class AuroraRenderer {
  private static final ResourceLocation TEXTURE = Prisma.asResource("textures/environment/aurora.png");

  public void render(MultiBufferSource bufferSource, float partialTick, Vec3 cameraPosition){
    Prisma.LOGGER.debug("Trying to render Aurora");
    VertexConsumer buffer = bufferSource.getBuffer(PrismaRenderTypes.aurora(TEXTURE));
    int white = ColorUtil.getIntColor("#ffffff");
    float cameraX = (float) cameraPosition.x;
    float cameraY = (float) cameraPosition.y;
    float cameraZ = (float) cameraPosition.z;
    buffer.addVertex(-96F - cameraX, 256F - cameraY, 0F - cameraZ).setColor(white).setUv(0.0F, 1.0F);
    buffer.addVertex(96F - cameraX,  256F - cameraY, 0F - cameraZ).setColor(white).setUv(1.0F, 1.0F);
    buffer.addVertex(96F - cameraX,  320F - cameraY, 0F - cameraZ).setColor(white).setUv(1.0F, 0.0F);
    buffer.addVertex(-96F - cameraX, 320F - cameraY, 0F - cameraZ).setColor(white).setUv(0.0F, 0.0F);
  }
}
