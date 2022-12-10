package com.example.myapplication.reposity.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.bean.mutiscreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [mutiscreen::class], version = 3)
abstract class mutiscreenDataBase:RoomDatabase() {
    abstract fun mutiscreendao():mutiscreenDao

    private class mutiscreenDataBaseCallBack(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val dao = database.mutiscreendao()

                    // Delete all content here.
                    dao.deleteAll()
                    val MultiScreen = mutiscreen(id = 0, isActive = true)
                    dao.insert(MultiScreen)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE:mutiscreenDataBase? = null
        fun getDataBase(context:Context,scope: CoroutineScope):mutiscreenDataBase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context,mutiscreenDataBase::class.java,"mutiscreen")
                    .fallbackToDestructiveMigration()
                    .addCallback(mutiscreenDataBaseCallBack(scope = scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}