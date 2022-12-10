package com.example.myapplication

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Preview(showBackground = true)
@Composable
fun ListColumn() {
    val arrayList = ArrayList<Int>()
    for (i in 0 until 5) {
        arrayList.add(i)
    }
    var offset by remember {
        mutableStateOf(Offset(0f, 0f))

    }
    val scrollableState = rememberScrollableState{
            delta->
        offset = Offset(delta,0f)
        delta
    }
    LazyRow(
        modifier =
        Modifier
            .scrollable(scrollableState, Orientation.Horizontal)
            .offset{
                   IntOffset(offset.x.roundToInt(),0)
            },
        content = {
            items(arrayList.size) {
                Text(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp), text = "$it"
                )
            }
        })

}