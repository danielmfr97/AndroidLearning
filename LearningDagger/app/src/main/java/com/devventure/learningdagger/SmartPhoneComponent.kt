package com.devventure.learningdagger

import dagger.Component
import javax.inject.Singleton

/**
 * Created by danie on 09/06/2021
 */
@Singleton
@Component(modules = [MemoryCardModule::class, NCBatteryModule::class])
interface SmartPhoneComponent {
//    fun getSmartPhone(): SmartPhone
    // Conforme for sendo usado em mais activities ou fragments devemos replicar o inject para tal
    fun inject(mainActivity: MainActivity)
}
