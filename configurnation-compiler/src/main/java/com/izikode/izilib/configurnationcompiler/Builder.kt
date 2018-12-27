package com.izikode.izilib.configurnationcompiler

import com.izikode.izilib.basekotlincompiler.component.AbstractKotlinClass
import com.izikode.izilib.configurnationannotations.ConfigurMember
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
            val type: String,
            val memberType: ConfigurMember.Type,
            val withVariants: Boolean

    ) {

        val property: String get() = if (!withVariants)
"""
    var $name: $type?
        get() = ${name}Field.get()
        set(value) { ${name}Field.set(value, true) }
"""
        else
"""
    fun get${name.capitalize()}(variant: kotlin.String? = null): $type? = ${name}Field.get(variant)

    fun set${name.capitalize()}(value: $type?, variant: kotlin.String? = null) {
        ${name}Field.set(variant, value, true)
    }
"""

        val dsls: String get() = if (!withVariants)
"""
    fun retrieve${name.capitalize()}(block: ($type?) -> Unit) {
        val value = ${name}Field.get()
        block(value)
    }

    fun submit${name.capitalize()}(value: $type?, block: () -> Unit) {
        ${name}Field.set(value)
        block()
    }
"""
        else
"""
    fun retrieve${name.capitalize()}(block: ($type?) -> Unit) {
        val value = ${name}Field.get(null)
        block(value)
    }

    fun retrieve${name.capitalize()}(variant: kotlin.String, block: ($type?) -> Unit) {
        val value = ${name}Field.get(variant)
        block(value)
    }

    fun submit${name.capitalize()}(value: $type?, variant: kotlin.String, block: () -> Unit) {
        ${name}Field.set(variant, value)
        block()
    }
"""

        val access: String get() = StringBuilder().apply {
            if (memberType == ConfigurMember.Type.SYNCHRONOUS || memberType == ConfigurMember.Type.MIXED) {
                append(property)
            }

            if (memberType == ConfigurMember.Type.ASYNCHRONOUS || memberType == ConfigurMember.Type.MIXED) {
                append(dsls)
            }
        }.toString()

        override fun toString(): String =
"""
    /* ${name.toUpperCase()} ${type.toUpperCase()} Member */
    private val ${name}Field by lazy { com.izikode.izilib.configurnation.Field<$type>(prefs, "$name", $type::class.java) }

    $access

    fun ${name}Exists(): Boolean = ${name}Field.exists()

"""

    }

}