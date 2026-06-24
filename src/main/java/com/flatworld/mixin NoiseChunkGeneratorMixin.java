package com.flatworld.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    /**
     * Target permukaan datar = Y=64 (satu blok di atas sea level Y=63).
     * Semua blok solid di atas Y=64 akan dihapus sebelum surface builder berjalan.
     * Ini menjaga:
     *   - Semua biome (sistem biome tidak disentuh)
     *   - Semua struktur (stronghold, mineshaft, dll. di bawah tanah tetap ada)
     *   - Semua ore (ore berada di bawah Y=64)
     *   - Air laut dan danau (sea level Y=63 tidak disentuh)
     */
    private static final int FLAT_SURFACE_Y = 64;

    @Inject(method = "buildSurface", at = @At("HEAD"))
    private void onBuildSurface(WorldGenLevel region, StructureManager structures,
                                RandomState randomState, ChunkAccess chunk, CallbackInfo ci) {
        flattenSurface(chunk);
    }

    private static void flattenSurface(ChunkAccess chunk) {
        ChunkPos chunkPos = chunk.getPos();
        int topY = chunk.getMaxBuildHeight();

        for (int localX = 0; localX < 16; localX++) {
            for (int localZ = 0; localZ < 16; localZ++) {
                int worldX = chunkPos.getMinBlockX() + localX;
                int worldZ = chunkPos.getMinBlockZ() + localZ;

                for (int y = FLAT_SURFACE_Y + 1; y < topY; y++) {
                    BlockPos pos = new BlockPos(worldX, y, worldZ);
                    BlockState state = chunk.getBlockState(pos);

                    // Jangan hapus udara atau air/lava (biarkan danau dan laut tetap ada)
                    if (!state.isAir() && !state.is(Blocks.WATER) && !state.is(Blocks.LAVA)) {
                        chunk.setBlockState(pos, Blocks.AIR.defaultBlockState(), false);
                    }
                }
            }
        }
    }
}

