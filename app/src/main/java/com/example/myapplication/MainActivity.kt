package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.base.Camera
import com.example.myapplication.bean.ScreenRouter
import com.example.myapplication.bean.dataStore
import com.example.myapplication.bean.dataStore.Companion.getDataStore
import com.example.myapplication.model.BottomModelSheetInit
import com.example.myapplication.reposity.room.mutiscreenApplication
import com.example.myapplication.ui.ClearDialog
import com.example.myapplication.ui.Page.*
import com.example.myapplication.ui.ScreenRotationDialog2
import com.example.myapplication.ui.SearchEngineDialog1
import com.example.myapplication.ui.UA
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.roundToInt

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {

    private val mutiscreenviewModel: mutiscreenViewModel by viewModels {
        WordViewModelFactory((application as mutiscreenApplication).repository)
    }

    private val dataStore: dataStore by lazy {
        (application as mutiscreenApplication).dataStore
    }

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DesNav(
                        viewModel = viewModel,
                        mutiscreenviewModel = mutiscreenviewModel,
                        dataStore = dataStore
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DragGestureDemo() {
    val boxSize = 100.dp
    var offset by remember { mutableStateOf(Offset.Zero) }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(Modifier
            .size(boxSize)
            .offset {
                IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
            }
            .background(Color.Green)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        // 拖动开始
                    },
                    onDragEnd = {
                        // 拖动结束
                    },
                    onDragCancel = {
                        // 拖动取消
                    },
                    onDrag = { _: PointerInputChange, dragAmount: Offset ->
                        // 拖动中
                        offset += dragAmount
                    }
                )
            }
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

@Composable
fun DesNav(
    viewModel: MainViewModel,
    mutiscreenviewModel: mutiscreenViewModel,
    dataStore: dataStore,
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = ScreenRouter.Home.Router) {
        composable(ScreenRouter.Home.Router) {
//            com.example.myapplication.ui.Page.HomePage2(
//                navController = navController,
//                viewModel = viewModel
//            )
            HomePage(
                navController = navController,
                context = context,
                mutiscreenViewModel = mutiscreenviewModel,
                viewModel = viewModel, dataStore = dataStore,
                scope = scope
            )
        }

        composable(
            route = "${ScreenRouter.SearchPage.Router}/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) {

            SearchPage(navController = navController, url = it.arguments?.getString("url"))

        }
        composable(ScreenRouter.ScreenRotationDialog.Router) {
//            ScreenRotationDialog1(scope = scope, context = context, onDismissRequest = {
//                navController.popBackStack()
//            }, OutlinedButtonClick = {
//                navController.popBackStack()
//            })
            ScreenRotationDialog2(
                scope = scope,
                navController = navController,
                dataStore = dataStore
            )
        }
        composable(ScreenRouter.SearchEngineDialog.Router) {
//            SearchEngineDialog(OutlinedButtonClick = {
//                navController.popBackStack()
//            }, context = context, scope = scope, onDismissRequest = {
//                navController.popBackStack()
//            })
            SearchEngineDialog1(scope = scope, navController = navController, dataStore = dataStore)
        }
        composable(ScreenRouter.SettingsPage.Router) {
            SettingsPage(navController = navController, dataStore = dataStore)
        }
        composable(
            "${ScreenRouter.WebViewPage.Router}/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) {

            WebPage1(
                navController = navController,
                url = it.arguments?.getString("url"),
                context = context,
                mutiscreenViewModel = mutiscreenviewModel,
                dataStore = dataStore
            )
        }


        composable(
            route = ScreenRouter.Camera.Router
        ) {

            Camera(navController = navController)

        }


        composable(
            route = "${ScreenRouter.DiaLog.Router}/{web}/{name}", arguments = listOf(
                navArgument("web") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType }
            )
        ) {

            My(
                web = it.arguments?.getString("web"),
                name = it.arguments?.getString("name"),
                navController
            )
        }
        composable(
            route = ScreenRouter.UA.Router
        ) {
            UA(dataStore = dataStore, navController = navController, scope = scope)
        }
        composable(
            route = ScreenRouter.ClearDialog.Router
        ) {
            ClearDialog(OutlinedButtonClick = {
                navController.popBackStack()

            }, onDismissRequest = {
                navController.popBackStack()
            }, ensureClick = {

            })
        }

    }
}