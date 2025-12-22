package com.dragonblockinfinity.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ItemSenzu extends Item {
    public static final FoodProperties SENZU_FOOD = new FoodProperties.Builder()
        .nutrition(20)
        .saturationMod(2.0F)
        .alwaysEat()
        .fast()
        .build();

    public ItemSenzu() {
        super(new Item.Properties()
            .food(SENZU_FOOD)
            .stacksTo(16));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 10;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            player.setHealth(player.getMaxHealth());
            player.removeAllEffects();
            player.getFoodData().setFoodLevel(20);
            player.getFoodData().setSaturation(20.0F);
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
