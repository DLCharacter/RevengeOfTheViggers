package com.savagevegetables.outbreak.init;

import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SavageVegetablesOutbreak.MODID);

    public static final RegistryObject<Block> MUTATED_SOIL = registerBlock("mutated_soil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> JERUSALEM_ARTICHOKE_CROP = BLOCKS.register("jerusalem_artichoke_crop", () -> new com.savagevegetables.outbreak.block.BossCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.JERUSALEM_ARTICHOKE_ENTITY));
    public static final RegistryObject<Block> RUTABAGA_CROP = BLOCKS.register("rutabaga_crop", () -> new com.savagevegetables.outbreak.block.BossCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.RUTABAGA_ENTITY));
    public static final RegistryObject<Block> CELERY_CROP = BLOCKS.register("celery_crop", () -> new com.savagevegetables.outbreak.block.BossCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), EntityInit.CELERY_ENTITY));

    public static final RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> EGGPLANT_CROP = BLOCKS.register("eggplant_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> ZUCCHINI_CROP = BLOCKS.register("zucchini_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> CUCUMBER_CROP = BLOCKS.register("cucumber_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> BROCCOLI_CROP = BLOCKS.register("broccoli_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
    public static final RegistryObject<Block> RADISH_CROP = BLOCKS.register("radish_crop", () -> new net.minecraft.world.level.block.CropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> MIMIC_PUMPKIN = BLOCKS.register("mimic_pumpkin", () -> new com.savagevegetables.outbreak.block.MimicPumpkinBlock(BlockBehaviour.Properties.copy(Blocks.PUMPKIN)));
    public static final RegistryObject<Block> MIMIC_TOMATO_CROP = BLOCKS.register("mimic_tomato_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.TOMATO_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_CORN_CROP = BLOCKS.register("mimic_corn_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.CORN_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_EGGPLANT_CROP = BLOCKS.register("mimic_eggplant_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.EGGPLANT_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_ZUCCHINI_CROP = BLOCKS.register("mimic_zucchini_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.ZUCCHINI_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_CUCUMBER_CROP = BLOCKS.register("mimic_cucumber_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.CUCUMBER_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_POTATO_CROP = BLOCKS.register("mimic_potato_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES), () -> EntityInit.POTATO_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_CARROT_CROP = BLOCKS.register("mimic_carrot_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.CARROTS), () -> EntityInit.CARROT_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_BROCCOLI_CROP = BLOCKS.register("mimic_broccoli_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.BROCCOLI_ENTITY.get()));
    public static final RegistryObject<Block> MIMIC_RADISH_CROP = BLOCKS.register("mimic_radish_crop", () -> new com.savagevegetables.outbreak.block.MimicCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), () -> EntityInit.RADISH_ENTITY.get()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
