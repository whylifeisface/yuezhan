package com.example.myapplication.bean

sealed class ScreenRouter(val Router:String) {

    object Home:ScreenRouter("home")
    object SearchPage:ScreenRouter("searchPage")
    object WebViewPage:ScreenRouter("webViewPage")
    object Camera:ScreenRouter("camera")
    object DiaLog:ScreenRouter("dialog")
    object ScreenRotationDialog:ScreenRouter("screenRotationDialog")
    object SearchEngineDialog:ScreenRouter("SearchEngineDialog")
    object SettingsPage:ScreenRouter("SettingsPage")
    object UA:ScreenRouter("ua")
    object ClearDialog:ScreenRouter("clearDialog")
}