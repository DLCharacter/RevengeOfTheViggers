package com.savagevegetables.outbreak.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class KillerPumpkinModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart jaw;
    private final ModelPart[] legs = new ModelPart[4];

    public KillerPumpkinModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.jaw = root.getChild("jaw");
        this.legs[0] = root.getChild("leg_front_right");
        this.legs[1] = root.getChild("leg_front_left");
        this.legs[2] = root.getChild("leg_back_right");
        this.legs[3] = root.getChild("leg_back_left");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Верхняя, основная часть тыквы (голова)
        PartDefinition head = rootPart.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-7.0F, -8.0F, -7.0F, 14.0F, 8.0F, 14.0F),
                PartPose.offset(0.0F, 19.0F, 0.0F));

        // Плодоножка на макушке
        head.addOrReplaceChild("stem", CubeListBuilder.create()
                        .texOffs(60, 0).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Нижняя челюсть тыквы
        rootPart.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(0, 25).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 3.0F, 14.0F),
                PartPose.offset(0.0F, 19.0F, 0.0F));

        // Корни-лапки (4 штуки)
        CubeListBuilder legBuilder = CubeListBuilder.create().texOffs(60, 10).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F);

        rootPart.addOrReplaceChild("leg_front_right", legBuilder, PartPose.offset(-4.0F, 21.0F, -4.0F));
        rootPart.addOrReplaceChild("leg_front_left", legBuilder, PartPose.offset(4.0F, 21.0F, -4.0F));
        rootPart.addOrReplaceChild("leg_back_right", legBuilder, PartPose.offset(-4.0F, 21.0F, 4.0F));
        rootPart.addOrReplaceChild("leg_back_left", legBuilder, PartPose.offset(4.0F, 21.0F, 4.0F));

        return LayerDefinition.create(mesh, 128, 64); // Для тыквы хватит высоты текстуры 64
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Поворот головы (верхней части) за целью
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        // Челюсть повторяет поворот головы (yRot), но имеет свою анимацию открытия (xRot)
        this.jaw.yRot = this.head.yRot;

        // Анимация угрожающего клацанья челюстью (открывается вниз)
        this.jaw.xRot = this.head.xRot + (Math.abs(Mth.sin(ageInTicks * 0.2F)) * 0.5F);

        // Анимация семенящих лапок при ходьбе
        this.legs[0].xRot = Mth.cos(limbSwing * 1.5F) * 1.4F * limbSwingAmount;
        this.legs[1].xRot = Mth.cos(limbSwing * 1.5F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legs[2].xRot = Mth.cos(limbSwing * 1.5F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legs[3].xRot = Mth.cos(limbSwing * 1.5F) * 1.4F * limbSwingAmount;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}