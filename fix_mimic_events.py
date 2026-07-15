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
import net.minecraftforge.event.TickEvent;
import net.minecraft.server.level.ServerPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.resources.ResourceKey;

@Mod.EventBusSubscriber(modid = SavageVegetablesOutbreak.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    // Map of Dimension -> (Map of BlockPos -> Ticks active near player)
    private static final Map<ResourceKey<Level>, Map<BlockPos, Integer>> trackingMimics = new HashMap<>();
    private static final Map<ResourceKey<Level>, Set<BlockPos>> checkedMimics = new HashMap<>();

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
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.getServer().getTickCount() % 10 == 0) {
            for (net.minecraft.server.level.ServerLevel level : event.getServer().getAllLevels()) {
                ResourceKey<Level> dimension = level.dimension();

                trackingMimics.putIfAbsent(dimension, new HashMap<>());
                checkedMimics.putIfAbsent(dimension, new HashSet<>());

                Map<BlockPos, Integer> dimTracking = trackingMimics.get(dimension);
                Set<BlockPos> dimChecked = checkedMimics.get(dimension);

                Set<BlockPos> currentlyNearBlocks = new HashSet<>();

                for (ServerPlayer player : level.players()) {
                    if (player.isSpectator() || player.isCreative()) continue;

                    BlockPos pPos = player.blockPosition();

                    for (int x = -5; x <= 5; x++) {
                        for (int y = -2; y <= 2; y++) {
                            for (int z = -5; z <= 5; z++) {
                                BlockPos checkPos = pPos.offset(x, y, z);
                                if (dimChecked.contains(checkPos)) continue;

                                net.minecraft.world.level.block.state.BlockState state = level.getBlockState(checkPos);
                                boolean isTargetCrop = false;

                                if (state.getBlock() instanceof com.savagevegetables.outbreak.block.MimicCropBlock crop && crop.isMaxAge(state)) {
                                    isTargetCrop = true;
                                } else if (state.getBlock() instanceof CropBlock crop && crop.isMaxAge(state)) {
                                    if (state.is(Blocks.CARROTS) || state.is(Blocks.POTATOES)) {
                                        isTargetCrop = true;
                                    }
                                } else if (state.is(Blocks.PUMPKIN)) {
                                    isTargetCrop = true;
                                }

                                if (isTargetCrop) {
                                    currentlyNearBlocks.add(checkPos);

                                    int ticks = dimTracking.getOrDefault(checkPos, 0) + 10;
                                    dimTracking.put(checkPos, ticks);

                                    if (ticks >= 100) { // 5 seconds
                                        triggerMimicRoll(level, checkPos, state);
                                        dimTracking.remove(checkPos);
                                        dimChecked.add(checkPos);
                                    }
                                }
                            }
                        }
                    }
                }

                // Cleanup blocks that are no longer near any player
                dimTracking.keySet().removeIf(pos -> !currentlyNearBlocks.contains(pos));
            }
        }
    }

    private static void triggerMimicRoll(net.minecraft.server.level.ServerLevel level, BlockPos pos, net.minecraft.world.level.block.state.BlockState state) {
        double chance = 0.0;
        if (ModCommands.overrideMimicChance >= 0) {
            chance = ModCommands.overrideMimicChance;
        } else {
            com.savagevegetables.outbreak.world.OutbreakSavedData data = com.savagevegetables.outbreak.world.OutbreakSavedData.get(level);
            int stage = data.getCurrentStage();
            if (stage >= 7) chance = 1.0;
            else if (stage >= 6) chance = 0.50;
            else if (stage >= 4) chance = 0.25;
            else if (stage >= 2) chance = 0.10;
        }

        if (level.random.nextDouble() < chance) {
            Mob mimicEntity = null;

            if (state.getBlock() instanceof com.savagevegetables.outbreak.block.MimicCropBlock mimicCrop) {
                mimicEntity = mimicCrop.getEntityType().get().create(level);
            } else if (state.is(Blocks.CARROTS)) {
                mimicEntity = EntityInit.CARROT_ENTITY.get().create(level);
            } else if (state.is(Blocks.POTATOES)) {
                mimicEntity = EntityInit.POTATO_ENTITY.get().create(level);
            } else if (state.is(Blocks.PUMPKIN)) {
                mimicEntity = EntityInit.PUMPKIN_ENTITY.get().create(level);
            }

            if (mimicEntity != null) {
                level.destroyBlock(pos, false);
                level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, net.minecraft.sounds.SoundSource.BLOCKS, 1.0f, 1.0f);

                mimicEntity.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                level.addFreshEntity(mimicEntity);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!event.getLevel().isClientSide() && event.getLevel() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            BlockPos pos = event.getPos();
            ResourceKey<Level> dim = serverLevel.dimension();

            // Clean up tracking when a block is broken
            if (trackingMimics.containsKey(dim)) trackingMimics.get(dim).remove(pos);
            if (checkedMimics.containsKey(dim)) checkedMimics.get(dim).remove(pos);
        }
    }
}
"""

with open('src/main/java/com/savagevegetables/outbreak/event/ModEvents.java', 'w') as f:
    f.write(correct_code)
