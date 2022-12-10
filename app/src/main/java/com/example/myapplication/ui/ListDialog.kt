package com.example.myapplication.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.bean.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ListDialog(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    OutlinedButtonClick: () -> Unit,
    textContent: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
) {
//    Surface(contentColor = Color.Gray, color = Color.White) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        text = textContent,
        title = title,
        modifier = modifier,
        dismissButton = dismissButton,
        confirmButton = {
            OutlinedButton(
                onClick = OutlinedButtonClick,
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Gray)
            ) {
                Text(text = "取消")
            }
        }, backgroundColor = Color.Gray, contentColor = Color.White
    )
//    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListDialogRadio(
    ListDialogRadioCardClick: () -> Unit,
    ListDialogRadioSelected: Boolean,
    modifier: Modifier = Modifier,
    text: String,
) {
    Card(
        onClick = ListDialogRadioCardClick,
        modifier = modifier,
        backgroundColor = Color.Gray,
        contentColor = Color.White
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = ListDialogRadioSelected,
                onClick = ListDialogRadioCardClick,
                colors = RadioButtonDefaults.colors(unselectedColor = Color.White)
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = text)
        }
    }
}

data class ListDialogRadioItem(
    val ListDialogRadioCardClick: () -> Unit,
    val ListDialogRadioSelected: Boolean,
    val text: String,
)

fun searchEngineDialogList(): List<ListDialogRadioItem> {
    return listOf(
        ListDialogRadioItem(ListDialogRadioCardClick = {}, ListDialogRadioSelected = false, "百度"),
        ListDialogRadioItem(ListDialogRadioCardClick = {}, ListDialogRadioSelected = true, "Bing"),
        ListDialogRadioItem(ListDialogRadioCardClick = {}, ListDialogRadioSelected = false, "搜狗"),
        ListDialogRadioItem(ListDialogRadioCardClick = {}, ListDialogRadioSelected = false, "头条"),
        ListDialogRadioItem(ListDialogRadioCardClick = {}, ListDialogRadioSelected = false, "夸克"),
    )
}

@Composable
fun SearchEngineDialog1(scope: CoroutineScope, navController: NavController, dataStore: dataStore) {

    val data: Int = dataStore.getData("默认搜索引擎", 1)
    var activeIndex by remember {
        mutableStateOf(data)
    }
    ListDialog(
        modifier = Modifier,
        title = { Text(text = "默认搜索引擎") },
        OutlinedButtonClick = {
            navController.popBackStack()
        },
        onDismissRequest = {
            navController.popBackStack()
            if (data != activeIndex) {
                scope.launch {
                    dataStore.putData("默认搜索引擎", activeIndex)
                    println(dataStore.getData("默认搜索引擎", 1))
                }
            }
        },
        textContent = {
            LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
                itemsIndexed(searchEngineDialogList()) { index, item ->
                    ListDialogRadio(
                        ListDialogRadioSelected = activeIndex == index,
                        ListDialogRadioCardClick = {
                            //搜索引擎m默认Bing
                            activeIndex = index
                        },
                        modifier = Modifier,
                        text = item.text
                    )
                }
            })
        }
    )
}
//
//@Composable
//fun SearchEngineDialog(
//    OutlinedButtonClick: () -> Unit,
//    scope: CoroutineScope,
//    context: Context,
//    onDismissRequest: () -> Unit,
//) {
//    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
//    var i = 0
//    for (it in searchEngineDialogList()) {
//        if (it.ListDialogRadioSelected) break
//        else i++
//    }
//    var activeIndex by remember {
//        mutableStateOf(i)
//    }
//    ListDialog(
//        modifier = Modifier,
//        title = { Text(text = "默认搜索引擎") },
//        OutlinedButtonClick = OutlinedButtonClick,
//        onDismissRequest = onDismissRequest,
//        textContent = {
//            LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
//                itemsIndexed(searchEngineDialogList()) { index, item ->
//                    ListDialogRadio(
//                        ListDialogRadioSelected = activeIndex == index,
//                        ListDialogRadioCardClick = {
//                            scope.launch {
//                                context.dataStore.edit { settings ->
//                                    //搜索引擎m默认Bing
//                                    activeIndex = index
//                                    var currentCounterValue = settings[EXAMPLE_COUNTER] ?: 1
//                                    if (currentCounterValue != index)
//                                        currentCounterValue = index
//                                    println(currentCounterValue)
//                                }
//                            }
//                        },
//                        modifier = Modifier,
//                        text = item.text
//                    )
//                }
//            })
//        }
//    )
//}

fun screenRotationList(): List<ListDialogRadioItem> {
    return listOf(
        ListDialogRadioItem(
            ListDialogRadioCardClick = {},
            ListDialogRadioSelected = false,
            "始终跟随系统"
        ),
        ListDialogRadioItem(ListDialogRadioCardClick = {}, ListDialogRadioSelected = true, "始终竖屏"),
        ListDialogRadioItem(ListDialogRadioCardClick = {}, ListDialogRadioSelected = false, "始终横屏")
    )
}

fun uaList(): List<String> {
    return listOf(
        "默认",
        "电脑",
        "iPhone",
        "iPad"
    )
}

