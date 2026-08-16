package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.pepper.BellPepperModel;
import com.savagevegetables.outbreak.entity.mob.vegetables.BellPepperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BellPepperRenderer extends MobRenderer<BellPepperEntity, BellPepperModel<BellPepperEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation BELL_PEPPER_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/bell_pepper.png");

    public BellPepperRenderer(EntityRendererProvider.Context context) {
        // 1.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new BellPepperModel<>(context.bakeLayer(ModelLayers.BELL_PEPPER)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(BellPepperEntity entity) {
        return BELL_PEPPER_TEXTURE;
    }
}