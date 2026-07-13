package com.savagevegetables.outbreak.world.wave;

import com.savagevegetables.outbreak.init.EntityInit;
import com.savagevegetables.outbreak.world.OutbreakSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class WaveManager {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            for (ServerLevel level : event.getServer().getAllLevels()) {
                if (level.dimension() == net.minecraft.world.level.Level.OVERWORLD) {
                    if (level.getDayTime() % 24000 == 13000) {
                        // Trigger at the start of night (time 13000) based on world time
                        long day = level.getDayTime() / 24000L;
                        boolean isNewMoon = (level.getMoonPhase() == 4); // 4 is new moon

                        OutbreakSavedData data = OutbreakSavedData.get(level);
                        boolean stage2 = data.isStage2Active();

                        for (ServerPlayer player : level.players()) {
                            if (isNewMoon) {
                                BlockPos respawnPos = player.getRespawnPosition();
                                if (respawnPos != null && player.distanceToSqr(respawnPos.getX(), respawnPos.getY(), respawnPos.getZ()) < 2500) {
                                    // Spawns massive wave only if near bed/base (50 blocks radius)
                                    spawnWave(level, player, stage2, true);
                                }
                            } else {
                                spawnWave(level, player, stage2, false);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void spawnWave(ServerLevel level, ServerPlayer player, boolean stage2, boolean isNewMoon) {
        int mobCount = isNewMoon ? 15 : 3;
        if (stage2) mobCount += 5;

        List<EntityType<? extends Mob>> pool = new ArrayList<>();
        pool.add(EntityInit.PUMPKIN_ENTITY.get());
        pool.add(EntityInit.TOMATO_ENTITY.get());
        pool.add(EntityInit.CORN_ENTITY.get());

        if (stage2) {
            pool.add(EntityInit.HYBRID_ENTITY.get());
            if (isNewMoon) {
                pool.add(EntityInit.EGGPLANT_ENTITY.get());
                pool.add(EntityInit.ZUCCHINI_ENTITY.get());
            }
        }

        for (int i = 0; i < mobCount; i++) {
            EntityType<? extends Mob> type = pool.get(RANDOM.nextInt(pool.size()));

            // Spawn in a radius around the player
            double angle = RANDOM.nextDouble() * 2 * Math.PI;
            double distance = 15 + RANDOM.nextDouble() * 10; // 15 to 25 blocks away
            int x = (int) (player.getX() + distance * Math.cos(angle));
            int z = (int) (player.getZ() + distance * Math.sin(angle));
            int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);

            BlockPos spawnPos = new BlockPos(x, y, z);

            Mob entity = type.create(level);
            if (entity != null) {
                entity.setPos(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5);
                entity.finalizeSpawn(level, level.getCurrentDifficultyAt(spawnPos), MobSpawnType.EVENT, null, null);
                level.addFreshEntity(entity);
                entity.setTarget(player);
            }
        }
    }
}
