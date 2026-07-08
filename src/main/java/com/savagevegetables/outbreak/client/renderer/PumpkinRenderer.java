package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.KillerPumpkinModel;
import com.savagevegetables.outbreak.entity.mob.vegetables.PumpkinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PumpkinRenderer extends MobRenderer<PumpkinEntity, KillerPumpkinModel<PumpkinEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation PUMPKIN_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/pumpkin.png");

    public PumpkinRenderer(EntityRendererProvider.Context context) {
        // 2.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new KillerPumpkinModel<>(context.bakeLayer(ModelLayers.PUMPKIN)), 0.9f);
    }

    @Override
    public ResourceLocation getTextureLocation(PumpkinEntity entity) {
        return PUMPKIN_TEXTURE;
    }
}