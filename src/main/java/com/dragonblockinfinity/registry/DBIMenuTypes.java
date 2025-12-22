package com.dragonblockinfinity.registry;

import com.dragonblockinfinity.DragonBlockInfinity;
import com.dragonblockinfinity.menu.EquipmentMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DBIMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = 
        DeferredRegister.create(ForgeRegistries.MENU_TYPES, DragonBlockInfinity.MOD_ID);

    public static final RegistryObject<MenuType<EquipmentMenu>> EQUIPMENT_MENU = 
        MENU_TYPES.register("equipment_menu",
            () -> IForgeMenuType.create((windowId, inv, data) -> new EquipmentMenu(windowId, inv)));
}
