package com.bernardo.dbi.capability;

import com.bernardo.dbi.form.Form;

public interface IPlayerForm {

    Form getForm();
    void setForm(Form form);

    void copyFrom(IPlayerForm other);
}