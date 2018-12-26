package com.izikode.izilib.configurnationcompiler

import com.izikode.izilib.basekotlincompiler.component.AbstractKotlinClass
import java.lang.StringBuilder

class Builder(

        override val packageName: String,
        override val simpleName: String

) : AbstractKotlinClass() {

    lateinit var name: String
    var mode: Int = 0
    lateinit var memberHelpers: Array<MemberHelper>

    private val members get() = StringBuilder().apply {
        memberHelpers.forEach { member ->
            append(member.toString())
        }
    }.toString()

    override val sourceCode: String get() =

""" package $packageName

class $simpleName(

        private val context: android.content.Context

) {

    private val prefs by lazy { context.getSharedPreferences(NAME, MODE) }

    $members

    companion object {
        const val NAME = "$name"
        const val MODE = $mode
    }

}

""".trimIndent()

    data class MemberHelper(

            val name: String,
            val type: String

    ) {

        override fun toString(): String =
"""
    private val ${name}Field by lazy { com.izikode.izilib.configurnation.Field<$type>(prefs, "$name", $type::class.java) }

    var $name: $type?
        get() = ${name}Field.get()
        set(value) { ${name}Field.set(value, true) }

    fun ${name}Exists(): Boolean = ${name}Field.exists()

"""

    }

}