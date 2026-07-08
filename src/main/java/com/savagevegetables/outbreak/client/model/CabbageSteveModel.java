package com.savagevegetables.outbreak.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class CabbageSteveModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public CabbageSteveModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.rightArm = this.body.getChild("right_arm");
        this.leftArm = this.body.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Главное тело (Торс Стива)
        PartDefinition body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(16, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F),
                PartPose.offset(0.0F, 12.0F, 0.0F));

        // Ростки, пробивающиеся из спины торса
        body.addOrReplaceChild("back_sprout", CubeListBuilder.create()
                        .texOffs(32, 32).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(-2.0F, -10.0F, 2.0F, 0.4F, 0.2F, 0.0F));

        // Голова (Левая часть человеческая, правая верхняя - капуста)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                        // Базовая голова Стива
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                        // Нарост капусты на ПРАВОЙ ВЕРХНЕЙ части головы (X от -4 до 0, Y от -9 до -4)
                        .texOffs(0, 32).addBox(-5.0F, -9.0F, -4.5F, 5.0F, 5.0F, 5.0F)
                        // Дополнительный выпирающий лист капусты
                        .texOffs(0, 42).addBox(-4.5F, -7.0F, -5.2F, 3.0F, 3.0F, 1.0F),
                PartPose.offset(0.0F, -12.0F, 0.0F));

        // Правая рука (Сильно мутировавшая, из нее торчат бугры)
        PartDefinition rightArm = body.addOrReplaceChild("right_arm", CubeListBuilder.create()
                        .texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F)
                        // Овощной шип/росток на плече
                        .texOffs(32, 32).addBox(-4.0F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F),
                PartPose.offset(-5.0F, -10.0F, 0.0F));

        // Левая рука (Обычная рука Стива, но с легким заражением)
        body.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.offset(5.0F, -10.0F, 0.0F));

        // Ноги (Стандартные ноги Стива)
        rootPart.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
                PartPose.offset(-1.9F, 12.0F, 0.0F));

        rootPart.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F)
                        // Росток, пробивающий колено левой ноги
                        .texOffs(32, 32).addBox(-1.0F, 4.0F, -3.0F, 2.0F, 2.0F, 1.0F),
                PartPose.offset(1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Обычная ходьба ногами
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        // Поворот головы с эффектом "сломанной шеи":
        // Голова поворачивается за игроком, но ВСЕГДА наклонена набок по оси Z из-за тяжелой капусты
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.zRot = 0.25F; // Постоянный наклон вправо

        // Агрессивная анимация атаки ближнего боя (руки вытянуты вперед как у зомби)
        // Но правая (тяжелая) рука опущена чуть ниже и совершает рваные замахи
        this.leftArm.xRot = -1.1F + Mth.sin(ageInTicks * 0.05F) * 0.05F;
        this.rightArm.xRot = -0.8F + Mth.cos(limbSwing * 0.3F) * 0.2F * limbSwingAmount;

        // Левое покачивание рук в стороны (эффект судорог)
        this.rightArm.zRot = 0.1F + Mth.sin(ageInTicks * 0.1F) * 0.05F;
        this.leftArm.zRot = -0.1F - Mth.sin(ageInTicks * 0.1F) * 0.05F;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}