with open('src/main/java/com/savagevegetables/outbreak/init/EntityInit.java', 'r') as f:
    content = f.read()

new_entities = """    public static final RegistryObject<EntityType<CornEntity>> CORN_ENTITY = ENTITY_TYPES.register("corn_entity",
            () -> EntityType.Builder.of(CornEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 1.5f).build("corn_entity"));

    // Additional Vegetables
    public static final RegistryObject<EntityType<LeekEntity>> LEEK_ENTITY = ENTITY_TYPES.register("leek",
            () -> EntityType.Builder.of(LeekEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f).build("leek"));

    public static final RegistryObject<EntityType<CucumberEntity>> CUCUMBER_ENTITY = ENTITY_TYPES.register("cucumber",
            () -> EntityType.Builder.of(CucumberEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f).build("cucumber"));

    public static final RegistryObject<EntityType<PotatoEntity>> POTATO_ENTITY = ENTITY_TYPES.register("potato",
            () -> EntityType.Builder.of(PotatoEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 0.8f).build("potato"));

    public static final RegistryObject<EntityType<CarrotEntity>> CARROT_ENTITY = ENTITY_TYPES.register("carrot_mob",
            () -> EntityType.Builder.of(CarrotEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f).build("carrot_mob"));

    public static final RegistryObject<EntityType<BroccoliEntity>> BROCCOLI_ENTITY = ENTITY_TYPES.register("broccoli",
            () -> EntityType.Builder.of(BroccoliEntity::new, MobCategory.MONSTER)
                    .sized(0.8f, 1.8f).build("broccoli"));

    public static final RegistryObject<EntityType<RadishEntity>> RADISH_ENTITY = ENTITY_TYPES.register("radish",
            () -> EntityType.Builder.of(RadishEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 0.6f).build("radish"));"""

content = content.replace("""    public static final RegistryObject<EntityType<CornEntity>> CORN_ENTITY = ENTITY_TYPES.register("corn_entity",
            () -> EntityType.Builder.of(CornEntity::new, MobCategory.MONSTER)
                    .sized(0.5f, 1.5f).build("corn_entity"));""", new_entities)

with open('src/main/java/com/savagevegetables/outbreak/init/EntityInit.java', 'w') as f:
    f.write(content)
