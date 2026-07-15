with open('src/main/java/com/savagevegetables/outbreak/init/ItemInit.java', 'r') as f:
    content = f.read()

import re

new_fruits = """    // Boss Fruits
    public static final RegistryObject<Item> JERUSALEM_ARTICHOKE_FRUIT = ITEMS.register("jerusalem_artichoke_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.0F).alwaysEat().build())));
    public static final RegistryObject<Item> RUTABAGA_FRUIT = ITEMS.register("rutabaga_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.0F).alwaysEat().build())));
    public static final RegistryObject<Item> CELERY_FRUIT = ITEMS.register("celery_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.0F).alwaysEat().build())));

    // Boss Seeds"""

content = content.replace("    // Boss Seeds", new_fruits)

with open('src/main/java/com/savagevegetables/outbreak/init/ItemInit.java', 'w') as f:
    f.write(content)


with open('src/main/java/com/savagevegetables/outbreak/init/CreativeTabInit.java', 'r') as f:
    tab_content = f.read()

new_tab_fruits = """                output.accept(ItemInit.JERUSALEM_ARTICHOKE_FRUIT.get());
                output.accept(ItemInit.RUTABAGA_FRUIT.get());
                output.accept(ItemInit.CELERY_FRUIT.get());

                output.accept(ItemInit.JERUSALEM_ARTICHOKE_SEED.get());"""

tab_content = tab_content.replace("                output.accept(ItemInit.JERUSALEM_ARTICHOKE_SEED.get());", new_tab_fruits)

with open('src/main/java/com/savagevegetables/outbreak/init/CreativeTabInit.java', 'w') as f:
    f.write(tab_content)
