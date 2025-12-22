package com.dragonblockinfinity.client.screen;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.race.Race;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CharacterCreationScreen extends Screen {
    private static final ResourceLocation BG = 
        new ResourceLocation(DragonBlockInfinity.MOD_ID, "textures/gui/menu/menu_inicial1.png");

    private static final int GUI_WIDTH = 256;
    private static final int GUI_HEIGHT = 256;

    private Race selectedRace = Race.SAIYAN_PURE;
    private int eyeType = 0, hairType = 0, noseType = 0, mouthType = 0;
    private static final int MAX_EYE = 6, MAX_HAIR = 12, MAX_NOSE = 4, MAX_MOUTH = 4;

    public CharacterCreationScreen() {
        super(Component.literal("Character Creation"));
    }

    @Override
    protected void init() {}

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = (this.width - GUI_WIDTH) / 2;
        int y = (this.height - GUI_HEIGHT) / 2;
        int relX = (int)(mouseX - x);
        int relY = (int)(mouseY - y);

        int btnSize = 16; // TAMANHO JINRYUU

        // RAÇA
        if (relX >= 50 && relX <= 66 && relY >= 20 && relY <= 36) {
            cycleRace(-1);
            return true;
        }
        if (relX >= 190 && relX <= 206 && relY >= 20 && relY <= 36) {
            cycleRace(1);
            return true;
        }

        // CUSTOMIZAÇÃO  
        int minusX = 50;
        int plusX = 190;
        int[] linesY = {85, 115, 145, 175};

        for (int i = 0; i < 4; i++) {
            if (relX >= minusX && relX <= minusX + btnSize && 
                relY >= linesY[i] && relY <= linesY[i] + btnSize) {
                decrementCustomization(i);
                return true;
            }
            if (relX >= plusX && relX <= plusX + btnSize && 
                relY >= linesY[i] && relY <= linesY[i] + btnSize) {
                incrementCustomization(i);
                return true;
            }
        }

        // OK
        if (relX >= 100 && relX <= 156 && relY >= 215 && relY <= 235) {
            onConfirm();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void cycleRace(int d) {
        Race[] r = Race.values();
        selectedRace = r[(selectedRace.ordinal() + d + r.length) % r.length];
    }

    private void decrementCustomization(int line) {
        switch (line) {
            case 0 -> eyeType = (eyeType - 1 + MAX_EYE) % MAX_EYE;
            case 1 -> hairType = (hairType - 1 + MAX_HAIR) % MAX_HAIR;
            case 2 -> noseType = (noseType - 1 + MAX_NOSE) % MAX_NOSE;
            case 3 -> mouthType = (mouthType - 1 + MAX_MOUTH) % MAX_MOUTH;
        }
    }

    private void incrementCustomization(int line) {
        switch (line) {
            case 0 -> eyeType = (eyeType + 1) % MAX_EYE;
            case 1 -> hairType = (hairType + 1) % MAX_HAIR;
            case 2 -> noseType = (noseType + 1) % MAX_NOSE;
            case 3 -> mouthType = (mouthType + 1) % MAX_MOUTH;
        }
    }

    private void onConfirm() {
        this.minecraft.setScreen(null);
    }

    private void drawButton(GuiGraphics g, int x, int y, String s) {
        int size = 16;
        g.fill(x - 1, y - 1, x + size + 1, y + size + 1, 0xDD000000);
        g.fill(x, y, x + size, y + size, 0xFFFFD700);
        
        int sx = x + (size - this.font.width(s)) / 2;
        int sy = y + (size - 8) / 2;
        g.drawString(this.font, s, sx, sy, 0xFF000000, false);
    }

    private void drawText(GuiGraphics g, String t, int x, int y, int c) {
        int p = 0xFF000000;
        for (int dx = -1; dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++)
                if (dx != 0 || dy != 0)
                    g.drawString(this.font, t, x + dx, y + dy, p, false);
        g.drawString(this.font, t, x, y, c, false);
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        int x = (this.width - GUI_WIDTH) / 2;
        int y = (this.height - GUI_HEIGHT) / 2;

        RenderSystem.setShaderTexture(0, BG);
        g.blit(BG, x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT, 256, 256);

        drawButton(g, x + 50, y + 20, "◄");
        drawButton(g, x + 190, y + 20, "►");
        
        String rn = selectedRace.getDisplayName();
        drawText(g, rn, x + 128 - this.font.width(rn) / 2, y + 25, 0xFFFF00);

        String[] l = {"Eyes" + (eyeType + 1), "Hair" + (hairType + 1), 
                     "Nose" + (noseType + 1), "Mouth" + (mouthType + 1)};
        int[] ly = {85, 115, 145, 175};

        for (int i = 0; i < 4; i++) {
            drawButton(g, x + 50, y + ly[i], "◄");
            drawButton(g, x + 190, y + ly[i], "►");
            drawText(g, l[i], x + 128 - this.font.width(l[i]) / 2, y + ly[i] + 4, 0xFFFFFF);
        }

        int okx = x + 128 - this.font.width("[ OK ]") / 2;
        g.fill(okx - 3, y + 213, okx + this.font.width("[ OK ]") + 3, y + 225, 0xAA000000);
        drawText(g, "[ OK ]", okx, y + 215, 0xFFFF00);

        super.render(g, mx, my, pt);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
