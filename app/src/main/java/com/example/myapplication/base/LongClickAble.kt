package com.example.myapplication.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.bean.LongClickItem
import org.w3c.dom.Text

@Composable
fun LongClickAble(modifier: Modifier, item: LongClickItem) {
    Card(
        modifier = modifier
            .size(36.dp)
    ) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            if (item.imageVector != null) Image(
                modifier = Modifier.size(
                    22.dp
                ),
                imageVector = item.imageVector,
                contentDescription = ""
            )
            else Image(
                modifier = Modifier.size(
                    22.dp
                ),
                painter = painterResource(id = R.drawable.vector_drawable_network),
                contentDescription = ""
            )
            androidx.compose.material.Text(
                modifier = modifier,
                text = item.name,
                style = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp)
            )
        }
    }
}