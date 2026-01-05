package com.bernardo.dbi.network;

import com.bernardo.dbi.Dbi;
import com.bernardo.dbi.network.packet.RequestPlayerStatusC2SPacket;
import com.bernardo.dbi.network.packet.SyncPlayerStatusS2CPacket;
import com.bernardo.dbi.network.packet.SyncPlayerRaceS2CPacket;
import com.bernardo.dbi.network.packet.SyncPlayerStyleS2CPacket;
import com.bernardo.dbi.network.packet.SyncPlayerFormS2CPacket;
import com.bernardo.dbi.network.packet.SyncPlayerMasteryS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class ModNetwork {

    public static final String PROTOCOL_VERSION = "1.0";
    public static SimpleChannel CHANNEL;

    private static int packetId = 0;
    private static int nextId() {
        return packetId++;
    }

    public static void register() {
        CHANNEL = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Dbi.MOD_ID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        // CLIENT -> SERVER
        CHANNEL.messageBuilder(RequestPlayerStatusC2SPacket.class, nextId())
                .encoder(RequestPlayerStatusC2SPacket::encode)
                .decoder(RequestPlayerStatusC2SPacket::decode)
                .consumerMainThread(RequestPlayerStatusC2SPacket::handle)
                .add();

        // SERVER -> CLIENT
        CHANNEL.messageBuilder(SyncPlayerStatusS2CPacket.class, nextId())
                .encoder(SyncPlayerStatusS2CPacket::encode)
                .decoder(SyncPlayerStatusS2CPacket::decode)
                .consumerMainThread(SyncPlayerStatusS2CPacket::handle)
                .add();

        CHANNEL.messageBuilder(SyncPlayerRaceS2CPacket.class, nextId())
                .encoder(SyncPlayerRaceS2CPacket::encode)
                .decoder(SyncPlayerRaceS2CPacket::decode)
                .consumerMainThread(SyncPlayerRaceS2CPacket::handle)
                .add();

        CHANNEL.messageBuilder(SyncPlayerStyleS2CPacket.class, nextId())
                .encoder(SyncPlayerStyleS2CPacket::encode)
                .decoder(SyncPlayerStyleS2CPacket::decode)
                .consumerMainThread(SyncPlayerStyleS2CPacket::handle)
                .add();

        CHANNEL.messageBuilder(SyncPlayerFormS2CPacket.class, nextId())
                .encoder(SyncPlayerFormS2CPacket::encode)
                .decoder(SyncPlayerFormS2CPacket::decode)
                .consumerMainThread(SyncPlayerFormS2CPacket::handle)
                .add();

        CHANNEL.messageBuilder(SyncPlayerMasteryS2CPacket.class, nextId())
                .encoder(SyncPlayerMasteryS2CPacket::encode)
                .decoder(SyncPlayerMasteryS2CPacket::decode)
                .consumerMainThread(SyncPlayerMasteryS2CPacket::handle)
                .add();
    }
    public static void sendToPlayer(ServerPlayer player, Object packet) {
        CHANNEL.send(
            PacketDistributor.PLAYER.with(() -> player),
            packet
        );
    }
}
