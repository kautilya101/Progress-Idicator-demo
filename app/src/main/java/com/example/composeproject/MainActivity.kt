package com.example.composeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeproject.ui.theme.ComposeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize(), Alignment.Center){
                CircularProgressBar(percentage = 0.8f, number = 100)
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage : Float, number:Int,
    fontsize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    clr: Color = Color.Cyan,
    strokeWidth: Dp = 8.dp,
    animDuration:Int = 1000,
    animDelay :Int = 0
){

    var animationPlayed by remember{
        mutableStateOf(false)
    }

    val currPercentage = animateFloatAsState (
        if(animationPlayed) percentage else 0f,
        tween(
            animDuration,animDelay
        )
    )
    LaunchedEffect(true){
        animationPlayed = true
    }

    Box(
        modifier = Modifier.size(radius * 2f),
        Alignment.Center,
    ){
        Canvas(modifier = Modifier.size(radius * 2f) ){
            drawArc(
                clr,
                -90f,
                360 * currPercentage.value,
                false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontsize,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}
