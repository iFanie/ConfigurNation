package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class BooleanIO extends IO<Boolean> {

    BooleanIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(Boolean value, boolean instantly) {
        SharedPreferences.Editor editor = preferences.edit().putBoolean(name, value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Boolean get() {
        if (!exist()) {
            return null;
        }

        return preferences.getBoolean(name, false);
    }

}
