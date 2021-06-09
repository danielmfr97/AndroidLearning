package com.devventure.learningdagger

import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by danie on 09/06/2021
 */
@Module
abstract class NCBatteryModule {

    @Binds
    abstract fun providesNCBattery(nickelCadmiumBattery: NickelCadmiumBattery): Battery
}