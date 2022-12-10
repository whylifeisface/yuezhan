package com.example.myapplication.base

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_CHOOSER
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import kotlin.math.acos

@Composable
fun BottomSheetDropDown(
    BottomSheetDropDownExpanded: Boolean,
    BottomSheetDropDownonDismissRequest: () -> Unit,
    context: Context
) {

    DropdownMenu(
        expanded = BottomSheetDropDownExpanded,
        onDismissRequest = BottomSheetDropDownonDismissRequest
    ) {
        DropdownMenuItem(onClick = { /*TODO*/ }) {
            androidx.compose.material.Text(text = "分享到安卓包")
        }
        DropdownMenuItem(onClick = {


        }) {
            androidx.compose.material.Text(text = "分享到qq")

        }
        DropdownMenuItem(onClick = { /*TODO*/ }) {
            androidx.compose.material.Text(text = "分享到微信")
        }

    }
}

