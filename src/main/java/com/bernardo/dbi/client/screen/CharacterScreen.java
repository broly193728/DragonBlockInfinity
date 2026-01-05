package com.bernardo.dbi.client.screen;

import com.bernardo.dbi.race.Race;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("null")
public class CharacterScreen extends Screen {

    private static final ResourceLocation MENU_BACKGROUND =
        new ResourceLocation("dbi", "textures/gui/menu/menu_base.png");

    private static final ResourceLocation BUTTON_LEFT =
        new ResourceLocation("dbi", "textures/gui/button_seta_esquerda.png");

    private static final ResourceLocation BUTTON_RIGHT =
        new ResourceLocation("dbi", "textures/gui/button_seta_direita.png");

    private static final int MENU_WIDTH = 300;
    private static final int MENU_HEIGHT = 230;

    private @Nullable Player player;

    private Race.RaceType currentRace = Race.RaceType.Humano;

    private @Nullable Button leftButton;
    private @Nullable Button rightButton;

    private @Nullable Button hairButton;
    private @Nullable Button noseButton;
    private @Nullable Button mouthButton;

    private @Nullable Button confirmButton;

    private int menuX;
    private int menuY;

    public CharacterScreen() {
        super(Component.literal("Character Menu"));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    protected void init() {
        int safeMarginX = (int)(this.width * 0.08f);
        int safeMarginY = (int)(this.height * 0.08f);

        int safeWidth = this.width - safeMarginX * 2;
        int safeHeight = this.height - safeMarginY * 2;

        this.menuX = safeMarginX + (safeWidth - MENU_WIDTH) / 2;
        this.menuY = safeMarginY + (safeHeight - MENU_HEIGHT) / 2 +20;

        this.leftButton = this.addRenderableWidget(
            Button.builder(Component.empty(), button -> {
                Race.RaceType[] races = Race.RaceType.values();
                int index = this.currentRace.ordinal();
                this.currentRace = races[(index - 1 + races.length) % races.length];
            }).bounds(this.menuX + 100, this.menuY + 150, 12, 12).build()
        );

        this.rightButton = this.addRenderableWidget(
            Button.builder(Component.empty(), button -> {
                Race.RaceType[] races = Race.RaceType.values();
                int index = this.currentRace.ordinal();
                this.currentRace = races[(index + 1) % races.length];
            }).bounds(this.menuX + 188, this.menuY + 150, 12, 12).build()
        );

        this.hairButton = this.addRenderableWidget(
            Button.builder(Component.literal("C"), button -> {
            }).bounds(this.menuX + MENU_WIDTH - 60, this.menuY + 50, 20, 20).build()
        );

        this.noseButton = this.addRenderableWidget(
            Button.builder(Component.literal("N"), button -> {
            }).bounds(this.menuX + MENU_WIDTH - 35, this.menuY + 50, 20, 20).build()
        );

        this.mouthButton = this.addRenderableWidget(
            Button.builder(Component.literal("B"), button -> {
            }).bounds(this.menuX + MENU_WIDTH - 60, this.menuY + 75, 20, 20).build()
        );

        this.confirmButton = this.addRenderableWidget(
            Button.builder(Component.literal("OK"), button -> this.onClose()).bounds(this.menuX + (MENU_WIDTH - 50) / 2, this.menuY + MENU_HEIGHT - 30, 50, 20).build()
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);

        guiGraphics.blit(
            MENU_BACKGROUND,
            this.menuX,
            this.menuY,
            0,
            0,
            MENU_WIDTH,
            MENU_HEIGHT,
            MENU_WIDTH,
            MENU_HEIGHT
        );

        this.renderPlayerModel(guiGraphics, this.menuX + 50, this.menuY + 120);

        this.renderRaceTexture(guiGraphics, this.currentRace, this.menuX + 118, this.menuY + 50);

        String raceName = this.currentRace.name().replace("_", " ");
        int textX = this.menuX + (MENU_WIDTH / 2) - Minecraft.getInstance().font.width(raceName) / 2;

        guiGraphics.drawString(
            Minecraft.getInstance().font,
            raceName,
            textX,
            this.menuY + 120,
            0xFFFFFF
        );

        if (this.leftButton != null) {
            guiGraphics.blit(
                BUTTON_LEFT,
                this.leftButton.getX(),
                this.leftButton.getY(),
                0,
                0,
                this.leftButton.getWidth(),
                this.leftButton.getHeight(),
                this.leftButton.getWidth(),
                this.leftButton.getHeight()
            );
        }

        if (this.rightButton != null) {
            guiGraphics.blit(
                BUTTON_RIGHT,
                this.rightButton.getX(),
                this.rightButton.getY(),
                0,
                0,
                this.rightButton.getWidth(),
                this.rightButton.getHeight(),
                this.rightButton.getWidth(),
                this.rightButton.getHeight()
            );
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void renderPlayerModel(GuiGraphics guiGraphics, int x, int y) {
        if (this.player == null) {
            return;
        }

        PoseStack poseStack = guiGraphics.pose();
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();

        poseStack.pushPose();
        poseStack.translate(x, y, 100);
        poseStack.scale(0.8f, 0.8f, 0.8f);

        dispatcher.render(
            this.player,
            0,
            0,
            0,
            0,
            1,
            poseStack,
            Minecraft.getInstance().renderBuffers().bufferSource(),
            15728880
        );

        poseStack.popPose();
    }

    private void renderRaceTexture(GuiGraphics guiGraphics, Race.RaceType race, int x, int y) {
        String name = race.name().toLowerCase();

        if (race == Race.RaceType.Half_Sayajin) {
            name = "fsayajin";
        }

        ResourceLocation texture =
            new ResourceLocation("dbi", "textures/entity/player/race/" + name + ".png");

        guiGraphics.blit(texture, x, y, 0, 0, 64, 64, 64, 64);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}