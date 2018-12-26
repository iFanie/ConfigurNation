package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class BooleanIO extends IO<Boolean> {

    BooleanIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(String variation, Boolean value, boolean instantly) {
        if (value == null) {
            clear(variation, instantly);
            return;
        }

        SharedPreferences.Editor editor = preferences.edit().putBoolean(preferenceName(variation), value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Boolean get(String variation) {
        if (!exist(variation)) {
            return null;
        }

        return preferences.getBoolean(preferenceName(variation), false);
    }

}
