package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.EggplantBlimpModel;
import com.savagevegetables.outbreak.entity.mob.stage2.EggplantEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EggplantRenderer extends MobRenderer<EggplantEntity, EggplantBlimpModel<EggplantEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation EGGPLANT_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/eggplant.png");

    public EggplantRenderer(EntityRendererProvider.Context context) {
        // 2.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new EggplantBlimpModel<>(context.bakeLayer(ModelLayers.EGGPLANT)), 2.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(EggplantEntity entity) {
        return EGGPLANT_TEXTURE;
    }
}