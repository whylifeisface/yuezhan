package com.example.myapplication.model

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.MainActivity
import com.example.myapplication.base.BottomTool1
import com.example.myapplication.bean.*
import com.example.myapplication.mutiscreenViewModel
import com.example.myapplication.share.share
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomModelSheet(
    modifier: Modifier,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit,
    sheetState: ModalBottomSheetState,
) {
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetContent = sheetContent,
        content = content,
        sheetState = sheetState,
        sheetBackgroundColor = Color.White
    )
}

/**
 * activeIndex 如何保存
 */

@Composable //bgc 由sheet渲染
fun BottomSheetMenuSetting(
    modifier: Modifier = Modifier,
    BottomSheetMenuSettingList: List<BottomSheetMenuSettingItem> = listOf(
        BottomSheetMenuSettingItem(text = "主页", true)
    ),
    activeIndex: Int = 0,
    removeMutiScreen: (id: Int) -> Unit,
    navController: NavController,
    mutiscreenAdd: () -> Unit,
) {

    val toMutableStateList = remember {
        BottomSheetMenuSettingList.toMutableStateList()
    }
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {

        toMutableStateList.forEachIndexed { index, i ->
            BottomSheetMenuSettingCard(
                text = i.text,
                color = if (i.active) Color.Blue.copy(0.6f) else Color.Black,
                IconButtonOnclick =
                {
                    if (toMutableStateList.size > 1) {
                        toMutableStateList.removeAt(index)
                        removeMutiScreen.invoke(index)
                    }
                },
                // TODO 上面的text 应该是数据库里面 的内容 点击是跳转到当前url
                // 未测试
                OutlinedButtonOnclick = {
                    if (toMutableStateList[index].text.length <= 2) {
                        if (navController.graph.route != ScreenRouter.Home.Router)
                            navController.navigate("${ScreenRouter.Home.Router}/{n}")
                    } else {
                        navController.navigate("${ScreenRouter.WebViewPage.Router}/{${toMutableStateList[index].text}}")
                    }
                },
                activeBoolean = activeIndex == index
            )
        }
        OutlinedButton(
            onClick = {
                toMutableStateList.add(BottomSheetMenuSettingItem(text = "主页", true))
                mutiscreenAdd.invoke()
            },
            modifier
                .align(CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text(text = "新建窗口", color = Color.Blue.copy(0.6f))
        }
    }
}

@Composable
fun BottomSheetMenuSettingCard(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    OutlinedButtonOnclick: () -> Unit,
    IconButtonOnclick: () -> Unit,
    activeBoolean: Boolean,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(0.8f),
            onClick = OutlinedButtonOnclick,
            elevation = ButtonDefaults.elevation(5.dp),
            border = if (activeBoolean) BorderStroke(
                1.dp,
                Color.Red
            ) else ButtonDefaults.outlinedBorder
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier, text = text, color = color)
                IconButton(modifier = Modifier, onClick = IconButtonOnclick) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                }
            }
        }
    }
}

@Composable
fun CustomSheet(
    modifier: Modifier = Modifier,
    list: List<BottomSheetItem>,
    color: Color,
) {

    LazyVerticalGrid(modifier = modifier
        .fillMaxWidth()
        .height(250.dp),
        columns = GridCells.Fixed(5),
        content = {
            itemsIndexed(list) { index, item ->
                Column(Modifier, horizontalAlignment = CenterHorizontally) {
                    Surface(
                        contentColor = if (listOf(
                                3,
                                4,
                                5,
                                8,
                                9
                            ).contains(index)
                        ) color else Color.Blue
                    ) {
                        IconButton(onClick = item.itemClick) {
                            Icon(
                                painter = painterResource(id = item.painter),
                                contentDescription = ""
                            )
                        }
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.subtitle1,
                            color = Color.Gray.copy(0.6f)
                        )
                    }
                }
            }
        })
}

enum class ModelBottomSheetEnum {
    BottomSheet,
    MenuSetting
}

fun exitApp() {
    val activity = MainActivity()
    activity.finish()
    exitProcess(0)
}

