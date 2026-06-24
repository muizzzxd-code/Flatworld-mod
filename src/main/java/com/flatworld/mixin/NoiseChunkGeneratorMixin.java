package com.flatworld.mixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseChunkGeneratorMixin {
    private static final int FLAT_Y = 64;

    @Inject(method = "buildSurface", at = @At("HEAD"))
    private void onBuildSurface(WorldGenLevel region, StructureManager structures,
                                RandomState randomState, ChunkAccess chunk, CallbackInfo ci) {
        ChunkPos chunkPos = chunk.getPos();
        int topY = chunk.getMaxBuildHeight();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int wx = chunkPos.getMinBlockX() + x;
                int wz = chunkPos.getMinBlockZ() + z;
                for (int y = FLAT_Y + 1; y < topY; y++) {
                    BlockPos pos = new BlockPos(wx, y, wz);
                    BlockState state = chunk.getBlockState(pos);
                    if (!state.isAir() && !state.is(Blocks.WATER) && !state.is(Blocks.LAVA)) {
                        chunk.setBlockState(pos, Blocks.AIR.defaultBlockState(), false);
                    }
                }
            }
        }
    }
          }
