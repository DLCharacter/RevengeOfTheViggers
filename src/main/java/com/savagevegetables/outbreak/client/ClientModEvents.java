package com.savagevegetables.outbreak.client;

import com.savagevegetables.outbreak.init.EntityInit;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "savage_vegetables", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.BULLET_PROJECTILE.get(), NoopRenderer::new); // fallback renderer
        event.registerEntityRenderer(EntityInit.VEGGIE_PROJECTILE.get(), NoopRenderer::new); // fallback renderer

        event.registerEntityRenderer(EntityInit.PUMPKIN_ENTITY.get(), NoopRenderer::new);
        event.registerEntityRenderer(EntityInit.TOMATO_ENTITY.get(), NoopRenderer::new);
        event.registerEntityRenderer(EntityInit.CORN_ENTITY.get(), NoopRenderer::new);
        event.registerEntityRenderer(EntityInit.JERUSALEM_ARTICHOKE_ENTITY.get(), NoopRenderer::new);
        event.registerEntityRenderer(EntityInit.RUTABAGA_ENTITY.get(), NoopRenderer::new);
        event.registerEntityRenderer(EntityInit.CELERY_ENTITY.get(), NoopRenderer::new);
        event.registerEntityRenderer(EntityInit.HYBRID_ENTITY.get(), NoopRenderer::new);
        event.registerEntityRenderer(EntityInit.EGGPLANT_ENTITY.get(), NoopRenderer::new);
        event.registerEntityRenderer(EntityInit.ZUCCHINI_ENTITY.get(), NoopRenderer::new);
    }
}
