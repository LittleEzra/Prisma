package com.feliscape.prisma.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class ShineParticle extends TextureSheetParticle {
    private final float rotSpeed;
    private final SpriteSet sprites;

    protected ShineParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed,
            float r, float g, float b, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ);
        this.setParticleSpeed(pXSpeed * 0.2D, pYSpeed * 0.2D, pZSpeed * 0.2D);
        this.lifetime = 20 + this.random.nextInt(10);
        this.sprites = pSprites;
        this.rotSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.setSpriteFromAge(pSprites);
        this.setColor(r, g, b);

        this.scale(0.5F);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime) {
            this.setSpriteFromAge(this.sprites);

            this.oRoll = this.roll;
            this.roll += (float)Math.PI * this.rotSpeed * 2.0F;

            this.move(this.xd, this.yd, this.zd);
        } else {
            this.remove();
        }
    }


    @Override
    public int getLightColor(float partialTick) {
        float f = ((float)this.age + partialTick) / (float)this.lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(partialTick);
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        j += (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return PrismaParticleRenderTypes.PARTICLE_SHEET_OPAQUE_ALWAYS_VISIBLE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class ClairvoyanceProvider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprites;

        public ClairvoyanceProvider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(ColorParticleOption pOption, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new ShineParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, pOption.getRed(), pOption.getGreen(), pOption.getBlue(), this.sprites);
        }
    }
}
