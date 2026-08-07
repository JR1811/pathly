package net.shirojr.pathly;

import net.fabricmc.api.ClientModInitializer;
import net.shirojr.pathly.init.PathlyEvents;
import net.shirojr.pathly.network.PathlyS2CNetworking;

public class PathlyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PathlyS2CNetworking.initialize();
        PathlyEvents.initializeClient();
    }
}
