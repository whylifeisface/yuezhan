package com.example.myapplication.base

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.bean.bottomSheetItemInit

@Preview
@Composable
fun BottomSheet() {
    var sheetVisibility by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomTool(
                text = "", modifier = Modifier,
                onClick = {
                    sheetVisibility = true
                },
                sheetVisibility = sheetVisibility
            )
        },
        content = { padding ->
            Text(text = "")
            println(padding)
        }
    )
    AnimatedVisibility(
        visible = sheetVisibility,
        enter = slideInVertically(animationSpec = tween(1000)) { fullHeight -> fullHeight },
        exit = slideOutVertically(animationSpec = tween(1000)) { fullHeight -> fullHeight }
    ) {
        CustomSheet(onClick = {
            sheetVisibility = false
        })
    }

}

@Composable
fun CustomSheet(onClick: (Offset) -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .offset(0.dp, (-80).dp)
            .background(Color.Gray.copy(0.6f))
            .fillMaxSize()
            .height(300.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = onClick)
            }, contentAlignment = Alignment.BottomEnd
    ) {
        LazyVerticalGrid(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),

            columns = GridCells.Fixed(5),
            content = {
                itemsIndexed(bottomSheetItemInit(oneClickItem = {})) { _, item ->
                    Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = item.painter),
                                contentDescription = ""
                            )
                        }
                        Text(text = item.text)
                    }
                }
            })
    }
}

