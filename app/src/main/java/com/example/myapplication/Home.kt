package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


@Composable
fun One(row: GridCells, content: LazyGridScope.() -> Unit) {
    LazyVerticalGrid(
        columns = row,
        content = content,
    )
}

//@Composable
//fun HomePage() {
//
//}
@Composable
fun One3(OnItemClick: () -> Unit, viewModel: MainViewModel) {
    var offset by remember { mutableStateOf(Offset.Zero) }

//    val scope = rememberCoroutineScope()
    One(row = GridCells.Adaptive(200.dp), content = {

        items(list()) {

            Text(
                modifier = Modifier
                    .offset {
                        IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                    }
                    .clickable {
                        OnItemClick()
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(

                            onDragEnd = {

                            }, onDrag = { _: PointerInputChange, dragAmount: Offset ->

                                offset += dragAmount
                            })
                    }
                    .LongClick {
                        viewModel.dropDownShow()
                    }, text = "$it"
            )
        }

    })
    Down(viewModel)
}

fun stringList(): List<String> {
    return listOf<String>(
        "编辑",
        "添加",
        "删除",
        "前往",
        "移动"
    )
}

@Composable
fun Down(viewModel: MainViewModel) {
    //只有初始化时调用
    //抛给viewModel 好处是别人也可以修改它的状态
    val expanded = remember {
        viewModel.expanded.value!!
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { viewModel.dropDownHidden() }) {
        stringList().forEach {
            Text(modifier = Modifier.clickable {
                viewModel.dropDownClick(it)
            }, text = it)
        }
    }
}

//@Composable
//fun One1() {
//
//    val offsetX = remember {
//        mutableStateOf(0f)
//    }
//    val offsetY = remember {
//        mutableStateOf(0f)
//    }
//    val bol = remember {
//        mutableStateOf(false)
//    }
//    val offset1 = remember {
//        mutableStateOf(0)
//    }
//
////    val off = MyOffset(offsetX, offsetY)
//    val st = remember {
//        mutableStateOf(0)
//    }
//    val listA = listA()
//    One(row = GridCells.Adaptive(minSize = 50.dp)) {
//        items(
//            listA
//        ) { i ->
//            Box(modifier = Modifier
//                .clickable {
//                }
//                .LongClick {
//                    bol.value = true
//                    offset1.value = (i.data % 6) * 50
//                    st.value = i.data
//                }) {
//                Text(
//                    text = "${list()[i.data]}",
//                    modifier = Modifier
//                        .pointerInput(Unit) {
//                            if (i.bol) {
//                                detectDragGestures { _, dragAmount ->
//                                    offsetX.value = dragAmount.x
//                                    offsetY.value = dragAmount.y
//                                }
//                            } else {
//                                return@pointerInput
//                            }
//                        }
//                        .offset {
//                            if (i.bol) {
//                                IntOffset(40, 100)
//                            } else {
//                                IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt())
//                            }
//                        }
//                )
//            }
//
//        }
//    }
//    val modifier = Modifier.offset {
//        IntOffset(offset1.value, offset2.value)
//    }
//    DropdownMenu(expanded = bol.value, onDismissRequest = { bol.value = false }) {
//        repeat(list().size - 10) {
//            DropdownMenuItem(
//                modifier = Modifier,
//                onClick = {
//                    when (it) {
//                        3 -> {
//                            listA[st.value].bol = true
//
//                        }
//                    }
//                }) {
//                Text(text = "$it")
//            }
//        }
//    }
//}

//data class MyOffset(val offsetX: MutableState<Float>, val offsetY: MutableState<Float>)

fun list(): List<Int> {
    val arrayList = ArrayList<Int>()
    for (i in 0..15) {
        arrayList.add(i)
    }
    return arrayList
}

fun listA(): List<st> {
    val arrayList = ArrayList<st>()
    for (i in 0..15) {
        arrayList.add(st(i, false))
    }
    return arrayList
}

data class st(var data: Int, var bol: Boolean)

@Composable
fun One2() {
    One(row = GridCells.Fixed(5), content = {
        items(items = list(), itemContent = { it ->
            Text(text = "${list()[it]}")
        }
        )
    })
}

//fun IntToFloat(int: Int): List<Dp> {
//    return listOf(
//        20.dp * int,
//        20.dp * int
//    )
//}

//fun SSS(): ArrayList<`class`> {
//    val arrayList = ArrayList<`class`>()
//    for (i in 0..3) {
//        arrayList.add(`class`(i, i * 20, 0))
//    }
//    return arrayList
//}

//
//@Preview(showBackground = true)
//@Composable
//fun One4() {
//    var offset by remember { mutableStateOf(Offset.Zero) }
//    val offsetList = remember {
//        mutableStateListOf<Int>(
//            0, 20, 40, 60
//        )
//    }
//
//    var state: Boolean by remember {
//        mutableStateOf(false)
//    }
//    Box(modifier = Modifier.width(200.dp)) {
//        SSS().forEach { i ->
//            Text(
//                modifier =
//                Modifier
//                    .offset {
//                        IntOffset(offsetList[i.data], 0)
//                    }
//                    .clickable {
//                        state = true
//                    }
//                    .pointerInput(Unit) {
//                        detectDragGestures(
//                            onDragStart = {
//                                if (state) {
//                                    offset = Offset(0f, 0f)
//                                }
//                            },
//                            onDrag = { _, d ->
//                                offsetList[i.data] += d.x.roundToInt()
//                                if (offsetList[i.data] == offsetList[i.data - 1]) {
//                                    offsetList[i.data - 1] += 20
//                                }
//                                if (offsetList[i.data] == offsetList[i.data + 1]) {
//                                    offsetList[i.data + 1] -= 20
//                                }
//                            }
//                        )
//                    },
//                text = "${i.data}"
//            )
//        }
//    }

