package com.example.myapplication.ui.Page

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.bean.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SettingsPageContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    dataStore: dataStore,
) {
//    Column(modifier = modifier) {
//        Basic2(navController = navController, dataStore = dataStore)
//        Privacy(dataStore = dataStore)
//    }
    val scroll = remember {
        ScrollState(initial = 0)
    }

    Column(modifier = modifier
        .verticalScroll(scroll)
        .height(1200.dp),
        content = {
            Basic2(navController = navController, dataStore = dataStore)
            Privacy(dataStore = dataStore, clearClick = {
                navController.navigate(ScreenRouter.ClearDialog.Router)
            })
    })
}

fun privacyList(clearClick:()->Unit): List<SettingsPageItem1> {
    return listOf(
        SettingsPageItem1(
            settingsPageItemEnum = SettingsPageItemEnum.SWITCH,
            title = "?????????????????????",
            summary = "??????????????????????????????????????????",
            defaultValue = true,
            settingsCardState = SettingsCardState(
                switchClicked = true,
                 switchOnCheckedChange = {}),
            SettingsCardClick = {

            }
        ),
        SettingsPageItem1(
            settingsPageItemEnum = SettingsPageItemEnum.SWITCH,
            title = "????????????",
            summary = "?????????????????????????????????????????????",
            defaultValue = true,
            settingsCardState = SettingsCardState(switchClicked = true, switchOnCheckedChange = {}),
            SettingsCardClick = {

            }
        ),
        SettingsPageItem1(
            settingsPageItemEnum = SettingsPageItemEnum.SWITCH,
            title = "???????????????",
            summary = "???????????????????????????",
            defaultValue = true,
            settingsCardState = SettingsCardState(switchClicked = false, switchOnCheckedChange = {}),
            SettingsCardClick = {

            }
        ),
        SettingsPageItem1(
            settingsPageItemEnum = SettingsPageItemEnum.DIALOG,
            title = "??????????????????",
            summary = "??????????????????",
            defaultValue = 99,
            settingsCardState = SettingsCardState( switchOnCheckedChange = {}),
            SettingsCardClick = clearClick
        ),
        SettingsPageItem1(
            settingsPageItemEnum = SettingsPageItemEnum.Snack,
            title = "??????????????????",
            summary = "???????????????????????????",
            defaultValue = 99,
            settingsCardState = SettingsCardState( switchOnCheckedChange = {}),
            SettingsCardClick = {

            }
        ),
        SettingsPageItem1(
            settingsPageItemEnum = SettingsPageItemEnum.NAVCONROLL,
            title = "????????????????????????",
            summary = "????????????????????????,???????????????",
            defaultValue = 99,
            settingsCardState = SettingsCardState( switchOnCheckedChange = {}),
            SettingsCardClick = {

            }
        ),
    )
}

@Composable
fun Privacy(dataStore: dataStore,clearClick:()->Unit) {
    TextWithPrimay("????????????")
    LazyColumn(content = {
        itemsIndexed(privacyList(clearClick = clearClick)) { index: Int, item: SettingsPageItem1 ->
            val sum = dataStore.getData(item.title, item.defaultValue)

            SettingsCard(
                title = item.title,
                summary = if (item.settingsPageItemEnum == SettingsPageItemEnum.DIALOG) sum.toString() else item.summary,
                SettingsCardClick = item.SettingsCardClick,
                settingsCardState = item.settingsCardState,
                dataStore = dataStore
            )
        }
    })
}

