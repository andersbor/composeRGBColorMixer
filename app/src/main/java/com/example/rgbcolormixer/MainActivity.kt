package com.example.rgbcolormixer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rgbcolormixer.ui.theme.RGBColorMixerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RGBColorMixerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var red by remember { mutableIntStateOf(0) }
                    var green by remember { mutableIntStateOf(0) }
                    var blue by remember { mutableIntStateOf(0) }
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ColorMixer(
                            red = red,
                            green = green,
                            blue = blue,
                            redChange = { red = it },
                            greenChange = { green = it },
                            blueChange = { blue = it },
                        )
                        val color = Color(red, green, blue)
                        Text(
                            text = "Selected Color",
                            modifier = Modifier
                                .size(200.dp)
                                .fillMaxWidth()
                                .background(color = color)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColorMixer(
    red: Int,
    green: Int,
    blue: Int,
    redChange: (Int) -> Unit,
    greenChange: (Int) -> Unit,
    blueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Red")
        Slider(
            value = red.toFloat(),
            onValueChange = { redChange(it.toInt()) },
            valueRange = 0f..255f
        )

        Text(text = "Green")
        Slider(
            value = green.toFloat(),
            onValueChange = { greenChange(it.toInt()) },
            valueRange = 0f..255f
        )

        Text(text = "Blue")
        Slider(
            value = blue.toFloat(),
            onValueChange = { blueChange(it.toInt()) },
            valueRange = 0f..255f
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ColorMixerPreview() {
    RGBColorMixerTheme {
        ColorMixer(red = 0, green = 0, blue = 0, redChange = {}, greenChange = {}, blueChange = {})
    }
}