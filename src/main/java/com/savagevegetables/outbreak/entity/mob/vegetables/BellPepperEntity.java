package com.savagevegetables.outbreak.entity.mob.vegetables;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class BellPepperEntity extends PathfinderMob /* Или от кого он наследуется */ {

    // Создаем стейт анимации
    public final AnimationState jumpAnimationState = new AnimationState();

    public BellPepperEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    // Этот метод вызывается, когда моб получает урон
    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean wasHurt = super.hurt(source, amount);

        // Если урон прошел успешно, и мы на сервере
        if (wasHurt && !this.level().isClientSide) {
            // Отправляем сигнал '10' на клиент всем игрокам рядом
            this.level().broadcastEntityEvent(this, (byte) 10);
        }
        return wasHurt;
    }

    // Этот метод ловит сигнал на клиенте и запускает анимацию
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 10) {
            // Если анимация уже идет, мы ее останавливаем и запускаем заново (чтобы при спаме ударами она не ломалась)
            this.jumpAnimationState.stop();
            this.jumpAnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(id);
        }
    }
}