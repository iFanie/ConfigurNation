package com.izikode.izilib.configurnation;

public class InvalidSharedPreferencesFieldTypeException extends Exception {

    public InvalidSharedPreferencesFieldTypeException(String type) {
        super(String.format("Type '%1$s' cannot be handled by the SharedPreferences", type));
    }

}
