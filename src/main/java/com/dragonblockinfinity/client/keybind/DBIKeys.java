package com.dragonblockinfinity.client.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class DBIKeys {
    public static final String CATEGORY = "key.categories.dragonblockinfinity";

    public static final KeyMapping OPEN_EQUIPMENT = new KeyMapping(
        "key.dragonblockinfinity.open_equipment",
        KeyConflictContext.IN_GAME,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_V,
        CATEGORY
    );
}
