package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public abstract class IO<Type> {

    protected final SharedPreferences preferences;
    protected final String name;

    protected void transact(SharedPreferences.Editor editor, boolean instantly) {
        if (instantly) {
            editor.commit();
        } else {
            editor.apply();
        }
    }

    IO(SharedPreferences preferences, String name) {
        this.preferences = preferences;
        this.name = name;
    }

    public boolean exist() {
        return preferences.contains(name);
    }

    public abstract void set(Type value, boolean instantly);

    public void set(Type value) {
        set(value, false);
    }

    @Nullable
    public abstract Type get();

}
