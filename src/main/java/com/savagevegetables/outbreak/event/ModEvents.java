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
            if ((cow.getTags().contains("exploding_cow") || cow.getTags().contains("exploding_cow_zucchini") || cow.getTags().contains("exploding_cow_eggplant"))) {
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
        if (event.getEntity() instanceof Cow cow) {
            Level level = cow.level();
            boolean isEggplantCow = cow.getTags().contains("exploding_cow_eggplant");
            boolean isZucchiniCow = cow.getTags().contains("exploding_cow_zucchini");

            if (isEggplantCow || isZucchiniCow) {
                // Maintain velocity for eggplant cows since air resistance slows them down
                if (isEggplantCow && cow.getPersistentData().contains("flyX")) {
                    double fX = cow.getPersistentData().getDouble("flyX");
                    double fY = cow.getPersistentData().getDouble("flyY");
                    double fZ = cow.getPersistentData().getDouble("flyZ");
                    cow.setDeltaMovement(fX, fY, fZ);
                }

                                if (!level.isClientSide && cow.tickCount > 5) {
                    // Check entity collisions
                    java.util.List<net.minecraft.world.entity.player.Player> players = level.getEntitiesOfClass(net.minecraft.world.entity.player.Player.class, cow.getBoundingBox().inflate(0.5D));
                    boolean hitPlayer = false;
                    for (net.minecraft.world.entity.player.Player p : players) {
                        if (!p.isSpectator() && !p.isCreative()) {
                            hitPlayer = true;
                            break;
                        }
                    }

                    // If it flies horizontally (NoGravity tag applied), explode if it hits a wall
                    if (hitPlayer || cow.horizontalCollision || (isEggplantCow && cow.verticalCollision)) {
                        level.explode(cow, cow.getX(), cow.getY(), cow.getZ(), 3.0F, Level.ExplosionInteraction.MOB);
                        cow.discard();
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEat(net.minecraftforge.event.entity.living.LivingEntityUseItemEvent.Finish event) {
        if (!event.getEntity().level().isClientSide && event.getEntity() instanceof net.minecraft.world.entity.player.Player player) {
            net.minecraft.world.item.ItemStack item = event.getItem();
            if (item.isEdible() && item.getItem().getFoodProperties() != null) {
                // Check if it's a vegetable (either our custom progression food or a vanilla vegetable)
                boolean isVeggie = item.getItem() instanceof com.savagevegetables.outbreak.item.custom.ProgressionFoodItem ||
                                   item.getItem() == net.minecraft.world.item.Items.CARROT ||
                                   item.getItem() == net.minecraft.world.item.Items.POTATO ||
                                   item.getItem() == net.minecraft.world.item.Items.BAKED_POTATO ||
                                   item.getItem() == net.minecraft.world.item.Items.BEETROOT;

                if (isVeggie) {
                    com.savagevegetables.outbreak.world.OutbreakSavedData data = com.savagevegetables.outbreak.world.OutbreakSavedData.get((net.minecraft.server.level.ServerLevel) player.level());
                    int previousStage = data.getCurrentStage();
                    data.addVeggieEaten();
                    int newStage = data.getCurrentStage();

                    if (newStage > previousStage) {
                        player.displayClientMessage(net.minecraft.network.chat.Component.translatable("message.savage_vegetables.stage_up", newStage), false);
                    }
                }
            }
        }
    }
}