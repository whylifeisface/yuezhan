package com.example.myapplication

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.bean.DropDownMenuItem

@Composable
fun DropDown(
    modifier: Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    list: List<DropDownMenuItem>,
) {
    DropdownMenu(modifier = modifier, expanded = expanded, onDismissRequest = onDismissRequest) {
        list.forEach {
            DropdownMenuItem(text = { Text(text = it.text) }, onClick = it.onItemClick)
        }
    }
}

//    DropdownMenu(modifier = Modifier, expanded = state, onDismissRequest = {
//        state = false
//    }) {
//        DropdownMenuItem(enabled = false, onClick = {}) {
//            androidx.compose.material.Text(text = list[mutableIndex.toInt()].name)
//        }
//        DropdownMenuItem(onClick = { /*TODO*/ }) {
//            androidx.compose.material.Text(text = "隐藏")
//        }
//        DropdownMenuItem(onClick = {
//            navController.navigate("${ScreenRouter.DiaLog.Router}/{${list[mutableIndex.toInt()].name}}/{${list[mutableIndex.toInt()].web}}")
//            //  mutableIndex = ""
//        }) {
//            androidx.compose.material.Text(text = "编辑")
//        }
//        DropdownMenuItem(onClick = { /*TODO*/ }) {
//            androidx.compose.material.Text(text = "复制")
//        }
//        DropdownMenuItem(onClick = {
//            removeIndex = mutableIndex.toInt()
//            if (removeIndex < list.size) {
//                var int: Int = 1
//                for (i in removeIndex until list.size) {
//                    if (i != removeIndex) {
//                        offsetX[i] -= HOME_LONG_CLICK_ABEL_ITEM_WIDTH * int
//                        int++
//                        //                                offsetX[i] -= HOME_LONG_CLICK_ABEL_ITEM_WIDTH
//                    }
//                }
//            }
//
//            state = false
//        }) {
//            androidx.compose.material.Text(text = "删除")
//        }
//        DropdownMenuItem(onClick = {
//            DropDownMenuEnable = false
//        }) {
//            androidx.compose.material.Text(text = "调整顺序")
//        }
//    }