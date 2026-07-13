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
import net.minecraft.world.phys.AABB;
import java.util.List;

import java.util.EnumSet;

public class EggplantEntity extends Ghast {
    @Override
    public boolean ignoreExplosion() {
        return true;
    }


    public EggplantEntity(EntityType<? extends Ghast> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // Remove Ghast's default movement and shooting goals
        this.goalSelector.removeAllGoals(goal -> goal.getClass().getSimpleName().contains("ShootFireball") || goal.getClass().getSimpleName().contains("RandomFlyingGoal"));

        // Add custom goals
        this.goalSelector.addGoal(5, new EggplantHoverGoal(this));
        this.goalSelector.addGoal(1, new ShootCowGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Ghast.createAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.FOLLOW_RANGE, 100.0D) // Increased detection range
                .add(Attributes.FLYING_SPEED, 0.4D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D);
    }


    static class EggplantHoverGoal extends Goal {
        private final EggplantEntity eggplant;

        public EggplantHoverGoal(EggplantEntity eggplant) {
            this.eggplant = eggplant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return this.eggplant.getTarget() != null;
        }

        @Override
        public void tick() {
            LivingEntity target = this.eggplant.getTarget();
            if (target == null) return;

            // Hover exactly 15 blocks above the player, and slightly drift towards them horizontally
            double targetX = target.getX();
            double targetY = target.getY() + 15.0D;
            double targetZ = target.getZ();

            Vec3 currentPos = this.eggplant.position();
            Vec3 targetPos = new Vec3(targetX, targetY, targetZ);

            Vec3 moveVec = targetPos.subtract(currentPos);
            if (moveVec.lengthSqr() > 1.0D) {
                moveVec = moveVec.normalize().scale(0.1D); // move slowly
            }
            this.eggplant.setDeltaMovement(this.eggplant.getDeltaMovement().add(moveVec).scale(0.9D)); // add friction
        }
    }

    static class ShootCowGoal extends Goal {
        private final EggplantEntity eggplant;
        public int chargeTime;



        public boolean canUse() {
            return this.eggplant.getTarget() != null && this.eggplant.distanceToSqr(this.eggplant.getTarget()) < 10000.0D;
        }


        public ShootCowGoal(EggplantEntity eggplant) {
            this.eggplant = eggplant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK)); // Prevent fireball from running at same time
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
                            Vec3 targetPos = new Vec3(target.getX(), target.getY() + 1.0D, target.getZ());
                            Vec3 shootDir = targetPos.subtract(cow.position()).normalize().scale(1.5D); // Speed of 1.5

                            cow.setDeltaMovement(shootDir);
                            cow.addTag("exploding_cow_eggplant"); // Distinguish from zucchini
                            cow.setNoGravity(true); // Fly straight like a fireball


                            // Save original vector so we can maintain it in tick
                            cow.getPersistentData().putDouble("flyX", shootDir.x);
                            cow.getPersistentData().putDouble("flyY", shootDir.y);
                            cow.getPersistentData().putDouble("flyZ", shootDir.z);


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
