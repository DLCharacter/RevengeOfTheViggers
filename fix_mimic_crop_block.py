with open('src/main/java/com/savagevegetables/outbreak/block/MimicCropBlock.java', 'r') as f:
    content = f.read()

correct_code = """package com.savagevegetables.outbreak.block;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.registries.RegistryObject;

public class MimicCropBlock extends CropBlock {

    private final RegistryObject<? extends EntityType<? extends Mob>> entityType;

    public MimicCropBlock(Properties properties, RegistryObject<? extends EntityType<? extends Mob>> entityType) {
        super(properties);
        this.entityType = entityType;
    }

    public RegistryObject<? extends EntityType<? extends Mob>> getEntityType() {
        return entityType;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return net.minecraft.world.item.Items.WHEAT_SEEDS;
    }
}
"""

with open('src/main/java/com/savagevegetables/outbreak/block/MimicCropBlock.java', 'w') as f:
    f.write(correct_code)
