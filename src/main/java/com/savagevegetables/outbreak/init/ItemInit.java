package com.savagevegetables.outbreak.init;

import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SavageVegetablesOutbreak.MODID);

    // Fertilizer and Ammo
    public static final RegistryObject<Item> MUTAGENIC_FERTILIZER = ITEMS.register("mutagenic_fertilizer", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VEGGIE_AMMO = ITEMS.register("veggie_ammo", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIREARM_AMMO = ITEMS.register("firearm_ammo", () -> new Item(new Item.Properties()));

    // Weapons
    public static final RegistryObject<Item> RIFLE = ITEMS.register("rifle", () -> new com.savagevegetables.outbreak.item.custom.FirearmItem(new Item.Properties().durability(300)));
    public static final RegistryObject<Item> VEGGIE_BLASTER = ITEMS.register("veggie_blaster", () -> new com.savagevegetables.outbreak.item.custom.VeggieWeaponItem(new Item.Properties().durability(300)));

    // Boss Fruits
    public static final RegistryObject<Item> JERUSALEM_ARTICHOKE_FRUIT = ITEMS.register("jerusalem_artichoke_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.0F).alwaysEat().build())));
    public static final RegistryObject<Item> RUTABAGA_FRUIT = ITEMS.register("rutabaga_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.0F).alwaysEat().build())));
    public static final RegistryObject<Item> CELERY_FRUIT = ITEMS.register("celery_fruit", () -> new com.savagevegetables.outbreak.item.custom.BossFruitItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(6).saturationMod(1.0F).alwaysEat().build())));

    // Boss Seeds
    public static final RegistryObject<Item> JERUSALEM_ARTICHOKE_SEED = ITEMS.register("jerusalem_artichoke_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.JERUSALEM_ARTICHOKE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RUTABAGA_SEED = ITEMS.register("rutabaga_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.RUTABAGA_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CELERY_SEED = ITEMS.register("celery_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CELERY_CROP.get(), new Item.Properties()));

    // Custom Seeds and Foods
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.TOMATO_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CORN_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> LEEK = ITEMS.register("leek", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> LEEK_SEEDS = ITEMS.register("leek_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.LEEK_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CUCUMBER_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> EGGPLANT = ITEMS.register("eggplant", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> EGGPLANT_SEEDS = ITEMS.register("eggplant_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.EGGPLANT_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> ZUCCHINI = ITEMS.register("zucchini", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> ZUCCHINI_SEEDS = ITEMS.register("zucchini_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.ZUCCHINI_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> BROCCOLI = ITEMS.register("broccoli", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> BROCCOLI_SEEDS = ITEMS.register("broccoli_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.BROCCOLI_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> RADISH = ITEMS.register("radish", () -> new com.savagevegetables.outbreak.item.custom.ProgressionFoodItem(new Item.Properties().food(new net.minecraft.world.food.FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> RADISH_SEEDS = ITEMS.register("radish_seeds", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.RADISH_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> LEEK_SPAWN_EGG = ITEMS.register("leek_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.LEEK_ENTITY, 0xFFFFFF, 0x00FF00, new Item.Properties()));
    public static final RegistryObject<Item> CUCUMBER_SPAWN_EGG = ITEMS.register("cucumber_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.CUCUMBER_ENTITY, 0x00FF00, 0x002200, new Item.Properties()));
    public static final RegistryObject<Item> POTATO_SPAWN_EGG = ITEMS.register("potato_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.POTATO_ENTITY, 0xDDBB77, 0xAA8855, new Item.Properties()));
    public static final RegistryObject<Item> CARROT_SPAWN_EGG = ITEMS.register("carrot_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.CARROT_ENTITY, 0xFF9900, 0x00FF00, new Item.Properties()));
    public static final RegistryObject<Item> BROCCOLI_SPAWN_EGG = ITEMS.register("broccoli_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.BROCCOLI_ENTITY, 0x00FF00, 0x00AA00, new Item.Properties()));
    public static final RegistryObject<Item> RADISH_SPAWN_EGG = ITEMS.register("radish_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.RADISH_ENTITY, 0xFF0055, 0xFFFFFF, new Item.Properties()));
    public static final RegistryObject<Item> BELL_PEPPER_SPAWN_EGG = ITEMS.register("bell_pepper_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.BELL_PEPPER_ENTITY, 0xFF0000, 0x00FF00, new Item.Properties()));


    // Spawn Eggs
    public static final RegistryObject<Item> PUMPKIN_SPAWN_EGG = ITEMS.register("pumpkin_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.PUMPKIN_ENTITY, 0xE3901D, 0x5E812F, new Item.Properties()));
    public static final RegistryObject<Item> TOMATO_SPAWN_EGG = ITEMS.register("tomato_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.TOMATO_ENTITY, 0xDF2020, 0x5E812F, new Item.Properties()));
    public static final RegistryObject<Item> CORN_SPAWN_EGG = ITEMS.register("corn_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.CORN_ENTITY, 0xF1D33D, 0x5E812F, new Item.Properties()));

    public static final RegistryObject<Item> JERUSALEM_ARTICHOKE_SPAWN_EGG = ITEMS.register("jerusalem_artichoke_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.JERUSALEM_ARTICHOKE_ENTITY, 0x8C7A5E, 0x4D4031, new Item.Properties()));
    public static final RegistryObject<Item> RUTABAGA_SPAWN_EGG = ITEMS.register("rutabaga_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.RUTABAGA_ENTITY, 0x9B6D87, 0xDFD495, new Item.Properties()));
    public static final RegistryObject<Item> CELERY_SPAWN_EGG = ITEMS.register("celery_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.CELERY_ENTITY, 0x76B648, 0x3E6325, new Item.Properties()));

    public static final RegistryObject<Item> HYBRID_SPAWN_EGG = ITEMS.register("hybrid_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.HYBRID_ENTITY, 0x3D3D3D, 0x76B648, new Item.Properties()));
    public static final RegistryObject<Item> EGGPLANT_SPAWN_EGG = ITEMS.register("eggplant_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.EGGPLANT_ENTITY, 0x461C4C, 0x5E812F, new Item.Properties()));
    public static final RegistryObject<Item> ZUCCHINI_SPAWN_EGG = ITEMS.register("zucchini_spawn_egg", () -> new net.minecraftforge.common.ForgeSpawnEggItem(EntityInit.ZUCCHINI_ENTITY, 0x304B26, 0xEBECC4, new Item.Properties()));
}
