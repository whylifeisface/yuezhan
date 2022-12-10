package com.example.myapplication.ui.Page

import android.content.Context
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.myapplication.MainViewModel
import com.example.myapplication.base.homeContent
import com.example.myapplication.bean.dataStore
import com.example.myapplication.model.BottomModelSheetInit
import com.example.myapplication.mutiscreenViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope

//
//@Composable
//fun HomePage(navController: NavController, viewModel: MainViewModel) {
//    Common(
//        navController = navController,
//        CommonContent = {
//
//            val state = rememberScrollableState(consumeScrollDelta = {
//                it
//            })
//
//            Column(
//                modifier = Modifier
//                    .height(4000.dp)
//                    .scrollable(state = state, orientation = Orientation.Vertical)
//            ) {
//                Spacer(modifier = Modifier.size(30.dp))
//                NewsTitle(modifier = Modifier.padding(horizontal = 10.dp), viewModel = viewModel)
//                Spacer(modifier = Modifier.size(30.dp))
//                One6()
//                Spacer(modifier = Modifier.size(20.dp))
//
//                Column {
//                    Row(horizontalArrangement = Arrangement.SpaceAround) {
//                        androidx.compose.material.Text(text = "精彩视频 >")
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(
//                                modifier = Modifier.size(16.dp),
//                                imageVector = Icons.Default.ArrowForward,
//                                contentDescription = ""
//                            )
//                        }
//                    }
//                    HomeSearch(navController = navController, HomePageTap = {
//                        println("再见再见")
//                        navController.navigate("${ScreenRouter.SearchPage.Router}/{null}")
//                    })
//
//                }
//
//                VideoBase1()
//                LazyColumn(content = {
//                    itemsIndexed(init()) { _, item ->
//                        VerOffsetVideoBase(item)
//                    }
//                })
//
//            }
//        }
//    )
//}
//
//@Composable
//fun HomePage1(navController: NavController, viewModel: MainViewModel) {
//    Common(
//        navController = navController,
//        CommonContent = {
//            val state = rememberScrollableState(consumeScrollDelta = {
//                it
//            })
//            LazyColumn(modifier = Modifier.scrollable(
//                state = state,
//                orientation = Orientation.Vertical
//            ), content = {
//
//                itemsIndexed(init()) { index, item ->
//
//                    Column(modifier = Modifier) {
//                        NewsTitle(modifier = Modifier, viewModel = viewModel)
//                        if (index == 0) {
//
//                            HomeSearch(
//                                navController = navController,
//                                HomePageTap = { navController.navigate("${ScreenRouter.SearchPage.Router}/{''}") })
//                            Spacer(modifier = Modifier.size(30.dp))
//                            HomeLongClick()
//                            Spacer(modifier = Modifier.size(20.dp))
//                            var offsetX by remember {
//                                mutableStateOf(
//                                    Offset(0f, 0f)
//                                )
//                            }
//                            val scrollable = rememberScrollableState(consumeScrollDelta = {
//                                offsetX = Offset(it, 0f)
//                                it
//                            })
//                            Row(horizontalArrangement = Arrangement.SpaceAround) {
//                                androidx.compose.material.Text(text = "精彩视频 >")
//                                IconButton(onClick = { /*TODO*/ }) {
//                                    Icon(
//                                        modifier = Modifier.size(16.dp),
//                                        imageVector = Icons.Default.ArrowForward,
//                                        contentDescription = ""
//                                    )
//                                }
//                            }
//                            LazyRow(modifier = Modifier
//                                .width(3000.dp)
//                                .scrollable(
//                                    state = scrollable,
//                                    orientation = Orientation.Horizontal
//                                )
//                                .offset {
//                                    IntOffset(
//                                        offsetX.x.roundToInt(),
//                                        offsetX.y.roundToInt()
//                                    )
//                                },
//                                content = {
//                                    items(10) {
//                                        VideoBase()
//                                    }
//                                })
//                        }
//                    }
//                    VerOffsetVideoBase(item)
//                }
//            })
//        })
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun HomePage2(navController: NavController, viewModel: MainViewModel) {
//    val snackHost = remember {
//        SnackbarHostState()
//    }
//    val scope = rememberCoroutineScope()
//
//    val sheetScaffoldState =
//        rememberBottomSheetScaffoldState(
//            bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed),
//            snackbarHostState = snackHost
//        )
//    BackHandler() {
//        if (sheetScaffoldState.bottomSheetState.isCollapsed) {
//            scope.launch {
//                snackHost.showSnackbar("再按一次返回键将退出应用")
//
//            }
//        } else {
//            scope.launch {
//                sheetScaffoldState.bottomSheetState.collapse()
//            }
//        }
//
//    }
//    val menuButtonClick: () -> Unit = {
//        if (sheetScaffoldState.bottomSheetState.isCollapsed)
//            scope.launch {
//                sheetScaffoldState.bottomSheetState.expand()
//            }
//        else
//            scope.launch {
//                sheetScaffoldState.bottomSheetState.collapse()
//            }
//
//    }
//    BottomSheetScaffold(sheetContent = {
//
//        LazyVerticalGrid(modifier = Modifier,
//            columns = GridCells.Fixed(5),
//            content = {
//                itemsIndexed(bottomSheetItemInit(oneClickItem = {})) { _, item ->
//                    Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(
//                                painter = painterResource(id = item.painter),
//                                contentDescription = ""
//                            )
//                        }
//                        androidx.compose.material.Text(text = item.text)
//                    }
//                }
//            })
//    }, scaffoldState = sheetScaffoldState,
//        content = {
//            Scaffold(
//                bottomBar = {
//                    BottomTool1(
//                        items = bottomToolItemInit(
//                            navController = navController,
//                            menuButtonClick = menuButtonClick
//                        ),
//                        bottomToolCenterText = "",
//                        sheetVisibility = sheetScaffoldState.bottomSheetState.isCollapsed
//                    )
//                },
//                content = { it ->
//                    println(it)
//                    homeContent(navController = navController, viewModel = viewModel)
//                }
//            )
//        })
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun HomePage3(navController: NavController, viewModel: MainViewModel) {
//    val sheetScaffoldState =
//        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//    val scope = rememberCoroutineScope()
//    val menuButtonClick: () -> Unit = {
//        scope.launch {
//            sheetScaffoldState.show()
//        }
//    }
//
//    ModalBottomSheetLayout(sheetContent = {
//        LazyVerticalGrid(modifier = Modifier,
//            columns = GridCells.Fixed(5),
//            content = {
//                itemsIndexed(bottomSheetItemInit(oneClickItem = {})) { _, item ->
//                    Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(
//                                painter = painterResource(id = item.painter),
//                                contentDescription = ""
//                            )
//                        }
//                        Text(text = item.text)
//                    }
//                }
//            })
//    }, sheetState = sheetScaffoldState, content = {
//        Scaffold(bottomBar = {
//            BottomTool1(
//                items = bottomToolItemInit(
//                    navController = navController,
//                    menuButtonClick = menuButtonClick
//                ),
//                bottomToolCenterText = "",
//                sheetVisibility = sheetScaffoldState.isVisible
//            )
//        }) { it ->
//            println(it)
//            homeContent(navController = navController, viewModel = viewModel)
//        }
//    })
//}
//
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context,
    mutiscreenViewModel: mutiscreenViewModel,
    viewModel: MainViewModel,
    dataStore: dataStore,
    scope: CoroutineScope,
) {
    dataStore.urlPut("主页")
    BottomModelSheetInit(
        navController = navController,
        modifier = modifier,
        context = context,
        content = {
            println(it)
            ProvideWindowInsets {
                // your content
                rememberSystemUiController()
                 rememberSystemUiController().apply {
                    setNavigationBarColor(Color.Transparent, darkIcons = false)
                    setStatusBarColor(
                        Color.Transparent, darkIcons = MaterialTheme.colors.isLight
                    )
                }
//设置导航栏透明色
                homeContent(
                    modifier = Modifier.navigationBarsPadding(),
                    navController = navController,
                    viewModel = viewModel,
                    scope = scope
                )
            }

        },
        mutiscreenViewModel = mutiscreenViewModel,
        dataStore = dataStore
    )
}