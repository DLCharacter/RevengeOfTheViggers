package com.savagevegetables.outbreak.client.renderer;

import com.savagevegetables.outbreak.client.model.BasicCubeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class VegetableRenderer<T extends Mob> extends MobRenderer<T, BasicCubeModel<T>> {

    private final ResourceLocation texture;

    public VegetableRenderer(EntityRendererProvider.Context context, net.minecraft.client.model.geom.ModelLayerLocation layer, String textureName, float shadowSize) {
        super(context, new BasicCubeModel<>(context.bakeLayer(layer)), shadowSize);
        this.texture = new ResourceLocation("savage_vegetables", "textures/entity/" + textureName + ".png");
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return texture;
    }
}
