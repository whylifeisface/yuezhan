package com.example.myapplication.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.bean.VerOffsetVideoBaseItem
import kotlin.math.roundToInt

//@Preview
@Composable
fun VideoBase() {
    Box(modifier = Modifier.size(80.dp, 240.dp)) {
        Card(modifier = Modifier) {
            Image(
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = ""
            )
            IconButton(modifier = Modifier
                .size(10.dp, 10.dp)
                .align(Alignment.TopEnd)
                .offset((-5).dp, 0.dp), onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }
        }

    }
}

@Preview
@Composable
fun Test1() {
    VerOffsetVideoBase(
        items = VerOffsetVideoBaseItem(
            text = "1",
            totalTime = ":9:30",
            comeAndComment = "a"
        ) {})
}

@Composable
fun VideoBase1() {
    var offsetX by remember {
        mutableStateOf(
            Offset(0f, 0f)
        )
    }
    val scrollable = rememberScrollableState(consumeScrollDelta = {
        offsetX = Offset(it, 0f)
        it
    })
    LazyRow(modifier = Modifier
        .width(3000.dp)
        .scrollable(state = scrollable, orientation = Orientation.Horizontal)
        .offset {
            IntOffset(offsetX.x.roundToInt(), offsetX.y.roundToInt())
        },
        content = {
            items(10) {
                VideoBase()
            }
        })
}
@Composable
fun VerOffsetVideoBase(items: VerOffsetVideoBaseItem) {
    Column {
        Text(text = items.text)
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = ""
            )

            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(3.dp))
                    .size(20.dp, 10.dp), onClick = { /*TODO*/ }, enabled = false
            ) {
                Text(text = items.totalTime)
            }
            Icon(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(0.6f))
                    .align(Alignment.Center),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "",
                tint = Color.White
            )
        }
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = items.comeAndComment)
            IconButton(modifier = Modifier.size(16.dp),onClick = items.iconButton) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
fun Text() {
    var offsetX by remember {
        mutableStateOf(
            Offset(0f, 0f)
        )
    }
    rememberScrollableState(consumeScrollDelta = {
        offsetX = Offset(it, 0f)
        it
    })
    LazyRow(modifier = Modifier
        .width(3000.dp)
        .offset {
            IntOffset(offsetX.x.roundToInt(), offsetX.y.roundToInt())
        }, content = {
        items(10) {
            VideoBase()
        }
    })
}