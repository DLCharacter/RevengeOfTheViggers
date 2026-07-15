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

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
