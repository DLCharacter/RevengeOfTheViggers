package com.savagevegetables.outbreak.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class EggplantBlimpModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart jaw;
    private final ModelPart tailFins;

    public EggplantBlimpModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.jaw = this.body.getChild("jaw");
        this.tailFins = this.body.getChild("tail_fins");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        // Основное тело баклажана-дирижабля
        PartDefinition body = rootPart.addOrReplaceChild("body", CubeListBuilder.create()
                        // Центральная массивная часть
                        .texOffs(0, 0).addBox(-7.0F, -7.0F, -10.0F, 14.0F, 14.0F, 20.0F)
                        // Лобная часть (лицо)
                        .texOffs(0, 35).addBox(-5.0F, -6.0F, -16.0F, 10.0F, 9.0F, 6.0F)
                        // Заднее сужение (хвост)
                        .texOffs(33, 35).addBox(-5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 6.0F),
                PartPose.offset(0.0F, 8.0F, 0.0F)); // Центр тяжести поднят над землей

        // Отдельная нижняя челюсть
        body.addOrReplaceChild("jaw", CubeListBuilder.create()
                        .texOffs(0, 52).addBox(-4.0F, 0.0F, -6.0F, 8.0F, 3.0F, 6.0F),
                PartPose.offset(0.0F, 3.0F, -15.0F));

        // Зеленая плодоножка (хвостовые "плавники" дирижабля)
        body.addOrReplaceChild("tail_fins", CubeListBuilder.create()
                        // Основание хвостика
                        .texOffs(68, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 6.0F)
                        // Верхний лист
                        .texOffs(68, 11).addBox(-1.0F, -6.0F, 1.0F, 2.0F, 4.0F, 6.0F)
                        // Нижний лист
                        .texOffs(68, 11).addBox(-1.0F, 2.0F, 1.0F, 2.0F, 4.0F, 6.0F)
                        // Левый лист
                        .texOffs(85, 0).addBox(2.0F, -1.0F, 1.0F, 4.0F, 2.0F, 6.0F)
                        // Правый лист
                        .texOffs(85, 0).addBox(-6.0F, -1.0F, 1.0F, 4.0F, 2.0F, 6.0F),
                PartPose.offset(0.0F, 0.0F, 16.0F));

        // Размер текстуры 128x128 из-за больших габаритов моба
        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Эффект левитации (медленное покачивание вверх-вниз)
        this.body.y = 8.0F + Mth.sin(ageInTicks * 0.05F) * 3.0F;

        // Поворот в сторону цели (как у Гаста)
        this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.body.xRot = headPitch * ((float)Math.PI / 180F);

        // Вращение хвостовых листьев (как пропеллер)
        this.tailFins.zRot = ageInTicks * 0.2F;

        // Здесь ты можешь привязать открытие челюсти к стрельбе
        // Если он стреляет, можно сделать: this.jaw.xRot = 0.5F;
        // Иначе: this.jaw.xRot = 0.0F;
        this.jaw.xRot = 0.0F; // По умолчанию рот закрыт
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}