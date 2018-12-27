package com.izikode.izilib.configurnationcompiler

import com.izikode.izilib.basekotlincompiler.BaseKotlinCompiler
import com.izikode.izilib.configurnationannotations.ConfigurMember
import com.izikode.izilib.configurnationannotations.ConfigurNation
import kotlin.reflect.KClass

class Compiler : BaseKotlinCompiler() {

    override val processes: Array<KClass<out Any>> = arrayOf( ConfigurNation::class, ConfigurMember::class )

    private val builders = mutableListOf<Builder>()

    override val roundHandler: CompilationRoundHandler.() -> Unit = {
        fetchClasses(ConfigurNation::class) { nations ->
            nations.forEach { nation ->

                val annotation = nation.annotation
                val members = nation.functionsWith(ConfigurMember::class)

                val nationBuilder = Builder(nation.parent.toString(), annotation.name).apply {
                    name = nation.info.name
                    mode = annotation.mode

                    memberHelpers = Array(members.size) { index ->
                        members[index].let { member ->
                            Builder.MemberHelper(member.info.name, member.info.type, member.annotation.type)
                        }
                    }
                }

                builders.add(nationBuilder)

            }
        }
    }

    override val finallyHandler: FinallyHandler.() -> Unit = {
        generateClasses {
            builders.toTypedArray()
        }
    }

}