//    var boxSize = 20.dp
//    Box(
//        modifier = Modifier.width(20.dp)
//    ) {
//        Box(Modifier
//            .size(boxSize)
//            .offset {
//                IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
//            }
//            .background(Color.Green)
//            .pointerInput(Unit) {
//                detectDragGestures(
//                    onDragStart = { offset ->
//                        // 拖动开始
//                    },
//                    onDragEnd = {
//                        // 拖动结束
//                    },
//                    onDragCancel = {
//                        // 拖动取消
//                    },
//                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
//                        // 拖动中
//                        offset += dragAmount
//                    }
//                )
//            }
//        )
//    }
//}


class Items(val id: Int, val data: Int) {

}

fun mylist(): List<Items> {
    return listOf(
        Items(0, 1),
        Items(1, 2),
        Items(2, 3),
        Items(3, 4),
        Items(4, 5),
        Items(5, 6),
        )
}

//fun count(int: Int): Map<Int, Int> {
//    var value: Int = int
//    var getOne: Int = 0 //行高
//    while (value >= 5) {
//        value -= 5 // 列数
//        getOne++
//    }
//    getOne++
//    return mapOf<Int, Int>(
//        Pair(getOne, value)
//    )
//}

fun getXnumber(int: Int):Int{
    return int%5
}
fun getYnumber(int:Int):Int{
    return (int / 5)
}
@Preview(showBackground = true)
@Composable
fun One5() {
    val list: Int = mylist().size
    val array = ArrayList<Offset>()

    for (i in 0 until list) {
        array.add(Offset(getXnumber(i) * 20f,getYnumber(i)* 20f))
    }
    val offset = remember {
        array.toMutableStateList()
    }

    Box(modifier = Modifier.size(300.dp, 300.dp), content = {
        mylist().forEach {
            println(offset[it.id])
            val id = it.id
            val x = offset[id].x
            val y = offset[id].y
            Text(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x.roundToInt(),
                            y.roundToInt()
                        )
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(onDrag = {
                                _: PointerInputChange,
                                dragAmount:
                                Offset
                            ->

//                            offset[it.id].OffsetX += dragAmount.x.roundToInt() // 不限制元素移动 控制其他元素移动
//                            offset[it.id].OffsetY += dragAmount.y.roundToInt()
                            offset[id] += dragAmount
//                            println("${offset[it.id].OffsetX}")
                            if (dragAmount.y < 20f && dragAmount.y > -20f) {
//                              没怎么拖动y轴 只是x方向的移动
                                if (x == offset[it.id - 1].x) {
                                    //不好模糊判断
                                    offset[id] += Offset(dragAmount.x, 0f)
                                }
                            } else {
//                                    拖动y轴了
//                                var a:Float = offset[it.id].x / 20 //最终拖动 1 - 5是第几个
                                for (i in it.id - getXnumber(it.id) - 5..it.id - getXnumber(it.id)) {
                                    offset[id] += Offset(dragAmount.x, 0f)
                                    if (i == it.id - getXnumber(it.id)) {
                                        offset[id] = Offset(0f, dragAmount.y)
                                    }
                                }
                            }
                        })
                    },

                text = "${it.data}"
            )
        }
    })
}
@Preview(showBackground = true)
@Composable
fun One6() {
    val list: Int = mylist().size
    val array = ArrayList<Int>()
    val array1 = ArrayList<Int>()

    for (i in 0 until list) {
        array.add(getXnumber(i) * 20)
        array1.add(getYnumber(i)* 20)
    }
    val offsetX = remember {
        array.toMutableStateList()
    }
    val offsetY = remember {
        array1.toMutableStateList()
    }
    offsetX.forEach {
        println("offsetX$it")
    }
    offsetY.forEach {
        println("offsetY$it")
    }
    Box(modifier = Modifier.size(300.dp, 300.dp), content = {
        mylist().forEach {
            val id = it.id

            Text(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            offsetX[id],
                            offsetY[id]
                        )
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(onDrag = {
                                _: PointerInputChange,
                                dragAmount:
                                Offset
                            ->

//                            offset[it.id].OffsetX += dragAmount.x.roundToInt() // 不限制元素移动 控制其他元素移动
//                            offset[it.id].OffsetY += dragAmount.y.roundToInt()
                            offsetX[id] += dragAmount.x.roundToInt()
                            offsetY[id] += dragAmount.y.roundToInt()

//                            println("${offset[it.id].OffsetX}")
                            if (dragAmount.y < 20f && dragAmount.y > -20f) {
//                              没怎么拖动y轴 只是x方向的移动
                                if (offsetX[id] == offsetX[it.id - 1]) {
                                    //不好模糊判断
                                    offsetX[it.id - 1] += 20
                                }
                            } else {
//                                    拖动y轴了
//                                var a:Float = offset[it.id].x / 20 //最终拖动 1 - 5是第几个
                                for (i in it.id - getXnumber(it.id) - 5 until it.id - getXnumber(it.id)) {
                                    if (i == it.id - getXnumber(it.id) - 1) {
                                        offsetX[i] = 0
                                        offsetY[i] = 20
                                    } else {
                                        offsetX[i] += 20
                                    }
                                }
                            }
                        })
                    },

                text = "${it.data}"
            )
        }
    })
}