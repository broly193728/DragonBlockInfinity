package com.bernardo.dbi.capability;

import com.bernardo.dbi.form.Form;
import net.minecraft.nbt.CompoundTag;
import java.util.Objects;

public class PlayerForm implements IPlayerForm {
    private Form form = Form.Base; // default

    @Override
    public Form getForm() {
        return form;
    }

    @Override
    public void setForm(Form form) {
        this.form = form;
    }

    @Override
    public void copyFrom(IPlayerForm other) {
        this.form = other.getForm();
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("form", Objects.requireNonNull(form.getId()));
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        String formId = tag.getString("form");
        // Para simplificar, assumindo que Base é o default
        this.form = Form.Base; // TODO: map from id
    }
}