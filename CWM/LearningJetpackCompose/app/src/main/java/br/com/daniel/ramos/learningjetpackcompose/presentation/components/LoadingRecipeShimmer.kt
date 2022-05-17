package br.com.daniel.ramos.learningjetpackcompose.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingRecipeShimmer(
    imageHeight: Dp,
    padding: Dp = 16.dp
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val cardWidthPx = with(LocalDensity.current) { (maxWidth - (padding * 2)).toPx() }
        val cardHeightPx = with(LocalDensity.current) { (imageHeight - padding).toPx() }
        val gradientWidthPx = 0.3f * cardHeightPx

        val shimmerAnimationSpec = infiniteRepeatable<Float>(
            animation = tween(
                durationMillis = 1300,
                delayMillis = 300,
                easing = LinearEasing
            )
        )

        val infiniteTransition = rememberInfiniteTransition()

        val colors = listOf(
            Color.LightGray.copy(alpha = .9f),
            Color.LightGray.copy(alpha = .3f),
            Color.LightGray.copy(alpha = .9f),
        )

        val xPosition = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = cardWidthPx,
            animationSpec = shimmerAnimationSpec
        )
        val yPosition = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = cardHeightPx,
            animationSpec = shimmerAnimationSpec
        )

        val brush = Brush.linearGradient(
            colors = colors,
            start = Offset(
                x = xPosition.value - gradientWidthPx,
                y = yPosition.value - gradientWidthPx
            ),
            end = Offset(x = xPosition.value, y = yPosition.value)
        )

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size (imageHeight)
                        .background(brush = brush)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 10)
                        .background(brush = brush)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 10)
                        .background(brush = brush)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight / 10)
                        .background(brush = brush)
                )
            }
        }
    }
}