package com.devventure.learningdagger

import android.util.Log
import javax.inject.Inject

/**
 * Created by danie on 09/06/2021
 */
class NickelCadmiumBattery @Inject constructor() : Battery {
    override fun getPower() {
        Log.i("MYTAG", "Power from NickelCadmiumBattery")
    }
}