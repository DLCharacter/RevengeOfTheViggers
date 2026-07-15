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
                // Break blocks in front
                BlockPos pos = this.blockPosition();
                for (int x = -1; x <= 1; x++) {
                    for (int y = 0; y <= 2; y++) {
                        for (int z = -1; z <= 1; z++) {
                            BlockPos targetPos = pos.offset(x, y, z);
                            net.minecraft.world.level.block.state.BlockState state = this.level().getBlockState(targetPos);
                            if (!state.isAir() && state.getDestroySpeed(this.level(), targetPos) >= 0 && state.getDestroySpeed(this.level(), targetPos) < 50.0F) {
                                this.level().destroyBlock(targetPos, true, this);
                            }
                        }
                    }
                }

                // Damage entities
                java.util.List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(1.0D));
                for (LivingEntity e : entities) {
                    if (e != this && !(e instanceof VegetableEntity) && !(e instanceof com.savagevegetables.outbreak.entity.mob.boss.BossVegetableEntity)) {
                        e.hurt(this.damageSources().mobAttack(this), 10.0f);
                    }
                }

                if (this.horizontalCollision || chargeTime > 40) {
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
                .add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    class RadishChargeAttackGoal extends Goal {
        private final RadishEntity radish;
        private LivingEntity target;

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
            return this.radish.distanceToSqr(target) > 16.0D && this.radish.distanceToSqr(target) < 256.0D;
        }

        @Override
        public void start() {
            this.target = this.radish.getTarget();
            radish.isCharging = true;
            radish.chargeTime = 0;
            radish.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (radish.isCharging) {
                radish.getLookControl().setLookAt(target, 30.0F, 30.0F);
                radish.chargeTime++;

                if (radish.chargeTime % 20 == 0) {
                    radish.playSound(net.minecraft.sounds.SoundEvents.GHAST_WARN, 1.0F, 1.0F);
                }

                if (radish.chargeTime >= 200) { // 10 seconds (20 ticks/sec * 10)
                    radish.isCharging = false;
                    radish.isDashing = true;
                    radish.chargeTime = 0;

                    double dx = target.getX() - radish.getX();
                    double dz = target.getZ() - radish.getZ();
                    double dist = Math.sqrt(dx * dx + dz * dz);
                    radish.setDeltaMovement(dx / dist * 2.0D, 0.2D, dz / dist * 2.0D); // Leap and dash
                    radish.playSound(net.minecraft.sounds.SoundEvents.ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                }
            }
        }
    }
}
