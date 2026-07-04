package com.savagevegetables.outbreak.item.custom;

import com.savagevegetables.outbreak.entity.projectile.VeggieProjectile;
import com.savagevegetables.outbreak.init.ItemInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.UseAnim;

import java.util.function.Predicate;

public class VeggieWeaponItem extends BowItem {

    public VeggieWeaponItem(Properties properties) {
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(ItemInit.VEGGIE_AMMO.get());
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            boolean hasAmmo = player.getAbilities().instabuild || player.getInventory().contains(new ItemStack(ItemInit.VEGGIE_AMMO.get()));

            if (hasAmmo) {
                if (!level.isClientSide) {
                    VeggieProjectile veggie = new VeggieProjectile(level, player);
                    veggie.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);
                    veggie.pickup = AbstractArrow.Pickup.DISALLOWED;

                    stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
                    level.addFreshEntity(veggie);
                }

                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SLIME_SQUISH, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);
                if (!player.getAbilities().instabuild) {
                    player.getInventory().clearOrCountMatchingItems(p -> p.is(ItemInit.VEGGIE_AMMO.get()), 1, player.inventoryMenu.getCraftSlots());
                }
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean hasAmmo = player.getAbilities().instabuild || player.getInventory().contains(new ItemStack(ItemInit.VEGGIE_AMMO.get()));

        if (!player.getAbilities().instabuild && !hasAmmo) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
}
