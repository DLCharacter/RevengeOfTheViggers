with open('src/main/java/com/savagevegetables/outbreak/world/wave/WaveManager.java', 'r') as f:
    content = f.read()

content = content.replace("""        pool.add(EntityInit.PUMPKIN_ENTITY.get());
        pool.add(EntityInit.TOMATO_ENTITY.get());""", """        pool.add(EntityInit.PUMPKIN_ENTITY.get());
        pool.add(EntityInit.TOMATO_ENTITY.get());
        pool.add(EntityInit.LEEK_ENTITY.get());
        pool.add(EntityInit.CUCUMBER_ENTITY.get());
        pool.add(EntityInit.POTATO_ENTITY.get());
        pool.add(EntityInit.CARROT_ENTITY.get());
        pool.add(EntityInit.BROCCOLI_ENTITY.get());
        pool.add(EntityInit.RADISH_ENTITY.get());""")

with open('src/main/java/com/savagevegetables/outbreak/world/wave/WaveManager.java', 'w') as f:
    f.write(content)
