package com.savagevegetables.outbreak.event;

import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import com.savagevegetables.outbreak.init.BlockInit;
import com.savagevegetables.outbreak.world.OutbreakSavedData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SavageVegetablesOutbreak.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MimicEventHandler {

    @SubscribeEvent
    public static void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        Level level = (Level) event.getLevel();
        if (level.isClientSide) return;

        BlockPos pos = event.getPos();
        BlockState state = event.getState();
        Block block = state.getBlock();

        if (block instanceof CropBlock cropBlock && cropBlock.isMaxAge(state)) {
            tryReplaceWithMimic((ServerLevel) level, pos, block);
        } else if (block == Blocks.PUMPKIN) {
            tryReplaceWithMimic((ServerLevel) level, pos, block);
        }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        Level level = (Level) event.getLevel();
        if (level.isClientSide) return;

        BlockPos pos = event.getPos();
        BlockState state = event.getPlacedBlock();
        Block block = state.getBlock();

        if (block == Blocks.PUMPKIN || block == Blocks.CARROTS || block == Blocks.POTATOES) {
            tryReplaceWithMimic((ServerLevel) level, pos, block);
        }
    }

    private static void tryReplaceWithMimic(ServerLevel level, BlockPos pos, Block block) {
        OutbreakSavedData data = OutbreakSavedData.get(level);
        int stage = data.getCurrentStage();

        double chance = 0.0;
        if (stage >= 2 && stage <= 3) {
            chance = 0.10;
        } else if (stage == 4 || stage == 5) {
            chance = 0.25;
        } else if (stage == 6) {
            chance = 0.50;
        } else if (stage >= 7) {
            chance = 1.0;
        }

        if (chance > 0 && level.random.nextDouble() < chance) {
            Block mimicBlock = getMimicForBlock(block);
            if (mimicBlock != null) {
                // Transfer age for crops if possible
                BlockState newState = mimicBlock.defaultBlockState();
                if (mimicBlock instanceof CropBlock mimicCrop && block instanceof CropBlock) {
                    newState = mimicCrop.getStateForAge(mimicCrop.getMaxAge());
                }
                level.setBlock(pos, newState, 3);
            }
        }
    }

    private static Block getMimicForBlock(Block block) {
        if (block == Blocks.PUMPKIN) return BlockInit.MIMIC_PUMPKIN.get();
        if (block == BlockInit.TOMATO_CROP.get()) return BlockInit.MIMIC_TOMATO_CROP.get();
        if (block == BlockInit.CORN_CROP.get()) return BlockInit.MIMIC_CORN_CROP.get();
        if (block == BlockInit.EGGPLANT_CROP.get()) return BlockInit.MIMIC_EGGPLANT_CROP.get();
        if (block == BlockInit.ZUCCHINI_CROP.get()) return BlockInit.MIMIC_ZUCCHINI_CROP.get();
        if (block == BlockInit.CUCUMBER_CROP.get()) return BlockInit.MIMIC_CUCUMBER_CROP.get();
        if (block == BlockInit.BROCCOLI_CROP.get()) return BlockInit.MIMIC_BROCCOLI_CROP.get();
        if (block == BlockInit.RADISH_CROP.get()) return BlockInit.MIMIC_RADISH_CROP.get();
        if (block == Blocks.CARROTS) return BlockInit.MIMIC_CARROT_CROP.get();
        if (block == Blocks.POTATOES) return BlockInit.MIMIC_POTATO_CROP.get();
        return null;
    }
}
