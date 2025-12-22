package com.dragonblockinfinity.item;

import net.minecraft.world.item.Item;

public class ItemScouter extends Item {
    public ItemScouter() {
        super(new Item.Properties()
            .stacksTo(1)
            .durability(300));
    }
}
