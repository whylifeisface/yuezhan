package com.example.myapplication.ui.Page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController


@Composable
fun OtherPage(
    modifier: Modifier = Modifier,
    navigationIconClick: () -> Unit,
    title: String,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            PageTopBar(navigationIconClick = navigationIconClick, title = title)
        },
        content = content
    )
}


@Composable
fun OtherPageWithClick(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    content: @Composable (PaddingValues) -> Unit,
) {
    OtherPage(
        modifier = modifier,
        title = title,
        content = content,
        navigationIconClick = {
            navController.popBackStack()
        }
    )
}

@Composable
fun PageTopBar(title: String, modifier: Modifier = Modifier, navigationIconClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navigationIconClick.invoke() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        title = {
            Text(text = title)
        }, modifier = modifier
    )
}