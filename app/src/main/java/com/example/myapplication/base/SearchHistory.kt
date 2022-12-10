package com.example.myapplication.base

import android.text.Layout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchHistory(SearchHistoryOnclick: () -> Unit, text: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            SearchHistoryOnclick.invoke()
        }, horizontalArrangement = Arrangement.SpaceBetween) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "")
        androidx.compose.material.Text(text = text)
        Icon(modifier = Modifier, imageVector = Icons.Default.Send, contentDescription = "")
    }
}