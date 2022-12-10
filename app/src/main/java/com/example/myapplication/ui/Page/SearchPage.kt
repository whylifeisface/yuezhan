package com.example.myapplication.ui.Page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.base.SearchHistory
import com.example.myapplication.base.SearchTextFiledBase
import com.example.myapplication.base.VideoBase1


@Composable
fun SearchPage(navController: NavController,url:String?) {

    var value by remember {
        mutableStateOf(" ")
    }
    LazyColumn {
        item {
            SearchTextFiledBase(navController, url, value = value, onValueChange = {
                value = it
            }, ClearValueClick = {
                value = ""
            })
            VideoBase1()
        }
        items(10) {
                SearchHistory(SearchHistoryOnclick = { /*TODO*/ }, text = "$it")
                Spacer(modifier = Modifier.size(10.dp))
        }

    }
}