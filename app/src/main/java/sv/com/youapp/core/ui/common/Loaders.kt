package sv.com.youapp.core.ui.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun GoogleTwoColorLoader(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    strokeWidth: Dp = 3.dp
) {
    val colors = listOf(
        Color(0xFF4285F4),
        Color(0xFFEA4335),
        Color(0xFFFBBC05),
        Color(0xFF34A853)
    )

    val transition = rememberInfiniteTransition(label = "google_two_color")

    // Rotación continua
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1100, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // “respiración” del arco (abre/cierra)
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "phase"
    )

    // fase de color continua (no salto brusco)
    val colorPhase by transition.animateFloat(
        initialValue = 0f,
        targetValue = colors.size.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1600,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "colorPhase"
    )

    Canvas(
        modifier = modifier
            .size(size)
            .rotate(rotation)
    ) {
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            cap = StrokeCap.Round
        )

        val minSweep = 90f
        val maxSweep = 220f
        val sweep = lerpFloat(minSweep, maxSweep, phase)

        val gap = 40f
        val start1 = -90f
        val start2 = start1 + sweep + gap

        // índice base y fracción para interpolar colores
        val rawIndex = colorPhase % colors.size
        val baseIndex = floor(rawIndex).toInt()
        val t = rawIndex - floor(rawIndex)

        fun colorAt(i: Int) = colors[i % colors.size]

        fun blendColor(c1: Color, c2: Color, f: Float): Color {
            return Color(
                red   = lerpFloat(c1.red,   c2.red,   f),
                green = lerpFloat(c1.green, c2.green, f),
                blue  = lerpFloat(c1.blue,  c2.blue,  f),
                alpha = lerpFloat(c1.alpha, c2.alpha, f)
            )
        }

        val c1 = blendColor(
            colorAt(baseIndex),
            colorAt(baseIndex + 1),
            t
        )
        val c2 = blendColor(
            colorAt(baseIndex + 1),
            colorAt(baseIndex + 2),
            t
        )

        drawArc(
            color = c1,
            startAngle = start1,
            sweepAngle = sweep,
            useCenter = false,
            style = stroke
        )

        drawArc(
            color = c2,
            startAngle = start2,
            sweepAngle = sweep,
            useCenter = false,
            style = stroke
        )
    }
}

private fun lerpFloat(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}