package com.example.myapplication.share

import android.content.Context
import android.content.Intent

fun share(context:Context){
    val intent = Intent()
    intent.apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, "SUBJECT")
        putExtra(Intent.EXTRA_SUBJECT, "URL")
    }
    context.startActivity(Intent.createChooser(intent,"share title"))
}