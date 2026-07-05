package com.savagevegetables.outbreak.entity.mob.stage2;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundEvents;

import java.util.EnumSet;

public class EggplantEntity extends Ghast {

    public EggplantEntity(EntityType<? extends Ghast> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // Add custom Cow shooting goal (overrides default fireball goal because it will trigger earlier/be higher priority)
        this.goalSelector.addGoal(2, new ShootCowGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.FOLLOW_RANGE, 100.0D) // Increased detection range
                .add(Attributes.FLYING_SPEED, 0.4D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    static class ShootCowGoal extends Goal {
        private final EggplantEntity eggplant;
        public int chargeTime;

        public ShootCowGoal(EggplantEntity eggplant) {
            this.eggplant = eggplant;
        }

        public boolean canUse() {
            return this.eggplant.getTarget() != null && this.eggplant.distanceToSqr(this.eggplant.getTarget()) < 10000.0D;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void tick() {
            LivingEntity target = this.eggplant.getTarget();
            if (target == null) return;

            double distanceSq = this.eggplant.distanceToSqr(target);
            if (distanceSq < 10000.0D) {
                Level level = this.eggplant.level();
                this.chargeTime++;
                if (this.chargeTime == 60) {
                    if (!level.isClientSide) {
                        Cow cow = EntityType.COW.create(level);
                        if (cow != null) {
                            // Spawn cow at eggplant position
                            cow.setPos(this.eggplant.getX(), this.eggplant.getY() - 1.0D, this.eggplant.getZ());

                            // Calculate trajectory towards player
                            Vec3 targetPos = new Vec3(target.getX(), target.getY(0.5D), target.getZ());
                            Vec3 shootDir = targetPos.subtract(cow.position()).normalize().scale(1.5D); // Speed of 1.5

                            cow.setDeltaMovement(shootDir);
                            cow.addTag("exploding_cow");

                            this.eggplant.playSound(SoundEvents.GHAST_SHOOT, 1.0F, 1.0F);
                            level.addFreshEntity(cow);
                        }
                    }
                    this.chargeTime = -40; // Cooldown
                }
            } else if (this.chargeTime > 0) {
                this.chargeTime--;
            }

            // Make eggplant look at player
            this.eggplant.getLookControl().setLookAt(target, 10.0F, this.eggplant.getMaxHeadXRot());
        }
    }
}
