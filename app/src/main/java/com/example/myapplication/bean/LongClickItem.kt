package com.example.myapplication.bean

import androidx.compose.ui.graphics.vector.ImageVector
import org.w3c.dom.Text

data class LongClickItem(
    val imageVector: ImageVector?,
    val name: String,
    val itemClick: () -> Unit,
    val web: String = "",
)

data class LongClickItem1(
    val imageVector: ImageVector?,
    val name: String,
    val itemClick: () -> Unit,
    val web: String = "",
    val offsetX: Int,
    val offsetY: Int,
)

fun longClickItemInit(): List<LongClickItem> {
    return listOf(
        LongClickItem(null, "1", web = "", itemClick = {}),
        LongClickItem(null, "2", web = "", itemClick = {}),
        LongClickItem(null, "3", web = "", itemClick = {}),
        LongClickItem(null, "4", web = "", itemClick = {}),
        LongClickItem(null, "5", web = "", itemClick = {}),
        LongClickItem(null, "6", web = "", itemClick = {}),
        LongClickItem(null, "7", web = "", itemClick = {}),
        LongClickItem(null, "8", web = "", itemClick = {}),
        LongClickItem(null, "9", web = "", itemClick = {}),
        LongClickItem(null, "10", web = "", itemClick = {}),
        LongClickItem(null, "添加", web = "", itemClick = {}),

        )

}

const val HOME_LONG_CLICK_ABEL_ITEM_HEIGHT: Int = 80
const val HOME_LONG_CLICK_ABEL_ITEM_WIDTH: Int = 70
const val HOME_LONG_CLICK_ABEL_ITEM_PADDING: Int = 40
fun myIntOffsetX(index: Int): Int {

    return (index % 5) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH
}

fun myIntOffsetY(index: Int): Int {
    return (index / 5) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
}

fun longClickItem1Init(): List<LongClickItem1> {
    return listOf(
        LongClickItem1(
            null,
            "1",
            web = "",
            itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "2", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "3", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "4", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "5", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "6", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "7", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "8", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "9", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
        LongClickItem1(
            null, "10", web = "", itemClick = {},
            offsetX = myIntOffsetX(1) * HOME_LONG_CLICK_ABEL_ITEM_WIDTH,
            offsetY = myIntOffsetY(1) * HOME_LONG_CLICK_ABEL_ITEM_HEIGHT
        ),
    )

}