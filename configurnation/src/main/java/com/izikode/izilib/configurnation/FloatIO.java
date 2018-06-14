package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class FloatIO extends IO<Float> {

    FloatIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(Float value, boolean instantly) {
        SharedPreferences.Editor editor = preferences.edit().putFloat(name, value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Float get() {
        if (!exist()) {
            return null;
        }

        return preferences.getFloat(name, 0);
    }

}
