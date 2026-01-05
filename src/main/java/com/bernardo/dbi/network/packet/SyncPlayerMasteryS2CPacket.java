package com.bernardo.dbi.network.packet;

import com.bernardo.dbi.capability.IPlayerMastery;
import com.bernardo.dbi.capability.PlayerMasteryProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.server.level.ServerPlayer;
import com.bernardo.dbi.network.ModNetwork;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("null")
public class SyncPlayerMasteryS2CPacket {

    private final Map<String, Float> masteryLevels;
    private final Map<String, Float> masteryExp;

    public SyncPlayerMasteryS2CPacket(IPlayerMastery masteryCap) {
        this.masteryLevels = new HashMap<>();
        this.masteryExp = new HashMap<>();
        // Assumindo que masteryCap é PlayerMastery
        if (masteryCap instanceof com.bernardo.dbi.capability.PlayerMastery pm) {
            this.masteryLevels.putAll(pm.getMasteryLevels());
            this.masteryExp.putAll(pm.getMasteryExp());
        }
    }

    public SyncPlayerMasteryS2CPacket(Map<String, Float> masteryLevels, Map<String, Float> masteryExp) {
        this.masteryLevels = masteryLevels;
        this.masteryExp = masteryExp;
    }

    /* ---------- NETWORK ---------- */

    public static void encode(SyncPlayerMasteryS2CPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.masteryLevels.size());
        for (Map.Entry<String, Float> entry : msg.masteryLevels.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeFloat(entry.getValue());
        }
        buf.writeInt(msg.masteryExp.size());
        for (Map.Entry<String, Float> entry : msg.masteryExp.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeFloat(entry.getValue());
        }
    }

    public static SyncPlayerMasteryS2CPacket decode(FriendlyByteBuf buf) {
        Map<String, Float> levels = new HashMap<>();
        int levelsSize = buf.readInt();
        for (int i = 0; i < levelsSize; i++) {
            levels.put(buf.readUtf(), buf.readFloat());
        }
        Map<String, Float> exp = new HashMap<>();
        int expSize = buf.readInt();
        for (int i = 0; i < expSize; i++) {
            exp.put(buf.readUtf(), buf.readFloat());
        }
        return new SyncPlayerMasteryS2CPacket(levels, exp);
    }

    public static void handle(SyncPlayerMasteryS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            player.getCapability(PlayerMasteryProvider.MASTERY).ifPresent(masteryCap -> {
                if (masteryCap instanceof com.bernardo.dbi.capability.PlayerMastery pm) {
                    pm.setMasteryLevels(msg.masteryLevels);
                    pm.setMasteryExp(msg.masteryExp);
                }
            });
        });
        ctx.get().setPacketHandled(true);
    }

    public static void sync(ServerPlayer player) {
        player.getCapability(PlayerMasteryProvider.MASTERY).ifPresent(cap -> {
            ModNetwork.sendToPlayer(
                    player,
                    new SyncPlayerMasteryS2CPacket(cap)
            );
        });
    }
}