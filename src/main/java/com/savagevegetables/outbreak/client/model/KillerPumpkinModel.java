package com.savagevegetables.outbreak.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class KillerPumpkinModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "pumpkin"), "main");
    private final ModelPart upper_jaw;
    private final ModelPart bone3;
    private final ModelPart bone;
    private final ModelPart chub;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart lower_jaw;
    private final ModelPart bone2;
    private final ModelPart bone4;

    public KillerPumpkinModel(ModelPart root) {
        this.upper_jaw = root.getChild("upper_jaw");
        this.bone3 = this.upper_jaw.getChild("bone3");
        this.bone = this.upper_jaw.getChild("bone");
        this.chub = this.upper_jaw.getChild("chub");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.lower_jaw = root.getChild("lower_jaw");
        this.bone2 = this.lower_jaw.getChild("bone2");
        this.bone4 = this.lower_jaw.getChild("bone4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition upper_jaw = partdefinition.addOrReplaceChild("upper_jaw", CubeListBuilder.create(), PartPose.offset(0.0F, 12.75F, 1.25F));

        PartDefinition bone3 = upper_jaw.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -3.0F, -5.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition cube_r1 = bone3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(62, 6).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.25F, -2.25F, -0.6109F, 0.0F, -0.3491F));

        PartDefinition cube_r2 = bone3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.25F, 2.0F, 0.0378F, 0.0218F, -0.5232F));

        PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(26, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.5F, 0.0F, 0.4341F, -0.6599F, -0.2472F));

        PartDefinition cube_r4 = bone3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.25F, 2.0F, 0.202F, -0.0829F, 0.3843F));

        PartDefinition cube_r5 = bone3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(52, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, -3.5F, 0.2685F, 0.309F, -0.1421F));

        PartDefinition cube_r6 = bone3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(30, 63).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 1.75F, -3.75F, -0.0465F, 0.2577F, -0.1806F));

        PartDefinition cube_r7 = bone3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(66, 38).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 2.0F, 2.0F, 0.0873F, -0.2182F, 0.4363F));

        PartDefinition cube_r8 = bone3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(66, 36).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.25F, 0.0F, 0.3111F, -0.8088F, -0.0053F));

        PartDefinition bone = upper_jaw.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(26, 52).addBox(-12.0F, -7.0F, -6.0F, 12.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 0).addBox(-12.0F, -7.0F, -5.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-12.0F, -7.0F, 5.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 67).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(-11.0F, -7.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(18, 59).addBox(-1.0F, -3.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(40, 14).addBox(-1.0F, -7.0F, -5.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(52, 58).addBox(-1.0F, -3.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(68, 6).addBox(-1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 10).addBox(-1.0F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 27).addBox(-1.0F, -2.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 59).addBox(-7.0F, -3.0F, -6.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 36).addBox(-1.0F, -3.0F, -6.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 14).addBox(-4.0F, -3.0F, -6.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 59).addBox(-9.0F, -3.0F, -6.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 48).addBox(-12.0F, -3.0F, -6.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 62).addBox(-3.0F, -3.0F, -6.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 66).addBox(-1.0F, -3.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 10).addBox(-3.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 12).addBox(-5.0F, 3.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 19).addBox(-7.0F, 3.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 50).addBox(-11.0F, -1.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 14).addBox(-11.0F, 0.0F, -6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 59).addBox(-12.0F, -3.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(62, 41).addBox(-12.0F, -2.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 68).addBox(-12.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 21).addBox(-12.0F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 20).addBox(-12.0F, -2.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 2).addBox(-12.0F, -3.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 66).addBox(-12.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 57).addBox(-11.0F, -2.0F, 5.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 57).addBox(-10.0F, -1.0F, 5.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.5F, -0.25F, -0.6981F, 0.0F, 0.0F));

        PartDefinition chub = upper_jaw.addOrReplaceChild("chub", CubeListBuilder.create().texOffs(62, 67).addBox(1.6F, -1.55F, -1.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 67).addBox(2.0F, -2.25F, -0.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 65).addBox(1.0F, -0.9F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 65).addBox(0.0F, 0.75F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 66).addBox(1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.25F, 5.0F, -0.6981F, 0.0F, 0.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(54, 66).addBox(0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 64).addBox(0.1F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 64).addBox(1.1F, -3.4F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 66).addBox(-0.9F, -1.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 58).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.6F, 24.0F, -3.0F, 0.0F, -0.2618F, 0.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(66, 60).addBox(0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 64).addBox(-0.1F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 64).addBox(0.85F, -3.4F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 62).addBox(-0.9F, -1.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 67).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.15F, 24.0F, -1.75F, 0.0F, 2.7925F, 0.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(12, 67).addBox(0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 64).addBox(0.1F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 64).addBox(1.1F, -3.4F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 67).addBox(-0.9F, -1.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 67).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 24.0F, 5.75F, 0.0F, 1.2217F, 0.0F));

        PartDefinition lower_jaw = partdefinition.addOrReplaceChild("lower_jaw", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

        PartDefinition bone2 = lower_jaw.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(40, 40).addBox(5.0F, 1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 54).addBox(-6.0F, 1.0F, 5.0F, 12.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(-5.0F, 2.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(52, 52).addBox(-6.0F, 1.0F, -6.0F, 12.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 28).addBox(-6.0F, 1.0F, -5.0F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-5.0F, -1.5F, -5.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(62, 30).addBox(5.0F, 0.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 59).addBox(5.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(62, 33).addBox(5.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 0).addBox(2.0F, 0.0F, -6.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(68, 8).addBox(0.0F, 0.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 44).addBox(-3.0F, 0.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 62).addBox(-6.0F, 0.0F, -6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 46).addBox(-6.0F, 0.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 48).addBox(-6.0F, 0.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 57).addBox(-6.0F, 0.0F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 55).addBox(-6.0F, -1.0F, 5.0F, 12.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone4 = lower_jaw.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(6.0F, 2.0F, 0.0F));

        PartDefinition cube_r9 = bone4.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(28, 66).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -3.0F, -1.5F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r10 = bone4.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(62, 61).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -2.75F, 1.0F, 0.0873F, 0.0F, -0.2618F));

        PartDefinition cube_r11 = bone4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(66, 17).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.25F, -3.0F, -2.0F, -0.0832F, -0.0262F, -0.3043F));

        PartDefinition cube_r12 = bone4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(20, 66).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -3.25F, -0.5F, -0.4821F, -0.2129F, -0.3838F));

        PartDefinition cube_r13 = bone4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(36, 48).addBox(0.0F, -1.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -3.5F, -4.5F, 0.211F, 0.056F, -0.2559F));

        PartDefinition cube_r14 = bone4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(62, 58).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.0F, -4.5F, 0.2233F, 0.2129F, 0.0479F));

        PartDefinition cube_r15 = bone4.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(24, 66).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -3.25F, -0.5F, 0.258F, -0.045F, 0.1687F));

        PartDefinition cube_r16 = bone4.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(18, 63).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -2.75F, 0.75F, -0.1745F, 0.0F, -0.1745F));

        PartDefinition cube_r17 = bone4.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(62, 24).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -2.75F, -3.5F, 1.139F, 0.1964F, -0.3922F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Базовый поворот за целью (игрок/атакуемый)
        float targetYRot = netHeadYaw * ((float)Math.PI / 180F);
        float targetXRot = headPitch * ((float)Math.PI / 180F);

        // Фаза укуса (амплитуда от 0 до 1)
        float biteAnim = Math.abs(Mth.sin(ageInTicks * 0.2F));

        // 1. Анимация челюстей
        // Верхняя челюсть откидывается назад
        this.upper_jaw.yRot = targetYRot;
        this.upper_jaw.xRot = targetXRot - (biteAnim * 0.3F);

        // Нижняя челюсть падает вниз (открывая пасть)
        this.lower_jaw.yRot = targetYRot;
        this.lower_jaw.xRot = targetXRot + (biteAnim * 0.5F);

        // 2. Синхронизация и покачивание отростка (chub)
        this.chub.yRot = this.upper_jaw.yRot; // Следит за верхней челюстью по горизонтали

        float swaySpeed = ageInTicks * 0.15F;
        float swayAmplitude = 0.1F;

        // Ось X: следует за наклоном верхней челюсти + качается вперед-назад
        this.chub.xRot = this.upper_jaw.xRot + (Mth.sin(swaySpeed) * swayAmplitude);
        // Ось Z: добавляет боковое покачивание (круговой эффект)
        this.chub.zRot = Mth.cos(swaySpeed) * swayAmplitude;

        // 3. Анимация бега для 3 лапок
        this.leg1.xRot = Mth.cos(limbSwing * 1.5F) * 1.4F * limbSwingAmount;
        this.leg2.xRot = Mth.cos(limbSwing * 1.5F + (float)Math.PI) * 1.4F * limbSwingAmount;
        // Третья нога сдвинута по фазе, чтобы походка казалась хаотичной
        this.leg3.xRot = Mth.cos(limbSwing * 1.5F + ((float)Math.PI / 2F)) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        upper_jaw.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        lower_jaw.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}