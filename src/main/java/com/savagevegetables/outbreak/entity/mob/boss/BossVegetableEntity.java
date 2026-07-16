package com.savagevegetables.outbreak.entity.mob.boss;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import com.savagevegetables.outbreak.world.OutbreakSavedData;
import com.savagevegetables.outbreak.entity.mob.vegetables.VegetableEntity;
import com.savagevegetables.outbreak.entity.mob.stage2.HybridEntity;
import net.minecraft.server.level.ServerLevel;
import java.util.function.Predicate;

public abstract class BossVegetableEntity extends Monster {

    protected BossVegetableEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        Predicate<net.minecraft.world.entity.LivingEntity> nonVegetablePredicate = (entity) -> {
            if (entity instanceof VegetableEntity) return false;
            if (entity instanceof BossVegetableEntity) return false;
            if (entity instanceof HybridEntity) return false;
            if (entity.getClass().getPackageName().contains("savagevegetables")) return false;

            if (!(entity instanceof Mob || entity instanceof Player)) return false;

            return true;
        };

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, nonVegetablePredicate));
    }

    public static AttributeSupplier.Builder createBossAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }
    @Override
    public void die(net.minecraft.world.damagesource.DamageSource source) {
        super.die(source);
        if (!this.level().isClientSide) {
            OutbreakSavedData data = OutbreakSavedData.get((ServerLevel) this.level());
            data.addBossDefeated();

            // Drop fruit based on entity type
            if (this instanceof JerusalemArtichokeEntity) {
                this.spawnAtLocation(com.savagevegetables.outbreak.init.ItemInit.JERUSALEM_ARTICHOKE_FRUIT.get());
            } else if (this instanceof RutabagaEntity) {
                this.spawnAtLocation(com.savagevegetables.outbreak.init.ItemInit.RUTABAGA_FRUIT.get());
            } else if (this instanceof CeleryEntity) {
                this.spawnAtLocation(com.savagevegetables.outbreak.init.ItemInit.CELERY_FRUIT.get());
            }
        }
    }
}
