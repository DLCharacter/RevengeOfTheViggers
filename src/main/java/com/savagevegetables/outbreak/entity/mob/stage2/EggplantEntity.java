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

import java.util.EnumSet;

public class EggplantEntity extends Ghast {

    public EggplantEntity(EntityType<? extends Ghast> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        // We replace standard Ghast shoot goal with custom drop cow goal
        this.goalSelector.addGoal(2, new DropCowGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.FLYING_SPEED, 0.4D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    static class DropCowGoal extends Goal {
        private final EggplantEntity eggplant;
        public int chargeTime;

        public DropCowGoal(EggplantEntity eggplant) {
            this.eggplant = eggplant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return this.eggplant.getTarget() != null;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void tick() {
            LivingEntity target = this.eggplant.getTarget();
            if (target == null) return;

            double distanceSq = this.eggplant.distanceToSqr(target);
            if (distanceSq < 4096.0D) {
                Level level = this.eggplant.level();
                this.chargeTime++;
                if (this.chargeTime == 60) {
                    if (!level.isClientSide) {
                        Cow cow = EntityType.COW.create(level);
                        if (cow != null) {
                            cow.setPos(this.eggplant.getX(), this.eggplant.getY() - 1.0D, this.eggplant.getZ());
                            // Giving the cow downward momentum and tag to make it explode on impact (needs custom logic later or mixin, but MVP we just drop it)
                            cow.setDeltaMovement(0, -0.5, 0);
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
