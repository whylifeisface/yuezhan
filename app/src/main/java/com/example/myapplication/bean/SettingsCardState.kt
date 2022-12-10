package com.example.myapplication.bean

class SettingsCardState(
    var switchClicked:Boolean? = null,
    val switchOnCheckedChange:()-> Unit?,
    val linearProgressIndicatorProgress: Float? = null,

    ) {

}
