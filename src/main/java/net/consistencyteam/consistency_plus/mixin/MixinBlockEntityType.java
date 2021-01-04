/*
 * Improved Stations by shedaniel.
 * Licensed under the MIT.
 */

package net.consistencyteam.consistency_plus.mixin;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.consistencyteam.consistency_plus.ConsistencyPlus;
import net.consistencyteam.consistency_plus.hooks.BlockEntityTypeHooks;
import net.consistencyteam.consistency_plus.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Mixin(BlockEntityType.class)
public class MixinBlockEntityType implements BlockEntityTypeHooks {
    @Shadow @Final public static BlockEntityType<FurnaceBlockEntity> FURNACE;
    @Shadow @Final public static BlockEntityType<SmokerBlockEntity> SMOKER;
    @Shadow @Final public static BlockEntityType<BlastFurnaceBlockEntity> BLAST_FURNACE;
    @Shadow @Final public static BlockEntityType<JukeboxBlockEntity> JUKEBOX;
    @Shadow @Mutable @Final private Set<Block> blocks;
    
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void afterStaticInit(CallbackInfo info) {
        ((BlockEntityTypeHooks) FURNACE).cplus_applyMoreBlocks(Blocks.SMOOTH_FURNACE);
    }
    
    @Override
    public void cplus_applyMoreBlocks(Block... blocks) {
        List<Block> list = Lists.newArrayList(this.blocks);
        Collections.addAll(list, blocks);
        this.blocks = ImmutableSet.copyOf(list);
    }
}