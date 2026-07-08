package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.ModelLayers;
import com.savagevegetables.outbreak.client.model.JerusalemArtichokeBossModel;
import com.savagevegetables.outbreak.client.model.ZucchiniKillerModel;
import com.savagevegetables.outbreak.entity.mob.boss.JerusalemArtichokeEntity;
import com.savagevegetables.outbreak.entity.mob.stage2.ZucchiniEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JerusalemArtichokeRenderer extends MobRenderer<JerusalemArtichokeEntity, JerusalemArtichokeBossModel<JerusalemArtichokeEntity>> {
    // Путь к твоей красивой текстуре
    private static final ResourceLocation JERUSALEM_ARTICHOKE_TEXTURE = new ResourceLocation("savage_vegetables", "textures/entity/jerusalem_artichoke.png");

    public JerusalemArtichokeRenderer(EntityRendererProvider.Context context) {
        // 1.0f в конце - это размер тени под мобом. Можешь поменять на свой!
        super(context, new JerusalemArtichokeBossModel<>(context.bakeLayer(ModelLayers.JERUSALEM_ARTICHOKE)), 0.2f);
    }

    @Override
    public ResourceLocation getTextureLocation(JerusalemArtichokeEntity entity) {
        return JERUSALEM_ARTICHOKE_TEXTURE;
    }
}