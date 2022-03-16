package br.com.daniel.ramos.learningjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import br.com.daniel.ramos.learningjetpackcompose.domain.model.Recipe
import br.com.daniel.ramos.learningjetpackcompose.network.RecipeService
import br.com.daniel.ramos.learningjetpackcompose.network.model.RecipeNetworkEntity
import br.com.daniel.ramos.learningjetpackcompose.network.model.RecipeNetworkMapper
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity "

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)

        CoroutineScope(IO).launch {
            val response = service.get(
                token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                583
            )
            Log.d(TAG, "onCreate: $response")
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Preview(showSystemUi = true)
    @Composable
    fun TestCompose() {
        Column() {
            LoadImage()
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(color = Color(0xFFF2F2F2))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Happy Meal",
                        style = TextStyle(
                            fontSize = TextUnit(26F, TextUnitType.Sp)
                        ),
                    )
                    Text(
                        text = "R$16,99",
                        style = TextStyle(
                            color = Color(0xff85bb65),
                            fontSize = TextUnit(17F, TextUnitType.Sp)
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    text = "800 calories",
                    style = TextStyle(
                        fontSize = TextUnit(17F, TextUnitType.Sp)
                    )
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "ORDER NOW")
                }
            }
        }
    }

    @Composable
    fun LoadImage() {
        Image(
            painter = painterResource(id = R.drawable.happy_meal_small),
            contentDescription = null,
            modifier = Modifier.height(300.dp),
            contentScale = ContentScale.Crop,
        )
    }
}