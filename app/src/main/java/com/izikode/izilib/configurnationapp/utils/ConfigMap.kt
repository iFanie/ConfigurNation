package com.izikode.izilib.configurnationapp.utils

import android.content.Context
import com.izikode.izilib.configurnationannotations.ConfigurMember
import com.izikode.izilib.configurnationannotations.ConfigurNation

@ConfigurNation( name = "SampleAppConfig", mode = Context.MODE_PRIVATE )
interface ConfigMap {

    @ConfigurMember
    fun aBool() : Boolean

    @ConfigurMember
    fun aFloat() : Float

    @ConfigurMember
    fun anInt() : Int

    @ConfigurMember
    fun aLong() : Long

    @ConfigurMember
    fun aString() : String

    @ConfigurMember
    fun name() : String

}