package com.izikode.izilib.configurnationapp.utils

import android.content.Context
import com.izikode.izilib.configurnationannotations.ConfigurMember
import com.izikode.izilib.configurnationannotations.ConfigurNation

@ConfigurNation(name = "SampleAppConfig", mode = Context.MODE_PRIVATE)
interface ConfigMap {

    @ConfigurMember
    fun aLong(): Long

    @ConfigurMember
    fun aDouble(): Double

    @ConfigurMember(type = ConfigurMember.Type.SYNCHRONOUS)
    fun aBool(): Boolean

    @ConfigurMember(type = ConfigurMember.Type.ASYNCHRONOUS)
    fun aFloat(): Float

    @ConfigurMember(withVariants = true)
    fun anInt(): Int

    @ConfigurMember
    fun aChar(): Char

    @ConfigurMember(withVariants = true)
    fun aString(): String

    @ConfigurMember
    fun name(): String

}
