package com.dragonblockinfinity.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemDragonRadar extends Item {
    public ItemDragonRadar() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            player.displayClientMessage(
                Component.literal("§6[Dragon Radar] §fNo Dragon Balls detected nearby..."), 
                true
            );
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
