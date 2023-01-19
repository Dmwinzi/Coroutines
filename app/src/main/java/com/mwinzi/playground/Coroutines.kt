package com.mwinzi.playground

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Coroutines {


    init {
        globalscope()
    }

    fun globalscope(){
        GlobalScope.launch {
            Log.d("Global","Hello from thread ${Thread.currentThread().name}")
        }
        println("Hello from thread ${Thread.currentThread().name}")
    }

}

suspend fun networcall(): String {
    delay(5000L)

    return "Job in execution"

}