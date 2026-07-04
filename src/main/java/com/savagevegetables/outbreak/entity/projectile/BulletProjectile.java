package com.savagevegetables.outbreak.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import com.savagevegetables.outbreak.init.ItemInit;

public class BulletProjectile extends AbstractArrow {

    public BulletProjectile(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.setBaseDamage(5.0D); // Custom damage
    }

    public BulletProjectile(Level level, LivingEntity shooter) {
        super(com.savagevegetables.outbreak.init.EntityInit.BULLET_PROJECTILE.get(), shooter, level);
        this.setBaseDamage(5.0D);
    }

    public BulletProjectile(Level level, double x, double y, double z) {
        super(com.savagevegetables.outbreak.init.EntityInit.BULLET_PROJECTILE.get(), x, y, z, level);
        this.setBaseDamage(5.0D);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.FIREARM_AMMO.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        // Add extra hit effects if needed
    }
}
