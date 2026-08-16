package com.savagevegetables.outbreak.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class CornMonsterModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "dikiy_kukuruz"), "main");
	private final ModelPart bone4;
	private final ModelPart bone;
	private final ModelPart bone3;
	private final ModelPart bone2;
	private final ModelPart bb_main;

	public CornMonsterModel(ModelPart root) {
		this.bone4 = root.getChild("bone4");
		this.bone = root.getChild("bone");
		this.bone3 = root.getChild("bone3");
		this.bone2 = root.getChild("bone2");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(62, 20).addBox(-2.0F, -4.0F, 6.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 63).addBox(-7.0F, -20.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(56, 37).addBox(-7.0F, -19.0F, 1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(26, 25).addBox(-10.0F, -15.0F, -1.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 69).addBox(0.0F, -4.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 56).addBox(0.0F, -4.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 44).addBox(-9.0F, -17.0F, 0.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-11.0F, -13.0F, -2.0F, 9.0F, 15.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(68, 68).addBox(0.0F, -4.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 68).addBox(0.0F, -2.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 14).addBox(0.0F, -2.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 56).addBox(-2.0F, -5.0F, 0.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 52).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(62, 16).addBox(-2.0F, -4.0F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 18.0F, -3.0F));

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(45, 60).addBox(2.0F, 2.0F, -8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 17).addBox(-8.0F, -4.0F, -9.0F, 11.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(46, 44).addBox(-8.0F, -7.0F, -10.0F, 11.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 25).addBox(-6.0F, -10.0F, -10.0F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 0).addBox(-6.0F, -12.0F, -10.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 3).addBox(-5.0F, -14.0F, -9.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 65).addBox(-4.0F, -16.0F, -9.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 9).addBox(-2.0F, -15.0F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 5).addBox(0.0F, -11.0F, -10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 0).addBox(-8.0F, -4.0F, -8.0F, 1.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 42).addBox(-8.0F, -4.0F, 2.0F, 11.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 50).addBox(-8.0F, -7.0F, 3.0F, 11.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(-9.0F, -7.0F, -9.0F, 1.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(56, 29).addBox(-7.0F, -10.0F, 3.0F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 57).addBox(-5.0F, -12.0F, 2.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 61).addBox(-3.0F, -14.0F, 2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 70).addBox(-1.0F, -16.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 66).addBox(-2.0F, -15.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 7).addBox(-4.0F, -13.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 68).addBox(-6.0F, -11.0F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(35, 62).addBox(2.0F, 3.0F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 13.0F, 3.0F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 33).addBox(-2.0F, -2.0F, 1.0F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -8.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 59).addBox(-1.0F, -1.0F, 1.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -11.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 70).addBox(1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -13.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(60, 7).addBox(0.0F, -1.0F, 1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -14.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r5 = bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 70).addBox(1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 64).addBox(1.0F, 1.0F, 1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -18.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(8, 65).addBox(8.0F, -2.0F, 16.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 66).addBox(4.0F, -3.0F, 16.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 52).addBox(5.0F, -1.0F, 16.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(66, 57).addBox(6.0F, 2.0F, 16.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 50).addBox(7.0F, 4.0F, 16.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 53).addBox(8.0F, 3.0F, 16.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 67).addBox(9.0F, 1.0F, 16.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 67).addBox(8.0F, -1.0F, 16.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 64).addBox(3.0F, -8.0F, 16.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 18).addBox(10.0F, -1.0F, 16.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 70).addBox(9.0F, -3.0F, 16.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 15.0F, -9.0F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(8, 65).addBox(8.0F, -2.0F, 16.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 66).addBox(4.0F, -3.0F, 16.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 52).addBox(5.0F, -1.0F, 16.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(66, 57).addBox(6.0F, 2.0F, 16.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 50).addBox(7.0F, 4.0F, 16.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 53).addBox(8.0F, 3.0F, 16.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 67).addBox(9.0F, 1.0F, 16.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 67).addBox(8.0F, -1.0F, 16.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 64).addBox(3.0F, -8.0F, 16.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 18).addBox(10.0F, -1.0F, 16.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 70).addBox(9.0F, -3.0F, 16.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 15.0F, -24.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(26, 35).addBox(-3.0F, -4.0F, -4.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(46, 50).addBox(-2.0F, -3.0F, -3.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(12, 67).addBox(0.0F, -2.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 11).addBox(0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}