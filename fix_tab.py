with open('src/main/java/com/savagevegetables/outbreak/init/CreativeTabInit.java', 'r') as f:
    content = f.read()

new_tab = """                output.accept(ItemInit.JERUSALEM_ARTICHOKE_SEED.get());
                output.accept(ItemInit.RUTABAGA_SEED.get());
                output.accept(ItemInit.CELERY_SEED.get());

                output.accept(ItemInit.TOMATO.get());
                output.accept(ItemInit.TOMATO_SEEDS.get());
                output.accept(ItemInit.CORN.get());
                output.accept(ItemInit.CORN_SEEDS.get());
                output.accept(ItemInit.LEEK.get());
                output.accept(ItemInit.LEEK_SEEDS.get());
                output.accept(ItemInit.CUCUMBER.get());
                output.accept(ItemInit.CUCUMBER_SEEDS.get());
                output.accept(ItemInit.EGGPLANT.get());
                output.accept(ItemInit.EGGPLANT_SEEDS.get());
                output.accept(ItemInit.ZUCCHINI.get());
                output.accept(ItemInit.ZUCCHINI_SEEDS.get());
                output.accept(ItemInit.BROCCOLI.get());
                output.accept(ItemInit.BROCCOLI_SEEDS.get());
                output.accept(ItemInit.RADISH.get());
                output.accept(ItemInit.RADISH_SEEDS.get());

                output.accept(BlockInit.MUTATED_SOIL.get());

                output.accept(ItemInit.PUMPKIN_SPAWN_EGG.get());
                output.accept(ItemInit.TOMATO_SPAWN_EGG.get());
                output.accept(ItemInit.CORN_SPAWN_EGG.get());
                output.accept(ItemInit.JERUSALEM_ARTICHOKE_SPAWN_EGG.get());
                output.accept(ItemInit.RUTABAGA_SPAWN_EGG.get());
                output.accept(ItemInit.CELERY_SPAWN_EGG.get());
                output.accept(ItemInit.HYBRID_SPAWN_EGG.get());
                output.accept(ItemInit.EGGPLANT_SPAWN_EGG.get());
                output.accept(ItemInit.ZUCCHINI_SPAWN_EGG.get());
                output.accept(ItemInit.LEEK_SPAWN_EGG.get());
                output.accept(ItemInit.CUCUMBER_SPAWN_EGG.get());
                output.accept(ItemInit.POTATO_SPAWN_EGG.get());
                output.accept(ItemInit.CARROT_SPAWN_EGG.get());
                output.accept(ItemInit.BROCCOLI_SPAWN_EGG.get());
                output.accept(ItemInit.RADISH_SPAWN_EGG.get());"""

content = content.replace("""                output.accept(ItemInit.JERUSALEM_ARTICHOKE_SEED.get());
                output.accept(ItemInit.RUTABAGA_SEED.get());
                output.accept(ItemInit.CELERY_SEED.get());

                output.accept(BlockInit.MUTATED_SOIL.get());
                output.accept(BlockInit.JERUSALEM_ARTICHOKE_CROP.get());
                output.accept(BlockInit.RUTABAGA_CROP.get());
                output.accept(BlockInit.CELERY_CROP.get());

                output.accept(ItemInit.PUMPKIN_SPAWN_EGG.get());
                output.accept(ItemInit.TOMATO_SPAWN_EGG.get());
                output.accept(ItemInit.CORN_SPAWN_EGG.get());
                output.accept(ItemInit.JERUSALEM_ARTICHOKE_SPAWN_EGG.get());
                output.accept(ItemInit.RUTABAGA_SPAWN_EGG.get());
                output.accept(ItemInit.CELERY_SPAWN_EGG.get());
                output.accept(ItemInit.HYBRID_SPAWN_EGG.get());
                output.accept(ItemInit.EGGPLANT_SPAWN_EGG.get());
                output.accept(ItemInit.ZUCCHINI_SPAWN_EGG.get());""", new_tab)

with open('src/main/java/com/savagevegetables/outbreak/init/CreativeTabInit.java', 'w') as f:
    f.write(content)
