package com.sv.youapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Greeting("YouApp 🚀")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier.
        fillMaxSize()
            .systemBarsPadding()
    ) {
        Text("Hola $name")
    }
}