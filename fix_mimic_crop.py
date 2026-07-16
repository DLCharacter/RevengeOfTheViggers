with open('src/main/java/com/savagevegetables/outbreak/block/MimicCropBlock.java', 'r') as f:
    content = f.read()

correct_code = """package com.savagevegetables.outbreak.block;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.registries.RegistryObject;
import com.savagevegetables.outbreak.world.OutbreakSavedData;
import net.minecraft.server.level.ServerLevel;
import com.savagevegetables.outbreak.command.ModCommands;

public class MimicCropBlock extends CropBlock {

    private final RegistryObject<? extends EntityType<? extends Mob>> entityType;

    public MimicCropBlock(Properties properties, RegistryObject<? extends EntityType<? extends Mob>> entityType) {
        super(properties);
        this.entityType = entityType;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, net.minecraft.util.RandomSource random) {
        super.randomTick(state, level, pos, random);

        if (this.isMaxAge(state)) {
            net.minecraft.world.entity.player.Player player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5.0D, false);
            if (player != null) {
                // Determine if we should reveal a mimic based on proximity and random chance checked on approach
                double chance = 0.0;
                if (ModCommands.overrideMimicChance >= 0) {
                    chance = ModCommands.overrideMimicChance;
                } else {
                    OutbreakSavedData data = OutbreakSavedData.get(level);
                    int stage = data.getCurrentStage();
                    if (stage >= 7) chance = 1.0;
                    else if (stage >= 6) chance = 0.50;
                    else if (stage >= 4) chance = 0.25;
                    else if (stage >= 2) chance = 0.10;
                }

                // Roll chance to trigger mimic on random tick when player is close
                if (level.random.nextDouble() < chance) {
                    revealMimic(level, pos, state);
                } else {
                    // Turn it into a regular crop visually or just break it
                    level.destroyBlock(pos, true);
                }
            }
        }
    }

    private void revealMimic(ServerLevel level, BlockPos pos, BlockState state) {
        level.destroyBlock(pos, false);
        level.playSound(null, pos, net.minecraft.sounds.SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, net.minecraft.sounds.SoundSource.BLOCKS, 1.0f, 1.0f);

        Mob entity = entityType.get().create(level);
        if (entity != null) {
            entity.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            level.addFreshEntity(entity);
        }
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return net.minecraft.world.item.Items.WHEAT_SEEDS;
    }
}
"""

with open('src/main/java/com/savagevegetables/outbreak/block/MimicCropBlock.java', 'w') as f:
    f.write(correct_code)
