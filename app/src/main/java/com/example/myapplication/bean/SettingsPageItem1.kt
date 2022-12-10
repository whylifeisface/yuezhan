package com.example.myapplication.bean


enum class SettingsPageItemEnum{
    DIALOG,
    SWITCH,
    LIN,
    NAVCONROLL,
    Snack
}
class SettingsPageItem1(
    val settingsPageItemEnum:SettingsPageItemEnum,
    val title:String,
    val summary:String,
    val defaultValue:Any,
    val settingsCardState: SettingsCardState,
    val SettingsCardClick:()->Unit
)
