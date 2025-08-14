package com.example.cinema.feature.buytickets.presentation

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitVerticalDragOrCancellation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType.Companion.Press
import androidx.compose.ui.input.pointer.PointerEventType.Companion.Release
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.geometry.Size

import androidx.compose.ui.graphics.drawscope.Stroke

@Preview
@Composable
fun BuyTicketsScreen(modifier: Modifier = Modifier) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var isGestureActive by remember { mutableStateOf(false) }
    val localScope = rememberCoroutineScope()

    val animatedScale by animateFloatAsState(
        targetValue = if (!isGestureActive) 1f else scale,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 300f),
        label = "scale_animation"
    )
    val animatedOffset by animateOffsetAsState(
        targetValue = if (!isGestureActive) Offset.Zero else offset,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 300f),
        label = "offset_animation"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, _ ->
                    isGestureActive = true
                    scale = (scale * zoom).coerceIn(0.5f, 3f)
                    offset += pan
                }
            }
            .pointerInput(Unit) {
                awaitEachGesture {
                    do {
                        val event = awaitPointerEvent()
                    } while (event.changes.any { it.pressed })

                    localScope.launch {
                        delay(200)
                        isGestureActive = false
                        scale = 1f
                        offset = Offset.Zero
                    }
                }
            }
    ) {

        CurvedScreen(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = animatedScale,
                    scaleY = animatedScale,
                    translationX = animatedOffset.x,
                    translationY = animatedOffset.y
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = animatedScale,
                    scaleY = animatedScale,
                    translationX = animatedOffset.x,
                    translationY = animatedOffset.y
                )

        ) {
            repeat(6) {
                Row {
                   repeat(6) {
                       Box(
                           modifier = Modifier
                               .size(40.dp)
                               .padding(horizontal = 10.dp, vertical = 10.dp)
                               .background(Color.Blue)
                       )
                   }
                }
            }
        }
    }
}







@Composable
fun CurvedScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            // Настройки дуги
            val arcHeight = 200f  // Чем больше, тем круче дуга
            val startX = 200f      // Отступ слева
            val endX = canvasWidth - 200f  // Отступ справа (можно сделать разным)
            val arcWidth = endX - startX + arcHeight // Ширина дуги (с запасом для выпуклости)

            drawArc(
                color = Color.Gray,
                startAngle = 200f,
                sweepAngle = 140f,
                useCenter = false,
                topLeft = Offset(
                    x = startX - (arcWidth - (endX - startX)) / 2,  // Центрируем дугу между startX и endX
                    y = canvasHeight * 0.2f  // Смещение сверху
                ),
                size = Size(arcWidth, arcHeight),
                style = Stroke(width = 4f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCurvedScreen() {
    CurvedScreen()
}