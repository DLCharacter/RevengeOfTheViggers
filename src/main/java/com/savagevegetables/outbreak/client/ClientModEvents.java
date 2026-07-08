package com.savagevegetables.outbreak.client;

import com.savagevegetables.outbreak.client.model.*;
import com.savagevegetables.outbreak.client.renderer.JerusalemArtichokeRenderer;
import com.savagevegetables.outbreak.init.EntityInit;
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
        event.registerLayerDefinition(ModelLayers.PUMPKIN, KillerPumpkinModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.TOMATO, RottenTomatoModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.CORN, CornMonsterModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.JERUSALEM_ARTICHOKE, JerusalemArtichokeBossModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.RUTABAGA, () -> BasicCubeModel.createBodyLayer(40.0f, 40.0f));
        event.registerLayerDefinition(ModelLayers.CELERY, CeleryBossModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.HYBRID, () -> BasicCubeModel.createBodyLayer(10.0f, 31.0f));
        event.registerLayerDefinition(ModelLayers.EGGPLANT, EggplantBlimpModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.ZUCCHINI, ZucchiniKillerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.BULLET_PROJECTILE.get(), NoopRenderer::new); // fallback renderer
        event.registerEntityRenderer(EntityInit.VEGGIE_PROJECTILE.get(), NoopRenderer::new); // fallback renderer

        event.registerEntityRenderer(EntityInit.PUMPKIN_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.PumpkinRenderer::new);
        event.registerEntityRenderer(EntityInit.TOMATO_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.TomatoRenderer::new);
        event.registerEntityRenderer(EntityInit.CORN_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.CornRenderer::new);
        event.registerEntityRenderer(EntityInit.JERUSALEM_ARTICHOKE_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.JerusalemArtichokeRenderer::new);
        event.registerEntityRenderer(EntityInit.RUTABAGA_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.RUTABAGA, "rutabaga", 1.2f));
        event.registerEntityRenderer(EntityInit.CELERY_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.CeleryRenderer::new);
        event.registerEntityRenderer(EntityInit.HYBRID_ENTITY.get(), context -> new VegetableRenderer<>(context, ModelLayers.HYBRID, "hybrid", 0.5f));
        event.registerEntityRenderer(EntityInit.EGGPLANT_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.EggplantRenderer::new);
        event.registerEntityRenderer(EntityInit.ZUCCHINI_ENTITY.get(), com.savagevegetables.outbreak.client.renderer.ZucchiniRenderer::new);
    }
}
