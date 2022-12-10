package com.example.myapplication.base

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.bean.LongClickItem
import com.example.myapplication.bean.ScreenRouter
import com.example.myapplication.bean.longClickItemInit
import org.burnoutcrew.reorderable.*
import kotlin.math.roundToInt

//
//@Composable
//fun HomeLongClick() {
//    var state by remember {
//        mutableStateOf(false)
//    }
//    var mutableIndex by remember {
//        mutableStateOf("")
//    }
//    var dialogShow by remember {
//        mutableStateOf(false)
//    }
//    val list = longClickItemInit().toMutableStateList()
//    LazyVerticalGrid(modifier = Modifier.height(260.dp), columns = GridCells.Fixed(5), content = {
//        itemsIndexed(list) { index, item ->
//            LongClickAble(
//                modifier = Modifier.pointerInput(Unit) {
//                    detectTapGestures(onLongPress = {
//                        state = true
//                        mutableIndex = "$index"
//                    }, onTap = {
//                        item.itemClick
//                    })
//                }, item = item
//            )
//        }
//    })
//    DropdownMenu(modifier = Modifier.offset(0.dp, 0.dp), expanded = state, onDismissRequest = {
//        state = false
//    }) {
//        DropdownMenuItem(enabled = false, onClick = {}) {
//            androidx.compose.material.Text(text = list[mutableIndex.toInt()].name)
//        }
//        DropdownMenuItem(onClick = { }) {
//            androidx.compose.material.Text(text = "隐藏")
//        }
//        DropdownMenuItem(onClick = {
//            dialogShow = true
////            mutableIndex = ""
//        }) {
//            androidx.compose.material.Text(text = "编辑")
//        }
//        DropdownMenuItem(onClick = {  }) {
//            androidx.compose.material.Text(text = "复制")
//        }
//        DropdownMenuItem(onClick = {
//            list.remove(list[mutableIndex.toInt()])
//            state = false
//            list.forEach {
//                println(it.name)
//            }
//        }) {
//            androidx.compose.material.Text(text = "删除")
//        }
//        DropdownMenuItem(onClick = {  }) {
//            androidx.compose.material.Text(text = "调整顺序")
//        }
//    }
//    if (dialogShow) if (mutableIndex.isEmpty()) Constrain()
//    else Constrain(
//        web = list[mutableIndex.toInt()].web, name = list[mutableIndex.toInt()].name
//    )
//}

const val HOME_LONG_CLICK_ABEL_ITEM_HEIGHT: Int = 80
const val HOME_LONG_CLICK_ABEL_ITEM_WIDTH: Int = 70
const val HOME_LONG_CLICK_ABEL_ITEM_PADDING: Int = 40
fun myIntOffsetX(index: Int): Int {

    return (index % 5) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH
}

fun myIntOffsetY(index: Int): Int {
    return (index / 5) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
}

//@Stable
//fun Modifier.draggableOrLongClick(
//    DropDownMenuEnable: Boolean,
//    onTap: (Offset) -> Unit,
//    onLongPress: (Offset) -> Unit,
//    onDrag: (change: PointerInputChange, dragAmount: Offset) -> Unit,
//): Modifier {
//    val modifier = if (DropDownMenuEnable) Modifier.pointerInput(Unit) {
//        detectTapGestures(onTap = onTap, onLongPress = onLongPress)
//    } else {
//
//        Modifier.pointerInput(Unit) {
//            println("else")
//            detectDragGestures { change, dragAmount ->
//                onDrag.invoke(change, dragAmount)
//            }
//            detectDragGestures(onDrag = onDrag)
//        }
//    }
//    return this.then(modifier)
//}

//@Stable
//fun Modifier.draggableOrLongClick1(
//    DropDownMenuEnable: Boolean,
//    onTap: (Offset) -> Unit,
//    onLongPress: (Offset) -> Unit,
//    onDrag: (change: PointerInputChange, dragAmount: Offset) -> Unit,
//): Modifier {
//    val modifier = Modifier.pointerInput(DropDownMenuEnable){
//        if (DropDownMenuEnable) detectTapGestures(onTap = onTap, onLongPress = {onLongPress.invoke(it)})
//        else detectDragGestures(onDrag = {change,dragAmount -> println("i am drag")
//            onDrag.invoke(change,dragAmount)})
//    }
//    return this.then(modifier)
//}

