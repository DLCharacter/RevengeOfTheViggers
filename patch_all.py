with open('src/main/java/com/savagevegetables/outbreak/init/BlockInit.java', 'r') as f:
    content = f.read()

replacement = """    public static final RegistryObject<Block> CELERY_CROP = BLOCKS.register("celery_crop", () -> new com.savagevegetables.outbreak.block.BossCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.CELERY_ENTITY));

    public static final RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> EGGPLANT_CROP = BLOCKS.register("eggplant_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> ZUCCHINI_CROP = BLOCKS.register("zucchini_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> CUCUMBER_CROP = BLOCKS.register("cucumber_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> BROCCOLI_CROP = BLOCKS.register("broccoli_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> RADISH_CROP = BLOCKS.register("radish_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> MIMIC_PUMPKIN = BLOCKS.register("mimic_pumpkin", () -> new com.savagevegetables.outbreak.block.MimicPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN)));
    public static final RegistryObject<Block> MIMIC_TOMATO_CROP = BLOCKS.register("mimic_tomato_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.TOMATO_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_CORN_CROP = BLOCKS.register("mimic_corn_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.CORN_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_EGGPLANT_CROP = BLOCKS.register("mimic_eggplant_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.EGGPLANT_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_ZUCCHINI_CROP = BLOCKS.register("mimic_zucchini_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.ZUCCHINI_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_CUCUMBER_CROP = BLOCKS.register("mimic_cucumber_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.CUCUMBER_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_POTATO_CROP = BLOCKS.register("mimic_potato_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES), () -> EntityInit.POTATO_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_CARROT_CROP = BLOCKS.register("mimic_carrot_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.CARROTS), () -> EntityInit.CARROT_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_BROCCOLI_CROP = BLOCKS.register("mimic_broccoli_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.BROCCOLI_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_RADISH_CROP = BLOCKS.register("mimic_radish_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.RADISH_ENTITY.get()));"""

target = """    public static final RegistryObject<Block> CELERY_CROP = BLOCKS.register("celery_crop", () -> new com.savagevegetables.outbreak.block.BossCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.CELERY_ENTITY));"""

content = content.replace(target, replacement)
with open('src/main/java/com/savagevegetables/outbreak/init/BlockInit.java', 'w') as f:
    f.write(content)

with open('src/main/java/com/savagevegetables/outbreak/init/ItemInit.java', 'r') as f:
    content = f.read()

replacement = """    public static final RegistryObject<Item> CELERY_SEED = ITEMS.register("celery_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CELERY_CROP.get(), new Item.Properties()));

    // Normal Seeds
    public static final RegistryObject<Item> TOMATO_SEED = ITEMS.register("tomato_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.TOMATO_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CORN_SEED = ITEMS.register("corn_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CORN_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> EGGPLANT_SEED = ITEMS.register("eggplant_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.EGGPLANT_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> ZUCCHINI_SEED = ITEMS.register("zucchini_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.ZUCCHINI_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CUCUMBER_SEED = ITEMS.register("cucumber_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CUCUMBER_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> BROCCOLI_SEED = ITEMS.register("broccoli_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.BROCCOLI_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RADISH_SEED = ITEMS.register("radish_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.RADISH_CROP.get(), new Item.Properties()));

    // Boss Fruits
    public static final RegistryObject<Item> JERUSALEM_ARTICHOKE_FRUIT = ITEMS.register("jerusalem_artichoke_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.2f).build())));
    public static final RegistryObject<Item> RUTABAGA_FRUIT = ITEMS.register("rutabaga_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.2f).build())));
    public static final RegistryObject<Item> CELERY_FRUIT = ITEMS.register("celery_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.2f).build())));"""

target = """    public static final RegistryObject<Item> CELERY_SEED = ITEMS.register("celery_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CELERY_CROP.get(), new Item.Properties()));"""

content = content.replace(target, replacement)

replacement2 = """    public static final RegistryObject<Item> ZUCCHINI_SPAWN_EGG = ITEMS.register("zucchini_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.ZUCCHINI_ENTITY, 0x304B26, 0xEBECC4, new Item.Properties()));

    public static final RegistryObject<Item> LEEK_SPAWN_EGG = ITEMS.register("leek_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.LEEK_ENTITY, 0x8DA955, 0x5E812F, new Item.Properties()));
    public static final RegistryObject<Item> CUCUMBER_SPAWN_EGG = ITEMS.register("cucumber_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.CUCUMBER_ENTITY, 0x304B26, 0x5E812F, new Item.Properties()));
    public static final RegistryObject<Item> CARROT_SPAWN_EGG = ITEMS.register("carrot_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.CARROT_ENTITY, 0xF1931E, 0x5E812F, new Item.Properties()));
    public static final RegistryObject<Item> POTATO_SPAWN_EGG = ITEMS.register("potato_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.POTATO_ENTITY, 0xD4A056, 0x5E812F, new Item.Properties()));
    public static final RegistryObject<Item> BROCCOLI_SPAWN_EGG = ITEMS.register("broccoli_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.BROCCOLI_ENTITY, 0x3B6833, 0x5E812F, new Item.Properties()));
    public static final RegistryObject<Item> RADISH_SPAWN_EGG = ITEMS.register("radish_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.RADISH_ENTITY, 0xE74861, 0x5E812F, new Item.Properties()));"""

