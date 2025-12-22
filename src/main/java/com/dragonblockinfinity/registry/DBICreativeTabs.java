package com.dragonblockinfinity.registry;

import com.dragonblockinfinity.DragonBlockInfinity;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DBICreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DragonBlockInfinity.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DBI_TAB = CREATIVE_TABS.register("main_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.dragonblockinfinity"))
            .icon(() -> new ItemStack(DBIItems.PLUS_YELLOW_ITEM.get()))
            .displayItems((params, output) -> {
                output.accept(DBIItems.NAMEK_DIRT_ITEM.get());
                output.accept(DBIItems.NAMEK_GRASS_ITEM.get());
                output.accept(DBIItems.NAMEK_LOG_ITEM.get());
                output.accept(DBIItems.NAMEK_LEAVES_ITEM.get());
                output.accept(DBIItems.NAMEK_SAPLING_ITEM.get());
                output.accept(DBIItems.NAMEK_TOP_ITEM.get());
                output.accept(DBIItems.NAMEK_SIDE_ITEM.get());
                output.accept(DBIItems.PLUS_WHITE_ITEM.get());
                output.accept(DBIItems.PLUS_BLACK_ITEM.get());
                output.accept(DBIItems.PLUS_RED_ITEM.get());
                output.accept(DBIItems.PLUS_YELLOW_ITEM.get());
                output.accept(DBIItems.SENZU_BEAN.get());
                output.accept(DBIItems.DRAGON_RADAR.get());
                output.accept(DBIItems.SCOUTER.get());
                output.accept(DBIItems.TRAINING_WEIGHT.get());
                output.accept(DBIItems.DINOSAUR_MEAT.get());
                output.accept(DBIItems.DINOSAUR_MEAT_COOKED.get());
            })
            .build());
}
