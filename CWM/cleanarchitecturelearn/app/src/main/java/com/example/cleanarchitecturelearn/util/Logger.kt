package com.example.cleanarchitecturelearn.util

import android.util.Log
import com.codingwithmitch.cleannotes.util.Constants.DEBUG
import com.codingwithmitch.cleannotes.util.Constants.TAG
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase

var isUnitTest = false

fun printLogD(className: String?, message: String ) {
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    }
    else if(DEBUG && isUnitTest){
        println("$className: $message")
    }
}

/*
    Priorities: Log.DEBUG, Log. etc....
 */
fun cLog(msg: String?){
    msg?.let {
        if(!DEBUG){
            Firebase.crashlytics.log(it)
        }
    }
}

