package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class LongIO extends IO<Long> {

    LongIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(String variation, Long value, boolean instantly) {
        if (value == null) {
            clear(variation, instantly);
            return;
        }

        SharedPreferences.Editor editor = preferences.edit().putLong(preferenceName(variation), value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Long get(String variation) {
        if (!exist(variation)) {
            return null;
        }

        return preferences.getLong(preferenceName(variation), 0);
    }

}
