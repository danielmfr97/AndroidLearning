package com.devventure.learningdagger

import dagger.Module
import dagger.Provides

/**
 * Created by danie on 09/06/2021
 */
@Module
class MemoryCardModule {

    @Provides
    fun providesMemoryCard(): MemoryCard {
        return MemoryCard()
    }
}