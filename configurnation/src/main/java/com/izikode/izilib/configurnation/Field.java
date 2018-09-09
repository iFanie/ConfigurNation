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

public class Field<Type> {

    private final IO io;

    public Field(SharedPreferences prefs, String name, Class type) {
        if (Boolean.class.isAssignableFrom(type)) {
            io = new BooleanIO(prefs, name);
        } else if (Float.class.isAssignableFrom(type)) {
            io = new FloatIO(prefs, name);
        } else if (Integer.class.isAssignableFrom(type)) {
            io = new IntegerIO(prefs, name);
        } else if (Long.class.isAssignableFrom(type)) {
            io = new LongIO(prefs, name);
        } else if (String.class.isAssignableFrom(type)) {
            io = new StringIO(prefs, name);
        } else {
            throw new RuntimeException(new InvalidSharedPreferencesFieldTypeException(type.getCanonicalName()));
        }
    }

    public boolean exists(String variation) {
        return io.exist(variation);
    }

    public boolean exists() {
        return io.exist();
    }

    @Nullable
    public Type get(String variation) {
        return (Type) io.get(variation);
    }

    @Nullable
    public Type get() {
        return (Type) io.get();
    }

    public void set(String variation, Type val, boolean instantly) {
        io.set(variation, val, instantly);
    }

    public void set(String variation, Type val) {
        io.set(variation, val);
    }

    public void set(Type val, boolean instantly) {
        io.set(val, instantly);
    }

    public void set(Type val) {
        io.set(val);
    }

    public void clear(String variation, boolean instantly) {
        io.clear(variation, instantly);
    }

    public void clear(String variation) {
        io.clear(variation);
    }

    public void clear(boolean instantly) {
        io.clear(instantly);
    }

    public void clear() {
        io.clear();
    }

}
