package com.savagevegetables.outbreak.entity.mob.vegetables;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class VegetableEntity extends Monster {
    protected VegetableEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
}
