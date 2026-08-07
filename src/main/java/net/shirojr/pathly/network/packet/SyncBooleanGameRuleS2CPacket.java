package net.shirojr.pathly.network.packet;

import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.shirojr.pathly.Pathly;

import java.util.Collection;

public record SyncBooleanGameRuleS2CPacket(String name, boolean value) implements FabricPacket {
    public static final PacketType<SyncBooleanGameRuleS2CPacket> TYPE =
            PacketType.create(Pathly.getId("sync_boolean_gamerule"), SyncBooleanGameRuleS2CPacket::read);

    private static SyncBooleanGameRuleS2CPacket read(PacketByteBuf buf) {
        return new SyncBooleanGameRuleS2CPacket(buf.readString(), buf.readBoolean());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(this.name);
        buf.writeBoolean(this.value);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

    public void send(Collection<ServerPlayerEntity> targets) {
        targets.forEach(target -> ServerPlayNetworking.send(target, this));
    }
}
