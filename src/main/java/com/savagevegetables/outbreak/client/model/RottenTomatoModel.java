package com.savagevegetables.outbreak.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class RottenTomatoModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart body;

    public RottenTomatoModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Главное тело томата. Слегка сплюснуто по высоте (12x10x12).
        // Центр вращения (Y=19) рассчитан так, чтобы нижняя часть касалась земли (Y=24).
        PartDefinition body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 10.0F, 12.0F)

                        // Выпирающая гниющая часть (вмятина/рана на лицевой стороне)
                        .texOffs(0, 23).addBox(-4.0F, -2.0F, -7.0F, 8.0F, 6.0F, 1.0F),
                PartPose.offset(0.0F, 19.0F, 0.0F));

        // Усохшая плодоножка и остатки листьев
        body.addOrReplaceChild("stem", CubeListBuilder.create()
                        .texOffs(36, 23).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 1.0F, 6.0F)
                        .texOffs(48, 0).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Поворот в сторону движения/взгляда
        this.body.yRot = netHeadYaw * ((float) Math.PI / 180F);

        // Главная фишка: качение по земле!
        // limbSwing увеличивается при движении. Умножаем на коэффициент для скорости вращения.
        // Если он стоит на месте, limbSwingAmount падает до нуля, и вращение останавливается.
        this.body.xRot = limbSwing * 0.8F;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}