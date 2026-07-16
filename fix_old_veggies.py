files = [
    'src/main/java/com/savagevegetables/outbreak/entity/mob/vegetables/PumpkinEntity.java',
    'src/main/java/com/savagevegetables/outbreak/entity/mob/vegetables/TomatoEntity.java',
    'src/main/java/com/savagevegetables/outbreak/entity/mob/vegetables/CornEntity.java'
]

import re

for file_path in files:
    with open(file_path, 'r') as f:
        content = f.read()

    # Replace registerGoals beginning with super call
    content = content.replace("protected void registerGoals() {", "protected void registerGoals() {\n        super.registerGoals();")

    # Remove redundant player target goal
    content = re.sub(r'this\.targetSelector\.addGoal\(1,\s*new\s*NearestAttackableTargetGoal\s*<\s*>\s*\(this,\s*Player\.class,\s*true\)\);\s*', '', content)

    with open(file_path, 'w') as f:
        f.write(content)
