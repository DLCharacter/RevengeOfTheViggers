package com.savagevegetables.outbreak.init;

import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SavageVegetablesOutbreak.MODID);

    public static final RegistryObject<SoundEvent> WAVE_SPAWN = SOUNDS.register("wave_spawn",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SavageVegetablesOutbreak.MODID, "wave_spawn")));
}
