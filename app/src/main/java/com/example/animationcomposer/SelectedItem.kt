package com.example.animationcomposer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SelectedAnimateItem(
    title: String,
    selected:Boolean,
    titleColor:Color = if(selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = .2f),
    titleSize:TextUnit = MaterialTheme.typography.h6.fontSize,
    titleFont:FontWeight = FontWeight.Medium,
    borderWidth: Dp = 2.dp,
    borderShape:Shape = RoundedCornerShape(size = 10.dp),
    borderColor: Color =  MaterialTheme.colors.primary,
    icon:ImageVector = Icons.Default.CheckCircle,
    iconColor: Color = if(selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = .2f),
    onClick: ()-> Unit
) {

    val scaleA = remember {
        Animatable(1f)
    }

    val scaleB = remember {
        Animatable(1f)
    }

    LaunchedEffect(key1 = selected) {
        if(selected) {
            launch {
                scaleA.animateTo(targetValue = .9f,
                    animationSpec = tween(durationMillis = 50)
                )
                scaleA.animateTo(targetValue = 1f,
                    animationSpec = spring()
                )
            }

            launch {
                scaleB.animateTo(targetValue = .3f,
                    animationSpec = tween(durationMillis = 50)
                )
                scaleB.animateTo(targetValue = 1f,
                    animationSpec = spring()
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 40.dp)
            .scale(scaleA.value)
            .width(300.dp)
            .border(
                width = borderWidth,
                shape = borderShape,
                color = borderColor
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .weight(8f)
                    .clickable { onClick() },
                color = titleColor,
                fontSize = titleSize,
                fontWeight = titleFont
            )
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Circle Icon",
                    tint = iconColor,
                    modifier = Modifier.weight(2f).scale(scaleB.value)
                )
            }
        }
    }
}