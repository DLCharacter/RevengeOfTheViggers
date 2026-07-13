package com.savagevegetables.outbreak.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import com.savagevegetables.outbreak.init.ItemInit;

public class VeggieProjectile extends AbstractArrow {

    public VeggieProjectile(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.setBaseDamage(3.0D); // Custom damage
    }

    public VeggieProjectile(Level level, LivingEntity shooter) {
        super(com.savagevegetables.outbreak.init.EntityInit.VEGGIE_PROJECTILE.get(), shooter, level);
        this.setBaseDamage(3.0D);
    }

    public VeggieProjectile(Level level, double x, double y, double z) {
        super(com.savagevegetables.outbreak.init.EntityInit.VEGGIE_PROJECTILE.get(), x, y, z, level);
        this.setBaseDamage(3.0D);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.VEGGIE_AMMO.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        // Add extra hit effects if needed
    }
}
