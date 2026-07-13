package com.savagevegetables.outbreak.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class CeleryBossModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart beard;
    private final ModelPart crown;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public CeleryBossModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("body").getChild("head");
        this.beard = this.head.getChild("beard");
        this.crown = this.head.getChild("crown");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Главный нижний стебель (Торс)
        PartDefinition body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -16.0F, -4.0F, 8.0F, 16.0F, 8.0F)
                        // Рельефные боковые ребра сельдерея
                        .texOffs(32, 0).addBox(-5.0F, -14.0F, -2.0F, 1.0F, 12.0F, 4.0F)
                        .texOffs(32, 0).addBox(4.0F, -14.0F, -2.0F, 1.0F, 12.0F, 4.0F),
                PartPose.offset(0.0F, 10.0F, 0.0F));

        // Голова (Верхняя часть стебля с ЛИЦОМ)
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
                        // Основа головы
                        .texOffs(0, 24).addBox(-4.0F, -14.0F, -4.0F, 8.0F, 14.0F, 8.0F)
                        // Особое внимание лицу: 3D-детали
                        // Массивные насупленные деревянные брови
                        .texOffs(0, 46).addBox(-4.5F, -11.0F, -5.5F, 9.0F, 2.0F, 2.0F)
                        // Длинный мудрый нос, как сучок дерева
                        .texOffs(0, 50).addBox(-1.0F, -9.0F, -6.0F, 2.0F, 5.0F, 2.0F)
                        // Глубокие скулы/кора по бокам лица
                        .texOffs(32, 16).addBox(-5.0F, -10.0F, -4.0F, 1.0F, 8.0F, 4.0F)
                        .texOffs(32, 16).addBox(4.0F, -10.0F, -4.0F, 1.0F, 8.0F, 4.0F),
                PartPose.offset(0.0F, -16.0F, 0.0F));

        // Длинная борода из свисающих корней сельдерея
        head.addOrReplaceChild("beard", CubeListBuilder.create()
                        .texOffs(0, 57).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 8.0F, 1.0F),
                PartPose.offset(0.0F, -3.0F, 0.0F));

        // Корона из листьев на макушке (Многослойная)
        PartDefinition crown = head.addOrReplaceChild("crown", CubeListBuilder.create()
                        // Центральный пучок стеблей зелени
                        .texOffs(48, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F),
                PartPose.offset(0.0F, -14.0F, 0.0F));

        // Раскидистые листья короны (направлены в разные стороны)
        crown.addOrReplaceChild("leaves_front", CubeListBuilder.create().texOffs(48, 12).addBox(-6.0F, -12.0F, -1.0F, 12.0F, 12.0F, 1.0F), PartPose.rotation(-0.2F, 0.0F, 0.0F));
        crown.addOrReplaceChild("leaves_back", CubeListBuilder.create().texOffs(48, 25).addBox(-6.0F, -12.0F, 0.0F, 12.0F, 12.0F, 1.0F), PartPose.rotation(0.2F, 0.0F, 0.0F));
        crown.addOrReplaceChild("leaves_left", CubeListBuilder.create().texOffs(74, 12).addBox(0.0F, -12.0F, -6.0F, 1.0F, 12.0F, 12.0F), PartPose.rotation(0.0F, 0.0F, -0.2F));
        crown.addOrReplaceChild("leaves_right", CubeListBuilder.create().texOffs(74, 25).addBox(-1.0F, -12.0F, -6.0F, 1.0F, 12.0F, 12.0F), PartPose.rotation(0.0F, 0.0F, 0.2F));

        // Мощные узловатые ноги-корни (длина 14 пикселей)
        CubeListBuilder legBuilder = CubeListBuilder.create().texOffs(0, 66).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F);
        rootPart.addOrReplaceChild("right_leg", legBuilder, PartPose.offset(-2.5F, 10.0F, 0.0F));
        rootPart.addOrReplaceChild("left_leg", legBuilder, PartPose.offset(2.5F, 10.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Величественный и медленный поворот головы за игроком
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F) * 0.6F;
        this.head.xRot = headPitch * ((float)Math.PI / 180F) * 0.5F;

        // Очень тяжелая, замедленная походка Энта (размашистые шаги)
        float walkSpeed = limbSwing * 0.3F;
        this.rightLeg.xRot = Mth.cos(walkSpeed) * 0.8F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(walkSpeed + (float)Math.PI) * 0.8F * limbSwingAmount;

        // Анимация «дыхания» мудрого дерева
        // Борода и корона листьев плавно колышутся в такт ветру
        float idleSway = Mth.sin(ageInTicks * 0.04F);
        this.beard.xRot = 0.05F + (idleSway * 0.08F);
        this.crown.zRot = idleSway * 0.03F;
        this.crown.xRot = (Mth.cos(ageInTicks * 0.04F) * 0.03F);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}