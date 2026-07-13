package com.savagevegetables.outbreak.item.custom;

import com.savagevegetables.outbreak.world.OutbreakSavedData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

public class ProgressionFoodItem extends Item {
    public ProgressionFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof Player player) {
            OutbreakSavedData data = OutbreakSavedData.get((ServerLevel) level);
            int previousStage = data.getCurrentStage();
            data.addVeggieEaten();
            int newStage = data.getCurrentStage();

            if (newStage > previousStage) {
                player.displayClientMessage(Component.translatable("message.savage_vegetables.stage_up", newStage), false);
            }
        }

        return result;
    }
}
