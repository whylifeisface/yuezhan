package com.example.myapplication.base

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.MainViewModel
import com.example.myapplication.bean.ScreenRouter
import com.example.myapplication.bean.init
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun homeContent(modifier: Modifier = Modifier,navController: NavController, viewModel: MainViewModel, scope: CoroutineScope) {
    val state = rememberScrollableState(
        consumeScrollDelta = {
            it
        }
    )

    val horScroll = rememberLazyListState()

    LazyColumn(modifier = modifier.scrollable(
        state = state,
        orientation = Orientation.Vertical
    ), content = {

        itemsIndexed(init()) { index, item ->

            Column(modifier = Modifier) {
                NewsTitle(modifier = Modifier, viewModel = viewModel)
                if (index == 0) {

                    HomeSearch(
                        navController = navController,
                        HomePageTap = { navController.navigate("${ScreenRouter.SearchPage.Router}/ ") })
                    Spacer(modifier = Modifier.size(30.dp))
//                    HomeLongClick2(navController)
                    HomeLongClick(navigationToDiaLog = {
                        navController.navigate("${ScreenRouter.DiaLog.Router}/{ }/{ }")
                    }, navigationToWebpage = {
                        navController.navigate("${ScreenRouter.WebViewPage.Router}/{$it}")
                    }, DropDownEditClick = {
                        navController.navigate("${ScreenRouter.DiaLog.Router}/${it.first}/{${it.second}}")
                    })
                    Spacer(modifier = Modifier.size(20.dp))
                    val offsetX by remember {
                        mutableStateOf(
                            Offset(0f, 0f)
                        )
                    }
//                    LaunchedEffect(key1 = toLeftEnd, block = {
//                        if (toLeftEnd){
//                            state.animateScrollBy(value = 0.8f)
//                        }
//                    })


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.material.Text(text = "精彩视频 >")
                        IconButton(onClick = {
                                println("a")
                            scope.launch {
                                horScroll.animateScrollToItem(index = 9)
                            }
                        }) {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Alignment.Bottom),
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = ""
                            )
                        }
                    }
                    LazyRow(state = horScroll,modifier = Modifier
                        .width(3000.dp)
                        .offset {
                            IntOffset(
                                offsetX.x.roundToInt(),
                                offsetX.y.roundToInt()
                            )
                        },
                        content = {
                            items(10) {
                                VideoBase()
                            }
                        })
                }
            }
            VerOffsetVideoBase(item)
        }
    })
}