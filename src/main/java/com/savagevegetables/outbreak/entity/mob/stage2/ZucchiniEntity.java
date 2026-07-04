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

    public ZucchiniEntity(EntityType<? extends Phantom> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new DropCowGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FLYING_SPEED, 0.6D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D);
    }

    static class DropCowGoal extends Goal {
        private final ZucchiniEntity zucchini;
        public int chargeTime;

        public DropCowGoal(ZucchiniEntity zucchini) {
            this.zucchini = zucchini;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
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
            if (distanceSq < 1024.0D) { // closer than eggplant
                Level level = this.zucchini.level();
                this.chargeTime++;
                if (this.chargeTime == 40) {
                    if (!level.isClientSide) {
                        Cow cow = EntityType.COW.create(level);
                        if (cow != null) {
                            cow.setPos(this.zucchini.getX(), this.zucchini.getY() - 1.0D, this.zucchini.getZ());
                            cow.setDeltaMovement(0, -0.8, 0);
                            cow.addTag("exploding_cow");
                            level.addFreshEntity(cow);
                        }
                    }
                    this.chargeTime = -40;
                }
            } else if (this.chargeTime > 0) {
                this.chargeTime--;
            }
        }
    }
}
