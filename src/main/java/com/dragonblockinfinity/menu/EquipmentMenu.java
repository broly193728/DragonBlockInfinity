package com.dragonblockinfinity.menu;

import com.dragonblockinfinity.player.capability.PlayerDataProvider;
import com.dragonblockinfinity.registry.DBIMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class EquipmentMenu extends AbstractContainerMenu {
    private final Player player;

    public EquipmentMenu(int id, Inventory playerInv) {
        super(DBIMenuTypes.EQUIPMENT_MENU.get(), id);
        this.player = playerInv.player;

        player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
            // Slot 0: Scouter (x=151, y=8)
            this.addSlot(new SlotItemHandler(data.getEquipment(), 0, 151, 8));
            
            // Slot 1: Peso Pulso (x=151, y=26)
            this.addSlot(new SlotItemHandler(data.getEquipment(), 1, 151, 26));
            
            // Slot 2: Peso Camisa (x=151, y=44)
            this.addSlot(new SlotItemHandler(data.getEquipment(), 2, 151, 44));
        });

        // Inventário do player (9x3)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