//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun Pointer(
//    navController: NavController,
//    snackbarHost: SnackbarHostState,
//    sheetScaffoldState: ModalBottomSheetState,
//    scope: CoroutineScope,
//) {
//    BackHandler(enabled = true) {
//        if (sheetScaffoldState.isVisible) {
//            scope.launch {
//                sheetScaffoldState.hide()
//            }
//        } else {
//            navController.popBackStack()
//            scope.launch {
//                snackbarHost.showSnackbar("sss")
//                object : OnBackPressedCallback(true) {
//                    override fun handleOnBackPressed() {
//                        exitApp()
//                    }
//
//                }
//            }
//        }
//
//
//    }
//}

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun BottomModelSheetInit(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context,
    mutiscreenViewModel: mutiscreenViewModel,
    content: @Composable (PaddingValues) -> Unit,
    dataStore: dataStore,
) {

    val scope = rememberCoroutineScope()
    val snackbarState = remember {
        SnackbarHostState()
    }
    val sheetScaffoldState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    var animatedContentTransition by remember {
        mutableStateOf(ModelBottomSheetEnum.BottomSheet)
    }


    LaunchedEffect(key1 = sheetScaffoldState.isVisible, block = {
        if (animatedContentTransition.name == ModelBottomSheetEnum.MenuSetting.name && !sheetScaffoldState.isVisible) {
            animatedContentTransition = ModelBottomSheetEnum.BottomSheet
        }
    })


    val time = remember {
        mutableStateOf(System.currentTimeMillis())
    }

//    Pointer(navController = navController, snackbarHost, sheetScaffoldState, scope)
    BackHandler(enabled = true) {
        if (sheetScaffoldState.isVisible) {
            scope.launch {
                sheetScaffoldState.hide()
            }
        } else {
//            navController.popBackStack()
            if (navController.graph.route == ScreenRouter.WebViewPage.Router) {
                navController.popBackStack()
            } else
                if (System.currentTimeMillis() - time.value > 20000) {

                    scope.launch {
                        snackbarState.showSnackbar("sss")
                        time.value = System.currentTimeMillis()
                    }
                } else {
                    exitApp()
                }
        }
    }

    BottomModelSheet(
        modifier = modifier,
        content = {
            BottomModelSheetContent(
                modifier = Modifier,
                navController = navController,
                menuButtonClick = {
                    scope.launch {
                        sheetScaffoldState.show()
                    }
                },
                sheetScaffoldState = sheetScaffoldState.isVisible,
                snackbarHost = {
                    SnackbarHost(hostState = snackbarState)
                }, content = content,
                dataStore = dataStore
            )
        },
        sheetContent = {

            AnimatedContent(
                targetState = animatedContentTransition, transitionSpec = {
                    ContentTransform(
                        slideInVertically { fullHeight: Int -> fullHeight },
                        slideOutVertically { fullHeight: Int -> fullHeight }
                    )
                }
            ) {
                if (animatedContentTransition.name == ModelBottomSheetEnum.BottomSheet.name) {
                    CustomSheet(
                        list = bottomSheetItemInit1(oneClickItem = {
                            animatedContentTransition = ModelBottomSheetEnum.MenuSetting
                        }, shareClick = {
                            share(context = context)
                        }, Settings = {
                            navController.navigate(ScreenRouter.SettingsPage.Router)
                            scope.launch {
                                sheetScaffoldState.hide()
                            }
                        }),
                        color = if (navController.graph.route == ScreenRouter.Home.Router) Color.Blue else Color.Gray.copy(
                            0.6f
                        )
                    )
                } else {
                    //TODO      OutlinedButtonOnclick
                    val bottomSheetMenuSettingList = MutableList(0) {
                        BottomSheetMenuSettingItem("主页", true)
                    }
                    var activeIndex = 0
                    LaunchedEffect(key1 = true, block = {



                        mutiscreenViewModel.query().apply {
                            forEachIndexed loop@{ index, item ->
                                if (item.isActive) {
                                    activeIndex = index
                                }
                                bottomSheetMenuSettingList.add(
                                    BottomSheetMenuSettingItem(
                                        item.text,
                                        item.isActive
                                    )
                                )
                            }
                        }

                    })
                    BottomSheetMenuSetting(
                        activeIndex = activeIndex,
                        removeMutiScreen = {

                            mutiscreenViewModel.deleteById(it)
                        },
                        navController = navController,
                        mutiscreenAdd = {
                            mutiscreenViewModel.insert()
                        }
                    )


                }
            }
        },
        sheetState = sheetScaffoldState,

        )
}

@Composable
fun BottomModelSheetContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    menuButtonClick: () -> Unit,
    sheetScaffoldState: Boolean,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    content: @Composable (PaddingValues) -> Unit,
    dataStore: dataStore,
) {

    val bottomToolCenterText by remember {
        mutableStateOf(dataStore.urlGet())
    }

    Scaffold(
        modifier = modifier, bottomBar = {
            BottomTool1(
                items = bottomToolItemInit(
                    navController = navController,
                    menuButtonClick = menuButtonClick
                ),
                bottomToolCenterText = bottomToolCenterText,
                sheetVisibility = sheetScaffoldState, atClick = {

                    navController.navigate("${ScreenRouter.SearchPage.Router}/ $it")
                }
            )
        },
        snackbarHost = snackbarHost, content = content
    )
}