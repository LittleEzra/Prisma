package com.feliscape.prisma.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class PrismaConsumables {
    public static final Consumable CRYSTAL_MELON_SLICE = Consumable.builder()
            .consumeSeconds(3.2F)
            .sound(PrismaSounds.EAT_CRYSTAL_MELON)
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(PrismaMobEffects.CLAIRVOYANCE, 20 * 60)
            ))
            .build();
}
