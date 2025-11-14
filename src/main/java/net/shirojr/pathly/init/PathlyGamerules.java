package net.shirojr.pathly.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public interface PathlyGamerules {
    GameRules.Key<GameRules.BooleanRule> ENABLE_PATH_TOP_REPLACABLES = GameRuleRegistry.register("enablePathTopReplacables",
            GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

    static void initialize() {
        // static initialisation
    }
}
