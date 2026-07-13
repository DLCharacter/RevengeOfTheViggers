package com.savagevegetables.outbreak.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import net.minecraft.util.RandomSource;
import java.util.function.Supplier;

public class MimicCropBlock extends CropBlock {

    private final Supplier<EntityType<?>> mimicEntitySupplier;

    public MimicCropBlock(Properties properties, Supplier<EntityType<?>> mimicEntitySupplier) {
        super(properties);
        this.mimicEntitySupplier = mimicEntitySupplier;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);
        if (entity instanceof Player) {
            checkForPlayers(level, pos, state);
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);
        if (entity instanceof Player) {
            checkForPlayers(level, pos, state);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        checkForPlayers(level, pos, state);
        level.scheduleTick(pos, this, 10);
    }

    public void checkForPlayers(Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide && this.isMaxAge(state)) {
            AABB area = new AABB(pos).inflate(5.0);
            List<Player> players = level.getEntitiesOfClass(Player.class, area);
            if (!players.isEmpty()) {
                triggerMimic(level, pos);
            }
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, 10);
    }

    private void triggerMimic(Level level, BlockPos pos) {
        Entity mimic = mimicEntitySupplier.get().create(level);
        if (mimic != null) {
            mimic.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            level.addFreshEntity(mimic);
            level.destroyBlock(pos, false);
            // TODO: Play sound and particles
        }
    }
}
