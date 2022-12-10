package com.example.myapplication

import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Scaffold() {
    val state = rememberBottomSheetScaffoldState()
    val mainViewModel = MainViewModel()
    BottomSheetScaffold(
        sheetContent = {
            One2()
        },
        content = {
            One3({

            },mainViewModel)
            Button(onClick = { state.bottomSheetState.isExpanded }) {
                Text(text = "2")
            }
        }, scaffoldState = state
    )
//    BackdropScaffold(
//        appBar = {
//
//        },
//        backLayerContent = {
//            NavHost(
//                navController = rememberNavController(),
//                startDestination = "one",
//                modifier = Modifier,
//                builder = {
//                    composable(route = "one", content = {
//                        One1()
//                    }
//                    )
//                }
//            )
//        },
//        frontLayerContent = {
//            One2()
//        }
//    )
}
