package com.example.myapplication.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myapplication.bean.bottomToolItemInit
import com.example.myapplication.bean.ScreenRouter

//@Composable
//fun Common(CommonContent: @Composable (PaddingValues) -> Unit, navController: NavController) {
//    var sheetVisibility by remember {
//        mutableStateOf(false)
//    }
//    val currentDestination = navController.currentBackStackEntry?.destination
//    val bottomToolCenterText =
//        if (currentDestination?.route == ScreenRouter.Home.Router) "主页" else "url"
//    Scaffold(
//        modifier = Modifier,
//        bottomBar = {
//            BottomTool1(
//                bottomToolItemInit(navController) { sheetVisibility = true },
//                bottomToolCenterText = bottomToolCenterText,
//                sheetVisibility = sheetVisibility,
//                atClick = {
//
//                })
//        },
//        content = CommonContent
//    )
//    AnimatedVisibility(
//        visible = sheetVisibility,
//        enter = slideInVertically(animationSpec = tween(1000)) { fullHeight -> fullHeight },
//        exit = slideOutVertically(animationSpec = tween(1000)) { fullHeight -> fullHeight }
//    ) {
//        CustomSheet(onClick = {
//            sheetVisibility = false
//        })
//    }
//}