with open('src/main/java/com/savagevegetables/outbreak/init/BlockInit.java', 'r') as f:
    content = f.read()

new_blocks = """    public static final RegistryObject<Block> CELERY_CROP = BLOCKS.register("celery_crop", () -> new com.savagevegetables.outbreak.block.BossCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.CELERY_ENTITY));

    // Custom crops (Tomato, Corn, Leek, Cucumber, Eggplant, Zucchini, Broccoli, Radish)
    public static final RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.TOMATO_ENTITY));
    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.CORN_ENTITY));
    public static final RegistryObject<Block> LEEK_CROP = BLOCKS.register("leek_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.LEEK_ENTITY));
    public static final RegistryObject<Block> CUCUMBER_CROP = BLOCKS.register("cucumber_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.CUCUMBER_ENTITY));
    public static final RegistryObject<Block> EGGPLANT_CROP = BLOCKS.register("eggplant_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.EGGPLANT_ENTITY));
    public static final RegistryObject<Block> ZUCCHINI_CROP = BLOCKS.register("zucchini_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.ZUCCHINI_ENTITY));
    public static final RegistryObject<Block> BROCCOLI_CROP = BLOCKS.register("broccoli_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.BROCCOLI_ENTITY));
    public static final RegistryObject<Block> RADISH_CROP = BLOCKS.register("radish_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.RADISH_ENTITY));
"""

content = content.replace('    public static final RegistryObject<Block> CELERY_CROP = BLOCKS.register("celery_crop", () -> new com.savagevegetables.outbreak.block.BossCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.CELERY_ENTITY));', new_blocks)

with open('src/main/java/com/savagevegetables/outbreak/init/BlockInit.java', 'w') as f:
    f.write(content)
