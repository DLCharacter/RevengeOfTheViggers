package com.savagevegetables.outbreak.init;

import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SavageVegetablesOutbreak.MODID);

    public static final RegistryObject<CreativeModeTab> SAVAGE_VEGETABLES_TAB = CREATIVE_MODE_TABS.register("savage_vegetables_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.savage_vegetables_tab"))
            .icon(() -> new ItemStack(ItemInit.MUTAGENIC_FERTILIZER.get()))
            .displayItems((parameters, output) -> {
                output.accept(ItemInit.MUTAGENIC_FERTILIZER.get());
                output.accept(ItemInit.VEGGIE_AMMO.get());
                output.accept(ItemInit.FIREARM_AMMO.get());
                output.accept(ItemInit.RIFLE.get());
                output.accept(ItemInit.VEGGIE_BLASTER.get());
                output.accept(ItemInit.JERUSALEM_ARTICHOKE_SEED.get());
                output.accept(ItemInit.RUTABAGA_SEED.get());
                output.accept(ItemInit.CELERY_SEED.get());

                output.accept(BlockInit.MUTATED_SOIL.get());
                output.accept(BlockInit.JERUSALEM_ARTICHOKE_CROP.get());
                output.accept(BlockInit.RUTABAGA_CROP.get());
                output.accept(BlockInit.CELERY_CROP.get());

                output.accept(ItemInit.PUMPKIN_SPAWN_EGG.get());
                output.accept(ItemInit.TOMATO_SPAWN_EGG.get());
                output.accept(ItemInit.CORN_SPAWN_EGG.get());
                output.accept(ItemInit.JERUSALEM_ARTICHOKE_SPAWN_EGG.get());
                output.accept(ItemInit.RUTABAGA_SPAWN_EGG.get());
                output.accept(ItemInit.CELERY_SPAWN_EGG.get());
                output.accept(ItemInit.HYBRID_SPAWN_EGG.get());
                output.accept(ItemInit.EGGPLANT_SPAWN_EGG.get());
                output.accept(ItemInit.ZUCCHINI_SPAWN_EGG.get());
            }).build());
}
