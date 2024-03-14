package com.kimlan.catonchest.mixin;

import net.minecraft.block.ChestBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBlock.class)
abstract class ChestBlockMixin {
    @Shadow
    private static boolean hasBlockOnTop(BlockView world, BlockPos pos) { return false; };

    @Inject(
            method = "isChestBlocked",
            at = @At("RETURN"),
            cancellable = true)
    private static void injectIsChestBlocked(WorldAccess world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(hasBlockOnTop(world, pos));
    }
}
