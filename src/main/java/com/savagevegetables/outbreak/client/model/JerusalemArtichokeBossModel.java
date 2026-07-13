package com.savagevegetables.outbreak.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class JerusalemArtichokeBossModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart mainStem;
    private final ModelPart upperLeaves;

    public JerusalemArtichokeBossModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.mainStem = this.body.getChild("main_stem");
        this.upperLeaves = this.mainStem.getChild("upper_leaves");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Маленькое, бугристое тело-корень (размер 8x8x8).
        // Расположено у самой земли, pivot-точка на Y=20.
        PartDefinition body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                        // Дополнительные бугорки, характерные для топинамбура
                        .texOffs(32, 0).addBox(-5.0F, -2.0F, -3.0F, 1.0F, 4.0F, 5.0F)
                        .texOffs(32, 0).addBox(4.0F, -3.0F, -2.0F, 1.0F, 5.0F, 4.0F),
                PartPose.offset(0.0F, 20.0F, 0.0F));

        // Здоровенный нижний стебель ботвы. Уходит вверх аж на 24 пикселя.
        PartDefinition mainStem = body.addOrReplaceChild("main_stem", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-2.0F, -24.0F, -2.0F, 4.0F, 24.0F, 4.0F)
                        // Огромные нижние листья-крылья, раскинутые в стороны
                        .texOffs(16, 16).addBox(-18.0F, -16.0F, 0.0F, 16.0F, 12.0F, 1.0F)
                        .texOffs(16, 29).addBox(2.0F, -16.0F, 0.0F, 16.0F, 12.0F, 1.0F),
                PartPose.offset(0.0F, -4.0F, 0.0F));

        // Верхняя часть ботвы (еще 24 пикселя вверх + корона листьев)
        // В сумме высота всей конструкции превышает тело более чем в 6 раз!
        mainStem.addOrReplaceChild("upper_leaves", CubeListBuilder.create()
                        // Продолжение стебля
                        .texOffs(0, 44).addBox(-1.5F, -24.0F, -1.5F, 3.0F, 24.0F, 3.0F)
                        // Гигантская верхняя розетка листьев (корона босса)
                        .texOffs(50, 0).addBox(-10.0F, -28.0F, -10.0F, 20.0F, 5.0F, 20.0F)
                        // Перекрестные верхушечные листья
                        .texOffs(0, 71).addBox(-8.0F, -36.0F, 0.0F, 16.0F, 8.0F, 1.0F)
                        .texOffs(0, 80).addBox(0.0F, -36.0F, -8.0F, 1.0F, 8.0F, 16.0F),
                PartPose.offset(0.0F, -24.0F, 0.0F));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Поворот злого лица в сторону игрока
        this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);

        // БОСС-анимация: инерция тяжелой ботвы при движении
        // Когда босс идет, всё его тело кренится вперед-назад от веса листьев
        float walkCycle = limbSwing * 0.4F;
        this.body.xRot = (headPitch * ((float)Math.PI / 180F)) + (Mth.sin(walkCycle) * 0.2F * limbSwingAmount);

        // Стебель гнется в противоположную сторону от наклона тела (создаем хлесткий эффект маятника)
        this.mainStem.xRot = -Mth.sin(walkCycle) * 0.25F * limbSwingAmount + (Mth.cos(ageInTicks * 0.05F) * 0.05F);
        this.mainStem.zRot = Mth.cos(walkCycle) * 0.15F * limbSwingAmount + (Mth.sin(ageInTicks * 0.05F) * 0.05F);

        // Верхушка ботвы колышется с задержкой (эффект мягкого растения)
        this.upperLeaves.xRot = -Mth.sin(walkCycle + 1.0F) * 0.15F * limbSwingAmount + (Mth.sin(ageInTicks * 0.07F) * 0.05F);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}