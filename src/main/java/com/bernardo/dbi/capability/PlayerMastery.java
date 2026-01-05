package com.bernardo.dbi.capability;

import net.minecraft.nbt.CompoundTag;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayerMastery implements IPlayerMastery {
    private final Map<String, Float> masteryLevels = new HashMap<>();
    private final Map<String, Float> masteryExp = new HashMap<>();

    @Override
    public float getMasteryLevel(String formId) {
        return masteryLevels.getOrDefault(formId, 0.0f);
    }

    @Override
    public void setMasteryLevel(String formId, float level) {
        masteryLevels.put(formId, level);
    }

    @Override
    public void addMasteryExp(String formId, float exp) {
        float currentExp = masteryExp.getOrDefault(formId, 0.0f);
        masteryExp.put(formId, currentExp + exp);
        // Calcular level baseado em exp, similar ao BaseForm
        float level = Math.min(50.0f, (currentExp + exp) / 100.0f);
        setMasteryLevel(formId, level);
    }

    @Override
    public void copyFrom(IPlayerMastery other) {
        // Copiar maps
        this.masteryLevels.clear();
        this.masteryExp.clear();
        // Assumindo que other é PlayerMastery
        if (other instanceof PlayerMastery pm) {
            this.masteryLevels.putAll(pm.masteryLevels);
            this.masteryExp.putAll(pm.masteryExp);
        }
    }

    public Map<String, Float> getMasteryLevels() {
        return new HashMap<>(masteryLevels);
    }

    public void setMasteryLevels(Map<String, Float> levels) {
        this.masteryLevels.clear();
        this.masteryLevels.putAll(levels);
    }

    public Map<String, Float> getMasteryExp() {
        return new HashMap<>(masteryExp);
    }

    public void setMasteryExp(Map<String, Float> exp) {
        this.masteryExp.clear();
        this.masteryExp.putAll(exp);
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        CompoundTag levelsTag = new CompoundTag();
        for (Map.Entry<String, Float> entry : masteryLevels.entrySet()) {
            levelsTag.putFloat(Objects.requireNonNull(entry.getKey()), entry.getValue());
        }
        tag.put("levels", levelsTag);

        CompoundTag expTag = new CompoundTag();
        for (Map.Entry<String, Float> entry : masteryExp.entrySet()) {
            expTag.putFloat(Objects.requireNonNull(entry.getKey()), entry.getValue());
        }
        tag.put("exp", expTag);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        CompoundTag levelsTag = tag.getCompound("levels");
        for (String key : levelsTag.getAllKeys()) {
            masteryLevels.put(Objects.requireNonNull(key), levelsTag.getFloat(key));
        }

        CompoundTag expTag = tag.getCompound("exp");
        for (String key : expTag.getAllKeys()) {
            masteryExp.put(Objects.requireNonNull(key), expTag.getFloat(key));
        }
    }
}