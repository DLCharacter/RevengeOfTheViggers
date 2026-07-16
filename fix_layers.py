with open('src/main/java/com/savagevegetables/outbreak/client/ModelLayers.java', 'r') as f:
    content = f.read()

insertion = """    public static final ModelLayerLocation EGGPLANT = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "eggplant"), "main");
    public static final ModelLayerLocation ZUCCHINI = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "zucchini"), "main");

    public static final ModelLayerLocation LEEK = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "leek"), "main");
    public static final ModelLayerLocation CUCUMBER = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "cucumber"), "main");
    public static final ModelLayerLocation POTATO = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "potato"), "main");
    public static final ModelLayerLocation CARROT = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "carrot"), "main");
    public static final ModelLayerLocation BROCCOLI = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "broccoli"), "main");
    public static final ModelLayerLocation RADISH = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "radish"), "main");"""

content = content.replace('    public static final ModelLayerLocation EGGPLANT = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "eggplant"), "main");\n    public static final ModelLayerLocation ZUCCHINI = new ModelLayerLocation(new ResourceLocation("savage_vegetables", "zucchini"), "main");', insertion)

with open('src/main/java/com/savagevegetables/outbreak/client/ModelLayers.java', 'w') as f:
    f.write(content)
