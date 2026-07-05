package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.RottenTomatoModel;
import com.savagevegetables.outbreak.entity.mob.vegetables.TomatoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TomatoRenderer extends MobRenderer<TomatoEntity, RottenTomatoModel<TomatoEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation TOMATO_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/tomato.png");

    public TomatoRenderer(EntityRendererProvider.Context context) {
        // 2.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new RottenTomatoModel<>(context.bakeLayer(ModelLayers.TOMATO)), 3.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(TomatoEntity entity) {
        return TOMATO_TEXTURE;
    }
}