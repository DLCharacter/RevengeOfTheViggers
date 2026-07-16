with open('src/main/java/com/savagevegetables/outbreak/init/ItemInit.java', 'r') as f:
    content = f.read()

new_items = """    public static final RegistryObject<Item> CELERY_SEED = ITEMS.register("celery_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CELERY_CROP.get(), new Item.Properties()));

    // Custom Seeds and Foods
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.TOMATO_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CORN_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> LEEK = ITEMS.register("leek", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> LEEK_SEEDS = ITEMS.register("leek_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.LEEK_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CUCUMBER_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> EGGPLANT = ITEMS.register("eggplant", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> EGGPLANT_SEEDS = ITEMS.register("eggplant_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.EGGPLANT_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> ZUCCHINI = ITEMS.register("zucchini", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> ZUCCHINI_SEEDS = ITEMS.register("zucchini_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.ZUCCHINI_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> BROCCOLI = ITEMS.register("broccoli", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> BROCCOLI_SEEDS = ITEMS.register("broccoli_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.BROCCOLI_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> RADISH = ITEMS.register("radish", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> RADISH_SEEDS = ITEMS.register("radish_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.RADISH_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> LEEK_SPAWN_EGG = ITEMS.register("leek_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.LEEK_ENTITY, 0xFFFFFF, 0x00FF00, new Item.Properties()));
    public static final RegistryObject<Item> CUCUMBER_SPAWN_EGG = ITEMS.register("cucumber_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.CUCUMBER_ENTITY, 0x00FF00, 0x002200, new Item.Properties()));
    public static final RegistryObject<Item> POTATO_SPAWN_EGG = ITEMS.register("potato_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.POTATO_ENTITY, 0xDDBB77, 0xAA8855, new Item.Properties()));
    public static final RegistryObject<Item> CARROT_SPAWN_EGG = ITEMS.register("carrot_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.CARROT_ENTITY, 0xFF9900, 0x00FF00, new Item.Properties()));
    public static final RegistryObject<Item> BROCCOLI_SPAWN_EGG = ITEMS.register("broccoli_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.BROCCOLI_ENTITY, 0x00FF00, 0x00AA00, new Item.Properties()));
    public static final RegistryObject<Item> RADISH_SPAWN_EGG = ITEMS.register("radish_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.RADISH_ENTITY, 0xFF0055, 0xFFFFFF, new Item.Properties()));
"""

content = content.replace('    public static final RegistryObject<Item> CELERY_SEED = ITEMS.register("celery_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CELERY_CROP.get(), new Item.Properties()));', new_items)

with open('src/main/java/com/savagevegetables/outbreak/init/ItemInit.java', 'w') as f:
    f.write(content)