//
//class SettingsPageItem(
//    val title: String,
//    val summary: String,
//    val settingsCardState: SettingsCardState,
//    val SettingsCardClick: () -> Unit,
//
//    )
//
//fun list(): List<SettingsPageItem> {
//    return listOf(
//        SettingsPageItem(title = "??????????????????",
//            summary = "??????????????????????????????",
//            SettingsCardClick = {
//            },
//            settingsCardState = SettingsCardState(
//                switchClicked = true,
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(
//            title = "??????????????????",
//            summary = "Bing",
//            SettingsCardClick = { },
//            settingsCardState = SettingsCardState(
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(
//            title = "????????????", summary = "????????????", SettingsCardClick = {
//
//            }, settingsCardState = SettingsCardState(
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(
//            title = "????????????", summary = "?????????????????????????????????", SettingsCardClick = {
//
//            }, settingsCardState = SettingsCardState(
//                switchClicked = true,
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(title = "????????????", summary = "?????????????????????????????????", SettingsCardClick = {
//
//        }, settingsCardState = SettingsCardState(
//            switchClicked = true,
//            switchOnCheckedChange = {}
//        )),
//        SettingsPageItem(
//            title = "???????????????(UA)", summary = "??????", SettingsCardClick = {
//
//            }, settingsCardState = SettingsCardState(
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(
//            title = "????????????????????????", summary = "??????????????????????????????", SettingsCardClick = {
//
//            }, settingsCardState = SettingsCardState(
//                linearProgressIndicatorProgress = 0.3f,
//                switchOnCheckedChange = {}
//            )
//        )
//    )
//}
//
//fun list1(searchClick: () -> Unit, RotationClick: () -> Unit): List<SettingsPageItem> {
//    return listOf(
//        SettingsPageItem(title = "??????????????????",
//            summary = "??????????????????????????????",
//            SettingsCardClick = {
//            },
//            settingsCardState = SettingsCardState(
//                switchClicked = true,
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(
//            title = "??????????????????",
//            summary = "Bing",
//            SettingsCardClick = searchClick,
//            settingsCardState = SettingsCardState(
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(
//            title = "????????????",
//            summary = "????????????",
//            SettingsCardClick = RotationClick,
//            settingsCardState = SettingsCardState(
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(
//            title = "????????????", summary = "?????????????????????????????????", SettingsCardClick = {
//
//            }, settingsCardState = SettingsCardState(
//                switchClicked = true,
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(title = "????????????", summary = "?????????????????????????????????", SettingsCardClick = {
//
//        }, settingsCardState = SettingsCardState(
//            switchClicked = true,
//            switchOnCheckedChange = {}
//        )),
//        SettingsPageItem(
//            title = "???????????????(UA)", summary = "??????", SettingsCardClick = {
//
//            }, settingsCardState = SettingsCardState(
//                switchOnCheckedChange = {}
//            )
//        ),
//        SettingsPageItem(
//            title = "????????????????????????", summary = "??????????????????????????????", SettingsCardClick = {
//
//            }, settingsCardState = SettingsCardState(
//                linearProgressIndicatorProgress = 0.3f,
//                switchOnCheckedChange = {}
//            )
//        )
//    )
//}

fun list2(
    searchClick: () -> Unit,
    RotationClick: () -> Unit,
    UaClick: () -> Unit,
): List<SettingsPageItem1> {
    return listOf(
        SettingsPageItem1(
            title = "??????????????????",
            summary = "??????????????????????????????",
            settingsPageItemEnum = SettingsPageItemEnum.NAVCONROLL,
            defaultValue = 1,
            settingsCardState = SettingsCardState(
                switchClicked = true,
                switchOnCheckedChange = {}
            ),
            SettingsCardClick = {
            },
        ),
        SettingsPageItem1(
            title = "??????????????????",
            summary = "Bing",
            settingsPageItemEnum = SettingsPageItemEnum.DIALOG,
            defaultValue = 1,
            settingsCardState = SettingsCardState(
                switchOnCheckedChange = {}
            ),
            SettingsCardClick = searchClick,
        ),
        SettingsPageItem1(
            title = "????????????",
            summary = "????????????",
            settingsPageItemEnum = SettingsPageItemEnum.DIALOG,
            defaultValue = 1,
            settingsCardState = SettingsCardState(
                switchOnCheckedChange = {}
            ),
            SettingsCardClick = RotationClick,
        ),
        SettingsPageItem1(
            title = "????????????", summary = "?????????????????????????????????",
            settingsPageItemEnum = SettingsPageItemEnum.SWITCH,
            defaultValue = false,
            settingsCardState = SettingsCardState(
                switchClicked = true,
                switchOnCheckedChange = {}
            ),
            SettingsCardClick = RotationClick,
        ),
        SettingsPageItem1(
            title = "????????????",
            summary = "?????????????????????????????????",
            settingsPageItemEnum = SettingsPageItemEnum.SWITCH,
            defaultValue = false,
            settingsCardState = SettingsCardState(
                switchClicked = true,
                switchOnCheckedChange = {}
            ),
            SettingsCardClick = {

            }
        ),
        SettingsPageItem1(
            title = "???????????????(UA)", summary = "??????",
            settingsPageItemEnum = SettingsPageItemEnum.DIALOG,
            defaultValue = 1,
            settingsCardState = SettingsCardState(
                switchOnCheckedChange = {}
            ),
            SettingsCardClick = UaClick
        ),
        SettingsPageItem1(
            title = "????????????????????????", summary = "??????????????????????????????",
            settingsPageItemEnum = SettingsPageItemEnum.LIN,
            defaultValue = 61f,
            settingsCardState = SettingsCardState(
                linearProgressIndicatorProgress = 0.3f,
                switchOnCheckedChange = {}
            ),
            SettingsCardClick = {

            }
        )
    )
}
//@Composable
//fun Basic() {
//    TextWithPrimay("??????")
//    LazyColumn(content = {
//        itemsIndexed(
//            list()
//        ) { _, item ->
//            SettingsCard(
//
//                title = item.title,
//                summary = item.summary,
//                SettingsCardClick = item.SettingsCardClick,
//                settingsCardState = item.settingsCardState
//            )
//        }
//    }
//    )
//}


//@Composable
//fun Basic1(navController: NavController) {
//    TextWithPrimay("??????")
//    LazyColumn(content = {
//        itemsIndexed(
//            list1(searchClick = {
//                navController.navigate(ScreenRouter.SearchEngineDialog.Router)
//            }, RotationClick = {
//                navController.navigate(ScreenRouter.ScreenRotationDialog.Router)
//            })
//        ) { _, item ->
//
//            SettingsCard(
//                title = item.title,
//                summary = item.summary,
//                SettingsCardClick = item.SettingsCardClick,
//                settingsCardState = item.settingsCardState,
//            )
//        }
//    }
//    )
//}


@Composable
fun Basic2(navController: NavController, dataStore: dataStore) {
    TextWithPrimay("??????")
    LazyColumn(content = {
        itemsIndexed(
            list2(searchClick = {
                navController.navigate(ScreenRouter.SearchEngineDialog.Router)
            }, RotationClick = {
                navController.navigate(ScreenRouter.ScreenRotationDialog.Router)
            }, UaClick = {
                navController.navigate(ScreenRouter.UA.Router)
            })
        ) { _, item ->
            val sum = dataStore.getData(item.title, defaultValue = item.defaultValue)

            if (sum is Boolean) item.settingsCardState.switchClicked =
                sum else item.settingsCardState.switchClicked
            println(sum)
            SettingsCard(
                title = item.title,
                summary = if (item.settingsCardState.linearProgressIndicatorProgress == null && item.settingsCardState.switchClicked == null) sum.toString() else item.summary,
                SettingsCardClick = item.SettingsCardClick,
                settingsCardState = item.settingsCardState,
                dataStore = dataStore
            )
        }
    }
    )
}

@Composable
fun TextWithPrimay(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 10.sp,
        color = MaterialTheme.colors.primary
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsCard(
    title: String,
    summary: String,
    SettingsCardClick: () -> Unit,
    settingsCardState: SettingsCardState,
    modifier: Modifier = Modifier,
    dataStore: dataStore,
) {
    //
    val scope = rememberCoroutineScope()
    Card(modifier = modifier.fillMaxWidth(0.8f), onClick = {
        if (settingsCardState.linearProgressIndicatorProgress == null)
            SettingsCardClick.invoke()
    }, enabled = settingsCardState.linearProgressIndicatorProgress == null) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var onDrag by remember {
                mutableStateOf(false)
            }
            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                Text(text = title)
                Text(
                    text = summary,
                    color = Color.Gray.copy(0.6f)
                )

                settingsCardState.linearProgressIndicatorProgress?.let { float ->
                    var value by remember {
                        mutableStateOf(float)
                    }
                    Slider(value = value, onValueChange = {
                            value = it

                    }, onValueChangeFinished = {
//                        dataStore.putData(title, value)
                    })
                }
                settingsCardState.switchClicked?.let {
                    var checked by remember {
                        mutableStateOf(it)
                    }
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = !checked
                            scope.launch {
                                // TODO ??????????????????????????? ?????????????????????
                                delay(2000)
                                dataStore.putData(title, checked)
                                println("kotlin")
                            }
                        },
                    )
                }
            }
        }
        Spacer(
            modifier = modifier
                .height(5.dp)
                .fillMaxWidth()
        )
    }
}

//title
@Composable
fun SettingsPage(navController: NavController, dataStore: dataStore) {
    OtherPageWithClick(
        content = {
            println(it)
            SettingsPageContent(navController = navController, dataStore = dataStore)
        },
        navController = navController,
        title = "??????"
    )
}

