package com.example.myapplication.bean

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.PopUpToBuilder
import androidx.navigation.navArgument

data class BottomToolItem(
    val imageVector: ImageVector? = null,
    val enabled: Boolean,
    val itemClick: () -> Unit,
)

fun bottomToolItemInit(
    navController: NavController,
    menuButtonClick: () -> Unit,
): List<BottomToolItem> {

    return listOf(
        BottomToolItem(
            imageVector = Icons.Default.ArrowBack,
            enabled = false,
            itemClick = { navController.popBackStack() }
        ) ,
        BottomToolItem(imageVector = Icons.Default.ArrowForward, enabled = false, itemClick = {
        }) ,
        BottomToolItem(enabled = true, itemClick = {
            navController.navigate("${ScreenRouter.SearchPage.Router}/ ") {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }) ,
        BottomToolItem(imageVector = Icons.Default.Home, enabled = true, itemClick = {
            navController.navigate(ScreenRouter.Home.Router) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }) ,
        BottomToolItem(
            imageVector = Icons.Default.Menu,
            enabled = true,
            itemClick = menuButtonClick
        )
    )
}