package com.example.testchat

import android.util.Log

object Logger {
    var isTestMode = false

    fun e(tag: String, message: String) {
        if (isTestMode) {
            println("ERROR: $tag - $message")
        } else {
            Log.e(tag, message)
        }
    }
}