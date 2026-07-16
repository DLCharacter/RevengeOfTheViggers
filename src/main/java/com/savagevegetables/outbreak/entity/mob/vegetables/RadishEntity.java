package com.savagevegetables.outbreak.entity.mob.vegetables;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import java.util.EnumSet;
import net.minecraft.world.phys.Vec3;
import net.minecraft.tags.DamageTypeTags;

public class RadishEntity extends VegetableEntity {
    private int chargeTime = 0;
    private boolean isCharging = false;
    private boolean isDashing = false;

    public RadishEntity(EntityType<? extends VegetableEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RadishChargeAttackGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            if (isDashing) {
                if (chargeTime > 10 && this.onGround()) {
                    // Create a massive explosion when landing after dashing
                    this.level().explode(this, this.getX(), this.getY(), this.getZ(), 8.0F, Level.ExplosionInteraction.MOB);
                    isDashing = false;
                    chargeTime = 0;
                } else if (this.horizontalCollision) {
                    // Explode on impact with a wall too
                    this.level().explode(this, this.getX(), this.getY(), this.getZ(), 8.0F, Level.ExplosionInteraction.MOB);
                    isDashing = false;
                    chargeTime = 0;
                } else {
                    chargeTime++;
                }
            }
        }
    }

    @Override
    public boolean hurt(net.minecraft.world.damagesource.DamageSource source, float amount) {
        if (source.is(DamageTypeTags.IS_EXPLOSION)) {
            return false;
        }

        if (isCharging) {
            isCharging = false;
            chargeTime = 0;
        }
        return super.hurt(source, amount);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D); // Vision range increased
    }

    class RadishChargeAttackGoal extends Goal {
        private final RadishEntity radish;
        private LivingEntity target;
        private Vec3 lastKnownTargetPos;

        public RadishChargeAttackGoal(RadishEntity radish) {
            this.radish = radish;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.radish.getTarget();
            if (target == null || !target.isAlive() || radish.isDashing) {
                return false;
            }
            // Engage range matching 64 blocks
            return this.radish.distanceToSqr(target) > 16.0D && this.radish.distanceToSqr(target) < 4096.0D;
        }

        @Override
        public boolean canContinueToUse() {
            // Keep going even if the target is lost (target == null or dead),
            // as long as we are still winding up the charge
            return radish.isCharging;
        }

        @Override
        public void start() {
            this.target = this.radish.getTarget();
            this.lastKnownTargetPos = target.position();
            radish.isCharging = true;
            radish.chargeTime = 0;
            radish.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (radish.isCharging) {
                // If target is still tracked, update prediction. Otherwise rely on last known.
                if (target != null && target.isAlive() && radish.hasLineOfSight(target)) {
                    // Predict future location based on target velocity
                    Vec3 targetVel = target.getDeltaMovement();

                    double dx = target.getX() - radish.getX();
                    double dz = target.getZ() - radish.getZ();
                    double dist = Math.sqrt(dx * dx + dz * dz);

                    // Flight time scales with distance
                    double estimatedTicksToLand = dist * 1.5D;

                    lastKnownTargetPos = target.position().add(targetVel.scale(estimatedTicksToLand));
                    radish.getLookControl().setLookAt(target, 30.0F, 30.0F);
                } else {
                    radish.getLookControl().setLookAt(lastKnownTargetPos.x, lastKnownTargetPos.y, lastKnownTargetPos.z, 30.0F, 30.0F);
                }

                radish.chargeTime++;

                if (radish.chargeTime % 20 == 0) {
                    radish.playSound(net.minecraft.sounds.SoundEvents.GHAST_WARN, 1.0F, 1.0F);
                }

                if (radish.chargeTime >= 200) { // 10 seconds (20 ticks/sec * 10)
                    radish.isCharging = false;
                    radish.isDashing = true;
                    radish.chargeTime = 0;

                    double dx = lastKnownTargetPos.x - radish.getX();
                    double dz = lastKnownTargetPos.z - radish.getZ();
                    double dist = Math.sqrt(dx * dx + dz * dz);

                    // Parabolic jump logic scaling better for long distances
                    // Need more horizontal force to cover long distances quickly
                    double jumpVelocityY = 1.0D + (dist * 0.08D);
                    double horizontalVelocity = 0.5D + (dist * 0.05D);

                    radish.setDeltaMovement(dx / dist * horizontalVelocity, jumpVelocityY, dz / dist * horizontalVelocity);
                    radish.playSound(net.minecraft.sounds.SoundEvents.ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                }
            }
        }
    }
}
