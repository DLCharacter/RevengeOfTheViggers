package com.savagevegetables.outbreak.block;

import com.savagevegetables.outbreak.init.BlockInit;
import com.savagevegetables.outbreak.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

public class BossCropBlock extends Block {

    private final RegistryObject<? extends EntityType<? extends Mob>> bossEntity;

    public BossCropBlock(Properties properties, RegistryObject<? extends EntityType<? extends Mob>> bossEntity) {
        super(properties.noCollission());
        this.bossEntity = bossEntity;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemInHand = player.getItemInHand(hand);

        if (itemInHand.is(ItemInit.MUTAGENIC_FERTILIZER.get())) {
            if (!level.isClientSide()) {
                // Check if soil is 3x3 of MUTATED_SOIL
                if (isValidSoilStructure(level, pos.below())) {
                    level.destroyBlock(pos, false);
                    Mob boss = bossEntity.get().create(level);
                    if (boss != null) {
                        boss.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                        level.addFreshEntity(boss);
                    }
                    if (!player.getAbilities().instabuild) {
                        itemInHand.shrink(1);
                    }
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    private boolean isValidSoilStructure(Level level, BlockPos centerPos) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (!level.getBlockState(centerPos.offset(x, 0, z)).is(BlockInit.MUTATED_SOIL.get())) {
                    return false;
                }
            }
        }
        return true;
    }
}
