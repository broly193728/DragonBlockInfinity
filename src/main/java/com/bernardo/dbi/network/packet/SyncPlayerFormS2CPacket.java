package com.bernardo.dbi.network.packet;

import com.bernardo.dbi.capability.IPlayerForm;
import com.bernardo.dbi.capability.PlayerFormProvider;
import com.bernardo.dbi.form.Form;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.server.level.ServerPlayer;
import com.bernardo.dbi.network.ModNetwork;

import java.util.function.Supplier;

@SuppressWarnings("null")
public class SyncPlayerFormS2CPacket {

    private final String formId;

    public SyncPlayerFormS2CPacket(IPlayerForm formCap) {
        this.formId = formCap.getForm().getId();
    }

    public SyncPlayerFormS2CPacket(String formId) {
        this.formId = formId;
    }

    /* ---------- NETWORK ---------- */

    public static void encode(SyncPlayerFormS2CPacket msg, FriendlyByteBuf buf) {
        buf.writeUtf(msg.formId);
    }

    public static SyncPlayerFormS2CPacket decode(FriendlyByteBuf buf) {
        return new SyncPlayerFormS2CPacket(buf.readUtf());
    }

    public static void handle(SyncPlayerFormS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            player.getCapability(PlayerFormProvider.FORM).ifPresent(formCap -> {
                // Para simplificar, assumindo Base
                formCap.setForm(Form.Base);
            });
        });
        ctx.get().setPacketHandled(true);
    }

    public static void sync(ServerPlayer player) {
        player.getCapability(PlayerFormProvider.FORM).ifPresent(cap -> {
            ModNetwork.sendToPlayer(
                    player,
                    new SyncPlayerFormS2CPacket(cap)
            );
        });
    }
}