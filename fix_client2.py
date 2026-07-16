with open('src/main/java/com/savagevegetables/outbreak/client/ClientModEvents.java', 'r') as f:
    content = f.read()

import re

layers_insertion = """        event.registerLayerDefinition(ModelLayers.ZUCCHINI, ZucchiniKillerModel::createBodyLayer);

        event.registerLayerDefinition(ModelLayers.LEEK, () -> BasicCubeModel.createBodyLayer(16.0f, 16.0f));
        event.registerLayerDefinition(ModelLayers.CUCUMBER, () -> BasicCubeModel.createBodyLayer(16.0f, 16.0f));
        event.registerLayerDefinition(ModelLayers.POTATO, () -> BasicCubeModel.createBodyLayer(16.0f, 16.0f));
        event.registerLayerDefinition(ModelLayers.CARROT, () -> BasicCubeModel.createBodyLayer(16.0f, 16.0f));
        event.registerLayerDefinition(ModelLayers.BROCCOLI, () -> BasicCubeModel.createBodyLayer(16.0f, 16.0f));
        event.registerLayerDefinition(ModelLayers.RADISH, () -> BasicCubeModel.createBodyLayer(16.0f, 16.0f));"""

content = content.replace("        event.registerLayerDefinition(ModelLayers.ZUCCHINI, ZucchiniKillerModel::createBodyLayer);", layers_insertion)

renderers_insertion = """        event.registerEntityRenderer(EntityInit.ZUCCHINI_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.ZucchiniRenderer::new);

        event.registerEntityRenderer(EntityInit.LEEK_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.LEEK, "cabbage_steve", 1.0f));
        event.registerEntityRenderer(EntityInit.CUCUMBER_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.CUCUMBER, "cabbage_steve", 1.0f));
        event.registerEntityRenderer(EntityInit.POTATO_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.POTATO, "cabbage_steve", 1.0f));
        event.registerEntityRenderer(EntityInit.CARROT_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.CARROT, "cabbage_steve", 1.0f));
        event.registerEntityRenderer(EntityInit.BROCCOLI_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.BROCCOLI, "cabbage_steve", 1.0f));
        event.registerEntityRenderer(EntityInit.RADISH_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.RADISH, "cabbage_steve", 1.0f));"""

content = content.replace("        event.registerEntityRenderer(EntityInit.ZUCCHINI_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.ZucchiniRenderer::new);", renderers_insertion)

with open('src/main/java/com/savagevegetables/outbreak/client/ClientModEvents.java', 'w') as f:
    f.write(content)
