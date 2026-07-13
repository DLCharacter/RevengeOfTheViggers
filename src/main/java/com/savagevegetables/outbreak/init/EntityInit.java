package com.savagevegetables.outbreak.init;

import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import com.savagevegetables.outbreak.entity.projectile.BulletProjectile;
import com.savagevegetables.outbreak.entity.projectile.VeggieProjectile;
import com.savagevegetables.outbreak.entity.mob.vegetables.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SavageVegetablesOutbreak.MODID);

    public static final RegistryObject<EntityType<BulletProjectile>> BULLET_PROJECTILE = ENTITY_TYPES.register("bullet_projectile",
            () -> EntityType.Builder.<BulletProjectile>of(BulletProjectile::new, MobCategory.MISC)
                    .sized(0.2f, 0.2f).clientTrackingRange(4).updateInterval(20).build("bullet_projectile"));

    public static final RegistryObject<EntityType<VeggieProjectile>> VEGGIE_PROJECTILE = ENTITY_TYPES.register("veggie_projectile",
            () -> EntityType.Builder.<VeggieProjectile>of(VeggieProjectile::new, MobCategory.MISC)
                    .sized(0.3f, 0.3f).clientTrackingRange(4).updateInterval(20).build("veggie_projectile"));

    // Stage 1 Vegetables
    public static final RegistryObject<EntityType<PumpkinEntity>> PUMPKIN_ENTITY = ENTITY_TYPES.register("pumpkin_entity",
            () -> EntityType.Builder.of(PumpkinEntity::new, MobCategory.MONSTER)
                    .sized(0.9f, 0.9f).build("pumpkin_entity"));

    public static final RegistryObject<EntityType<TomatoEntity>> TOMATO_ENTITY = ENTITY_TYPES.register("tomato_entity",
            () -> EntityType.Builder.of(TomatoEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 0.6f).build("tomato_entity"));

    public static final RegistryObject<EntityType<CornEntity>> CORN_ENTITY = ENTITY_TYPES.register("corn_entity",
            () -> EntityType.Builder.of(CornEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.5f, 1.5f).build("corn_entity"));

    public static final RegistryObject<EntityType<LeekEntity>> LEEK_ENTITY = ENTITY_TYPES.register("leek",
            () -> EntityType.Builder.of(LeekEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.6f, 1.8f).build("leek"));

    public static final RegistryObject<EntityType<CucumberEntity>> CUCUMBER_ENTITY = ENTITY_TYPES.register("cucumber",
            () -> EntityType.Builder.of(CucumberEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.5f, 1.5f).build("cucumber"));

    public static final RegistryObject<EntityType<CarrotEntity>> CARROT_ENTITY = ENTITY_TYPES.register("carrot",
            () -> EntityType.Builder.of(CarrotEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.6f, 1.8f).build("carrot"));

    public static final RegistryObject<EntityType<PotatoEntity>> POTATO_ENTITY = ENTITY_TYPES.register("potato",
            () -> EntityType.Builder.of(PotatoEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.8f, 0.8f).build("potato"));

    public static final RegistryObject<EntityType<BroccoliEntity>> BROCCOLI_ENTITY = ENTITY_TYPES.register("broccoli",
            () -> EntityType.Builder.of(BroccoliEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(1.2f, 1.8f).build("broccoli"));

    public static final RegistryObject<EntityType<RadishEntity>> RADISH_ENTITY = ENTITY_TYPES.register("radish",
            () -> EntityType.Builder.of(RadishEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                    .sized(0.8f, 0.8f).build("radish"));

    // Stage 2 Bosses
    public static final RegistryObject<EntityType<com.savagevegetables.outbreak.entity.mob.boss.JerusalemArtichokeEntity>> JERUSALEM_ARTICHOKE_ENTITY = ENTITY_TYPES.register("jerusalem_artichoke",
            () -> EntityType.Builder.of(com.savagevegetables.outbreak.entity.mob.boss.JerusalemArtichokeEntity::new, MobCategory.MONSTER)
                    .sized(2.0f, 2.5f).build("jerusalem_artichoke"));

    public static final RegistryObject<EntityType<com.savagevegetables.outbreak.entity.mob.boss.RutabagaEntity>> RUTABAGA_ENTITY = ENTITY_TYPES.register("rutabaga",
            () -> EntityType.Builder.of(com.savagevegetables.outbreak.entity.mob.boss.RutabagaEntity::new, MobCategory.MONSTER)
                    .sized(2.5f, 2.5f).build("rutabaga"));

    public static final RegistryObject<EntityType<com.savagevegetables.outbreak.entity.mob.boss.CeleryEntity>> CELERY_ENTITY = ENTITY_TYPES.register("celery",
            () -> EntityType.Builder.of(com.savagevegetables.outbreak.entity.mob.boss.CeleryEntity::new, MobCategory.MONSTER)
                    .sized(1.5f, 3.5f).build("celery"));

    // Stage 2 Entities
    public static final RegistryObject<EntityType<com.savagevegetables.outbreak.entity.mob.stage2.HybridEntity>> HYBRID_ENTITY = ENTITY_TYPES.register("hybrid",
            () -> EntityType.Builder.of(com.savagevegetables.outbreak.entity.mob.stage2.HybridEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("hybrid"));

    public static final RegistryObject<EntityType<com.savagevegetables.outbreak.entity.mob.stage2.EggplantEntity>> EGGPLANT_ENTITY = ENTITY_TYPES.register("eggplant",
            () -> EntityType.Builder.of(com.savagevegetables.outbreak.entity.mob.stage2.EggplantEntity::new, MobCategory.MONSTER)
                    .sized(4.0f, 4.0f).fireImmune().clientTrackingRange(10).build("eggplant"));

    public static final RegistryObject<EntityType<com.savagevegetables.outbreak.entity.mob.stage2.ZucchiniEntity>> ZUCCHINI_ENTITY = ENTITY_TYPES.register("zucchini",
            () -> EntityType.Builder.of(com.savagevegetables.outbreak.entity.mob.stage2.ZucchiniEntity::new, MobCategory.MONSTER)
                    .sized(0.9f, 0.5f).fireImmune().clientTrackingRange(8).build("zucchini"));
}
