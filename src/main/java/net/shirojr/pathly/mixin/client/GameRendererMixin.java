package net.shirojr.pathly.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.shirojr.pathly.init.PathlyTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements AutoCloseable {
    @WrapOperation(method = "updateTargetedEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;raycast(DFZ)Lnet/minecraft/util/hit/HitResult;"))
    private HitResult adjustRaycastForHandItem(Entity instance, double maxDistance, float tickDelta, boolean includeFluids, Operation<HitResult> original) {
        HitResult originalCall = original.call(instance, maxDistance, tickDelta, includeFluids);
        if (!(instance instanceof LivingEntity livingEntity)) {
            return originalCall;
        }
        if (!livingEntity.getMainHandStack().isIn(PathlyTags.ItemTags.MODIFIED_RAYCAST)) {
            return originalCall;
        }
        if (!(originalCall instanceof BlockHitResult blockHitResult)) return originalCall;
        World world = livingEntity.getWorld();
        BlockPos targetPos = blockHitResult.getBlockPos();
        if (!canPassThroughBlock(world, targetPos)) return originalCall;
        return recastRaycast(livingEntity, blockHitResult, maxDistance, tickDelta, includeFluids);
    }

    @Unique
    private static boolean canPassThroughBlock(WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.isIn(PathlyTags.BlockTags.RAYCAST_PASS_THROUGH)) return true;
        else return state.getCollisionShape(world, pos).isEmpty();
    }

    @Unique
    private static HitResult recastRaycast(Entity entity, BlockHitResult firstHit, double maxDistance, float tickDelta, boolean includeFluids) {
        Vec3d cameraPos = entity.getCameraPosVec(tickDelta);
        double consumedDistance = cameraPos.distanceTo(firstHit.getPos());
        double remainingDistance = maxDistance - consumedDistance;
        if (remainingDistance <= 0) {
            return firstHit;
        }
        Vec3d look = entity.getRotationVec(tickDelta);
        Vec3d offsetStart = firstHit.getPos().add(look.multiply(0.01));
        RaycastContext context = new RaycastContext(
                offsetStart, offsetStart.add(look.multiply(remainingDistance)), RaycastContext.ShapeType.OUTLINE,
                includeFluids ? RaycastContext.FluidHandling.ANY : RaycastContext.FluidHandling.NONE, entity
        );
        BlockHitResult secondHit = entity.getWorld().raycast(context);
        if (secondHit.getType() == HitResult.Type.BLOCK && canPassThroughBlock(entity.getWorld(), secondHit.getBlockPos())) {
            return recastRaycast(entity, secondHit, maxDistance, tickDelta, includeFluids);
        }
        return secondHit;
    }
}
