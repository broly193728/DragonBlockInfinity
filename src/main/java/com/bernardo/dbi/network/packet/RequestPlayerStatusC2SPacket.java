package com.bernardo.dbi.network.packet;

import com.bernardo.dbi.capability.PlayerStatusProvider;
import com.bernardo.dbi.network.ModNetwork;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestPlayerStatusC2SPacket {

    // ===== CLIENT -> SERVER =====
    public RequestPlayerStatusC2SPacket() {}

    // ===== ENCODE (vazio) =====
    public static void encode(RequestPlayerStatusC2SPacket msg, FriendlyByteBuf buf) {
        // nada pra enviar
    }

    // ===== DECODE =====
    public static RequestPlayerStatusC2SPacket decode(FriendlyByteBuf buf) {
        return new RequestPlayerStatusC2SPacket();
    }

    // ===== HANDLE (SERVER SIDE) =====
    public static void handle(RequestPlayerStatusC2SPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            player.getCapability(PlayerStatusProvider.STATUS).ifPresent(status -> {
                ModNetwork.sendToPlayer(
                        player,
                        new SyncPlayerStatusS2CPacket(status)
                );
            });
        });

        ctx.get().setPacketHandled(true);
    }
}
