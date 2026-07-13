package com.savagevegetables.outbreak.world.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.ScatteredFeaturePiece;
import com.savagevegetables.outbreak.init.StructurePieceInit;
import com.savagevegetables.outbreak.init.BlockInit;

public class BasePiece extends ScatteredFeaturePiece {

    public BasePiece(RandomSource random, int x, int y, int z) {
        super(StructurePieceInit.BASE_PIECE.get(), x, y, z, 16, 8, 16, getRandomHorizontalDirection(random));
    }

    public BasePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(StructurePieceInit.BASE_PIECE.get(), tag);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        super.addAdditionalSaveData(context, tag);
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
        // Build a simple 16x8x16 concrete box with a hollow inside
        this.generateBox(level, box, 0, 0, 0, 15, 7, 15, Blocks.STONE_BRICKS.defaultBlockState(), Blocks.STONE_BRICKS.defaultBlockState(), false);
        this.generateBox(level, box, 1, 1, 1, 14, 6, 14, Blocks.AIR.defaultBlockState(), Blocks.AIR.defaultBlockState(), false);

        // Add a chest with loot
        BlockPos chestPos = new BlockPos(this.getWorldX(8, 8), this.getWorldY(1), this.getWorldZ(8, 8));
        if (box.isInside(chestPos)) {
            level.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 2);
            // In a real mod we would set a loot table here, but for simplicity we skip setting exact nbt here, or use simple gen
        }

        // Add mutated soil platform in center
        BlockPos soilCenter = new BlockPos(this.getWorldX(4, 4), this.getWorldY(1), this.getWorldZ(4, 4));
        for(int dx = -1; dx <= 1; dx++) {
            for(int dz = -1; dz <= 1; dz++) {
                BlockPos sp = new BlockPos(this.getWorldX(4+dx, 4+dz), this.getWorldY(1), this.getWorldZ(4+dx, 4+dz));
                if (box.isInside(sp)) {
                    level.setBlock(sp, BlockInit.MUTATED_SOIL.get().defaultBlockState(), 2);
                }
            }
        }
    }
}
