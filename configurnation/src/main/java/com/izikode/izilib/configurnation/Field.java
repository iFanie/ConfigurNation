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

    public boolean exists() {
        return io.exist();
    }

    @Nullable
    public Type get() {
        return (Type) io.get();
    }

    public void set(Type val, boolean instantly) {
        io.set(val, instantly);
    }

    public void set(Type val) {
        io.set(val);
    }

}