target2 = """    public static final RegistryObject<Item> ZUCCHINI_SPAWN_EGG = ITEMS.register("zucchini_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.ZUCCHINI_ENTITY, 0x304B26, 0xEBECC4, new Item.Properties()));"""

content = content.replace(target2, replacement2)

with open('src/main/java/com/savagevegetables/outbreak/init/ItemInit.java', 'w') as f:
    f.write(content)


with open('src/main/java/com/savagevegetables/outbreak/init/EntityInit.java', 'r') as f:
    content = f.read()

replacement = """    public static final RegistryObject<EntityType<CornEntity>> CORN_ENTITY = ENTITY_TYPES.register("corn_entity",
            () -> EntityType.Builder.of(CornEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.5f, 1.5f).build("corn_entity"));

    public static final RegistryObject<EntityType<LeekEntity>> LEEK_ENTITY = ENTITY_TYPES.register("leek",
            () -> EntityType.Builder.of(LeekEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.6f, 1.8f).build("leek"));

    public static final RegistryObject<EntityType<CucumberEntity>> CUCUMBER_ENTITY = ENTITY_TYPES.register("cucumber",
            () -> EntityType.Builder.of(CucumberEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.5f, 1.5f).build("cucumber"));

    public static final RegistryObject<EntityType<CarrotEntity>> CARROT_ENTITY = ENTITY_TYPES.register("carrot",
            () -> EntityType.Builder.of(CarrotEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.6f, 1.8f).build("carrot"));

    public static final RegistryObject<EntityType<PotatoEntity>> POTATO_ENTITY = ENTITY_TYPES.register("potato",
            () -> EntityType.Builder.of(PotatoEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.8f, 0.8f).build("potato"));

    public static final RegistryObject<EntityType<BroccoliEntity>> BROCCOLI_ENTITY = ENTITY_TYPES.register("broccoli",
            () -> EntityType.Builder.of(BroccoliEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(1.2f, 1.8f).build("broccoli"));

    public static final RegistryObject<EntityType<RadishEntity>> RADISH_ENTITY = ENTITY_TYPES.register("radish",
            () -> EntityType.Builder.of(RadishEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.8f, 0.8f).build("radish"));"""

target = """    public static final RegistryObject<EntityType<CornEntity>> CORN_ENTITY = ENTITY_TYPES.register("corn_entity",
            () -> EntityType.Builder.of(CornEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 1.5f).build("corn_entity"));"""

content = content.replace(target, replacement)
with open('src/main/java/com/savagevegetables/outbreak/init/EntityInit.java', 'w') as f:
    f.write(content)

with open('src/main/java/com/savagevegetables/outbreak/init/CreativeTabInit.java', 'r') as f:
    content = f.read()

replacement = """                output.accept(ItemInit.CELERY_SEED.get());

                output.accept(ItemInit.TOMATO_SEED.get());
                output.accept(ItemInit.CORN_SEED.get());
                output.accept(ItemInit.EGGPLANT_SEED.get());
                output.accept(ItemInit.ZUCCHINI_SEED.get());
                output.accept(ItemInit.CUCUMBER_SEED.get());
                output.accept(ItemInit.BROCCOLI_SEED.get());
                output.accept(ItemInit.RADISH_SEED.get());

                output.accept(ItemInit.JERUSALEM_ARTICHOKE_FRUIT.get());
                output.accept(ItemInit.RUTABAGA_FRUIT.get());
                output.accept(ItemInit.CELERY_FRUIT.get());"""
target = """                output.accept(ItemInit.CELERY_SEED.get());"""
content = content.replace(target, replacement)

replacement2 = """                output.accept(ItemInit.CORN_SPAWN_EGG.get());
                output.accept(ItemInit.LEEK_SPAWN_EGG.get());
                output.accept(ItemInit.CUCUMBER_SPAWN_EGG.get());
                output.accept(ItemInit.CARROT_SPAWN_EGG.get());
                output.accept(ItemInit.POTATO_SPAWN_EGG.get());
                output.accept(ItemInit.BROCCOLI_SPAWN_EGG.get());
                output.accept(ItemInit.RADISH_SPAWN_EGG.get());"""
target2 = """                output.accept(ItemInit.CORN_SPAWN_EGG.get());"""
content = content.replace(target2, replacement2)

with open('src/main/java/com/savagevegetables/outbreak/init/CreativeTabInit.java', 'w') as f:
    f.write(content)
