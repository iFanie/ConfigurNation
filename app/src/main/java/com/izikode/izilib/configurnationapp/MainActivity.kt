package com.izikode.izilib.configurnationapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.izikode.izilib.configurnationapp.utils.SampleAppConfig

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val config = SampleAppConfig(this)
    }

}
