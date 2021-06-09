package com.devventure.learningdagger

import android.util.Log
import javax.inject.Inject

/**
 * Created by danie on 09/06/2021
 */
class ServiceProvider @Inject constructor(){
    init {
        Log.i("MYTAG","Service Provider Constructed")
    }

    fun getServiceProvider(){
        Log.i("MYTAG","Service provider connected")
    }
}