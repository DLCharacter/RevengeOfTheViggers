package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.CornMonsterModel;
import com.savagevegetables.outbreak.client.model.HybridCabbageStalkerModel;
import com.savagevegetables.outbreak.entity.mob.stage2.HybridEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HybridCabbageStalkerRenderer extends MobRenderer<HybridEntity, HybridCabbageStalkerModel<HybridEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation HYBRID_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/hybrid_cabbage_stalker.png");

    public HybridCabbageStalkerRenderer(EntityRendererProvider.Context context) {
        // 2.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new HybridCabbageStalkerModel<>(context.bakeLayer(ModelLayers.HYBRID)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HybridEntity entity) {
        return HYBRID_TEXTURE;
    }
}