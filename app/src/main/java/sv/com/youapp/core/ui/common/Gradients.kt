package sv.com.youapp.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientBox(content: @Composable () -> Unit){
    Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFF6200EE), Color(0xFF03DAC5))
                ),
                shape = CircleShape
            )
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) { content () }
}

