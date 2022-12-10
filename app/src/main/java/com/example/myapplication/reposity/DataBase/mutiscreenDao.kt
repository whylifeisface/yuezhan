package com.example.myapplication.reposity.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.bean.mutiscreen
import kotlinx.coroutines.selects.select

@Dao
interface mutiscreenDao {
    @Query("select * from mutiscreen")
    suspend fun queryAll():List<mutiscreen>

    @Insert
    suspend  fun insertAll(vararg mutiscreen: mutiscreen)

    @Insert
    suspend  fun insert(mutiscreen: mutiscreen)

    @Query("DELETE FROM mutiscreen")
    suspend  fun deleteAll()

    @Query("DELETE FROM mutiscreen WHERE id = :i")
    suspend  fun deleteById(i:Int):Int

    @Delete
    suspend fun delete(mutiscreen: mutiscreen):Int

    @Update
    suspend fun update(mutiscreen: mutiscreen):Int

    @Query("SELECT COUNT(id) FROM mutiscreen")
    suspend fun length():Int

}