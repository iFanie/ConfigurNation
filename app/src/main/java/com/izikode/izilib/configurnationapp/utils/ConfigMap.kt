package com.izikode.izilib.configurnationapp.utils

import android.content.Context
import com.izikode.izilib.configurnationannotations.ConfigurMember
import com.izikode.izilib.configurnationannotations.ConfigurNation

@ConfigurNation( name = "SampleAppConfig", mode = Context.MODE_PRIVATE )
interface ConfigMap {

    @ConfigurMember
    var aBool: Boolean

    @ConfigurMember
    var aFloat: Float

    @ConfigurMember
    var anInt: Int

    @ConfigurMember
    var aLong: Long

    @ConfigurMember
    var aString: String

    @ConfigurMember
    var name: String

}