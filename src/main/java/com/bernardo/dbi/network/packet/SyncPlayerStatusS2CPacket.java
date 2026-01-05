package com.bernardo.dbi.network.packet;

import com.bernardo.dbi.capability.IPlayerStatus;
import com.bernardo.dbi.capability.PlayerStatusProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.server.level.ServerPlayer;
import com.bernardo.dbi.network.ModNetwork;

import java.util.function.Supplier;

public class SyncPlayerStatusS2CPacket {

    private final int str;
    private final int dex;
    private final int con;
    private final int spi;
    private final int will;
    private final int mnd;

    public SyncPlayerStatusS2CPacket(IPlayerStatus status) {
        this.str = status.getStr();
        this.dex = status.getDex();
        this.con = status.getCon();
        this.spi = status.getSpi();
        this.will = status.getWill();
        this.mnd = status.getMnd();
    }

    public SyncPlayerStatusS2CPacket(int str, int dex, int con, int spi, int will, int mnd) {
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.spi = spi;
        this.will = will;
        this.mnd = mnd;
    }

    /* ---------- NETWORK ---------- */

    public static void encode(SyncPlayerStatusS2CPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.str);
        buf.writeInt(msg.dex);
        buf.writeInt(msg.con);
        buf.writeInt(msg.spi);
        buf.writeInt(msg.will);
        buf.writeInt(msg.mnd);
    }

    public static SyncPlayerStatusS2CPacket decode(FriendlyByteBuf buf) {
        return new SyncPlayerStatusS2CPacket(
                buf.readInt(),
                buf.readInt(),
                buf.readInt(),
                buf.readInt(),
                buf.readInt(),
                buf.readInt()
        );
    }

    public static void handle(SyncPlayerStatusS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        Player player = Minecraft.getInstance().player;
            if (player == null) return;

            player.getCapability(PlayerStatusProvider.STATUS).ifPresent(status -> {
            status.setStr(msg.str);
            status.setDex(msg.dex);
            status.setCon(msg.con);
            status.setSpi(msg.spi);
            status.setWill(msg.will);
            status.setMnd(msg.mnd);
                 });
            });
            ctx.get().setPacketHandled(true);
        }
    
    public static void sync(ServerPlayer player) {
        player.getCapability(PlayerStatusProvider.STATUS).ifPresent(cap -> {
            ModNetwork.sendToPlayer(
                    player,
                    new SyncPlayerStatusS2CPacket(cap)
            );
        });
    }
 }

