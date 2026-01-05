package com.bernardo.dbi.network.packet;

import com.bernardo.dbi.capability.IPlayerRace;
import com.bernardo.dbi.capability.PlayerRaceProvider;
import com.bernardo.dbi.race.Race;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.server.level.ServerPlayer;
import com.bernardo.dbi.network.ModNetwork;

import java.util.function.Supplier;

@SuppressWarnings("null")
public class SyncPlayerRaceS2CPacket {

    private final Race.RaceType race;

    public SyncPlayerRaceS2CPacket(IPlayerRace raceCap) {
        this.race = raceCap.getRace();
    }

    public SyncPlayerRaceS2CPacket(Race.RaceType race) {
        this.race = race;
    }

    /* ---------- NETWORK ---------- */

    public static void encode(SyncPlayerRaceS2CPacket msg, FriendlyByteBuf buf) {
        buf.writeEnum(msg.race);
    }

    public static SyncPlayerRaceS2CPacket decode(FriendlyByteBuf buf) {
        return new SyncPlayerRaceS2CPacket(buf.readEnum(Race.RaceType.class));
    }

    public static void handle(SyncPlayerRaceS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            player.getCapability(PlayerRaceProvider.RACE).ifPresent(raceCap -> {
                raceCap.setRace(msg.race);
            });
        });
        ctx.get().setPacketHandled(true);
    }

    public static void sync(ServerPlayer player) {
        player.getCapability(PlayerRaceProvider.RACE).ifPresent(cap -> {
            ModNetwork.sendToPlayer(
                    player,
                    new SyncPlayerRaceS2CPacket(cap)
            );
        });
    }
}