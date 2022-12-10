package com.example.myapplication

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput


fun Modifier.LongClick(onLongClick: (Offset) -> Unit):Modifier = pointerInput(this){
    detectTapGestures(onLongPress= onLongClick)
}