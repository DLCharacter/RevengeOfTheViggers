package com.savagevegetables.outbreak.client;

import com.savagevegetables.outbreak.init.EntityInit;
import com.savagevegetables.outbreak.client.model.BasicCubeModel;
import com.savagevegetables.outbreak.client.renderer.VegetableRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "savage_vegetables", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayers.PUMPKIN, () -> BasicCubeModel.createBodyLayer(14.0f, 14.0f));
        event.registerLayerDefinition(ModelLayers.TOMATO, () -> BasicCubeModel.createBodyLayer(10.0f, 10.0f));
        event.registerLayerDefinition(ModelLayers.CORN, () -> BasicCubeModel.createBodyLayer(8.0f, 24.0f));
        event.registerLayerDefinition(ModelLayers.JERUSALEM_ARTICHOKE, () -> BasicCubeModel.createBodyLayer(32.0f, 40.0f));
        event.registerLayerDefinition(ModelLayers.RUTABAGA, () -> BasicCubeModel.createBodyLayer(40.0f, 40.0f));
        event.registerLayerDefinition(ModelLayers.CELERY, () -> BasicCubeModel.createBodyLayer(24.0f, 56.0f));
        event.registerLayerDefinition(ModelLayers.HYBRID, () -> BasicCubeModel.createBodyLayer(10.0f, 31.0f));
        event.registerLayerDefinition(ModelLayers.EGGPLANT, () -> BasicCubeModel.createBodyLayer(64.0f, 64.0f));
        event.registerLayerDefinition(ModelLayers.ZUCCHINI, () -> BasicCubeModel.createBodyLayer(14.0f, 8.0f));
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.BULLET_PROJECTILE.get(), NoopRenderer::new); // fallback renderer
        event.registerEntityRenderer(EntityInit.VEGGIE_PROJECTILE.get(), NoopRenderer::new); // fallback renderer

        event.registerEntityRenderer(EntityInit.PUMPKIN_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.PUMPKIN, "pumpkin", 0.5f));
        event.registerEntityRenderer(EntityInit.TOMATO_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.TOMATO, "tomato", 0.4f));
        event.registerEntityRenderer(EntityInit.CORN_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.CORN, "corn", 0.4f));
        event.registerEntityRenderer(EntityInit.JERUSALEM_ARTICHOKE_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.JERUSALEM_ARTICHOKE, "jerusalem_artichoke", 1.0f));
        event.registerEntityRenderer(EntityInit.RUTABAGA_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.RUTABAGA, "rutabaga", 1.2f));
        event.registerEntityRenderer(EntityInit.CELERY_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.CELERY, "celery", 0.8f));
        event.registerEntityRenderer(EntityInit.HYBRID_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.HYBRID, "hybrid", 0.5f));
        event.registerEntityRenderer(EntityInit.EGGPLANT_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.EGGPLANT, "eggplant", 2.0f));
        event.registerEntityRenderer(EntityInit.ZUCCHINI_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.ZUCCHINI, "zucchini", 0.6f));
    }
}
