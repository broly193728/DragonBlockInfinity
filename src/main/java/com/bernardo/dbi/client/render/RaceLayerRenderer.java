package com.bernardo.dbi.client.render;

import com.bernardo.dbi.capability.CapabilityInit;
import com.bernardo.dbi.capability.IPlayerRace;
import com.bernardo.dbi.race.Race;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;

public class RaceLayerRenderer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public RaceLayerRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LazyOptional<IPlayerRace> raceCap = player.getCapability(CapabilityInit.PLAYER_RACE);
        raceCap.ifPresent(race -> {
            Race.RaceType raceType = race.getRace();
            ResourceLocation texture = getRaceTexture(raceType);
            if (texture != null) {
                // Renderizar a textura da raça sobre o player
                this.getParentModel().renderToBuffer(poseStack, buffer.getBuffer(this.getParentModel().renderType(texture)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        });
    }

    private ResourceLocation getRaceTexture(Race.RaceType race) {
        String textureName = race.name().toLowerCase();
        if (race == Race.RaceType.Half_Sayajin) {
            textureName = "fsayajin";
        }
        return new ResourceLocation("dbi", "textures/player/" + textureName + ".png");
    }
}