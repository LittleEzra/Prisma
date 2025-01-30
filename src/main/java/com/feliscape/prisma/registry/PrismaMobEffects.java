package com.feliscape.prisma.registry;

import com.feliscape.prisma.Prisma;
import com.feliscape.prisma.content.mobeffect.ClairvoyanceMobEffect;
import com.feliscape.prisma.util.ColorUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PrismaMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Prisma.MOD_ID);

    public static final Holder<MobEffect> CLAIRVOYANCE = MOB_EFFECTS.register("clairvoyance",
            () -> new ClairvoyanceMobEffect(MobEffectCategory.BENEFICIAL, ColorUtil.getIntColor("#eb6594")));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
