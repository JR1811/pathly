package net.shirojr.pathly.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class PathlyGamerules {
    GameRules.Key<GameRules.BooleanRule> PATH_TOP_REPLACABLES = GameRuleRegistry.register("enablePathTopReplacables",
            GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

    public static void initialize() {
        // static initialisation
    }
}
