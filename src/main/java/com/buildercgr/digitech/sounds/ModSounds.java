package com.buildercgr.digitech.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, "digitech");

    public static final Supplier<SoundEvent> COMPUTER_SOUND_EVENT =
            SOUNDS.register("pc_sound",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath("digitech", "pc_boot")));

    public static SoundType computerSoundType() {
        return new SoundType(1.0F, 1.0F,
                COMPUTER_SOUND_EVENT.get(),
                COMPUTER_SOUND_EVENT.get(),
                COMPUTER_SOUND_EVENT.get(),
                COMPUTER_SOUND_EVENT.get(),
                COMPUTER_SOUND_EVENT.get());
    }

}
