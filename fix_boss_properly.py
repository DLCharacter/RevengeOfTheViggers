boss_path = "src/main/java/com/savagevegetables/outbreak/entity/mob/boss/BossVegetableEntity.java"
with open(boss_path, "r") as f:
    content = f.read()

import re

# Safely extract and modify the exact string block instead of regex blindly over the file
start_str = "    @Override\n    public void die(net.minecraft.world.damagesource.DamageSource source) {"
if start_str in content:
    idx = content.find(start_str)
    # The die method we want to remove spans multiple lines:
    #     @Override
    #     public void die(net.minecraft.world.damagesource.DamageSource source) {
    #         super.die(source);
    #         if (!this.level().isClientSide) {
    #             OutbreakSavedData data = OutbreakSavedData.get((ServerLevel) this.level());
    #             data.setStage2Active(true);
    #         }
    #     }
    # It ends with 4 spaces and a closing brace.
    end_idx = content.find("    }", idx) + 5
    old_block = content[idx:end_idx]

    content = content.replace(old_block, "")

with open(boss_path, "w") as f:
    f.write(content)
