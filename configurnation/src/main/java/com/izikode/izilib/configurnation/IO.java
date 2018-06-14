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
