package com.dragonblockinfinity.registry;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.item.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DBIItems {
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, DragonBlockInfinity.MOD_ID);

    // ===== BLOCK ITEMS (Namek) =====
    public static final RegistryObject<Item> NAMEK_DIRT_ITEM = ITEMS.register("namek_dirt",
        () -> new BlockItem(DBIBlocks.NAMEK_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> NAMEK_GRASS_ITEM = ITEMS.register("namek_grass",
        () -> new BlockItem(DBIBlocks.NAMEK_GRASS.get(), new Item.Properties()));

    public static final RegistryObject<Item> NAMEK_LOG_ITEM = ITEMS.register("namek_log",
        () -> new BlockItem(DBIBlocks.NAMEK_LOG.get(), new Item.Properties()));

    public static final RegistryObject<Item> NAMEK_LEAVES_ITEM = ITEMS.register("namek_leaves",
        () -> new BlockItem(DBIBlocks.NAMEK_LEAVES.get(), new Item.Properties()));

    public static final RegistryObject<Item> NAMEK_SAPLING_ITEM = ITEMS.register("namek_sapling",
        () -> new BlockItem(DBIBlocks.NAMEK_SAPLING.get(), new Item.Properties()));

    public static final RegistryObject<Item> NAMEK_TOP_ITEM = ITEMS.register("namek_top",
        () -> new BlockItem(DBIBlocks.NAMEK_TOP.get(), new Item.Properties()));

    public static final RegistryObject<Item> NAMEK_SIDE_ITEM = ITEMS.register("namek_side",
        () -> new BlockItem(DBIBlocks.NAMEK_SIDE.get(), new Item.Properties()));

    // ===== BLOCK ITEMS (Plus) =====
    public static final RegistryObject<Item> PLUS_WHITE_ITEM = ITEMS.register("plus_white",
        () -> new BlockItem(DBIBlocks.PLUS_WHITE.get(), new Item.Properties()));

    public static final RegistryObject<Item> PLUS_BLACK_ITEM = ITEMS.register("plus_black",
        () -> new BlockItem(DBIBlocks.PLUS_BLACK.get(), new Item.Properties()));

    public static final RegistryObject<Item> PLUS_RED_ITEM = ITEMS.register("plus_red",
        () -> new BlockItem(DBIBlocks.PLUS_RED.get(), new Item.Properties()));

    public static final RegistryObject<Item> PLUS_YELLOW_ITEM = ITEMS.register("plus_yellow",
        () -> new BlockItem(DBIBlocks.PLUS_YELLOW.get(), new Item.Properties()));

    // ===== FUNCTIONAL ITEMS =====
    public static final RegistryObject<Item> SENZU_BEAN = ITEMS.register("senzu",
        () -> new ItemSenzu());

    public static final RegistryObject<Item> DRAGON_RADAR = ITEMS.register("dragon_radar_earth",
        () -> new ItemDragonRadar());

    public static final RegistryObject<Item> SCOUTER = ITEMS.register("scouter",
        () -> new ItemScouter());

    public static final RegistryObject<Item> TRAINING_WEIGHT = ITEMS.register("training_weight",
        () -> new ItemTrainingWeight());

    // ===== FOOD ITEMS =====
    public static final RegistryObject<Item> DINOSAUR_MEAT = ITEMS.register("dinosaur_meat",
        () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).meat().build())));

    public static final RegistryObject<Item> DINOSAUR_MEAT_COOKED = ITEMS.register("dinosaur_meat_cooked",
        () -> new Item(new Item.Properties().food(
            new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).meat().build())));
}
