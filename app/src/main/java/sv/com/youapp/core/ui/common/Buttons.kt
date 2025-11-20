package sv.com.youapp.core.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingButton(
    loading: Boolean, onClick: () -> Unit, content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = !loading,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        if (loading) {
            GradientBox {
                CircularProgressIndicator(color = Color.White)
            }

        } else {
            content()
        }

    }
}

@Composable
fun LoadingGradientButton(
    loading: Boolean, text: String, onClick: () -> Unit
) {
    LoadingButton(loading, onClick) {
        GradientBox {
            Text(text)
        }
    }
}

@Composable
fun GradientButton(
    text: String, onClick: () -> Unit
) {
    Button(
        onClick = onClick, shape = CircleShape, contentPadding = PaddingValues(0.dp)
    ) {
        GradientBox {
            Text(text)
        }
    }
}

@Preview
@Composable
fun PreviewGradientLoadingButton() {
    var loading by remember { mutableStateOf(false) }
    LoadingGradientButton(loading, "Hola Mundo") { loading = true }
}

@Preview
@Composable
fun PreviewLoadingButton() {
    var loading by remember { mutableStateOf(false) }
    LoadingButton(loading, {
        loading = true
    }) { Text("Hola Mundo") }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    var loading by remember { mutableStateOf(false) }
    GradientButton("Hola Mundo") { loading = true }
}