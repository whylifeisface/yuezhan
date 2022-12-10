package com.example.myapplication.ui.Page

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.bean.dataStore
import com.example.myapplication.model.BottomModelSheetInit
import com.example.myapplication.mutiscreenViewModel
import com.example.myapplication.web.WebPreview

//@Composable
//fun WebPage(navController: NavController,url:String?) {
//    Common(navController = navController, CommonContent = {
//        WebPreview(1, navController = navController,url)
//    })
//}

@Composable
fun WebPage1(
    navController: NavController,
    url: String?,
    context: Context,
    mutiscreenViewModel: mutiscreenViewModel,
    dataStore: dataStore
) {
    BottomModelSheetInit(
        navController = navController,
        context = context,
        mutiscreenViewModel = mutiscreenViewModel,
        content = {
            WebPreview(1, navController = navController,url,mutiscreenViewModel, datastore = dataStore)
        },
        dataStore = dataStore
    )
}