package com.izikode.izilib.configurnation;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class Field<Type> {

    private final IO io;

    public Field(SharedPreferences prefs, String name, Class type) {
        if (boolean.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type)) {
            io = new BooleanIO(prefs, name);
        } else if (float.class.isAssignableFrom(type) || Float.class.isAssignableFrom(type)) {
            io = new FloatIO(prefs, name);
        } else if (int.class.isAssignableFrom(type) || Integer.class.isAssignableFrom(type)) {
            io = new IntegerIO(prefs, name);
        } else if (long.class.isAssignableFrom(type) || Long.class.isAssignableFrom(type)) {
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
