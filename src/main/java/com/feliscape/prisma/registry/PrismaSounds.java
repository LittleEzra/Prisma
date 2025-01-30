package com.feliscape.prisma.registry;

import com.feliscape.prisma.Prisma;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PrismaSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Prisma.MOD_ID);

    public static final Holder<SoundEvent> EAT_CRYSTAL_MELON = registerSoundEvent("prisma.eat_crystal_melon");

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name)
    {
        return SOUND_EVENTS.register(name, SoundEvent::createVariableRangeEvent);
    }
    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name, float pRange)
    {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createFixedRangeEvent(Prisma.asResource(name), pRange));
    }
}
