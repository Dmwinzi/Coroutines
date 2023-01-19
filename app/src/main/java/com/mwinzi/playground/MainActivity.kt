package com.mwinzi.playground

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mwinzi.playground.ui.theme.PlaygroundTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // val coroutines  = Coroutines()
        setContent {
            val viewModel by viewModels<Mainviewmodel>()
            var number  = viewModel.countdown.collectAsState(initial = 10)
            PlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val context = LocalContext.current
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(text = number.value.toString(), style = TextStyle(fontSize = 35.sp))

                       LaunchedEffect(key1 = true){
                           val coroutines = Coroutines()
                       }

                        LaunchedEffect(key1 = true){
                           var job =  GlobalScope.launch(Dispatchers.IO) {
                              val netcallresult =  donetworkcall()
                               val netcallresult2 = donetworkcall2()
                                Log.d("Networkcall",netcallresult)
                                Log.d("Networkcall2",netcallresult2)
                               withContext(Dispatchers.Main){
                                   Toast.makeText(context,netcallresult,Toast.LENGTH_LONG).show()
                               }

                            }
                        }
                    }
                }
            }
        }
    }
}

suspend fun donetworkcall() : String{

    delay(5000L)

    return "Data fetched from networkcall1"
}


suspend fun donetworkcall2() : String{
    delay(5000L)

    return "Data fetched from networkcall2"
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlaygroundTheme {
        Greeting("Android")
    }
}