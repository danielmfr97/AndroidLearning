package com.devventure.learningdagger

import android.util.Log
import javax.inject.Inject

/**
 * Created by danie on 09/06/2021
 */
class Battery @Inject constructor(){
    init {
        Log.i("MYTAG","Battery Constructed")
    }

    fun getPower(){
        Log.i("MYTAG","Battery power is available")
    }
}
