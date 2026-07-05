package com.savagevegetables.outbreak.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class CornMonsterModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public CornMonsterModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Голова (сужающаяся макушка початка)
        rootPart.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F),
                PartPose.offset(0.0F, -6.0F, 0.0F));

        // Основное тело (сам початок)
        PartDefinition body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F),
                PartPose.offset(0.0F, -6.0F, 0.0F));

        // Лохмотья шелухи (листья, свисающие с тела)
        // Передний лист
        body.addOrReplaceChild("husk_front", CubeListBuilder.create()
                        .texOffs(24, 30).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 10.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 6.0F, -3.0F, -0.15F, 0.0F, 0.0F));
        // Задний лист
        body.addOrReplaceChild("husk_back", CubeListBuilder.create()
                        .texOffs(24, 30).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 10.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 6.0F, 3.0F, 0.15F, 0.0F, 0.0F));
        // Боковые листья
        body.addOrReplaceChild("husk_right", CubeListBuilder.create()
                        .texOffs(38, 30).addBox(-0.5F, 0.0F, -3.0F, 1.0F, 10.0F, 6.0F),
                PartPose.offsetAndRotation(-3.0F, 6.0F, 0.0F, 0.0F, 0.0F, 0.15F));
        body.addOrReplaceChild("husk_left", CubeListBuilder.create()
                        .texOffs(38, 30).addBox(-0.5F, 0.0F, -3.0F, 1.0F, 10.0F, 6.0F),
                PartPose.offsetAndRotation(3.0F, 6.0F, 0.0F, 0.0F, 0.0F, -0.15F));

        // Супер узкие и длинные руки (толщина 2x2, длина 18)
        rootPart.addOrReplaceChild("right_arm", CubeListBuilder.create()
                        .texOffs(24, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 18.0F, 2.0F),
                PartPose.offset(-4.0F, -4.0F, 0.0F));

        rootPart.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(24, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 18.0F, 2.0F),
                PartPose.offset(4.0F, -4.0F, 0.0F));

        // Супер узкие ноги (толщина 2x2, длина 14)
        rootPart.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(32, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F),
                PartPose.offset(-1.5F, 10.0F, 0.0F));

        rootPart.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(32, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F),
                PartPose.offset(1.5F, 10.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Поворот головы и тела за целью
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        // Тело слегка поворачивается вслед за головой для криповости
        this.body.yRot = this.head.yRot * 0.5F;

        // Жуткая, размашистая ходьба длинными ногами
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        // Руки болтаются в противофазе, как плети
        this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.2F * limbSwingAmount;
        this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;

        // Легкое подергивание рук в состоянии покоя
        this.rightArm.zRot = Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.zRot = Mth.cos(ageInTicks * 0.09F) * 0.05F - 0.05F;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}