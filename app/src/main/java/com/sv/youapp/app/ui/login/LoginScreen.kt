package com.sv.youapp.app.ui.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sv.youapp.app.R
import com.sv.youapp.app.test.TextEngine
import com.sv.youapp.app.ui.GradientButton

@Composable
fun LoginScreen(onLoginClicked: () -> Unit) {
    Column(Modifier.fillMaxSize()
        .background(colorResource(id = R.color.background_color)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painterResource(R.drawable.ic_logo),
            contentDescription = "LOGO",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit
        )
        Text(stringResource(R.string.bienvenida), style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.purple_text))
        Spacer(modifier = Modifier.height(16.dp))

        GradientButton("Iniciar Sesion") {onLoginClicked()}

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            val ss: String = TextEngine().processFrom("HOLA MUNDO")
            Log.i("LoginScreen","String from C ++ {ss}");
        }) {
            Text("Registrarse")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen({})
}