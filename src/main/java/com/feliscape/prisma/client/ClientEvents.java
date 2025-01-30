package com.feliscape.prisma.client;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.client.particle.ShineParticle;
import com.feliscape.prisma.registry.PrismaBlockEntityTypes;
import com.feliscape.prisma.registry.PrismaBlocks;
import com.feliscape.prisma.registry.PrismaMobEffects;
import com.feliscape.prisma.registry.PrismaParticleTypes;
import com.feliscape.prisma.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ShaderProgram;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import org.joml.Vector3f;

import java.util.List;

public class ClientEvents {
    @EventBusSubscriber(modid = Prisma.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(PrismaBlockEntityTypes.MOD_SIGN.get(), SignRenderer::new);
            event.registerBlockEntityRenderer(PrismaBlockEntityTypes.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }

        @SubscribeEvent
        public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event)
        {
            new PrismaClient(event);
        }
        @SubscribeEvent
        public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event)
        {
            event.registerSpriteSet(PrismaParticleTypes.CLAIRVOYANCE.get(), ShineParticle.ClairvoyanceProvider::new);
        }

        @SubscribeEvent
        public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event){
            event.register((state, tintGetter, blockPos, tintIndex) -> {
                if (blockPos == null) return ColorUtil.getIntColor("#eb6594");

                int x = blockPos.getX();
                int y = blockPos.getY();
                int z = blockPos.getZ();

                float f = Mth.sin((float)x / 32.0F);
                f += (Mth.sin((float)y / 24.0F) + (float)y * 0.125F) * 0.5F;
                f += Mth.sin((float)z / 32.0F) * 0.5F;

                return ColorUtil.getIntFromVec3f(PrismaClient.getInstance().getAuroraLeavesGradient()
                        .sampleColor(f % 1F));
            }, PrismaBlocks.PRISMA_LEAVES.get());
        }

        @SubscribeEvent
        public static void registerItemTintSources(RegisterColorHandlersEvent.ItemTintSources event){

        }

        @SubscribeEvent
        public static void registerShaders(RegisterShadersEvent event){
            for (ShaderProgram shader : PrismaShaders.getProgramsToPreload()) {
                event.registerShader(shader);
            }
        }
    }
    @EventBusSubscriber(modid = Prisma.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
    public static class ClientGameEvents
    {
        @SubscribeEvent
        public static void afterClientTick(ClientTickEvent.Post event)
        {
            Player player = Minecraft.getInstance().player;
            if (player != null && player.hasEffect(PrismaMobEffects.CLAIRVOYANCE)){
                Level level = player.level();
                List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(12.0D));
                for (LivingEntity entity : entities){
                    if (entity != player && !entity.isDeadOrDying() && !entity.isInvisibleTo(player) && level.random.nextInt(3) > 0){
                        float bbWidth = entity.getBbWidth();
                        float bbHeight = entity.getBbHeight();
                        double x = bbWidth *  player.getRandom().nextFloat() - bbWidth / 2.0F + entity.getX();
                        double y = bbHeight * player.getRandom().nextFloat() + entity.getY();
                        double z = bbWidth *  player.getRandom().nextFloat() - bbWidth / 2.0F + entity.getZ();
                        Vector3f color = PrismaClient.getInstance().getAuroraGradient().sampleColor(level.getGameTime() * 0.01F % 1.0F);
                        level.addParticle(ColorParticleOption.create(PrismaParticleTypes.CLAIRVOYANCE.get(), color.x, color.y, color.z),
                                x, y, z, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
        @SubscribeEvent
        public static void renderLevelStage(RenderLevelStageEvent event)
        {
            if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_WEATHER){
                PrismaClient.getInstance().getAuroraRenderer().render(
                        Minecraft.getInstance().renderBuffers().bufferSource(),
                        event.getPartialTick().getGameTimeDeltaPartialTick(true),
                        event.getCamera().getPosition());
            }
        }
    }
}
