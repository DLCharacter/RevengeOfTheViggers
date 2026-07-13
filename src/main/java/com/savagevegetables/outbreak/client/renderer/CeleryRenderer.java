package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.CeleryBossModel;
import com.savagevegetables.outbreak.entity.mob.boss.CeleryEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CeleryRenderer extends MobRenderer<CeleryEntity, CeleryBossModel<CeleryEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation CELERY_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/celery.png");

    public CeleryRenderer(EntityRendererProvider.Context context) {
        // 1.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new CeleryBossModel<>(context.bakeLayer(ModelLayers.CELERY)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(CeleryEntity entity) {
        return CELERY_TEXTURE;
    }
}