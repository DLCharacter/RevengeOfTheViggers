package com.savagevegetables.outbreak.entity.mob.vegetables;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;

public class CucumberEntity extends VegetableEntity {
    private int spitCooldown = 0;

    public CucumberEntity(EntityType<? extends VegetableEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effectInstance) {
        if (effectInstance.getEffect() == MobEffects.POISON) {
            return false;
        }
        return super.canBeAffected(effectInstance);
    }

    @Override
    public void tick() {
        super.tick();
        if (spitCooldown > 0) {
            spitCooldown--;
        }

        if (!this.level().isClientSide && this.isAlive() && this.getTarget() != null) {
            LivingEntity target = this.getTarget();
            if (this.distanceToSqr(target) < 36.0D && spitCooldown <= 0 && this.hasLineOfSight(target)) {
                // Spit AoE cloud
                AreaEffectCloud cloud = new AreaEffectCloud(this.level(), target.getX(), target.getY(), target.getZ());
                cloud.setOwner(this);
                cloud.setParticle(net.minecraft.core.particles.ParticleTypes.ITEM_SLIME);
                cloud.setRadius(3.0F);
                cloud.setRadiusOnUse(-0.5F);
                cloud.setWaitTime(10);
                cloud.setRadiusPerTick(-cloud.getRadius() / (float)cloud.getDuration());
                cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));

                this.level().addFreshEntity(cloud);
                this.playSound(net.minecraft.sounds.SoundEvents.LLAMA_SPIT, 1.0F, 1.0F);

                spitCooldown = 100; // 5 seconds
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }
}
