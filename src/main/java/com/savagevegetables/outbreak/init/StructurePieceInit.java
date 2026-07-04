package com.savagevegetables.outbreak.init;

import com.savagevegetables.outbreak.SavageVegetablesOutbreak;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class StructurePieceInit {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, SavageVegetablesOutbreak.MODID);

    public static final RegistryObject<StructurePieceType> BASE_PIECE = STRUCTURE_PIECE_TYPES.register("base_piece", () -> com.savagevegetables.outbreak.world.structure.BasePiece::new);
}
