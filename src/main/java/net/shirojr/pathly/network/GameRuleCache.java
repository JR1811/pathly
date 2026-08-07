package net.shirojr.pathly.network;

import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.shirojr.pathly.gamerule.BooleanGameruleEntry;
import net.shirojr.pathly.gamerule.IntGameruleEntry;

public class GameRuleCache {
    public static final Object2IntOpenHashMap<IntGameruleEntry> INT_RULES = new Object2IntOpenHashMap<>();
    public static final Object2BooleanOpenHashMap<BooleanGameruleEntry> BOOLEAN_RULES = new Object2BooleanOpenHashMap<>();

    public static int get(IntGameruleEntry entry, World world) {
        if (world instanceof ServerWorld serverWorld) {
            return serverWorld.getGameRules().getInt(entry.rule());
        } else {
            return INT_RULES.getOrDefault(entry, entry.defaultValue());
        }
    }

    public static boolean get(BooleanGameruleEntry entry, World world) {
        if (world instanceof ServerWorld serverWorld) {
            return serverWorld.getGameRules().getBoolean(entry.rule());
        } else {
            return BOOLEAN_RULES.getOrDefault(entry, entry.defaultValue());
        }
    }
}
