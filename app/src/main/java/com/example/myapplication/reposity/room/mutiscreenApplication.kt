package com.example.myapplication.reposity.room

import android.app.Application
import com.example.myapplication.bean.dataStore.Companion.getDataStore
import com.example.myapplication.reposity.DataBase.mutiscreenDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class mutiscreenApplication:Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { mutiscreenDataBase.getDataBase(this,applicationScope) }
    val repository by lazy { mutiscreenReposity(database.mutiscreendao()) }

    val dataStore by lazy { getDataStore(this) }
}