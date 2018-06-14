package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class LongIO extends IO<Long> {

    LongIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(Long value, boolean instantly) {
        SharedPreferences.Editor editor = preferences.edit().putLong(name, value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public Long get() {
        if (!exist()) {
            return null;
        }

        return preferences.getLong(name, 0);
    }

}
