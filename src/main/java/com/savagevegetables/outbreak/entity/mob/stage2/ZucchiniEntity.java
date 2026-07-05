package com.savagevegetables.outbreak.entity.mob.stage2;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;

import java.util.EnumSet;

public class ZucchiniEntity extends Phantom {
    @Override
    public boolean ignoreExplosion() {
        return true;
    }


    public ZucchiniEntity(EntityType<? extends Phantom> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        // We keep PhantomSweepAttackGoal because it handles diving. We just override doHurtTarget.
        this.goalSelector.addGoal(2, new DiveAndDropCowGoal(this));
    }

    // Override to prevent PhantomSweepAttackGoal from checking attack_damage which causes crashes
    @Override
    public boolean doHurtTarget(net.minecraft.world.entity.Entity entity) {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 64.0D) // Increased detection range
                .add(Attributes.FLYING_SPEED, 1.2D) // Doubled speed
                .add(Attributes.MOVEMENT_SPEED, 1.2D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D); // Add ATTACK_DAMAGE to prevent crash
    }

    static class DiveAndDropCowGoal extends Goal {
        private final ZucchiniEntity zucchini;
        public int chargeTime;

        public DiveAndDropCowGoal(ZucchiniEntity zucchini) {
            this.zucchini = zucchini;
        }

        public boolean canUse() {
            return this.zucchini.getTarget() != null;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void tick() {
            LivingEntity target = this.zucchini.getTarget();
            if (target == null) return;

            double distanceSq = this.zucchini.distanceToSqr(target);

            // Starts dropping cows when quite close (closer than before, within 15 blocks / 225 distance squared)
            if (distanceSq < 225.0D) {
                Level level = this.zucchini.level();
                this.chargeTime++;
                if (this.chargeTime == 20) { // drops faster (every 1 second when close)
                    if (!level.isClientSide) {
                        Cow cow = EntityType.COW.create(level);
                        if (cow != null) {
                            cow.setPos(this.zucchini.getX(), this.zucchini.getY() - 1.0D, this.zucchini.getZ());
                            // Drops fast
                            cow.setDeltaMovement(0, -1.5, 0);

                            cow.addTag("exploding_cow_zucchini");
                            level.addFreshEntity(cow);
                        }
                    }
                    this.chargeTime = -30; // Short cooldown
                }
            } else if (this.chargeTime > 0) {
                this.chargeTime--;
            }
        }
    }
}
