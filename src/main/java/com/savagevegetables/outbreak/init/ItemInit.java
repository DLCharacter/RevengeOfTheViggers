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

    // Boss Seeds
    public static final RegistryObject<Item> JERUSALEM_ARTICHOKE_SEED = ITEMS.register("jerusalem_artichoke_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.JERUSALEM_ARTICHOKE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RUTABAGA_SEED = ITEMS.register("rutabaga_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.RUTABAGA_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CELERY_SEED = ITEMS.register("celery_seed", () -> new net.minecraft.world.item.ItemNameBlockItem(BlockInit.CELERY_CROP.get(), new Item.Properties()));

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
