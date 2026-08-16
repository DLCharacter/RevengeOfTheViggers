package com.savagevegetables.outbreak.client.model.pepper;

import com.savagevegetables.outbreak.entity.mob.vegetables.BellPepperEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

// Меняем EntityModel на HierarchicalModel
public class BellPepperModel<T extends Entity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "bell_pepper"), "main");

	private final ModelPart root; // ДОБАВЛЕНО: нужно для HierarchicalModel
	private final ModelPart bell_pepper;
	private final ModelPart right_leg;
	private final ModelPart sprout;
	private final ModelPart body;
	private final ModelPart happy_one;
	private final ModelPart scared_one;
	private final ModelPart left_hand;
	private final ModelPart right_hand;
	private final ModelPart left_leg;

	public BellPepperModel(ModelPart root) {
		this.root = root; // Сохраняем корень
		this.bell_pepper = root.getChild("bell_pepper");
		this.right_leg = this.bell_pepper.getChild("right_leg");
		this.sprout = this.bell_pepper.getChild("sprout");
		this.body = this.bell_pepper.getChild("body");
		this.happy_one = this.body.getChild("happy_one");
		this.scared_one = this.body.getChild("scared_one");
		this.left_hand = this.bell_pepper.getChild("left_hand");
		this.right_hand = this.bell_pepper.getChild("right_hand");
		this.left_leg = this.bell_pepper.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		// Твой код геометрии остается АБСОЛЮТНО БЕЗ ИЗМЕНЕНИЙ!
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bell_pepper = partdefinition.addOrReplaceChild("bell_pepper", CubeListBuilder.create(), PartPose.offset(-1.0F, 24.0F, -0.6F));
		bell_pepper.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 10).addBox(1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 34).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		bell_pepper.addOrReplaceChild("sprout", CubeListBuilder.create().texOffs(4, 34).addBox(0.5F, 0.3F, -2.1F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 35).addBox(0.2F, -0.5F, -1.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 35).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -9.0F, 1.8F));

		PartDefinition body = bell_pepper.addOrReplaceChild("body", CubeListBuilder.create().texOffs(23, 13).addBox(-1.6F, -7.2F, -3.4F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(19, 23).addBox(-1.6F, -7.2F, 1.2F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(1, 22).addBox(-2.4F, -7.1F, -3.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(18, 31).addBox(-0.5F, -7.0F, -1.6F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.6F));
		body.addOrReplaceChild("happy_one", CubeListBuilder.create().texOffs(5, 12).addBox(0.7F, -6.2F, -1.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.8F, -0.9F, -1.6F));
		body.addOrReplaceChild("scared_one", CubeListBuilder.create().texOffs(24, 0).addBox(-0.3F, -6.2F, -1.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.7F, -0.9F, -1.6F));

		bell_pepper.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(30, 31).addBox(0.0F, -5.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 10).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 31).addBox(1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 21).addBox(0.6F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 23).addBox(-0.4F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, 3.8F));
		bell_pepper.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(0, 34).addBox(0.4F, -6.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 25).addBox(0.4F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 33).addBox(1.4F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 27).addBox(1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 29).addBox(0.0F, -2.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.6F, 0.0F, -2.8F));
		bell_pepper.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(28, 10).addBox(1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 34).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	// ДОБАВЛЕНО: Указывает корень модели
	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// ОЧЕНЬ ВАЖНО: Сбрасываем позицию всех частей перед анимацией
		this.root().getAllParts().forEach(ModelPart::resetPose);

		// Стандартная анимация ходьбы (когда он спокоен)
		this.right_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		// ЗАПУСК АНИМАЦИИ ИЗ КЛАССА JumpAnimation
		// Замени BellPepperEntity на класс твоего моба!
		if (entity instanceof BellPepperEntity pepper) {
			this.animate(pepper.jumpAnimationState, JumpAnimation.jump_of_nightmare, ageInTicks, 1.0F);
		}
	}

	// Метод renderToBuffer я удалил, так как HierarchicalModel автоматически отрисовывает root()!
}