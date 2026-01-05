package com.bernardo.dbi;

import com.bernardo.dbi.race.RaceRegistry;
import com.bernardo.dbi.network.ModNetwork;

import com.bernardo.dbi.style.StyleRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.bernardo.dbi.capability.CapabilityInit;
import com.bernardo.dbi.client.KeyBindings;

public class ModRegister {

    public static void registerAll() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RaceRegistry.registerAll();
        StyleRegistry.registerAll();
        ModNetwork.register();

        CapabilityInit.register(); // 👈 AQUI
        
        // Registrar keybinds (já automático via @Mod.EventBusSubscriber, mas garantido aqui)
        modEventBus.addListener(KeyBindings::registerKeyMappings);
    }
}