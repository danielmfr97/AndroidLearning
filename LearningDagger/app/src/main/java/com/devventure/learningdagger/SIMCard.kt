package com.devventure.learningdagger

import android.util.Log
import javax.inject.Inject

/**
 * Created by danie on 09/06/2021
 */
class SIMCard @Inject constructor(private  val serviceProvider: ServiceProvider) {


    init {
        Log.i("MYTAG","SIM Card Constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}
