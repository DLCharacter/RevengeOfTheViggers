package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.ZucchiniKillerModel;
import com.savagevegetables.outbreak.entity.mob.stage2.ZucchiniEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ZucchiniRenderer extends MobRenderer<ZucchiniEntity, ZucchiniKillerModel<ZucchiniEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation ZUCCHINI_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/zucchini.png");

    public ZucchiniRenderer(EntityRendererProvider.Context context) {
        // 1.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new ZucchiniKillerModel<>(context.bakeLayer(ModelLayers.ZUCCHINI)), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(ZucchiniEntity entity) {
        return ZUCCHINI_TEXTURE;
    }
}