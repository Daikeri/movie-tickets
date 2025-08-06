package com.example.cinema.feature.buytickets.presentation

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun BuyTicketsScreen(modifier: Modifier = Modifier) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var isGestureActive by remember { mutableStateOf(false) }
    val localScope = rememberCoroutineScope()

    // Анимация для плавного возврата
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
                    localScope.launch {
                        delay(2000)
                        isGestureActive = false
                    }
                }
            }
            .graphicsLayer(
                scaleX = animatedScale,
                scaleY = animatedScale,
                translationX = animatedOffset.x,
                translationY = animatedOffset.y
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)

        ) {
            repeat(8) {
                Row {
                   repeat(8) {
                       Box(
                           modifier
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