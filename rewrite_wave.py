import re

with open('src/main/java/com/savagevegetables/outbreak/world/wave/WaveManager.java', 'r') as f:
    content = f.read()

# Add night spawning loop
night_spawn = """
                    if (level.getDayTime() % 24000 == 13000) {
                        // Trigger at the start of night (time 13000) based on world time
                        long day = level.getDayTime() / 24000L;
                        boolean isNewMoon = (level.getMoonPhase() == 4); // 4 is new moon

                        OutbreakSavedData data = OutbreakSavedData.get(level);
                        int stage = data.getCurrentStage();
                        if (stage > 0) {
                            for (ServerPlayer player : level.players()) {
                                if (isNewMoon) {
                                    BlockPos respawnPos = player.getRespawnPosition();
                                    if (respawnPos != null && player.distanceToSqr(respawnPos.getX(), respawnPos.getY(), respawnPos.getZ()) < 2500) {
                                        // Spawns massive wave only if near bed/base (50 blocks radius)
                                        spawnWave(level, player, stage, true);
                                    }
                                } else {
                                    spawnWave(level, player, stage, false);
                                }
                            }
                        }
                    }

                    OutbreakSavedData data = OutbreakSavedData.get(level);
                    int stage = data.getCurrentStage();
                    if (stage >= 6 && level.isNight() && level.getGameTime() % 400 == 0) {
                        for (ServerPlayer player : level.players()) {
                            if (RANDOM.nextDouble() < 0.3) {
                                spawnWave(level, player, stage, false);
                            }
                        }
                    }"""

content = re.sub(r'if \(level\.getDayTime\(\) % 24000 == 13000\) \{.*?\}', night_spawn, content, flags=re.DOTALL)

# Modify spawnWave signature and logic
spawn_wave_code = """
    private static void spawnWave(ServerLevel level, ServerPlayer player, int stage, boolean isNewMoon) {
        int mobCount = isNewMoon ? 15 : 3;
        if (stage >= 2) mobCount += 2;
        if (stage >= 4) mobCount += 2;
        if (stage >= 5) mobCount += 3;

        List<EntityType<? extends Mob>> pool = new ArrayList<>();
        pool.add(EntityInit.PUMPKIN_ENTITY.get());
        pool.add(EntityInit.TOMATO_ENTITY.get());

        if (stage >= 2) {
            pool.add(EntityInit.CORN_ENTITY.get());
            pool.add(EntityInit.HYBRID_ENTITY.get());
            if (isNewMoon) {
                pool.add(EntityInit.EGGPLANT_ENTITY.get());
                pool.add(EntityInit.ZUCCHINI_ENTITY.get());
            }
        }

        if (stage >= 7) {
            pool.add(EntityInit.JERUSALEM_ARTICHOKE_ENTITY.get());
            pool.add(EntityInit.RUTABAGA_ENTITY.get());
            pool.add(EntityInit.CELERY_ENTITY.get());
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
"""

content = re.sub(r'private static void spawnWave.*?\}[\s\n]*\}[\s\n]*\}', spawn_wave_code, content, flags=re.DOTALL)

with open('src/main/java/com/savagevegetables/outbreak/world/wave/WaveManager.java', 'w') as f:
    f.write(content)
