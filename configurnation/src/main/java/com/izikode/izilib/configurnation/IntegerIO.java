package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class IntegerIO extends IO<Integer> {

    IntegerIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(Integer value, boolean instantly) {
        SharedPreferences.Editor editor = preferences.edit().putInt(name, value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Integer get() {
        if (!exist()) {
            return null;
        }

        return preferences.getInt(name, 0);
    }

}
