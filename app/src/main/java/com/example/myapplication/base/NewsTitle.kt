package com.example.myapplication.base

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.myapplication.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NewsTitle(modifier: Modifier,viewModel: MainViewModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
//        val transition = rememberInfiniteTransition()
//        val offsetY by transition.animateFloat(
//            initialValue = 0f,
//            targetValue = 20f,
//            animationSpec = InfiniteRepeatableSpec(
//                animation = tween(2000),
//                repeatMode = RepeatMode.Restart
//            )
//        )
        val text by viewModel.text.collectAsState(initial = "")

        Column(modifier = Modifier.height(100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedContent(targetState = text
            , transitionSpec =  {
                    SlideInAndSlideOut()
                }) {
                tragetCount ->
                Text(text = "$tragetCount", textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview
@Composable
fun NewsTitlePreview() {
    NewsTitle(modifier = Modifier, viewModel = MainViewModel())
}
@OptIn(ExperimentalAnimationApi::class)
fun SlideInAndSlideOut(duration:Int=800):ContentTransform {
    return ContentTransform(
        targetContentEnter = slideInVertically(animationSpec = tween(durationMillis = duration)){fullHeight: Int -> fullHeight } + fadeIn(animationSpec = tween(durationMillis = duration)),
        initialContentExit = slideOutVertically(animationSpec = tween(durationMillis = duration)){fullHeight: Int -> -fullHeight } + fadeOut(animationSpec = tween(durationMillis = duration)),
        sizeTransform = SizeTransform(clip = true)
    )
}
