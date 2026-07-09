boss_path = "src/main/java/com/savagevegetables/outbreak/entity/mob/boss/BossVegetableEntity.java"
with open(boss_path, "r") as f:
    content = f.read()

# Remove the empty override
import re
content = re.sub(r'    @Override\s+public void die.*?data\.setStage2Active\(true\);\s+\}\s+\}', "", content, flags=re.DOTALL)
content = re.sub(r'    @Override\s+public void die.*?\}', "", content, flags=re.DOTALL)
content = re.sub(r'    @Override\s+\}', "}", content, flags=re.DOTALL)

if not content.strip().endswith("}"):
    content = content.strip() + "\n}\n"

with open(boss_path, "w") as f:
    f.write(content)
