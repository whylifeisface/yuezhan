package com.example.myapplication.bean

import com.example.myapplication.R
import com.example.myapplication.model.ModelBottomSheetEnum
import com.example.myapplication.model.exitApp

data class BottomSheetItem(
    val enabled: Boolean,
    val text: String,
    val painter: Int,
    val itemClick: () -> Unit,
)


fun bottomSheetItemInit(oneClickItem: ()->Unit): List<BottomSheetItem> {
    return listOf(
        BottomSheetItem(
            enabled = true, text = "多窗口", painter = R.drawable.vector_drawable_duochuangkou
        ) {
            oneClickItem.invoke()
        },
        BottomSheetItem(
            enabled = true, text = "书签", painter = R.drawable.vector_drawable_shuqian
        ) {},
        BottomSheetItem(
            enabled = true, text = "历史", painter = R.drawable.vector_drawable_lishijilu_o
        ) {},
        BottomSheetItem(
            enabled = true, text = "阅读模式", painter = R.drawable.vector_drawable_yuedumoshi
        ) {},
        BottomSheetItem(
            enabled = true, text = "刷新", painter = R.drawable.vector_drawable_shuaxin_
        ) {},
        BottomSheetItem(
            enabled = true, text = "添加书签", painter = R.drawable.vector_drawable_like
        ) {},
        BottomSheetItem(
            enabled = true, text = "显示隐藏", painter = R.drawable.vector_drawable_suoding
        ) {},
        BottomSheetItem(
            enabled = true, text = "隐身模式", painter = R.drawable.vector_drawable_duochuangkou
        ) {},
        BottomSheetItem(
            enabled = true, text = "举报", painter = R.drawable.vector_drawable_jubao
        ) {},
        BottomSheetItem(
            enabled = true, text = "复制网址", painter = R.drawable.vector_drawable_fuzhi
        ) {},
        BottomSheetItem(
            enabled = true, text = "无图模式", painter = R.drawable.vector_drawable_tupian
        ) {},
        BottomSheetItem(
            enabled = true, text = "意见反馈", painter = R.drawable.vector_drawable_yijianfankui
        ) {

        },
        BottomSheetItem(
            enabled = true, text = "分享", painter = R.drawable.vector_drawable_share
        ) {

        },
        BottomSheetItem(
            enabled = true, text = "设置", painter = R.drawable.vector_drawable_setting
        ) {},
        BottomSheetItem(
            enabled = true, text = "退出", painter = R.drawable.vector_drawable_tuichu
        ) {
            exitApp()
        }
    )
}

fun bottomSheetItemInit1(oneClickItem: ()->Unit, Settings:() -> Unit,shareClick:()->Unit): List<BottomSheetItem> {
    return listOf(
        BottomSheetItem(
            enabled = true, text = "多窗口", painter = R.drawable.vector_drawable_duochuangkou
        ) {
            oneClickItem.invoke()
        },
        BottomSheetItem(
            enabled = true, text = "书签", painter = R.drawable.vector_drawable_shuqian
        ) {},
        BottomSheetItem(
            enabled = true, text = "历史", painter = R.drawable.vector_drawable_lishijilu_o
        ) {},
        BottomSheetItem(
            enabled = true, text = "阅读模式", painter = R.drawable.vector_drawable_yuedumoshi
        ) {},
        BottomSheetItem(
            enabled = true, text = "刷新", painter = R.drawable.vector_drawable_shuaxin_
        ) {},
        BottomSheetItem(
            enabled = true, text = "添加书签", painter = R.drawable.vector_drawable_like
        ) {},
        BottomSheetItem(
            enabled = true, text = "显示隐藏", painter = R.drawable.vector_drawable_suoding
        ) {},
        BottomSheetItem(
            enabled = true, text = "隐身模式", painter = R.drawable.vector_drawable_duochuangkou
        ) {},
        BottomSheetItem(
            enabled = true, text = "举报", painter = R.drawable.vector_drawable_jubao
        ) {},
        BottomSheetItem(
            enabled = true, text = "复制网址", painter = R.drawable.vector_drawable_fuzhi
        ) {},
        BottomSheetItem(
            enabled = true, text = "无图模式", painter = R.drawable.vector_drawable_tupian
        ) {},
        BottomSheetItem(
            enabled = true, text = "意见反馈", painter = R.drawable.vector_drawable_yijianfankui
        ) {

        },
        BottomSheetItem(
            enabled = true, text = "分享", painter = R.drawable.vector_drawable_share
        ) {
            shareClick.invoke()
        },
        BottomSheetItem(
            enabled = true, text = "设置", painter = R.drawable.vector_drawable_setting
        ) { Settings.invoke() },
        BottomSheetItem(
            enabled = true, text = "退出", painter = R.drawable.vector_drawable_tuichu
        ) {
            exitApp()
        }
    )
}