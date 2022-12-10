package com.example.myapplication.base

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.bean.ScreenRouter
import com.example.myapplication.bean.longClickItemInit
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

fun PairOffset(total: Int): Pair<List<Int>, List<Int>> {
    val arrayList = ArrayList<Int>()
    for (i in 0..total) {
        arrayList.add(myIntOffsetX(i))
    }
    val arrayList1 = ArrayList<Int>()
    for (i in 0..total) {
        arrayList.add(myIntOffsetY(i))
    }
    return Pair(arrayList, arrayList1)
}

suspend fun animateTo(
    prevValue: Float,
    target: Float,
    spec: AnimationSpec<Float> = tween(durationMillis = 800),
): AnimationResult<Float, AnimationVector1D> {
    return Animatable(prevValue).animateTo(target, spec)
}

@Composable
fun HomeLongClick3(navController: NavController) {
    var state by remember {
        mutableStateOf(false)
    }
    var mutableIndex by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val list = longClickItemInit().toMutableStateList()
//    Spacer(modifier = Modifier.width(60.dp))

    val offsetX = remember {
        getOffsetX(list.size).toMutableStateList()
    }
    val offsetY = remember {
        getOffsetY(list.size).toMutableStateList()
    }
    var DropDownMenuEnable by remember {
        mutableStateOf(true)
    }
    var removeIndex by remember {
        mutableStateOf(999)
    }
    if (DropDownMenuEnable) {
        Box(modifier = Modifier.offset {
            if (mutableIndex.isEmpty()) IntOffset(0, 0) else IntOffset(
                myIntOffsetX(mutableIndex.toInt()) + HOME_LONG_CLICK_ABEL_ITEM_PADDING,
                myIntOffsetY(mutableIndex.toInt()) + HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
            )
        }) {
            DropdownMenu(modifier = Modifier, expanded = state, onDismissRequest = {
                state = false
            }) {
                DropdownMenuItem(enabled = false, onClick = {}) {
                    androidx.compose.material.Text(text = list[mutableIndex.toInt()].name)
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    androidx.compose.material.Text(text = "隐藏")
                }
                DropdownMenuItem(onClick = {
                    navController.navigate("${ScreenRouter.DiaLog.Router}/{${list[mutableIndex.toInt()].name}}/{${list[mutableIndex.toInt()].web}}")
                    //  mutableIndex = ""
                }) {
                    androidx.compose.material.Text(text = "编辑")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    androidx.compose.material.Text(text = "复制")
                }
                DropdownMenuItem(onClick = {
                    removeIndex = mutableIndex.toInt()
                    if (removeIndex < list.size) {
                        var int: Int = 1
                        for (i in removeIndex until list.size) {
                            if (i != removeIndex) {
                                offsetX[i] -= HOME_LONG_CLICK_ABEL_ITEM_WIDTH * int
                                int++
                                //                                offsetX[i] -= HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                            }
                        }
                    }

                    state = false
                }) {
                    androidx.compose.material.Text(text = "删除")
                }
                DropdownMenuItem(onClick = {
                    DropDownMenuEnable = false
                }) {
                    androidx.compose.material.Text(text = "调整顺序")
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HOME_LONG_CLICK_ABEL_ITEM_PADDING.dp)
            .height(400.dp),
        contentAlignment = Alignment.TopStart
    ) {
        list.forEachIndexed { index, longClickItem ->


            if (index != removeIndex) {
                LongClickAble(modifier = Modifier
                    .offset {
                        IntOffset(offsetX[index], offsetY[index])
                    }
                    .pointerInput(DropDownMenuEnable) {
                        if (DropDownMenuEnable) detectTapGestures(onTap = {}, onLongPress = {
                            state = true
                            mutableIndex = index.toString()
                        })
                        else detectDragGestures(onDrag = { change: PointerInputChange, dragAmount: Offset ->

                            offsetX[index] += dragAmount.x.roundToInt()
                            offsetY[index] += dragAmount.y.roundToInt()
                            if (offsetX[index] < change.previousPosition.x) when (index % 5) {
                                1 -> {
                                    if (offsetX[index] == offsetX[index - 1]) {
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 1].toFloat(),
                                                target = (offsetX[index - 1] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
//                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH

                                    }

                                }
                                2 -> {
                                    if (offsetX[index] == offsetX[index - 1]) {
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 1].toFloat(),
                                                target = (offsetX[index - 1] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
//                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 2]) {
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 2].toFloat(),
                                                target = (offsetX[index - 2] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
//                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    }

                                }
                                3 -> {
                                    if (offsetX[index] == offsetX[index - 1]) {
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 1].toFloat(),
                                                target = (offsetX[index - 1] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
//                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 2]) {
//                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 2].toFloat(),
                                                target = (offsetX[index - 2] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
                                    } else if (offsetX[index] == offsetX[index - 3]) {
//                                        offsetX[index - 3] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 3].toFloat(),
                                                target = (offsetX[index - 3] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
                                    }

                                }
                                4 -> {
                                    if (offsetX[index] == offsetX[index - 1]) {
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 1].toFloat(),
                                                target = (offsetX[index - 1] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
//                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 2]) {
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 2].toFloat(),
                                                target = (offsetX[index - 2] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
//                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 3]) {
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 3].toFloat(),
                                                target = (offsetX[index - 3] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
//                                        offsetX[index - 3] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 4]) {
                                        scope.launch {
                                            animateTo(
                                                prevValue = offsetX[index - 4].toFloat(),
                                                target = (offsetX[index - 4] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
                                        }
//                                        offsetX[index - 4] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    }
                                }
                            } else when (index % 5) {
                                0 -> {
                                    scope.launch {

                                        if (offsetX[index] == offsetX[index + 1]) {
                                            animateTo(
                                                prevValue = offsetX[index + 1].toFloat(),
                                                target = (offsetX[index + 1] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        } else if (offsetX[index] == offsetX[index + 2]) {
                                            animateTo(
                                                prevValue = offsetX[index + 2].toFloat(),
                                                target = (offsetX[index + 2] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        } else if (offsetX[index] == offsetX[index + 3]) {
                                            animateTo(
                                                prevValue = offsetX[index + 3].toFloat(),
                                                target = (offsetX[index + 3] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 3] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        } else if (offsetX[index] == offsetX[index + 4]) {
                                            animateTo(
                                                prevValue = offsetX[index + 4].toFloat(),
                                                target = (offsetX[index + 4] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 4] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        }
                                    }

                                }
                                1 -> {
                                    scope.launch {
                                        if (offsetX[index] == offsetX[index + 1]) {
                                            animateTo(
                                                prevValue = offsetX[index + 1].toFloat(),
                                                target = (offsetX[index + 1] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        } else if (offsetX[index] == offsetX[index + 2]) {
                                            animateTo(
                                                prevValue = offsetX[index + 2].toFloat(),
                                                target = (offsetX[index + 2] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        } else if (offsetX[index] == offsetX[index + 3]) {
                                            animateTo(
                                                prevValue = offsetX[index + 3].toFloat(),
                                                target = (offsetX[index + 3] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 3] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        }
                                    }



                                }
                                2 -> {
                                    scope.launch {
                                        if (offsetX[index] == offsetX[index + 1]) {
                                            animateTo(
                                                prevValue = offsetX[index + 1].toFloat(),
                                                target = (offsetX[index + 1] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        } else if (offsetX[index] == offsetX[index + 2]) {
                                            animateTo(
                                                prevValue = offsetX[index + 2].toFloat(),
                                                target = (offsetX[index + 2] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        }
                                    }
                                }
                                3 -> {
                                    scope.launch {
                                        if (offsetX[index] == offsetX[index + 1]) {
                                            animateTo(
                                                prevValue = offsetX[index + 1].toFloat(),
                                                target = (offsetX[index + 1] - HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat()
                                            )
//                                            offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                        }
                                    }


                                }

                            }
                        })
                    }, longClickItem
                )
            }
        }
    }
}