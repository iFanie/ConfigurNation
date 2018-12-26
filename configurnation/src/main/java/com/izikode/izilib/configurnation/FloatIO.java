package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class FloatIO extends IO<Float> {

    FloatIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(String variation, Float value, boolean instantly) {
        if (value == null) {
            clear(variation, instantly);
            return;
        }

        SharedPreferences.Editor editor = preferences.edit().putFloat(preferenceName(variation), value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Float get(String variation) {
        if (!exist(variation)) {
            return null;
        }

        return preferences.getFloat(preferenceName(variation), 0);
    }

}
