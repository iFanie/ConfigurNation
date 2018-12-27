package com.izikode.izilib.configurnationapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.izikode.izilib.configurnationapp.utils.SampleAppConfig

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val config = SampleAppConfig(this)

        config.aBool = true
        val value = config.aBool
        val exists = config.aBoolExists()

        config.submitAFloat(1f) {
            /* value applied */
        }

        config.retrieveAFloat { newValue ->
            /* new value retrieved */
        }

        config.setAnInt(10)
        config.setAnInt(10, "Second")
    }

}
