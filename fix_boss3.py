import re
boss_path = "src/main/java/com/savagevegetables/outbreak/entity/mob/boss/BossVegetableEntity.java"
with open(boss_path, "r") as f:
    content = f.read()

# Replace trailing garbage completely and close the class
content = content.replace("    @Override\n}", "}")
content = content.replace("    @Override\n    \n}", "}")

if not content.strip().endswith("}"):
    content = content.strip() + "\n}\n"

with open(boss_path, "w") as f:
    f.write(content)
