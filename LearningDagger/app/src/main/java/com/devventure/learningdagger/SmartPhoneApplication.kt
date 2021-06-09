package com.devventure.learningdagger

import android.app.Application

/**
 * Created by danie on 09/06/2021
 */
class SmartPhoneApplication : Application() {
    lateinit var smartPhoneComponent: SmartPhoneComponent

    override fun onCreate() {
        smartPhoneComponent = initDagger()
        super.onCreate()
    }

    private fun initDagger(): SmartPhoneComponent =
        DaggerSmartPhoneComponent.builder()
            .memoryCardModule(MemoryCardModule(1000))
            .build()
}