package com.dragonblockinfinity.player.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class PlayerData {
    // Stats
    private int str = 5;
    private int dex = 5;
    private int con = 5;
    private int will = 5;
    private int mnd = 5;
    private int spi = 5;
    private int tp = 0;

    // Equipment Slots (3 customizados)
    private final ItemStackHandler equipment = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            // Trigger sync quando mudar
        }
    };

    // Getters e Setters de Stats
    public int getStr() { return str; }
    public void setStr(int str) { this.str = str; }
    
    public int getDex() { return dex; }
    public void setDex(int dex) { this.dex = dex; }
    
    public int getCon() { return con; }
    public void setCon(int con) { this.con = con; }
    
    public int getWill() { return will; }
    public void setWill(int will) { this.will = will; }
    
    public int getMnd() { return mnd; }
    public void setMnd(int mnd) { this.mnd = mnd; }
    
    public int getSpi() { return spi; }
    public void setSpi(int spi) { this.spi = spi; }
    
    public int getTp() { return tp; }
    public void setTp(int tp) { this.tp = tp; }
    public void addTp(int amount) { this.tp += amount; }

    // Equipment
    public ItemStackHandler getEquipment() { return equipment; }

    // NBT Save/Load
    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("str", str);
        nbt.putInt("dex", dex);
        nbt.putInt("con", con);
        nbt.putInt("will", will);
        nbt.putInt("mnd", mnd);
        nbt.putInt("spi", spi);
        nbt.putInt("tp", tp);
        nbt.put("equipment", equipment.serializeNBT());
    }

    public void loadNBTData(CompoundTag nbt) {
        str = nbt.getInt("str");
        dex = nbt.getInt("dex");
        con = nbt.getInt("con");
        will = nbt.getInt("will");
        mnd = nbt.getInt("mnd");
        spi = nbt.getInt("spi");
        tp = nbt.getInt("tp");
        equipment.deserializeNBT(nbt.getCompound("equipment"));
    }

    public void copyFrom(PlayerData source) {
        this.str = source.str;
        this.dex = source.dex;
        this.con = source.con;
        this.will = source.will;
        this.mnd = source.mnd;
        this.spi = source.spi;
        this.tp = source.tp;
        for (int i = 0; i < 3; i++) {
            equipment.setStackInSlot(i, source.equipment.getStackInSlot(i).copy());
        }
    }
}