@Composable
fun ClearDialog(
    modifier: Modifier = Modifier,
    OutlinedButtonClick: () -> Unit,
    onDismissRequest: () -> Unit,
    ensureClick: () -> Unit,
) {
    ListDialog(
        title = { Text(text = "清除浏览数据") },
        modifier = modifier,
        OutlinedButtonClick = OutlinedButtonClick,
        textContent = {
            LazyColumn(content = {
                itemsIndexed(clearDialogItem()) { index, item ->
                    val checkedList = remember {
                        MutableList(clearDialogItem().size, init = {
                            false
                        }).toMutableStateList()
                    }
                    ClearDialogCheckbox(
                        checked = checkedList[index],
                        onCheckedChange = {
                            checkedList[index] = !checkedList[index]
                        },
                        text = item
                    )
                }
            })
        },
        onDismissRequest = onDismissRequest,
        dismissButton = {
            OutlinedButton(
                onClick = ensureClick,
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Gray)
            ) {
                Text(text = "确定")
            }
        }
    )
}

fun clearDialogItem(): List<String> {
    return listOf(
        "网站访问历史记录",
        "搜索记录",
        "Cookie和网站数据",
        "缓存的图片和文件",
        "保存的表单信息",
        "保存的密码信息"
    )
}

@Composable
fun ClearDialogCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange, colors = CheckboxDefaults.colors(uncheckedColor = Color.White, checkedColor = MaterialTheme.colors.primary))
        Spacer(modifier = modifier.width(25.dp))
        Text(text = text, color = Color.White)
    }
}

@Composable
fun UA(dataStore: dataStore, navController: NavController, scope: CoroutineScope) {
    val ua: Int = dataStore.UAGet()
    var activeIndex by remember {
        mutableStateOf(ua)
    }
    ListDialog(
        title = { Text(text = "浏览器标识(UA)") },
        OutlinedButtonClick = { navController.popBackStack() },
        textContent = {
            LazyColumn(content = {
                itemsIndexed(
                    uaList()
                ) { index: Int, item: String ->
                    ListDialogRadio(
                        ListDialogRadioCardClick = {
                            if (activeIndex != index) activeIndex = index
                        },
                        ListDialogRadioSelected = activeIndex == index,
                        text = item
                    )
                }
            })
        },
        onDismissRequest = {
            navController.popBackStack()
            if (activeIndex != dataStore.UAGet())
                scope.launch {
                    dataStore.UASet(activeIndex)
                }
        })
}
//fun screenRotationList1():List<String>{
//
//    return listOf(
//        "始终跟随系统",
//        "始终竖屏",
//        "始终横屏"
//    )
//}
//@Composable
//fun ScreenRotationDialog() {
//    var i = 0
//    for (it in screenRotationList()) {
//        if (it.ListDialogRadioSelected) break
//        else i++
//    }
//
//    var activeIndex by remember {
//        mutableStateOf(i)
//    }
//
//    ListDialog(
//        modifier = Modifier,
//        title = { Text(text = "屏幕旋转") },
//        OutlinedButtonClick = { },
//        onDismissRequest = {},
//        textContent = {
//            LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
//                itemsIndexed(screenRotationList()) { index, item ->
//                    ListDialogRadio(
//                        ListDialogRadioSelected = activeIndex == index,
//                        ListDialogRadioCardClick = {
//                            activeIndex = index
//
//                        },
//                        modifier = Modifier,
//                        text = item.text
//                    )
//                }
//            })
//        }
//    )
//}


//@Composable
//fun ScreenRotationDialog1(
//    scope: CoroutineScope,
//    context: Context,
//    onDismissRequest: () -> Unit,
//    OutlinedButtonClick: () -> Unit,
//) {
//    val SCREEN_ROTATION = intPreferencesKey("screen_rotation")
//
//    var i = 0
//    for (it in screenRotationList()) {
//        if (it.ListDialogRadioSelected) break
//        else i++
//    }
//
//    var activeIndex by remember {
//        mutableStateOf(i)
//    }
//
//    ListDialog(
//        modifier = Modifier,
//        title = { Text(text = "屏幕旋转") },
//        OutlinedButtonClick = OutlinedButtonClick,
//        onDismissRequest = onDismissRequest,
//        textContent = {
//            LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
//                itemsIndexed(screenRotationList()) { index, item ->
//                    ListDialogRadio(
//                        ListDialogRadioSelected = activeIndex == index,
//                        ListDialogRadioCardClick = {
//                            activeIndex = index
//                            scope.launch {
//                                context.dataStore.edit {
//                                    var cre = it[SCREEN_ROTATION] ?: 0
//                                    if (index != cre)
//                                        cre = index
//                                    println(cre)
//                                }
//                            }
//                        },
//                        modifier = Modifier,
//                        text = item.text
//                    )
//                }
//            })
//        }
//    )
//}

@Composable
fun ScreenRotationDialog2(
    scope: CoroutineScope,
    navController: NavController,
    dataStore: dataStore,
) {
//
//    var i = 0
//    for (it in screenRotationList()) {
//        if (it.ListDialogRadioSelected) break
//        else i++
//    }
    val i = dataStore.getData("屏幕旋转", 1)
    var activeIndex by remember {
        mutableStateOf(i)
    }

    ListDialog(
        modifier = Modifier,
        title = { Text(text = "屏幕旋转") },
        OutlinedButtonClick = { navController.popBackStack() },
        onDismissRequest = {
            navController.popBackStack()
            scope.launch {
                if (activeIndex != i)
                    scope.launch {
                        dataStore.putData("屏幕旋转", activeIndex)
                    }
            }
        },

        textContent = {
            LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
                itemsIndexed(screenRotationList()) { index, item ->
                    ListDialogRadio(
                        ListDialogRadioSelected = activeIndex == index,
                        ListDialogRadioCardClick = {
                            activeIndex = index
                        },
                        modifier = Modifier,
                        text = item.text
                    )
                }
            })
        }
    )
}