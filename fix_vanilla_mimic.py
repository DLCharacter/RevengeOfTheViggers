with open('src/main/java/com/savagevegetables/outbreak/event/ModEvents.java', 'r') as f:
    content = f.read()

correct_code = """package com.savagevegetables.outbreak.event;

import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.entity.Mob;
import net.minecraft.core.BlockPos;
import com.savagevegetables.outbreak.command.ModCommands;
import com.savagevegetables.outbreak.init.EntityInit;

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

                boolean isBossFruit = item.getItem() instanceof com.savagevegetables.outbreak.item.custom.BossFruitItem;

                if (isBossFruit) {
                    com.savagevegetables.outbreak.world.OutbreakSavedData data = com.savagevegetables.outbreak.world.OutbreakSavedData.get((net.minecraft.server.level.ServerLevel) player.level());
                    data.addBossFruitEaten();
                } else {
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

    @SubscribeEvent
    public static void onCommandsRegister(net.minecraftforge.event.RegisterCommandsEvent event) {
        com.savagevegetables.outbreak.command.ModCommands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!event.getLevel().isClientSide() && event.getLevel() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            BlockPos pos = event.getPos();
            net.minecraft.world.level.block.state.BlockState state = event.getState();

            boolean isFullyGrownVanillaCrop = false;
            Mob mimicEntity = null;

            if (state.getBlock() instanceof CropBlock crop && crop.isMaxAge(state)) {
                if (state.is(Blocks.CARROTS)) {
                    isFullyGrownVanillaCrop = true;
                    mimicEntity = EntityInit.CARROT_ENTITY.get().create(serverLevel);
                } else if (state.is(Blocks.POTATOES)) {
                    isFullyGrownVanillaCrop = true;
                    mimicEntity = EntityInit.POTATO_ENTITY.get().create(serverLevel);
                }
            } else if (state.is(Blocks.PUMPKIN)) {
                isFullyGrownVanillaCrop = true;
                mimicEntity = EntityInit.PUMPKIN_ENTITY.get().create(serverLevel);
            }

            if (isFullyGrownVanillaCrop && mimicEntity != null) {
                // Check mimic chance
                double chance = 0.0;
                if (ModCommands.overrideMimicChance >= 0) {
                    chance = ModCommands.overrideMimicChance;
                } else {
                    com.savagevegetables.outbreak.world.OutbreakSavedData data = com.savagevegetables.outbreak.world.OutbreakSavedData.get(serverLevel);
                    int stage = data.getCurrentStage();
                    if (stage >= 7) chance = 1.0;
                    else if (stage >= 6) chance = 0.50;
                    else if (stage >= 4) chance = 0.25;
                    else if (stage >= 2) chance = 0.10;
                }

                if (serverLevel.random.nextDouble() < chance) {
                    serverLevel.playSound(null, pos, net.minecraft.sounds.SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, net.minecraft.sounds.SoundSource.BLOCKS, 1.0f, 1.0f);

                    mimicEntity.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                    serverLevel.addFreshEntity(mimicEntity);

                    // Allow the block to break naturally, but don't drop items
                    event.getPlayer().level().destroyBlock(pos, false);
                    event.setCanceled(true); // Stop standard block break (so it doesn't drop carrots)
                }
            }
        }
    }
}
"""

with open('src/main/java/com/savagevegetables/outbreak/event/ModEvents.java', 'w') as f:
    f.write(correct_code)
