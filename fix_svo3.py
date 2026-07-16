with open('src/main/java/com/savagevegetables/outbreak/SavageVegetablesOutbreak.java', 'r') as f:
    content = f.read()

content = content.replace('EntityInit.ENTITY_TYPES.register(modEventBus);', 'EntityInit.ENTITY_TYPES.register(modEventBus);\n        SoundInit.SOUNDS.register(modEventBus);')

with open('src/main/java/com/savagevegetables/outbreak/SavageVegetablesOutbreak.java', 'w') as f:
    f.write(content)
