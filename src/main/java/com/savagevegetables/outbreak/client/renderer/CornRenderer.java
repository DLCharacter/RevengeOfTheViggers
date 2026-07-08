package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.CornMonsterModel;
import com.savagevegetables.outbreak.entity.mob.vegetables.CornEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CornRenderer extends MobRenderer<CornEntity, CornMonsterModel<CornEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation CORN_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/corn.png");

    public CornRenderer(EntityRendererProvider.Context context) {
        // 2.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new CornMonsterModel<>(context.bakeLayer(ModelLayers.CORN)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CornEntity entity) {
        return CORN_TEXTURE;
    }
}