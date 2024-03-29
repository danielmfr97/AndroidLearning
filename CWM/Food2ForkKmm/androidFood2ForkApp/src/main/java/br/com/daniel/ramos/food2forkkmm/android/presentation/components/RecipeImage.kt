package br.com.daniel.ramos.food2forkkmm.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

const val RECIPE_IMAGE_HEIGHT = 260

@Composable
fun RecipeImage(
    url: String,
    contentDescription: String,
) {
    val painter = rememberCoilPainter(request = url)
    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
    }

    when(painter.loadState) {
        is ImageLoadState.Error -> {
            // Show error or w/e
        }
        is ImageLoadState.Success -> {
            // do smtg
        }
        is ImageLoadState.Loading -> {
            // Create a shimmer
            Box(modifier = Modifier.fillMaxWidth().height(RECIPE_IMAGE_HEIGHT.dp)) {
                // empty for white background
            }
        }
    }
}