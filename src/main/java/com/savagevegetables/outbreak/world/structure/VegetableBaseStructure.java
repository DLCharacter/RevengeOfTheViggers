package com.savagevegetables.outbreak.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import com.savagevegetables.outbreak.init.StructureInit;

import java.util.Optional;

public class VegetableBaseStructure extends Structure {

    public static final Codec<VegetableBaseStructure> CODEC = simpleCodec(VegetableBaseStructure::new);

    public VegetableBaseStructure(Structure.StructureSettings config) {
        super(config);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        int startY = context.chunkGenerator().getFirstOccupiedHeight(
                context.chunkPos().getMinBlockX(),
                context.chunkPos().getMinBlockZ(),
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(),
                context.randomState()
        );

        BlockPos blockPos = new BlockPos(context.chunkPos().getMinBlockX(), startY, context.chunkPos().getMinBlockZ());

        return Optional.of(new GenerationStub(blockPos, builder -> generatePieces(builder, context, blockPos)));
    }

    private void generatePieces(StructurePiecesBuilder builder, GenerationContext context, BlockPos pos) {
        builder.addPiece(new BasePiece(context.random(), pos.getX(), pos.getY(), pos.getZ()));
    }

    @Override
    public StructureType<?> type() {
        return StructureInit.VEGETABLE_BASE.get();
    }
}
