package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class IntegerIO extends IO<Integer> {

    IntegerIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(String variation, Integer value, boolean instantly) {
        if (value == null) {
            clear(variation, instantly);
            return;
        }

        SharedPreferences.Editor editor = preferences.edit().putInt(preferenceName(variation), value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Integer get(String variation) {
        if (!exist(variation)) {
            return null;
        }

        return preferences.getInt(preferenceName(variation), 0);
    }

}
