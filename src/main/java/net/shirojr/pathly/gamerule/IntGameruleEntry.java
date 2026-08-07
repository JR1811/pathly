package net.shirojr.pathly.gamerule;

import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Objects;

public record IntGameruleEntry(String name, GameRules.Key<GameRules.IntRule> rule, int defaultValue) {
    public static final HashSet<IntGameruleEntry> ALL_ENTRIES = new HashSet<>();

    @Nullable
    public static IntGameruleEntry get(String name) {
        for (IntGameruleEntry entry : ALL_ENTRIES) {
            if (entry.name().equals(name)) return entry;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IntGameruleEntry intGameruleEntry)) return false;
        return Objects.equals(name(), intGameruleEntry.name());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name());
    }
}
