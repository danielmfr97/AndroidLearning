package br.com.daniel.ramos.core

class Logger(
    private val tag: String,
    private val isDebug: Boolean = true
) {
    fun log(msg:String) {
        if (!isDebug) {
            // Production logging - Crashlytics or w/e
        } else {
            printLogD(tag, msg)
        }
    }

    companion object Factory {
        fun buildDebug(tag: String): Logger {
            return Logger(tag = tag, isDebug = true)
        }
        fun buildRelease(tag: String): Logger {
            return Logger(tag = tag, isDebug = false)
        }
    }
}

fun printLogD(tag: String, message: String) {
    println("$tag: $message")
}