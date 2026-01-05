package com.bernardo.dbi.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class PlayerStatus implements IPlayerStatus {
    private int str, dex, con, spi, will, mnd;

    public PlayerStatus() {
        this.str = 0;
        this.dex = 0;
        this.con = 0;
        this.spi = 0;
        this.will = 0;
        this.mnd = 0;
    }

    @Override public int getStr() { return str; }
    @Override public void setStr(int value) { this.str = value; }

    @Override public int getDex() { return dex; }
    @Override public void setDex(int value) { this.dex = value; }

    @Override public int getCon() { return con; }
    @Override public void setCon(int value) { this.con = value; }

    @Override public int getSpi() { return spi; }
    @Override public void setSpi(int value) { this.spi = value; }

    @Override public int getWill() { return will; }
    @Override public void setWill(int value) { this.will = value; }

    @Override public int getMnd() { return mnd; }
    @Override public void setMnd(int value) { this.mnd = value; }

    @Override
    public void copyFrom(IPlayerStatus other) {
        this.str = other.getStr();
        this.dex = other.getDex();
        this.con = other.getCon();
        this.spi = other.getSpi();
        this.will = other.getWill();
        this.mnd = other.getMnd();
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("str", str);
        tag.putInt("dex", dex);
        tag.putInt("con", con);
        tag.putInt("spi", spi);
        tag.putInt("will", will);
        tag.putInt("mnd", mnd);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        this.str = tag.getInt("str");
        this.dex = tag.getInt("dex");
        this.con = tag.getInt("con");
        this.spi = tag.getInt("spi");
        this.will = tag.getInt("will");
        this.mnd = tag.getInt("mnd");
    }

    public static PlayerStatus fromBuf(FriendlyByteBuf buf) {
        PlayerStatus s = new PlayerStatus();
        s.setStr(buf.readInt());
        s.setDex(buf.readInt());
        s.setCon(buf.readInt());
        s.setSpi(buf.readInt());
        s.setWill(buf.readInt());
        s.setMnd(buf.readInt());
        return s;
    }

    public static void toBuf(FriendlyByteBuf buf, IPlayerStatus status) {
        buf.writeInt(status.getStr());
        buf.writeInt(status.getDex());
        buf.writeInt(status.getCon());
        buf.writeInt(status.getSpi());
        buf.writeInt(status.getWill());
        buf.writeInt(status.getMnd());
    }
}
