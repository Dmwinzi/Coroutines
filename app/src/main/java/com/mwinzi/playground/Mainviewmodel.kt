package com.mwinzi.playground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class Mainviewmodel : ViewModel() {

    val countdown = flow<Int> {

        var initialvalue = 10
        var currentvalue = initialvalue
        emit(initialvalue)
        while (currentvalue > 0){
            delay(1000)
            currentvalue--
            emit(currentvalue)
        }

    }

    init {
        collectflow()
    }

    private fun collectflow(){

        viewModelScope.launch {
              countdown
                  .filter {  time ->
                   time % 2 == 0
                   }
                  .map { time ->
                      time * time
                  }
                  .onEach { time -> println("For each value emit $time")}
                  .collect { time ->
                    delay(1000)
                  println("The currentvalue is $time")
                 }

            val count = countdown.count {
                it % 2 == 0
            }

            println("The count is $count")

            val reduce = countdown.reduce { accumulator, value ->
                accumulator + value
            }
           println("Reduce value is $reduce")

            val fold = countdown.fold(100){
                accumulator, value -> accumulator+ value
            }

            println("Fold is $fold")

        }

    }

}