package com.devventure.learningdagger

import android.util.Log
import dagger.Module
import dagger.Provides

/**
 * Created by danie on 09/06/2021
 */
@Module
class MemoryCardModule(val memorySize: Int) {

    @Provides
    fun providesMemoryCard(): MemoryCard {
        Log.i("MYTAG", "Size of memory $memorySize")
        return MemoryCard()
    }
}