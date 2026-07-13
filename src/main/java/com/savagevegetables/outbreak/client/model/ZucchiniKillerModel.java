package com.savagevegetables.outbreak.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class ZucchiniKillerModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public ZucchiniKillerModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.leftWing = this.body.getChild("left_wing");
        this.rightWing = this.body.getChild("right_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Главное тело кабачка (Центр тяжести)
        PartDefinition body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        // Центральная, самая толстая часть кабачка
                        .texOffs(0, 0).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 10.0F, 14.0F)
                        // Передняя часть (Лоб и лицо) - сужается
                        .texOffs(0, 25).addBox(-5.0F, -4.0F, -15.0F, 10.0F, 8.0F, 8.0F)
                        // Задняя часть (Хвост) - сильно сужается для обтекаемости
                        .texOffs(0, 42).addBox(-4.0F, -3.0F, 7.0F, 8.0F, 6.0F, 10.0F)
                        // Маленький хуёчек на голове (как в PvZ)
                        .texOffs(60, 20).addBox(-1.0F, -9.0F, -12.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Левое крыло-лист (как у фантома)
        body.addOrReplaceChild("left_wing", CubeListBuilder.create()
                        .texOffs(60, 0).addBox(0.0F, 0.0F, -4.0F, 12.0F, 1.0F, 8.0F),
                PartPose.offset(7.0F, -2.0F, -2.0F));

        // Правое крыло-лист
        body.addOrReplaceChild("right_wing", CubeListBuilder.create()
                        .texOffs(60, 10).addBox(-12.0F, 0.0F, -4.0F, 12.0F, 1.0F, 8.0F),
                PartPose.offset(-7.0F, -2.0F, -2.0F));

        // Текстура 128x128 для хорошей детализации
        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Поворот тела за взглядом
        this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.body.xRot = headPitch * ((float)Math.PI / 180F);

        // Легкое покачивание тела в полете
        this.body.y = Mth.cos(ageInTicks * 0.1F) * 2.0F;

        // Взмахи крыльями-листьями (эффект полета фантома)
        // Если моб пикирует или ускоряется, можно умножить скорость на другой коэффициент
        float flapSpeed = ageInTicks * 0.2F;
        this.leftWing.zRot = Mth.cos(flapSpeed) * 0.3F;
        this.rightWing.zRot = -Mth.cos(flapSpeed) * 0.3F;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}