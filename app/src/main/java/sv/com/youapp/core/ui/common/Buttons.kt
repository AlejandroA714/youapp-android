package sv.com.youapp.core.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.com.youapp.R

@Composable
fun GoogleButton(loading: Boolean,enabled: Boolean = false, onClick: () -> Unit){
    Surface(
        modifier = Modifier.size(48.dp).padding(0.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        shadowElevation = 8.dp,
        onClick = { if (!loading) onClick() },
        enabled = enabled,
    ){
    LoadingButton(loading = loading, enabled = enabled, onClick = onClick, colors = ButtonDefaults.buttonColors(
        containerColor = Color.White.copy(alpha = 0.95f),
        contentColor = Color(0xD9000000), // negro con ~85% opacidad
        disabledContainerColor = Color.White.copy(alpha = 0.3f),
        disabledContentColor = Color(0x99000000)
    )) {
        Icon(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "Google",
            modifier = Modifier.size(28.dp),
            tint = if (enabled) Color.Unspecified else Color(0xFF9E9E9E)
        )
    }
    }
}
@Composable
fun AppleButton(loading: Boolean, enabled: Boolean, onClick: () -> Unit){
    Surface(
        modifier = Modifier.size(48.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        shadowElevation = 8.dp,
        onClick = onClick,
        enabled = enabled
    ){
        LoadingButton(loading = loading, enabled = enabled,onClick= onClick, colors =  ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.White
        )) {
            Icon(
                painter = painterResource(id = R.drawable.ic_apple),
                contentDescription = "Google",
                modifier = Modifier.size(28.dp),
                tint = if (enabled) Color.White else Color.White.copy(alpha = 0.4f)
            )
        }
    }
}

@Composable
fun LoadingButton(
    onClick: () -> Unit,
    loading: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = !loading,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = if (loading) Color.Transparent else colorResource(R.color.disabled),
        disabledContentColor = if (loading) Color.Transparent else colorResource(R.color.disabled_content)
    ),
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = colors,
        contentPadding = PaddingValues(0.dp),
        shape = shape
    ) {
        if (loading) {
            GoogleTwoColorLoader()
        } else {
            content()
        }
    }
}

@Composable
fun LoadingGradientButton(
    loading: Boolean,enabled: Boolean = !loading, text: String, onClick: () -> Unit
) {
    LoadingButton(loading = loading, enabled = enabled, onClick = onClick, shape = CircleShape) {
        GradientBox(enabled && !loading) {
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
    LoadingGradientButton(loading, true,"Hola Mundo") { loading = true }
}

@Preview
@Composable
fun PreviewLoadingButton() {
    var loading by remember { mutableStateOf(false) }
    LoadingButton(loading = loading, onClick = {
        loading = true
    }) { Text("Hola Mundo") }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    var loading by remember { mutableStateOf(false) }
    GradientButton("Hola Mundo") { loading = true }
}

@Preview
@Composable
fun PreviewGoogleButton(){
    GoogleButton(false) { }
}

@Preview
@Composable
fun PreviewAppleButton(){
    AppleButton (false, false) { }
}
