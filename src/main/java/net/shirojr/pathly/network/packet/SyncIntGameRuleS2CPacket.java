package net.shirojr.pathly.network.packet;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.shirojr.pathly.Pathly;

import java.util.Collection;

public record SyncIntGameRuleS2CPacket(String name, int value) implements FabricPacket {
    public static final PacketType<SyncIntGameRuleS2CPacket> TYPE =
            PacketType.create(Pathly.getId("sync_int_gamerule"), SyncIntGameRuleS2CPacket::read);

    private static SyncIntGameRuleS2CPacket read(PacketByteBuf buf) {
        return new SyncIntGameRuleS2CPacket(buf.readString(), buf.readVarInt());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(this.name);
        buf.writeVarInt(this.value);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public void send(Collection<ServerPlayerEntity> targets) {
        targets.forEach(target -> ServerPlayNetworking.send(target, this));
    }
}
