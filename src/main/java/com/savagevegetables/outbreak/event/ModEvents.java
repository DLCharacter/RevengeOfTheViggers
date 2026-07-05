package com.savagevegetables.outbreak.event;

import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.savagevegetables.outbreak.SavageVegetablesOutbreak;

@Mod.EventBusSubscriber(modid = SavageVegetablesOutbreak.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onCowFall(LivingFallEvent event) {
        if (event.getEntity() instanceof Cow cow) {
            if (cow.getTags().contains("exploding_cow")) {
                if (!cow.level().isClientSide) {
                    cow.level().explode(cow, cow.getX(), cow.getY(), cow.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
                    cow.discard();
                }
                event.setCanceled(true); // Cancel standard fall damage
            }
        }
    }

    @SubscribeEvent
    public static void onCowTick(net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Cow cow && cow.getTags().contains("exploding_cow")) {
            Level level = cow.level();
            if (!level.isClientSide) {
                // If it flies horizontally (NoGravity tag applied), explode if it hits a wall
                if (cow.horizontalCollision || cow.verticalCollision) {
                    level.explode(cow, cow.getX(), cow.getY(), cow.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
                    cow.discard();
                }
            }
        }
    }
}
