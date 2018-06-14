package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class StringIO extends IO<String> {

    StringIO(SharedPreferences preferences, String name) {
        super(preferences, name);
    }

    @Override
    public void set(String value, boolean instantly) {
        SharedPreferences.Editor editor = preferences.edit().putString(name, value);
        transact(editor, instantly);
    }

    @Override
    @Nullable
    public String get() {
        if (!exist()) {
            return null;
        }

        return preferences.getString(name, null);
    }

}
