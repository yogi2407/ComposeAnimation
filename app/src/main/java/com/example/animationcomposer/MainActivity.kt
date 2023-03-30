package com.example.animationcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isVisible by remember {
                mutableStateOf(false)
            }

            var shape1 by remember {
                mutableStateOf(false)
            }

            var shape2 by remember {
                mutableStateOf(false)
            }

            var shape3 by remember {
                mutableStateOf(false)
            }

           Column(
               modifier = Modifier.fillMaxSize()
           ) {

               Row(modifier = Modifier.fillMaxWidth()) {
                   Button(onClick = {
                       isVisible = !isVisible
                       shape1 = !shape1
                   }) {
                       Text(text = "Toggle1")
                   }

                   Spacer(modifier = Modifier.width(10.dp))

                   Button(onClick = {
                       shape2 = !shape2
                   }) {
                       Text(text = "Toggle2")
                   }
                   Spacer(modifier = Modifier.width(10.dp))
                   Button(onClick = {
                       shape3 = !shape3
                   }) {
                       Text(text = "Toggle3")
                   }
               }


               val transitionState = updateTransition(
                   targetState = shape1,label = null)

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


               SelectedAnimateItem(title = "Yogesh Manoharan", selected = shape1, onClick = {
                   shape1 = !shape1
               })
               Spacer(modifier = Modifier.height(10.dp))
               SelectedAnimateItem(title = "Aarthi Yogesh", selected = shape2, onClick = {
                   shape2 = !shape2
               })
               Spacer(modifier = Modifier.height(10.dp))
               SelectedAnimateItem(title = "Sivagami", selected = shape2, onClick = {
                   shape2 = !shape2
               })


               
             /* AnimatedVisibility(
                   visible = isVisible,
                   modifier = Modifier
                       .fillMaxWidth()
                       .weight(1f),
                   enter = slideInHorizontally(
                       initialOffsetX = {
                           -it
                       }
                   ) /*+ fadeIn(animationSpec = tween(
                       durationMillis = 10
                   ))*/,
                   exit = slideOutHorizontally(
                       targetOffsetX = {
                           -it
                       }
                   ) /*+ fadeOut(animationSpec = tween(
                       durationMillis = 10
                   ))*/
               ) {
                   Box(modifier = Modifier
                       .background(Color.Blue)
                       .clip(RoundedCornerShape(100)))
               }*/
           }
        }
    }
}

