package com.savagevegetables.outbreak.init;

import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructureInit {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, SavageVegetablesOutbreak.MODID);

    public static final RegistryObject<StructureType<com.savagevegetables.outbreak.world.structure.VegetableBaseStructure>> VEGETABLE_BASE = STRUCTURE_TYPES.register("vegetable_base", () -> () -> com.savagevegetables.outbreak.world.structure.VegetableBaseStructure.CODEC);
}
