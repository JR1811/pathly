package net.shirojr.pathly.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shirojr.pathly.init.PathlyTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin extends MiningToolItem {
    @Shadow @Final protected static Map<Block, BlockState> PATH_STATES;

    private ShovelItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    @WrapOperation(method = "useOnBlock", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object forwardPathMakingDown(Map<Block, BlockState> instance, Object o, Operation<Object> original,
                                         @Local(argsOnly = true) ItemUsageContext context,
                                         @Local LocalRef<BlockPos> pos,
                                         @Local LocalRef<BlockState> state) {
        BlockState originalState = state.get();
        BlockPos originalPos = pos.get();
        BlockState originalPathState = PATH_STATES.get(originalState.getBlock());
        if (originalPathState != null) return originalPathState;
        World world = context.getWorld();
        BlockPos posBelow = originalPos.down();
        BlockState stateBelow = world.getBlockState(posBelow);
        BlockState pathStateBelow = PATH_STATES.get(stateBelow.getBlock());
        if (pathStateBelow == null) return null;
        if (!originalState.isReplaceable() && !originalState.isIn(PathlyTags.BlockTags.PATH_TOP_REPLACABLES)) return null;
        world.breakBlock(originalPos, true, context.getPlayer());
        pos.set(posBelow);
        state.set(stateBelow);
        return pathStateBelow;
    }
}
