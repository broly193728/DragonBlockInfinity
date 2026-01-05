package com.bernardo.dbi.network.packet;

import com.bernardo.dbi.capability.IPlayerStatus;
import com.bernardo.dbi.capability.PlayerStatusProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@SuppressWarnings("null")
public class SyncPlayerStatusPacket {

    private final int str, dex, con, spi, will, mnd;

    public SyncPlayerStatusPacket(IPlayerStatus data) {
        this.str = data.getStr();
        this.dex = data.getDex();
        this.con = data.getCon();
        this.spi = data.getSpi();
        this.will = data.getWill();
        this.mnd = data.getMnd();
    }

    public SyncPlayerStatusPacket(FriendlyByteBuf buf) {
        this.str = buf.readInt();
        this.dex = buf.readInt();
        this.con = buf.readInt();
        this.spi = buf.readInt();
        this.will = buf.readInt();
        this.mnd = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(str);
        buf.writeInt(dex);
        buf.writeInt(con);
        buf.writeInt(spi);
        buf.writeInt(will);
        buf.writeInt(mnd);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.getCapability(PlayerStatusProvider.STATUS).ifPresent(cap -> {
                    cap.setStr(str);
                    cap.setDex(dex);
                    cap.setCon(con);
                    cap.setSpi(spi);
                    cap.setWill(will);
                    cap.setMnd(mnd);
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }

    /* ===== SERVER SEND ===== */
    public static void sync(ServerPlayer player) {
        player.getCapability(PlayerStatusProvider.STATUS).ifPresent(cap -> {
            // enviar usando ModNetwork
        });
    }
}
