package net.shirojr.pathly.gamerule;

import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Objects;

public record BooleanGameruleEntry(String name, GameRules.Key<GameRules.BooleanRule> rule, boolean defaultValue) {
    public static final HashSet<BooleanGameruleEntry> ALL_ENTRIES = new HashSet<>();

    @Nullable
    public static BooleanGameruleEntry get(String name) {
        for (BooleanGameruleEntry entry : ALL_ENTRIES) {
            if (entry.name().equals(name)) return entry;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BooleanGameruleEntry booleanGameruleEntry)) return false;
        return Objects.equals(name(), booleanGameruleEntry.name());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name());
    }
}
