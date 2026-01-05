package com.bernardo.dbi.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dbi")
public class PlayerCapabilityEvents {

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    new ResourceLocation("dbi", "player_status"),
                    new PlayerStatusProvider()
            );
            event.addCapability(
                    new ResourceLocation("dbi", "player_race"),
                    new PlayerRaceProvider()
            );
            event.addCapability(
                    new ResourceLocation("dbi", "player_style"),
                    new PlayerStyleProvider()
            );
            event.addCapability(
                    new ResourceLocation("dbi", "player_form"),
                    new PlayerFormProvider()
            );
            event.addCapability(
                    new ResourceLocation("dbi", "player_mastery"),
                    new PlayerMasteryProvider()
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        // Status
        event.getOriginal().getCapability(PlayerStatusProvider.STATUS).ifPresent(oldCap -> {
            event.getEntity().getCapability(PlayerStatusProvider.STATUS).ifPresent(newCap -> {
                newCap.copyFrom(oldCap);
            });
        });
        // Race
        event.getOriginal().getCapability(PlayerRaceProvider.RACE).ifPresent(oldCap -> {
            event.getEntity().getCapability(PlayerRaceProvider.RACE).ifPresent(newCap -> {
                newCap.copyFrom(oldCap);
            });
        });
        // Style
        event.getOriginal().getCapability(PlayerStyleProvider.STYLE).ifPresent(oldCap -> {
            event.getEntity().getCapability(PlayerStyleProvider.STYLE).ifPresent(newCap -> {
                newCap.copyFrom(oldCap);
            });
        });
        // Form
        event.getOriginal().getCapability(PlayerFormProvider.FORM).ifPresent(oldCap -> {
            event.getEntity().getCapability(PlayerFormProvider.FORM).ifPresent(newCap -> {
                newCap.copyFrom(oldCap);
            });
        });
        // Mastery
        event.getOriginal().getCapability(PlayerMasteryProvider.MASTERY).ifPresent(oldCap -> {
            event.getEntity().getCapability(PlayerMasteryProvider.MASTERY).ifPresent(newCap -> {
                newCap.copyFrom(oldCap);
            });
        });
    }
}