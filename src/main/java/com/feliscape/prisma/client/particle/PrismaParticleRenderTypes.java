package com.feliscape.prisma.client.particle;

import com.feliscape.prisma.client.PrismaRenderTypes;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;

public class PrismaParticleRenderTypes {
    public static final ParticleRenderType PARTICLE_SHEET_OPAQUE_ALWAYS_VISIBLE = new ParticleRenderType(
            "PARTICLE_SHEET_OPAQUE_ALWAYS_VISIBLE", PrismaRenderTypes.alwaysVisibleParticle(TextureAtlas.LOCATION_PARTICLES), false
    );
}
