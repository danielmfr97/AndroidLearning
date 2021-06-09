package com.devventure.learningdagger

import android.util.Log
import javax.inject.Inject

/**
 * Created by danie on 09/06/2021
 */
class MemoryCard @Inject constructor(){
    init {
        Log.i("MYTAG","Memory Card Constructed")
    }

    fun getSpaceAvailablity(){
        Log.i("MYTAG","Memory space available")
    }
}