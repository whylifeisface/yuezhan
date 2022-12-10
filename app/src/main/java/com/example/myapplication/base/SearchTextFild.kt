package com.example.myapplication.base

import android.Manifest
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.bean.ScreenRouter
import com.google.accompanist.permissions.*
import kotlinx.coroutines.delay

@Composable
fun SearchTextFiled(text: String) {
    var value by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .background(Color.Gray.copy(0.6f))
            .padding(5.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(Color.White),
            value = value,
            onValueChange = {
                value = it
            },
            leadingIcon = {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray.copy(alpha = 0.6f))
                ) {
                    CompositionLocalProvider() {
                        Text(text = "全部")
                    }
                }
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(cursorColor = Color.Blue.copy(alpha = 0.6f))
        )
        TextButton(colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Gray.copy(
                alpha = 0.6f
            ), contentColor = Color.Black
        ), onClick = { /*TODO*/ }) {
            Text(text = text)
        }
    }
}

@Preview
@Composable
fun Sample() {
    SearchTextFiled("取消")
}

@Composable
fun SearchTextFiledBase(navController: NavController, url: String? = null,value:String,onValueChange:(String)->Unit,ClearValueClick:()->Unit) {
    val destination = navController.currentBackStackEntry?.destination
//    var value by remember {
//        if (url == null) {
//            mutableStateOf("")
//        } else {
//            mutableStateOf(url)
//        }
//    }
    val text = if (destination?.route == ScreenRouter.WebViewPage.Router) "取消" else "搜索"
    var cancelIconShow by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = value, block = {
        delay(500)
        cancelIconShow = value.isNotEmpty()
    })

    Row(
        modifier = Modifier
            .background(Color.Gray.copy(0.6f))
            .padding(5.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(Color.White),
            value = value,
            onValueChange = {
                            onValueChange.invoke(it)
            },
            leadingIcon = {
                if (destination?.route == ScreenRouter.WebViewPage.Router) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray.copy(alpha = 0.6f))
                    ) {
                        Text(text = "全部")
                    }
                } else {
                    Spacer(modifier = Modifier.size(0.dp))
                }

            },
            trailingIcon = {
                AnimatedVisibility(visible = cancelIconShow) {
                    Icon(modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            ClearValueClick.invoke()
                        })
                    }, imageVector = Icons.Default.Clear, contentDescription = "")
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(cursorColor = Color.Blue.copy(alpha = 0.6f))
        )
        TextButton(colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Gray.copy(
                alpha = 0.6f
            ), contentColor = Color.Black
        ), onClick = {

            navController.navigate(route = "${ScreenRouter.WebViewPage.Router}/ $value")

        }) {
            Text(text = text)
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeSearch(HomePageTap: () -> Unit, navController: NavController) {

    val permission = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            if (permission.status.isGranted) {
                println("already")

            } else {
                permission.launchPermissionRequest()
            }
        }

    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(key1 = permission, effect = {
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    })

    val permissionClick: () -> Unit = {
        if (permission.status.isGranted) {
            navController.navigate(ScreenRouter.Camera.Router)
        } else {
            permission.launchPermissionRequest()
            if (permission.status.isGranted) {
                navController.navigate(ScreenRouter.Camera.Router)
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        TextField(
            value = "", onValueChange = {
            },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp)
                .clip(RoundedCornerShape(20.dp)),
            placeholder = {
                Text(modifier = Modifier
                    .pointerInput(Unit) {
                    detectTapGestures {
                        HomePageTap.invoke()
                    }
                }, text = "输入名称搜索")
            },
            trailingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = permissionClick
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.vector_drawable_cam),
                            contentDescription = ""
                        )
                    }
                }
            }
        )
    }
}

@Preview
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PerMission() {
    val permission = rememberPermissionState(permission = Manifest.permission.CAMERA)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START || event == Lifecycle.Event.ON_ANY) {
                println("java")
                permission.launchPermissionRequest()
            }
        }
        DisposableEffect(key1 = lifecycle, effect = {
            lifecycle.addObserver(observer)
            onDispose {
                lifecycle.removeObserver(observer)
            }
        })
        if (permission.status.isGranted) {
            Text(text = "camera is needed")
        }
        Text(text = "java")
    }
}