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

}
