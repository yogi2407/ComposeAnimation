package com.example.animationcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.animationcomposer.ui.theme.AnimationComposerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isVisible by remember {
                mutableStateOf(false)
            }
           Column(
               modifier = Modifier.fillMaxSize()
           ) {
               Button(onClick = { 
                   isVisible = !isVisible
               }) {
                   Text(text = "Toggle")
               }
               
               AnimatedVisibility(
                   visible = isVisible,
                   modifier = Modifier.fillMaxWidth().weight(1f),
                   enter = slideInHorizontally() + fadeIn()
               ) {
                   Box(modifier = Modifier.background(Color.Blue))
               }
           }
        }
    }
}

