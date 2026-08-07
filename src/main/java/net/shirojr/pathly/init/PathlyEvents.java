package net.shirojr.pathly.init;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.shirojr.pathly.event.PathlyConnectionEventsHandler;

public class PathlyEvents {
    private static final PathlyConnectionEventsHandler connectionEvents = new PathlyConnectionEventsHandler();


    public static void initializeCommon() {
        ServerPlayConnectionEvents.JOIN.register(connectionEvents);
    }

    public static void initializeClient() {

    }
}
