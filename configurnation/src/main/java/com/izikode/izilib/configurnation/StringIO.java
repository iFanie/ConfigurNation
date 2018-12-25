package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class StringIO extends IO<String> {

    StringIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(String variation, String value, boolean instantly) {
        if (value == null) {
            clear(variation, instantly);
            return;
        }

        SharedPreferences.Editor editor = preferences.edit().putString(preferenceName(variation), value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public String get(String variation) {
        if (!exist(variation)) {
            return null;
        }

        return preferences.getString(preferenceName(variation), null);
    }

}
