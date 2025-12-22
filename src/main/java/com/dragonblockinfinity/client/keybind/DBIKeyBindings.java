package com.dragonblockinfinity.client.keybind;

import com.dragonblockinfinity.client.screen.CharacterCreationScreen;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DBIKeyBindings {
    public static final String CATEGORY = "key.categories.dragonblockinfinity";

    public static final KeyMapping OPEN_CHARACTER_CREATION = new KeyMapping(
        "key.dragonblockinfinity.character_creation",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_C,
        CATEGORY
    );

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CHARACTER_CREATION);
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                Minecraft mc = Minecraft.getInstance();
                while (OPEN_CHARACTER_CREATION.consumeClick()) {
                    mc.setScreen(new CharacterCreationScreen());
                }
            }
        }
    }
}
