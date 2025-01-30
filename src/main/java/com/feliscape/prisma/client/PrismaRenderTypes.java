package com.feliscape.prisma.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.TriState;

import java.util.function.BiFunction;
import java.util.function.Function;

import static net.minecraft.client.renderer.RenderStateShard.*;


public class PrismaRenderTypes {
    public static final BiFunction<ResourceLocation, DepthTestStateShard, RenderType> OPAQUE_ALWAYS_VISIBLE_PARTICLE = Util.memoize(
            (location, stateShard) -> RenderType.create(
                    "opaque_always_visible_particle",
                    DefaultVertexFormat.PARTICLE,
                    VertexFormat.Mode.QUADS,
                    1536,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setDepthTestState(stateShard)
                            .setShaderState(PARTICLE_SHADER)
                            .setTextureState(new RenderStateShard.TextureStateShard(location, TriState.FALSE, false))
                            .setLightmapState(RenderStateShard.LIGHTMAP)
                            .setWriteMaskState(COLOR_WRITE)
                            .createCompositeState(false)
            )
    );
    public static final Function<ResourceLocation, RenderType> AURORA = Util.memoize(
            (location) -> RenderType.create(
                    "aurora",
                    DefaultVertexFormat.POSITION_TEX,
                    VertexFormat.Mode.QUADS,
                    1536,
                    false,
                    false,
                    RenderType.CompositeState.builder()
                            .setShaderState(PrismaRenderStateShards.AURORA_SHADER)
                            .setTextureState(new RenderStateShard.TextureStateShard(location, TriState.FALSE, false))
                            .setLightmapState(RenderStateShard.LIGHTMAP)
                            .setWriteMaskState(COLOR_WRITE)
                            .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                            .createCompositeState(false)
            )
    );

    public static RenderType alwaysVisibleParticle(ResourceLocation location) {
        return OPAQUE_ALWAYS_VISIBLE_PARTICLE.apply(location, NO_DEPTH_TEST);
    }
    public static RenderType aurora(ResourceLocation location) {
        return AURORA.apply(location);
    }


    /*public static final RenderType AURORA = RenderType.create(
            "aurora",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            4194304,
            true,
            false,
            auroraState(AuroraRenderStateShards.RENDERTYPE_AURORA_SHADER)
    );

    private static RenderType.CompositeState auroraState(RenderStateShard.ShaderStateShard state) {
        return RenderType.CompositeState.builder()
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setShaderState(state)
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setOutputState(RenderStateShard.TRANSLUCENT_TARGET)
                .createCompositeState(true);
    }*/
}
