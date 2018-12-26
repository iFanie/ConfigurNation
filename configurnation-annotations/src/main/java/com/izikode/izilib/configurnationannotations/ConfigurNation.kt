package com.izikode.izilib.configurnationannotations

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class ConfigurNation(

        /**
         * Config file name and SharedPreferences identifier
         */
        val name: String,

        /**
         * SharedPreferences operation mode
         */
        val mode: Int

)
