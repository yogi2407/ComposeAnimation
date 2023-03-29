package com.example.animationcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animationcomposer.ui.theme.AnimationComposerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isVisible by remember {
                mutableStateOf(false)
            }

            var shape by remember {
                mutableStateOf(false)
            }

           Column(
               modifier = Modifier.fillMaxSize()
           ) {
               Button(onClick = { 
                   isVisible = !isVisible
                   shape = !shape
               }) {
                   Text(text = "Toggle")
               }

               val transitionState = updateTransition(
                   targetState = shape,label = null)

               val radiusRange by transitionState.animateInt(transitionSpec = {tween(durationMillis = 1000)}, label = "radius", targetValueByState = { isShape->
                   if(isShape) 100 else 0

               })

               val colorRange by transitionState.animateColor(transitionSpec = {
                   tween(durationMillis = 1000)
               }, label = "color", targetValueByState = { shapeRange ->
                   if(shapeRange) Color.Blue else Color.Magenta
               })



               Box(modifier = Modifier
                   .padding(30.dp)
                   .width(200.dp)
                   .height(200.dp)
                   .clip(RoundedCornerShape(radiusRange))
                   .background(colorRange))
               
              AnimatedVisibility(
                   visible = isVisible,
                   modifier = Modifier
                       .fillMaxWidth()
                       .weight(1f),
                   enter = slideInHorizontally() + fadeIn(animationSpec = tween(
                       durationMillis = 200
                   )),
                   exit = slideOutHorizontally() + fadeOut(animationSpec = tween(
                       durationMillis = 200
                   ))
               ) {
                   Box(modifier = Modifier
                       .background(Color.Blue)
                       .clip(RoundedCornerShape(100)))
               }
           }
        }
    }
}

