package com.bernardo.dbi.network.packet;

import com.bernardo.dbi.capability.IPlayerStyle;
import com.bernardo.dbi.capability.PlayerStyleProvider;
import com.bernardo.dbi.style.FightStyle;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.server.level.ServerPlayer;
import com.bernardo.dbi.network.ModNetwork;

import java.util.function.Supplier;

@SuppressWarnings("null")
public class SyncPlayerStyleS2CPacket {

    private final FightStyle style;

    public SyncPlayerStyleS2CPacket(IPlayerStyle styleCap) {
        this.style = styleCap.getStyle();
    }

    public SyncPlayerStyleS2CPacket(FightStyle style) {
        this.style = style;
    }

    /* ---------- NETWORK ---------- */

    public static void encode(SyncPlayerStyleS2CPacket msg, FriendlyByteBuf buf) {
        buf.writeEnum(msg.style);
    }

    public static SyncPlayerStyleS2CPacket decode(FriendlyByteBuf buf) {
        return new SyncPlayerStyleS2CPacket(buf.readEnum(FightStyle.class));
    }

    public static void handle(SyncPlayerStyleS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            player.getCapability(PlayerStyleProvider.STYLE).ifPresent(styleCap -> {
                styleCap.setStyle(msg.style);
            });
        });
        ctx.get().setPacketHandled(true);
    }

    public static void sync(ServerPlayer player) {
        player.getCapability(PlayerStyleProvider.STYLE).ifPresent(cap -> {
            ModNetwork.sendToPlayer(
                    player,
                    new SyncPlayerStyleS2CPacket(cap)
            );
        });
    }
}