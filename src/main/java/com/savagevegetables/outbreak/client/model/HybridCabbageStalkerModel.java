package com.savagevegetables.outbreak.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class HybridCabbageStalkerModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart sporeCannon;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public HybridCabbageStalkerModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        // Правая рука заменена на пушку, привязанную к телу
        this.sporeCannon = this.body.getChild("spore_cannon");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Тело (база Стива + наросты)
        PartDefinition body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F)
                        // Нарост на груди
                        .texOffs(0, 48).addBox(-3.0F, 2.0F, -3.0F, 4.0F, 4.0F, 1.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Голова (слева Стив, справа капуста)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Капустная мутация на голове (отдельный куб для объема)
        head.addOrReplaceChild("cabbage_mutation", CubeListBuilder.create()
                        .texOffs(32, 0).addBox(-1.0F, -9.0F, -5.0F, 6.0F, 7.0F, 6.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Левая рука (база Стива + ростки)
        body.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F)
                        .texOffs(0, 32).addBox(0.0F, 2.0F, -3.0F, 2.0F, 5.0F, 1.0F),
                PartPose.offset(5.0F, 2.0F, 0.0F));

        // Правая рука-пушка (Споровый кочан)
        PartDefinition sporeCannon = body.addOrReplaceChild("spore_cannon", CubeListBuilder.create()
                        // Основание пушки (плечо)
                        .texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F)
                        // Основной ствол-кочан
                        .texOffs(32, 42).addBox(-4.0F, 4.0F, -4.0F, 6.0F, 10.0F, 6.0F)
                        // Дуло (отверстие)
                        .texOffs(56, 42).addBox(-2.0F, 14.0F, -2.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offset(-5.0F, 2.0F, 0.0F));

        // Ноги (база Стива + ростки)
        rootPart.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.offset(1.9F, 12.0F, 0.0F));

        rootPart.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F)
                        .texOffs(0, 32).addBox(-3.0F, 4.0F, -3.0F, 1.0F, 4.0F, 1.0F),
                PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Анимация ходьбы
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        // Голова следит за целью
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        // Поза дальнего боя: рука-пушка всегда поднята и направлена вперед
        this.sporeCannon.xRot = -1.0F + (headPitch * ((float)Math.PI / 180F));

        // Эффект "пульсации" или подготовки к выстрелу (легкое вращение ствола)
        this.sporeCannon.yRot = Mth.sin(ageInTicks * 0.1F) * 0.05F;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}