@Composable
fun HomeLongClick2(navController: NavController) {
    var state by remember {
        mutableStateOf(false)
    }
    var mutableIndex by remember {
        mutableStateOf("")
    }
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
    var offset = remember {
        Offset.Zero
    }
    if (DropDownMenuEnable) {
        Box(modifier = Modifier
            .offset {
                if (mutableIndex.isEmpty()) IntOffset(0, 0) else IntOffset(
                    myIntOffsetX(mutableIndex.toInt()) + HOME_LONG_CLICK_ABEL_ITEM_PADDING,
                    myIntOffsetY(mutableIndex.toInt()) + HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
                )
            }) {
            DropdownMenu(modifier = Modifier, expanded = state, onDismissRequest = {
                state = false
            }) {
                DropdownMenuItem(enabled = false, onClick = {}) {
                    Text(text = list[mutableIndex.toInt()].name)
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Text(text = "隐藏")
                }
                DropdownMenuItem(onClick = {
                    navController.navigate("${ScreenRouter.DiaLog.Router}/{${list[mutableIndex.toInt()].name}}/{${list[mutableIndex.toInt()].web}}")
                    //  mutableIndex = ""
                }) {
                    Text(text = "编辑")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Text(text = "复制")
                }
                DropdownMenuItem(onClick = {
                    removeIndex = mutableIndex.toInt()
                    if (removeIndex < list.size) {
                        var int = 1
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
                    Text(text = "删除")
                }
                DropdownMenuItem(onClick = {
                    DropDownMenuEnable = false
                }) {
                    Text(text = "调整顺序")
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
                            offset = Offset(it.x, it.y)
                            state = true
                            mutableIndex = index.toString()
                        })
                        else detectDragGestures(onDrag = { change: PointerInputChange, dragAmount: Offset ->

                            offsetX[index] += dragAmount.x.roundToInt()
                            offsetY[index] += dragAmount.y.roundToInt()
                            if (offsetX[index] < change.previousPosition.x) when (index % 5) {
                                1 -> {
                                    if (offsetX[index] == offsetX[index - 1]) {
//                                        scope.launch {
//
//                                        animateTo(prevValue = offsetX[index - 1].toFloat(), target = (offsetX[index - 1] + HOME_LONG_CLICK_ABEL_ITEM_WIDTH).toFloat())
//                                        }
                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH

                                    }

                                }
                                2 -> {
                                    if (offsetX[index] == offsetX[index - 1]) {
                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 2]) {
                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    }

                                }
                                3 -> {
                                    if (offsetX[index] == offsetX[index - 1]) {
                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 2]) {
                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 3]) {
                                        offsetX[index - 3] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    }

                                }
                                4 -> {
                                    if (offsetX[index] == offsetX[index - 1]) {
                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 2]) {
                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 3]) {
                                        offsetX[index - 3] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index - 4]) {
                                        offsetX[index - 4] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    }
                                }
                            } else when (index % 5) {
                                0 -> {
                                    if (offsetX[index] == offsetX[index + 1]) {
                                        offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index + 2]) {
                                        offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index + 3]) {
                                        offsetX[index + 3] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index + 4]) {
                                        offsetX[index + 4] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    }
                                }
                                1 -> {
                                    if (offsetX[index] == offsetX[index + 1]) {
                                        offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index + 2]) {
                                        offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index + 3]) {
                                        offsetX[index + 3] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    }


                                }
                                2 -> {
                                    if (offsetX[index] == offsetX[index + 1]) {
                                        offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    } else if (offsetX[index] == offsetX[index + 2]) {
                                        offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
                                    }


                                }
                                3 -> {
                                    if (offsetX[index] == offsetX[index + 1]) {
                                        offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
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

//
//@Composable
//fun HomeLongClick1(navController: NavController) {
//    var state by remember {
//        mutableStateOf(false)
//    }
//    var mutableIndex by remember {
//        mutableStateOf("")
//    }
//
//    val list = longClickItemInit().toMutableStateList()
////    Spacer(modifier = Modifier.width(60.dp))
//
//    val offsetX = remember {
//        getOffsetX(list.size).toMutableStateList()
//    }
//    val offsetY = remember {
//        getOffsetY(list.size).toMutableStateList()
//    }
//    var DropDownMeunEnable by remember {
//        mutableStateOf(true)
//    }
//    var removeIndex by remember {
//        mutableStateOf(999)
//    }
//    if (DropDownMeunEnable) {
//        Box(modifier = Modifier.offset {
//            if (mutableIndex.isEmpty()) IntOffset(0, 0) else IntOffset(
//                myIntOffsetX(mutableIndex.toInt()) + HOME_LONG_CLICK_ABEL_ITEM_PADDING,
//                myIntOffsetY(mutableIndex.toInt()) + HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
//            )
//        }) {
//            DropdownMenu(modifier = Modifier, expanded = state, onDismissRequest = {
//                state = false
//            }) {
//                DropdownMenuItem(enabled = false, onClick = {}) {
//                    androidx.compose.material.Text(text = list[mutableIndex.toInt()].name)
//                }
//                DropdownMenuItem(onClick = { }) {
//                    androidx.compose.material.Text(text = "隐藏")
//                }
//                DropdownMenuItem(onClick = {
//                    navController.navigate("${ScreenRouter.DiaLog.Router}/{${list[mutableIndex.toInt()].name}}/{${list[mutableIndex.toInt()].web}}")
//                    //  mutableIndex = ""
//                }) {
//                    androidx.compose.material.Text(text = "编辑")
//                }
//                DropdownMenuItem(onClick = {  }) {
//                    androidx.compose.material.Text(text = "复制")
//                }
//                DropdownMenuItem(onClick = {
//                    removeIndex = mutableIndex.toInt()
//                    if (removeIndex < list.size) {
//                        var int: Int = 1
//                        for (i in removeIndex until list.size) {
//                            if (i != removeIndex) {
//                                offsetX[i] -= HOME_LONG_CLICK_ABEL_ITEM_WIDTH * int
//                                int++
//
//                            }
//                        }
//                    }
//
//                    state = false
//                }) {
//                    androidx.compose.material.Text(text = "删除")
//                }
//                DropdownMenuItem(onClick = {
//                    DropDownMeunEnable = false
//                }) {
//                    androidx.compose.material.Text(text = "调整顺序")
//                }
//            }
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = HOME_LONG_CLICK_ABEL_ITEM_PADDING.dp)
//            .height(400.dp),
//        contentAlignment = Alignment.TopStart
//    ) {
//        list.forEachIndexed { index, longClickItem ->
//            if (DropDownMeunEnable) {
//                if (index != removeIndex) {
//                    LongClickAble(modifier = Modifier
//                        .offset {
//                            IntOffset(offsetX[index], offsetY[index])
//                        }
//                        .pointerInput(Unit) {
//                            detectTapGestures(onLongPress = {
//                                mutableIndex = index.toString()
//                                state = true
//                            }, onTap = {
//                                longClickItem.itemClick
//                            })
//
//                        }, longClickItem
//                    )
//                }
//
//            } else
//
//                LongClickAble(modifier = Modifier
//                    .offset {
//                        IntOffset(offsetX[index], offsetY[index])
//                    }
//                    .pointerInput(Unit) {
//
//                        detectDragGestures { change, dragAmount ->
//                            offsetX[index] += dragAmount.x.roundToInt()
//                            offsetY[index] += dragAmount.y.roundToInt()
//                            if (offsetX[index] < change.previousPosition.x) when (index % 5) {
//                                1 -> {
//                                    if (offsetX[index] == offsetX[index - 1]) {
//                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    }
//
//                                }
//                                2 -> {
//                                    if (offsetX[index] == offsetX[index - 1]) {
//                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index - 2]) {
//                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    }
//
//                                }
//                                3 -> {
//                                    if (offsetX[index] == offsetX[index - 1]) {
//                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index - 2]) {
//                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index - 3]) {
//                                        offsetX[index - 3] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    }
//
//                                }
//                                4 -> {
//                                    if (offsetX[index] == offsetX[index - 1]) {
//                                        offsetX[index - 1] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index - 2]) {
//                                        offsetX[index - 2] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index - 3]) {
//                                        offsetX[index - 3] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index - 4]) {
//                                        offsetX[index - 4] += HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    }
//                                }
//                            } else when (index % 5) {
//                                0 -> {
//                                    if (offsetX[index] == offsetX[index + 1]) {
//                                        offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index + 2]) {
//                                        offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index + 3]) {
//                                        offsetX[index + 3] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index + 4]) {
//                                        offsetX[index + 4] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    }
//                                }
//                                1 -> {
//                                    if (offsetX[index] == offsetX[index + 1]) {
//                                        offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index + 2]) {
//                                        offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index + 3]) {
//                                        offsetX[index + 3] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    }
//
//
//                                }
//                                2 -> {
//                                    if (offsetX[index] == offsetX[index + 1]) {
//                                        offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    } else if (offsetX[index] == offsetX[index + 2]) {
//                                        offsetX[index + 2] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    }
//
//
//                                }
//                                3 -> {
//                                    if (offsetX[index] == offsetX[index + 1]) {
//                                        offsetX[index + 1] += -HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                                    }
//
//                                }
//
//                            }
//                        }
//                    }, longClickItem
//                )
//        }
//    }
//
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeLongClick(navigationToDiaLog: () -> Unit, navigationToWebpage: (String) -> Unit,DropDownEditClick:(Pair<String,String>)->Unit) {
    val list = remember {
        longClickItemInit().toMutableStateList()
    }
    var isModifier by remember {
        mutableStateOf(false)
    }
    var activeIndex by remember {
        mutableStateOf(9)
    }
    var from by remember {
        mutableStateOf(99)
    }
    var offset by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    var beginning = Offset.Zero
    var add = 0
//    隐藏 编辑 复制 删除 调整顺序
    DropdownMenu(
        modifier = Modifier.offset{
            if(activeIndex in 0..10){
                IntOffset(coun(activeIndex) * 30,activeIndex / 5 * 30)
            }else{
                IntOffset(0,0)

            }
        },
        expanded = expanded,
        onDismissRequest = { expanded = false; activeIndex = 99 }) {
        LazyColumn(modifier = Modifier.height(40.dp),content = {
            val dropList = listOf("隐藏", "编辑", "复制", "删除", "调整顺序")
            itemsIndexed(dropList) { _: Int, item: String ->

                DropdownMenuItem(onClick = {
                    when (item) {
                        "隐藏" -> {}
                        "编辑" -> {
                            val pair = Pair(list[activeIndex].web,list[activeIndex].name)
                            DropDownEditClick.invoke(pair)
                        }
                        "复制" -> {

                        }
                        "删除" -> {
                            list.removeAt(activeIndex)
                            expanded = false
                        }
                        "调整顺序" -> {
                            isModifier = true
                            expanded = false
                        }
                    }
                }) {
                    Text(text = item)
                }

            }
        })
    }

    LaunchedEffect(key1 = isModifier, block = {
        if (!isModifier) {
            offset = Offset.Zero
            beginning = Offset.Zero
        }
    })

            LazyVerticalGrid(
                modifier = Modifier.height(200.dp),
                columns = GridCells.Fixed(5),
                contentPadding = PaddingValues(5.dp)
            ) {
                itemsIndexed(
                    items = list,
                    key = { item, _ -> item }) { index: Int, item: LongClickItem ->
                    // TODO 隐藏最后一项 添加
                    if (isModifier) {
                        if (index != list.lastIndex) {
                            HomeLongClickCard(
                                modifier = Modifier
                                    .size(40.dp)
                                    .animateItemPlacement()
                                    .offset {
                                        // TODO
                                        if (activeIndex == index) IntOffset(
                                            offset.x.roundToInt(),
                                            offset.y.roundToInt()
                                        )
                                        else if (index in from until activeIndex) {
                                            IntOffset(30, 0)
                                        } else {
                                            IntOffset(0, 0)
                                        }
                                    }
                                    .pointerInput(Unit) {
                                        detectDragGestures(onDrag = { change, dragAmount ->
                                            //
                                            if (beginning == Offset.Zero || activeIndex != index)
                                                beginning = Offset(
                                                    change.previousPosition.x,
                                                    change.previousPosition.y
                                                )
                                            activeIndex = index

                                            // TODO判断是否要移动
                                            offset =
                                                Offset(
                                                    offset.x + dragAmount.x,
                                                    offset.y + dragAmount.y
                                                )

                                            if ((offset.x.roundToInt() - beginning.x.roundToInt() - (6 * add)) in (-5..0)) {
                                                from = activeIndex - add
                                                add += 1
                                                if ((offset.y.roundToInt() - beginning.y.roundToInt() - 30) in (-5..0)) {
                                                    from -= 5
                                                }
                                            }

                                        })

                                    },
                                text = item.name,
                                imageVector = item.imageVector
                                    ?: ImageVector.vectorResource(id = R.drawable.vector_drawable_network),
                                isModifier = true,
                                // Color.Black -> Color.White
                                color = if (index == 0) Color.Red else Color.Black, delClick = {
                                    list.removeAt(index)
                                }
                            )
                        }
                    } else
                        HomeLongClickCard(
                            modifier = Modifier
                                .size(40.dp)
                                .animateItemPlacement()
                                .offset {
                                    // TODO
                                    if (activeIndex == index) IntOffset(
                                        offset.x.roundToInt(),
                                        offset.y.roundToInt()
                                    )
                                    else if (index in from until activeIndex) {
                                        IntOffset(30, 0)
                                    } else {
                                        IntOffset(0, 0)
                                    }
                                }
                                .pointerInput(Unit) {
                                    detectTapGestures(onTap = {
                                        if (index == list.lastIndex) {
                                            navigationToDiaLog.invoke()
                                        } else {
                                            navigationToWebpage.invoke(item.web)
                                        }
                                    },onLongPress = {
                                        if (index < list.lastIndex){

                                            activeIndex = index
                                            expanded = true
                                        }
                                    })
                                },
                            text = item.name,
                            imageVector = item.imageVector
                                ?: ImageVector.vectorResource(id = R.drawable.vector_drawable_network),
                            isModifier = false,
                            // Color.Black -> Color.White
                            color = if (index == 0) Color.Red else Color.Black,
                            delClick = {
                                list.removeAt(index)
                            })

                }
            }
}


//TODO 数组越界异常
//fun changeFormTo(list: MutableList<LongClickItem>, from: Int, to: Int) {
//    if (from < list.size && to < list.size && from < to) {
//        for (i in list.size..0) {
//            if (i in from..to) {
//                list[i + 1] = list[i]
//            }
//        }
//    } else {
//        throw NotImplementedError()
//    }
//}
//
fun coun(i: Int): Int {
    return if (i == 1) {
        1
    } else if (i % 5 == 0) {
        5
    } else {
        i % 5
    }
}
//fun isCover(active:Float,):Boolean{
//
//}

@Composable
fun HomeLongClickCard(
    modifier: Modifier = Modifier,
    text: String,
    imageVector: ImageVector,
    color: Color,

    isModifier: Boolean,
    delClick: () -> Unit,
) {
    Card(
        modifier = modifier.size(30.dp),
        backgroundColor = Color.Transparent
    ) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier) {
                Icon(imageVector = imageVector, contentDescription = "", tint = Color.White)
                if (isModifier) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = Color.Red,
                        modifier = Modifier
                            .offset(
                                (-3).dp,
                                (-3).dp
                            )
                            .pointerInput(Unit) {
                                detectTapGestures {
                                    delClick.invoke()
                                }
                            }
                    )
                }
            }
            Text(
                modifier = Modifier,
                text = text,
                color = color
            )

        }
    }
    Spacer(modifier = Modifier.width(3.dp))
}

fun getOffsetX(total: Int): List<Int> {
    val arrayList = ArrayList<Int>()
    for (i in 0..total) {
        arrayList.add(myIntOffsetX(i))
    }
    return arrayList
}

fun getOffsetY(total: Int): List<Int> {
    val arrayList = ArrayList<Int>()
    for (i in 0..total) {
        arrayList.add(myIntOffsetY(i))
    }
    return arrayList
}