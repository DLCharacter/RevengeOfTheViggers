with open('src/main/java/com/savagevegetables/outbreak/entity/mob/boss/BossVegetableEntity.java', 'r') as f:
    content = f.read()

import re

drop_code = """    @Override
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
"""

content = re.sub(r'\}[\s\n]*$', drop_code, content)

with open('src/main/java/com/savagevegetables/outbreak/entity/mob/boss/BossVegetableEntity.java', 'w') as f:
    f.write(content)
