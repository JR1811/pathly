package net.shirojr.pathly.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.shirojr.pathly.gamerule.BooleanGameruleEntry;
import net.shirojr.pathly.gamerule.IntGameruleEntry;
import net.shirojr.pathly.network.packet.SyncBooleanGameRuleS2CPacket;
import net.shirojr.pathly.network.packet.SyncIntGameRuleS2CPacket;

public class PathlyS2CNetworking {
    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(SyncIntGameRuleS2CPacket.TYPE, PathlyS2CNetworking::handleIntGameruleSync);
        ClientPlayNetworking.registerGlobalReceiver(SyncBooleanGameRuleS2CPacket.TYPE, PathlyS2CNetworking::handleBooleanGameruleSync);
    }

    private static void handleIntGameruleSync(SyncIntGameRuleS2CPacket packet, ClientPlayerEntity player, PacketSender sender) {
        String name = packet.name();
        IntGameruleEntry entry = IntGameruleEntry.get(name);
        int value = packet.value();
        MinecraftClient.getInstance().execute(() -> {
            if (entry == null) {
                throw new IllegalArgumentException("Gamerule not found for S2C sync: " + name);
            }
            GameRuleCache.INT_RULES.put(entry, value);
        });
    }

    private static void handleBooleanGameruleSync(SyncBooleanGameRuleS2CPacket packet, ClientPlayerEntity player, PacketSender sender) {
        String name = packet.name();
        BooleanGameruleEntry entry = BooleanGameruleEntry.get(name);
        boolean value = packet.value();
        MinecraftClient.getInstance().execute(() -> {
            if (entry == null) {
                throw new IllegalArgumentException("Gamerule not found for S2C sync: " + name);
            }
            GameRuleCache.BOOLEAN_RULES.put(entry, value);
        });
    }
}
