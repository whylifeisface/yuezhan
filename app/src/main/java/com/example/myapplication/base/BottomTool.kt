package com.example.myapplication.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.bean.BottomToolItem


@Composable
fun BottomTool(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    sheetVisibility: Boolean,
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            start = 10.dp,
            end = 10.dp
        ),
    ) {
        println(sheetVisibility)
        val scope = rememberCoroutineScope()
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            AnimatedVisibility(visible = sheetVisibility) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        })
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            AnimatedVisibility(visible = sheetVisibility) {

                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
            }
        })
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Button(
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                onClick = { /*TODO*/ },
                contentPadding = ButtonDefaults.TextButtonContentPadding
            ) {
                Text(text = text)
            }
        })
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            AnimatedVisibility(visible = sheetVisibility) {

                Icon(imageVector = Icons.Default.Home, contentDescription = "")
            }
        })
        BottomNavigationItem(selected = false, onClick = onClick, icon = {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "")
        })

    }
}


@Composable
fun BottomTool1(
    items: List<BottomToolItem>,
    bottomToolCenterText: String,
    sheetVisibility: Boolean,
    atClick: (String) -> Unit,
) {

//    val bottomToolCenterText = if (currentDestination?.route == ScreenRouter.Home.Router)"主页" else "url"
    BottomNavigation(modifier = Modifier.height(80.dp)) {
        items.forEach {
            BottomNavigationItem(selected = false, onClick = it.itemClick, icon = {
                if (it.imageVector != null) {
                    if (sheetVisibility) {
                        if (it.imageVector == Icons.Default.Menu)
                            Icon(imageVector = it.imageVector, contentDescription = "")
                        else Spacer(modifier = Modifier.size(10.dp))
                    } else Icon(imageVector = it.imageVector, contentDescription = "")

                } else {
                    Button(
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        enabled = true,
                        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                        onClick = {
                            var string = " "
                            if (bottomToolCenterText != "主页") {
                                string = bottomToolCenterText
                            }
                            atClick.invoke(string)
                        },
                        contentPadding = ButtonDefaults.TextButtonContentPadding
                    ) {
                        Text(
                            text = bottomToolCenterText,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            })
        }
    }
}
//@Composable
//fun BottomTool2(items: List<BottomToolItem>, bottomToolCenterText: String,sheetVisibility:Boolean) {
//
////    val bottomToolCenterText = if (currentDestination?.route == ScreenRouter.Home.Router)"主页" else "url"
//    BottomNavigation(modifier = Modifier.height(80.dp)) {
//        items.forEach {
//            BottomNavigationItem(selected = false, onClick = it.itemClick, icon = {
//                if (it.imageVector != null) {
//                    if (sheetVisibility)
//                    {
//                        if (it.imageVector == Icons.Default.Menu)
//                            Icon(imageVector = it.imageVector, contentDescription = "")
//                    }
//                    else  Icon(imageVector = it.imageVector, contentDescription = "")
//
//                } else {
//                    Button(
//                        colors = ButtonDefaults.textButtonColors(
//                            backgroundColor = Color.Transparent,
//                            contentColor = Color.Black
//                        ),
//                        onClick = { it.itemClick },
//                        contentPadding = ButtonDefaults.TextButtonContentPadding
//                    ) {
//                        Text(text = bottomToolCenterText)
//                    }
//                }
//            })
//        }
//    }
//}