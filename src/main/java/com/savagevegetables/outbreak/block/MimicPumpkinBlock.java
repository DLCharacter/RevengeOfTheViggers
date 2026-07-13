package com.savagevegetables.outbreak.block;

import com.savagevegetables.outbreak.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class MimicPumpkinBlock extends Block {
    public MimicPumpkinBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);
        if (entity instanceof Player) {
            checkForPlayers(level, pos);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        checkForPlayers(level, pos);
        level.scheduleTick(pos, this, 10);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        checkForPlayers(level, pos);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, 10);
    }

    public void checkForPlayers(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            AABB area = new AABB(pos).inflate(5.0);
            List<Player> players = level.getEntitiesOfClass(Player.class, area);
            if (!players.isEmpty()) {
                triggerMimic(level, pos);
            }
        }
    }

    private void triggerMimic(Level level, BlockPos pos) {
        Entity mimic = EntityInit.PUMPKIN_ENTITY.get().create(level);
        if (mimic != null) {
            mimic.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            level.addFreshEntity(mimic);
            level.destroyBlock(pos, false);
            // TODO: Play sound and particles
        }
    }
}
