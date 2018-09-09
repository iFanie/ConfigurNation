/*
 * Copyright 2018 Fanie Veizis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Locale;

public abstract class IO<Type> {

    protected final SharedPreferences preferences;
    private final String name;

    protected String preferenceName(@Nullable String variation) {
        if (variation == null) {
            return name;
        } else {
            return String.format(Locale.ENGLISH, "%1$s_%2$s", name, variation);
        }
    }

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

    public boolean exist(String variation) {
        return preferences.contains(preferenceName(variation));
    }

    public boolean exist() {
        return exist(null);
    }

    public abstract void set(String variation, Type value, boolean instantly);

    public void set(String variation, Type value) {
        set(variation, value, false);
    }

    public void set(Type value, boolean instantly) {
        set(null, value, instantly);
    }

    public void set(Type value) {
        set(null, value, false);
    }

    @Nullable
    public abstract Type get(String variation);

    @Nullable
    public Type get() {
        return get(null);
    }

    public void clear(String variation, boolean instantly) {
        SharedPreferences.Editor editor = preferences.edit().remove(preferenceName(variation));
        transact(editor, instantly);
    }

    public void clear(String variation) {
        clear(variation, false);
    }

    public void clear(boolean instantly) {
        clear(null, instantly);
    }

    public void clear() {
        clear(null, false);
    }

}
