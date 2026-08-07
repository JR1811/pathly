package net.shirojr.pathly.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.world.GameRules;
import net.shirojr.pathly.gamerule.BooleanGameruleEntry;
import net.shirojr.pathly.gamerule.IntGameruleEntry;
import net.shirojr.pathly.network.packet.SyncBooleanGameRuleS2CPacket;
import net.shirojr.pathly.network.packet.SyncIntGameRuleS2CPacket;

import java.util.function.BiFunction;

public interface PathlyGamerules {

    GameRules.Key<GameRules.BooleanRule> ENABLE_PATH_TOP_REPLACABLES = GameRuleRegistry.register("enablePathTopReplacables",
            GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    IntGameruleEntry PATH_TOP_REPLACABLES_SCAN_DEPTH = registerSyncableIntRule(
            "pathTopReplacablesScanDepth", 3,
            GameRules.Category.MISC,
            (name, defaultValue) -> GameRuleFactory.createIntRule(
                    defaultValue, 1,
                    (server, intRule) ->
                            new SyncIntGameRuleS2CPacket(name, intRule.get()).send(PlayerLookup.all(server))
            )
    );
    BooleanGameruleEntry MELEE_HITS_THROUGH_BLOCKS = registerSyncableBooleanRule(
            "meleeHitsThroughBlocks", true,
            GameRules.Category.MISC,
            (name, defaultValue) -> GameRuleFactory.createBooleanRule(
                    defaultValue,
                    (server, booleanRule) ->
                            new SyncBooleanGameRuleS2CPacket(name, booleanRule.get()).send(PlayerLookup.all(server))
            )
    );


    @SuppressWarnings("SameParameterValue")
    private static IntGameruleEntry registerSyncableIntRule(String name, int defaultValue, GameRules.Category category,
                                                            BiFunction<String, Integer, GameRules.Type<GameRules.IntRule>> typeFactory) {
        IntGameruleEntry registeredEntry = new IntGameruleEntry(
                name,
                GameRuleRegistry.register(name, category, typeFactory.apply(name, defaultValue)),
                defaultValue
        );
        IntGameruleEntry.ALL_ENTRIES.add(registeredEntry);
        return registeredEntry;
    }

    @SuppressWarnings("SameParameterValue")
    private static BooleanGameruleEntry registerSyncableBooleanRule(String name, boolean defaultValue, GameRules.Category category,
                                                                    BiFunction<String, Boolean, GameRules.Type<GameRules.BooleanRule>> typeFactory) {
        BooleanGameruleEntry registeredEntry = new BooleanGameruleEntry(
                name,
                GameRuleRegistry.register(name, category, typeFactory.apply(name, defaultValue)),
                defaultValue
        );
        BooleanGameruleEntry.ALL_ENTRIES.add(registeredEntry);
        return registeredEntry;
    }

    static void initialize() {
        // static initialisation
    }
}
