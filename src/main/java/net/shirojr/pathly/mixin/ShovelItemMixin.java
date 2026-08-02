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
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.shirojr.pathly.init.PathlyGamerules;
import net.shirojr.pathly.init.PathlyTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin extends MiningToolItem {
    @Unique
    private static final int PATH_TOP_REPLACEABLE_SCAN_DEPTH = 3;

    @Shadow
    @Final
    protected static Map<Block, BlockState> PATH_STATES;

    private ShovelItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    @WrapOperation(method = "useOnBlock", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object forwardPathMakingDown(Map<Block, BlockState> instance, Object o, Operation<Object> original,
                                         @Local(argsOnly = true) ItemUsageContext context,
                                         @Local LocalRef<BlockPos> pos,
                                         @Local LocalRef<BlockState> state) {
        if (!context.getWorld().getGameRules().getBoolean(PathlyGamerules.ENABLE_PATH_TOP_REPLACABLES)) {
            return original.call(instance, o);
        }
        World world = context.getWorld();
        BlockPos.Mutable posWalker = pos.get().mutableCopy();
        List<BlockPos> toBeBroken = new ArrayList<>();

        for (int i = 0; i < PATH_TOP_REPLACEABLE_SCAN_DEPTH; i++) {
            BlockState blockState = world.getBlockState(posWalker);
            BlockState pathState = PATH_STATES.get(blockState.getBlock());
            if (pathState != null) {
                toBeBroken.forEach(posEntry ->
                        world.breakBlock(posEntry, true, context.getPlayer())
                );
                pos.set(posWalker.toImmutable());
                state.set(blockState);
                return pathState;
            }
            if (!isPathTopReplaceable(blockState)) {
                return null;
            }
            toBeBroken.add(posWalker.toImmutable());
            posWalker.move(Direction.DOWN);
        }

        return null;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    @Unique
    private static boolean isPathTopReplaceable(BlockState state) {
        return state.isReplaceable() || state.isIn(PathlyTags.BlockTags.PATH_TOP_REPLACABLES);
    }
}
