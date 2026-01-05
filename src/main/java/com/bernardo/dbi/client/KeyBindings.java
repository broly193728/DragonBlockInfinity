package com.bernardo.dbi.client;

import com.bernardo.dbi.client.screen.CharacterScreen;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "dbi", value = Dist.CLIENT)
public class KeyBindings {

    public static final KeyMapping OPEN_CHARACTER_MENU = new KeyMapping(
            "key.dbi.open_character_menu",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C, // Tecla C
            "key.categories.dbi"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CHARACTER_MENU);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = Minecraft.getInstance();
            if (OPEN_CHARACTER_MENU.consumeClick() && mc.screen == null) {
                mc.setScreen(new CharacterScreen());
            }
        }
    }
}