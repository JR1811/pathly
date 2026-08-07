package net.shirojr.pathly.event;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.shirojr.pathly.gamerule.BooleanGameruleEntry;
import net.shirojr.pathly.gamerule.IntGameruleEntry;
import net.shirojr.pathly.network.packet.SyncBooleanGameRuleS2CPacket;
import net.shirojr.pathly.network.packet.SyncIntGameRuleS2CPacket;

import java.util.Set;

public class PathlyConnectionEventsHandler implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        for (IntGameruleEntry syncableIntRule : IntGameruleEntry.ALL_ENTRIES) {
            int value = server.getGameRules().getInt(syncableIntRule.rule());
            new SyncIntGameRuleS2CPacket(syncableIntRule.name(), value).send(Set.of(handler.getPlayer()));
        }
        for (BooleanGameruleEntry syncableBooleanRule : BooleanGameruleEntry.ALL_ENTRIES) {
            boolean value = server.getGameRules().getBoolean(syncableBooleanRule.rule());
            new SyncBooleanGameRuleS2CPacket(syncableBooleanRule.name(), value).send(Set.of(handler.getPlayer()));
        }
    }
}
