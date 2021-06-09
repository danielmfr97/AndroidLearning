package com.devventure.learningdagger

import dagger.Component

/**
 * Created by danie on 09/06/2021
 */
@Component(modules = [MemoryCardModule::class])
interface SmartPhoneComponent {
    fun getSmartPhone(): SmartPhone
}