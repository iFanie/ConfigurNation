package com.izikode.izilib.configurnationannotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface ConfigurNation {

    /**
     * Config file name and SharedPreferences identifier
     * @return String
     */
    String name();

    /**
     * SharedPreferences operation mode
     * @return Integer
     */
    int mode();

}
