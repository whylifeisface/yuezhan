package com.example.myapplication.web


import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.base.SearchTextFiledBase
import com.example.myapplication.bean.ScreenRouter
import com.example.myapplication.bean.dataStore
import com.example.myapplication.bean.mutiscreen
import com.example.myapplication.dataStore
import com.example.myapplication.mutiscreenViewModel
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import kotlinx.coroutines.launch

@Composable
fun Web(
    modifier: Modifier = Modifier,
    url: String,
    mutiscreenViewModel: mutiscreenViewModel,
    datastore:dataStore
) {
    val scope = rememberCoroutineScope()


    val webViewState = rememberWebViewState(url = url)
    val webClient = remember {
        object : AccompanistWebViewClient() {
            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                super.doUpdateVisitedHistory(view, url, isReload)
                if (!isReload) {
                    datastore.urlPut(if (url.isNullOrEmpty()) "主页" else url)
                    scope.launch {
                        mutiscreenViewModel.query().forEach {
                            if (it.isActive){
                                val mutiscreen = url?.let {
                                        it1 -> mutiscreenViewModel.update(mutiscreen = mutiscreen(it.id, it1,true))
                                }
                            }
                        }
                    }

                }
            }
        }
    }
    WebView(
        modifier = modifier,
        state = webViewState,
        client = webClient,
    )

}

@Composable
fun WebPreview(
    selectTabIndex: Int,
    navController: NavController,
    url: String?,
    mutiscreenViewModel: mutiscreenViewModel,
    datastore: dataStore
) {
    var value by remember {
        mutableStateOf(url?:" ")
    }
    val webUrl by remember {
        mutableStateOf("https://cn.bing.com/search?q=$value&form=QBLH&sp=-1&pq=&sc=0-0&qs=n&sk=&cvid=C9466555AB664EE7B6D90E25FDEBF0F9&ghsh=0&ghacc=0&ghpl=")
    }
    var selectTabIndexState by remember {
        mutableStateOf(selectTabIndex)
    }

    val listOf = listOf("bing", "阅搜")

        SearchTextFiledBase(navController = navController,value = value , onValueChange = {
            value = it
        }, ClearValueClick = {
            value = " "
        })

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            TabRow(
                selectedTabIndex = selectTabIndex,
                modifier = Modifier.width(60.dp),
                backgroundColor = Color.White,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .tabIndicatorOffset(it[selectTabIndex])
                            .wrapContentSize(Alignment.BottomCenter)
                            .height(1.dp), color = Color.Green
                    )
                },
                tabs = {
                    listOf.forEachIndexed { index: Int, s: String ->
                        Tab(
                            modifier = Modifier.padding(bottom = 3.dp),
                            selected = index == selectTabIndex,
                            onClick = { selectTabIndexState = index }) {
                            Text(text = s)
                            Spacer(modifier = Modifier.width(30.dp))
                        }
                    }
                }
            )
        }



    Web(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .offset(0.dp, 100.dp),
        webUrl,
        mutiscreenViewModel = mutiscreenViewModel,
        datastore
    )
//        Row(
//            modifier = Modifier.fillMaxWidth().height(40.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            listOf.forEach {
//                Text(text = it)
//            }
//        }
    //修改指示器长度
//    val indicator: @Composable (List<TabPosition>) -> Unit = { tabPositions: List<TabPosition> ->
//        val currentTabPosition = tabPositions[0]
//        val currentTabWidth by animateDpAsState(
//            targetValue = currentTabPosition.width / 4,
//            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
//        )
//        //让指示器offset居中
//        val indicatorOffset by animateDpAsState(
//            targetValue = currentTabPosition.left + (currentTabPosition.width / 2 - currentTabWidth / 2),
//            animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
//        )
//
//    }

}