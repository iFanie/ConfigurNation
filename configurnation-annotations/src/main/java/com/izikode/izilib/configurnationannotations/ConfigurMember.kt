package com.izikode.izilib.configurnationannotations

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class ConfigurMember(

        /**
         * Member retrieval type. <i>Default value is [Type.MIXED].</i>
         */
        val type: Type = Type.MIXED

) {

    enum class Type {

        /**
         * Get and Set of the member is handled with DSLs.
         */
        ASYNCHRONOUS,

        /**
         * Get and Set of the member is handled with a property.
         */
        SYNCHRONOUS,

        /**
         * Member has both DSL and property for Get and Set.
         */
        MIXED

    }

}
