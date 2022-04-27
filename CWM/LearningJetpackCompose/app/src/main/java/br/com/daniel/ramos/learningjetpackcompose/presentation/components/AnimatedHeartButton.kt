package br.com.daniel.ramos.learningjetpackcompose.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

enum class HeartButtonState {
    IDLE, ACTIVE
}

@Composable
fun AnimatedHeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartButtonState>,
    onToggle: () -> Unit,
) {

    val idleIconSize = 50.dp
    val expandedIconSize = 80.dp
    val currentState by remember { mutableStateOf(buttonState) }
    val transition = updateTransition(currentState, label = "HeartAnimation")

    val heartColor by transition.animateColor(
        transitionSpec = {
            if (targetState.value == HeartButtonState.IDLE)
                tween(durationMillis = 500)
            else
                tween(durationMillis = 500)
        },
        label = "heartColor"
    ) { state ->
        when (state.value) {
            HeartButtonState.IDLE -> Companion.LightGray
            HeartButtonState.ACTIVE -> Companion.Red
        }
    }

    val heartSize by animateDpAsState(
        if (currentState.value == HeartButtonState.ACTIVE)
            expandedIconSize
        else
            idleIconSize,

        keyframes {
            durationMillis = 500
            expandedIconSize at 150
            idleIconSize at 300
        }

    )

    Image(
        imageVector = Icons.Default.Favorite,
        contentDescription = "HeartButton",
        modifier = modifier
            .height(heartSize)
            .width(heartSize)
            .clickable(
                onClick = onToggle
            ),
        colorFilter = ColorFilter.tint(heartColor)
    )
}